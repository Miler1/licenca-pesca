package br.ufla.lemaf.ti.carteirapesca.domain.services.impl;

import br.ufla.lemaf.ti.carteirapesca.domain.enuns.TipoArquivoEnum;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.Arquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.TipoArquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.*;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.TipoArquivoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.RemessaRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.TituloRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.services.RemessaBuilder;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import lombok.extern.slf4j.Slf4j;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class RemessaBuilderImpl implements RemessaBuilder {

	@Autowired
	RemessaRepository remessaRepository;

	@Autowired
	TipoArquivoRepository tipoArquivoRepository;

	@Autowired
	TituloRepository tituloRepository;

	private static final String PATH_TEMPLATE_REMESSA = "templates/banco/remessa/bradesco_remessa_cnab400.txg.xml";
	private static final DateTimeFormatter FORMATO_DATA_REMESSA = DateTimeFormatter.ofPattern("ddMMYY");
	private static final DateTimeFormatter FORMATO_DATA_NOME_ARQUIVO_REMESSA = DateTimeFormatter.ofPattern("ddMM");

	@Override
	public Remessa geraRemessa() throws IOException {

		List<Titulo> titulos = tituloRepository.findAll();

		if(titulos.size() > 0) {

			Remessa novaRemessa = inicializaNovaRemessa();

			FlatFile<Record> ff = construirInformacoesRemessa(titulos, novaRemessa);

			Remessa remessa = geraArquivo(ff, novaRemessa);

			atualizaTitulos(titulos, remessa);

			return remessa;

		} else {
			return null;
		}

	}

	public Remessa inicializaNovaRemessa() {

		Remessa ultimaRemessaEnviada = remessaRepository.buscaUltimaRemessaGerada();

		if(ultimaRemessaEnviada == null) {
			return new Remessa(1);
		}

		Remessa ultimaRemessaEnviadaDia = remessaRepository.buscaUltimaRemessaGeradaNoDia();
		Remessa novaRemessa;

		if(ultimaRemessaEnviadaDia != null) {
			novaRemessa = new Remessa(ultimaRemessaEnviada.getSequencia() + 1, ultimaRemessaEnviadaDia.getSequencialNomeArquivo() + 1);
		} else {
			novaRemessa = new Remessa(ultimaRemessaEnviada.getSequencia() + 1, 1);
		}

		return novaRemessa;

	}

	public FlatFile<Record> construirInformacoesRemessa(List<Titulo> titulos, Remessa novaRemessa) throws IOException {

		ClassPathResource resource = new ClassPathResource(PATH_TEMPLATE_REMESSA);
		FlatFile<Record> ff = Texgit.createFlatFile(resource.getFile());

		ff.addRecord(geraCabecalho(ff, titulos.get(0), novaRemessa));

		Integer index = 2;
		for (Titulo titulo : titulos) {
			ff.addRecord(geraTramsacao(ff, titulo, index));
			index++;
		}

		ff.addRecord(geraTrailler(ff, index));

		return ff;

	}

	private void atualizaTitulos(List<Titulo> titulos, Remessa remessa) {

		titulos.forEach(t -> {
			t.setRemessa(remessa);
			tituloRepository.save(t);
		});

	}

	private Record geraCabecalho(FlatFile<Record> flatFile, Titulo titulo, Remessa remessa) {
		BeneficiarioTitulo beneficiario = titulo.getBeneficiario();

		Record header = flatFile.createRecord("Header");

		header.setValue("CodigoDaEmpresa", completaStringComZerosEsquerda(20, beneficiario.getConvenio()));
		header.setValue("NomeEmpresa", completaStringComEspaçosDireita(30, beneficiario.getSigla()));
		header.setValue("DataGravacaoArquivo", LocalDate.now().format(FORMATO_DATA_REMESSA));
		header.setValue("EspacoBranco", completaStringComEspacosEsquerda(8, ""));
		header.setValue("NumeroSequencialRemessa", remessa.getSequencia());
		header.setValue("sequencia", 1);

		return header;
	}

	private Record geraTramsacao(FlatFile<Record> flatFile, Titulo titulo, Integer index) {

		Record transacao = flatFile.createRecord("TransacaoTitulo");

		BeneficiarioTitulo beneficiario = titulo.getBeneficiario();

		transacao.setValue("OpcionalAgenciaDebito", completaStringComZerosEsquerda(5, ""));
		transacao.setValue("OpcionalDigitoAgenciaDebito", completaStringComZerosEsquerda(1, ""));
		transacao.setValue("OpcionalRazaoContaCorrente", completaStringComZerosEsquerda(5, ""));
		transacao.setValue("OpcionalContaCorrente", completaStringComZerosEsquerda(7, ""));
		transacao.setValue("OpcionalDigitoContaCorrente", completaStringComZerosEsquerda(1, ""));

		transacao.setValue("IdentificacaoEmpresaCarteira", completaStringComZerosEsquerda(4, beneficiario.getCarteira()));
		transacao.setValue("IdentificacaoEmpresaAgenciaSemDigito", completaStringComZerosEsquerda(5, beneficiario.getAgencia()));
		transacao.setValue("IdentificacaoEmpresaContaCorrente", completaStringComZerosEsquerda(7, beneficiario.getContaCorrente()));
		transacao.setValue("IdentificacaoEmpresaDigitoContaCorrente", beneficiario.getDigitoContaCorrente());

		transacao.setValue("NumeroControleParticipante", completaStringComZerosEsquerda(25, titulo.getId().toString()));
		transacao.setValue("CodigoBanco", beneficiario.getBanco().getCodigo());

		/** Possui cobranca multa (0 - sem multa; 2 - Considerar cobranca)*/
		transacao.setValue("PossuiCobrancaMulta", completaStringComZerosEsquerda(1, ""));
		transacao.setValue("PercentualMulta", completaStringComZerosEsquerda(4, ""));

		/**Nosso número*/
		String nossoNumero = completaStringComZerosEsquerda(11, titulo.getNossoNumero());
		transacao.setValue("IdentificacaoTituloBanco", nossoNumero);
		//TODO criar função para realizar o calculo do digito verificado nosso número
		transacao.setValue("DigitoIdentificacaoTituloBanco", calculaDigitoVerificadorNossoNumero(beneficiario.getCarteira(), nossoNumero));

		transacao.setValue("DescontoBonificacaoPorDia", completaStringComZerosEsquerda(10, ""));

		/**Condições emissão papeleta: (1 - Banco emite e processa o registro; 2 - Cliente emite e banco processa o registro) */
		transacao.setValue("CondicaoEmissaoPapeletaCobranca", "2");

		/**Emite boleto para debito automático: (N - Não registra na cobrança; Diferente de N - registra e emite o boleto) */
		transacao.setValue("BoletoParaDebitoAutomatico", "N");

		transacao.setValue("IdentificacaoOperacaoBanco", completaStringComEspacosEsquerda(10, ""));

		/**Indicadores de Rateio de Crédito: (R - se empresa contratou rateio de credito; "espaco em branco" se não foi contratado)*/
		transacao.setValue("OpcinalIdentificadoRateioCredito", completaStringComEspacosEsquerda(1, ""));

		/**Endereçamentos do Aviso de Débito Automático em Conta Corrente
		 * 1 - Emite aviso e assume endereço do pagador constante na remessa
		 * 2 - não emite aviso*/
		transacao.setValue("OpcinalEnderecamentoAvisoDebitoAutomatico", "2");

		/**Quantidade de parcelas: (01 - qtd mínima; 99 - qtd máxima)*/
		transacao.setValue("QtdPossivelPagamento", "01");
		transacao.setValue("identificacaoOcorrencia", "01");
		transacao.setValue("NumeroDocumento", titulo.getNossoNumero());

		transacao.setValue("DataVencimentoTitulo", titulo.getDataVencimento().format(FORMATO_DATA_REMESSA));
		transacao.setValue("ValorTitulo", completaStringComZerosEsquerda(13, formataValorPadraoRemessa(titulo.getValor())));
		transacao.setValue("BancoEncarregadoCobranca", completaStringComZerosEsquerda(3, ""));
		transacao.setValue("AgenciaDepositaria", completaStringComZerosEsquerda(5, ""));
		transacao.setValue("EspecieTitulo", titulo.getEspecieDocumento().getCodigoRemessa());
		transacao.setValue("Identificacao", "N");
		transacao.setValue("DataEmissaoTitulo", titulo.getDataEmissao().format(FORMATO_DATA_REMESSA));
		transacao.setValue("PrimeiraInstrucao", completaStringComZerosEsquerda(2, ""));

		/** Segunda instrução: 09 - Não receber após vencimento*/
		transacao.setValue("SegundaInstrucao", "09");
		transacao.setValue("ValorCobradoDiasAtraso", completaStringComZerosEsquerda(13, ""));
		transacao.setValue("DataLimiteConcessaoDesconto", titulo.getDataVencimento().format(FORMATO_DATA_REMESSA));
		transacao.setValue("ValorDesconto", completaStringComZerosEsquerda(13, "1"));
		transacao.setValue("ValorIOF", completaStringComZerosEsquerda(13, ""));
		transacao.setValue("ValorAbatimentoASerConcedidoOuCancelado", completaStringComZerosEsquerda(13, ""));

		/** Identificação do tipo inscrição pagador: (01 - CPF; 02 - CNPJ)*/
		transacao.setValue("IdentificacaoTipoInscricaoPagador", "01");

		//TODO verificar problema quando for pessoa estrangeira pois aceita apenas CPF ou CNPJ
		PagadorTitulo pagador = titulo.getPagador();
		transacao.setValue("NumeroInscricaoPagador", completaStringComZerosEsquerda(14, pagador.getCpfPassaporte()));
		transacao.setValue("NomePagador", completaStringComEspaçosDireita(40, validaStringMaiorPermitidoCampo(40, pagador.getNome())));
		transacao.setValue("EnderecoPagador", completaStringComEspaçosDireita(40, getEnderecoCompleto(40, pagador.getEndereco())));
		transacao.setValue("PrimeiraMensagem", completaStringComEspacosEsquerda(12, ""));

		String cep = pagador.getEndereco().getCep();
		transacao.setValue("CepPagador", cep.replaceAll("-", "").substring(0, 5));
		transacao.setValue("SufixoCepPagador", cep.replaceAll("-", "").substring(5, 8));
		transacao.setValue("SegundaMensagem", completaStringComEspaçosDireita(60, "PAGAVEL EM QUALQUER AGENCIA ATE O VENCIMENTO"));
		transacao.setValue("sequencia", index);

		return transacao;

	}

	private Record geraTrailler(FlatFile<Record> flatFile, Integer index) {

		Record trailler = flatFile.createRecord("Trailler");

		trailler.setValue("EspacoBranco", completaStringComEspacosEsquerda(393, ""));
		trailler.setValue("sequencia", index);

		return trailler;
	}

	/**Para o cálculo do dígito, será necessário acrescentar o número da carteira à esquerda
	 antes do Nosso Número, e aplicar o módulo 11, com base 7 */
	private String calculaDigitoVerificadorNossoNumero(String carteira, String nossoNumero) {

		String carteiraNossoNumero = carteira + nossoNumero;
		Integer operador = 2;
		Integer numeroPosicao, resultadoMultiplicacao, resultadoSoma = 0;
		Integer indexInicio, indexFinal;

		for(int i = 0; i < carteiraNossoNumero.length(); i++) {

			if(operador > 7) {
				operador = 2;
			}

			indexInicio = (carteiraNossoNumero.length() - 1 - i);
			indexFinal = carteiraNossoNumero.length() - i;

			numeroPosicao = Integer.valueOf(carteiraNossoNumero.substring(indexInicio, indexFinal));

			resultadoMultiplicacao = numeroPosicao * operador;

			resultadoSoma = resultadoSoma + resultadoMultiplicacao;

			operador++;

		}

		Integer restoDivisao = resultadoSoma % 11;

		if(restoDivisao == 0) {
			return "0";
		} else if(restoDivisao == 1) {
			return "P";
		} else {
			return String.valueOf(11 - restoDivisao);
		}

	}

	private Remessa geraArquivo(FlatFile<Record> ff, Remessa remessa) throws IOException {

		//TODO Implementar solução para o nome do arquivo de remessa tenha caracteres alfanuméricos nos últimos 2 digítos
		String nomeArquivoRemessa = "CB" + LocalDate.now().format(FORMATO_DATA_NOME_ARQUIVO_REMESSA) +
			completaStringComZerosEsquerda(2, remessa.getSequencialNomeArquivo().toString())  + ".REM";

		String diretorioDiaGeracaoRemessa = LocalDate.now().format(FORMATO_DATA_REMESSA);

		Path pathRemessa = Paths.get(Properties.pathArquivoRemessa() + "/" + diretorioDiaGeracaoRemessa + "/" + nomeArquivoRemessa);
		File arquivoRemessa = pathRemessa.toFile();

		if(!arquivoRemessa.exists()) {
			Files.createDirectories(pathRemessa.getParent());
		}

		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivoRemessa), "UTF8"));

		for(String linhaArquivo : ff.write()) {
			out.append(linhaArquivo).append("\r\n");
		}

		out.flush();
		out.close();

		TipoArquivo tipoArquivo = tipoArquivoRepository.findByCodigo(TipoArquivoEnum.REMESSA.getCodigo());
		remessa.setArquivo(new Arquivo(pathRemessa.toString(), nomeArquivoRemessa, tipoArquivo));

		return remessaRepository.save(remessa);

	}

	private String completaStringComZerosEsquerda(Integer tamanhoCampo, String valor) {
		return completaStringComEspacosEsquerda(tamanhoCampo, valor).replaceAll(" ", "0");
	}

	private String completaStringComEspacosEsquerda(Integer tamanhoCampo, String valor) {
		return String.format("%1$" + tamanhoCampo + "s", valor);
	}

	private String completaStringComEspaçosDireita(Integer tamanhoCampo, String valor) {

		valor = removeCaracteresEspeciais(valor.toUpperCase());

		Integer tamanhoString = valor.length();

		if(tamanhoCampo == tamanhoString) {
			return valor;
		} else if(tamanhoCampo > tamanhoString) {
			return valor + String.format("%1$" + (tamanhoCampo - tamanhoString) + "s", "");
		} else {
			return valor.substring(0, tamanhoCampo - 1);
		}

	}

	private String removeCaracteresEspeciais(String valor) {

		return Normalizer.normalize(valor, Normalizer.Form.NFD)
			.replaceAll("[^\\p{ASCII}]", "")
			.replaceAll("[&\\/\\\\#,ºª+()$~%.'\":*?<>{}]", "");

	}

	private String formataValorPadraoRemessa(BigDecimal valor) {

		return valor.setScale(2, BigDecimal.ROUND_HALF_EVEN)
			.toString()
			.replace(".", "");

	}

	private String getEnderecoCompleto(Integer tamanhoCampo, Endereco endereco) {

		String descricaoEndereco = endereco.getLogradouro() +
			(endereco.getNumero() == null ? "" : " Nº " + endereco.getNumero()) +
			(endereco.getComplemento() == null ? "" : ", " + endereco.getComplemento());

		return validaStringMaiorPermitidoCampo(tamanhoCampo, descricaoEndereco);

	}

	private String validaStringMaiorPermitidoCampo(Integer tamanhoCampo, String valor) {

		if(valor.length() > tamanhoCampo) {
			return valor.substring(0, tamanhoCampo - 1).toUpperCase();
		}

		return valor.toUpperCase();

	}

}
