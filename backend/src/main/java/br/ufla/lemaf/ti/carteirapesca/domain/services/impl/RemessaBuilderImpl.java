package br.ufla.lemaf.ti.carteirapesca.domain.services.impl;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.BeneficiarioTitulo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Endereco;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.PagadorTitulo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Titulo;
import br.ufla.lemaf.ti.carteirapesca.domain.services.Remessa240Builder;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.jni.Local;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class RemessaBuilderImpl implements Remessa240Builder {

	private static final String PATH_TEMPLATE_REMESSA = "/home/hiagosouza/git/amazonas/carteira-de-pesca/backend/src/main/resources/templates/banco/remessa/bradesco_remessa_cnab400.txg.xml";
	private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("ddMMYY");

	@Override
	public String geraRemessa(List<Titulo> titulos) {

		File file = new File(PATH_TEMPLATE_REMESSA);
		FlatFile<Record> ff = Texgit.createFlatFile(file);

		ff.addRecord(geraCabecalho(ff, titulos.get(0)));
		for (Titulo titulo : titulos) {
			ff.addRecord(geraInfoTitulos(ff, titulo));
		}
		ff.addRecord(geraTrailler(ff));

		String remessa = StringUtils.join(ff.write(), "\r\n");

		return remessa;

	}

	private void salvaArquivoRemessa(String remessa) {

		LocalDate hoje = LocalDate.now();

		//TODO Implementar solução de nome permanete para a remessa com as variáveis alfanumericas
		String nomeArquivoRemessa = "CB" + String.format("%02d", hoje.getDayOfMonth()) + String.format("%02d", hoje.getMonthValue() + "01.REM");

		File file = new File(Properties.pathArquivoRemessa() + "/" + nomeArquivoRemessa);

	}

	private Record geraCabecalho(FlatFile<Record> flatFile, Titulo titulo) {
		BeneficiarioTitulo beneficiario = titulo.getBeneficiario();

		Record header = flatFile.createRecord("Header");

		header.setValue("CodigoDaEmpresa", completaStringComZerosEsquerda(20, beneficiario.getConvenio()));
		header.setValue("NomeEmpresa", completaStringComEspacosEsquerda(30, beneficiario.getSigla()));
		header.setValue("DataGravacaoArquivo", LocalDate.now().format(FORMATO_DATA));
		header.setValue("EspacoBranco", completaStringComEspacosEsquerda(8, ""));
		header.setValue("NumeroSequencialRemessa", String.format("%1$7s", ""));
//		header.setValue("sequencia", String.format("%1$3d", ""));

		return header;
	}

	private Record geraInfoTitulos(FlatFile<Record> flatFile, Titulo titulo) {

		Record infoTitulo = flatFile.createRecord("TransacaoTitulo");

		BeneficiarioTitulo beneficiario = titulo.getBeneficiario();

		infoTitulo.setValue("OpcionalAgenciaDebito", completaStringComZerosEsquerda(5, ""));
		infoTitulo.setValue("OpcionalDigitoAgenciaDebito", completaStringComEspacosEsquerda(1, ""));
		infoTitulo.setValue("OpcionalRazaoContaCorrente", completaStringComZerosEsquerda(5, ""));
		infoTitulo.setValue("OpcionalContaCorrente", completaStringComZerosEsquerda(7, ""));
		infoTitulo.setValue("OpcionalDigitoContaCorrente", completaStringComZerosEsquerda(1, ""));

		infoTitulo.setValue("IdentificacaoEmpresaCarteira", completaStringComZerosEsquerda(4, beneficiario.getCarteira()));
		infoTitulo.setValue("IdentificacaoEmpresaAgenciaSemDigito", completaStringComZerosEsquerda(5, beneficiario.getAgencia()));
		infoTitulo.setValue("IdentificacaoEmpresaContaCorrente", completaStringComZerosEsquerda(7, beneficiario.getContaCorrente()));
		infoTitulo.setValue("IdentificacaoEmpresaDigitoContaCorrente", beneficiario.getDigitoContaCorrente());

		infoTitulo.setValue("NumeroControleParticipante", completaStringComZerosEsquerda(25, titulo.getId().toString()));
		infoTitulo.setValue("CodigoBanco", beneficiario.getBanco().getCodigo());

		/** Possui cobranca multa (0 - sem multa; 2 - Considerar cobranca)*/
		infoTitulo.setValue("PossuiCobrancaMulta", completaStringComZerosEsquerda(1, ""));
		infoTitulo.setValue("PercentualMulta", completaStringComZerosEsquerda(4, ""));

		/**Nosso número*/
		infoTitulo.setValue("IdentificacaoTituloBanco", completaStringComZerosEsquerda(11, titulo.getNossoNumero()));
		//TODO criar função para realizar o calculo do digito verificado nosso número
		infoTitulo.setValue("DigitoIdentificacaoTituloBanco", "0");

		infoTitulo.setValue("DescontoBonificacaoPorDia", completaStringComZerosEsquerda(10, ""));

		/**Condições emissão papeleta: (1 - Banco emite e processa o registro;
		 * 2 - Cliente emite e banco processa o registro) */
		infoTitulo.setValue("CondicaoEmissaoPapeletaCobranca", "2");

		/**Emite boleto para debito automático: (N - Não registra na cobrança;
		 * Diferente de N - registra e emite o boleto) */
		infoTitulo.setValue("BoletoParaDebitoAutomatico", "N");

		infoTitulo.setValue("IdentificacaoOperacaoBanco", completaStringComEspacosEsquerda(10, ""));

		/**Indicadores de Rateio de Crédito: (R - se empresa contratou rateio de credito;
		 * "espaco em branco" se não foi contratado)*/
		infoTitulo.setValue("OpcinalIdentificadoRateioCredito", completaStringComEspacosEsquerda(1, ""));

		/**Endereçamentos do Aviso de Débito Automático em Conta Corrente
		 * 1 - Emite aviso e assume endereço do pagador constante na remessa
		 * 2 - não emite aviso*/
		infoTitulo.setValue("OpcinalEnderecamentoAvisoDebitoAutomatico", "2");

		/**Quantidade de parcelas: (01 - qtd mínima; 99 - qtd máxima)*/
		infoTitulo.setValue("QtdPossivelPagamento", "01");
		infoTitulo.setValue("identificacaoOcorrencia", "01");
		infoTitulo.setValue("NumeroDocumento", titulo.getNossoNumero());

		infoTitulo.setValue("DataVencimentoTitulo", titulo.getDataVencimento().format(FORMATO_DATA));
		infoTitulo.setValue("ValorTitulo", completaStringComZerosEsquerda(13, formataValorPadraoRemessa(titulo.getValor())));
		infoTitulo.setValue("BancoEncarregadoCobranca", completaStringComZerosEsquerda(3, ""));
		infoTitulo.setValue("AgenciaDepositaria", completaStringComZerosEsquerda(5, ""));
		infoTitulo.setValue("EspecieTitulo", titulo.getEspecieDocumento().getCodigoRemessa());
		infoTitulo.setValue("Identificacao", "N");
		infoTitulo.setValue("DataEmissaoTitulo", titulo.getDataEmissao().format(FORMATO_DATA));
		infoTitulo.setValue("PrimeiraInstrucao", completaStringComZerosEsquerda(2, ""));

		/** Segunda instrução: 09 - Não receber após vencimento*/
		infoTitulo.setValue("SegundaInstrucao", "09");
		infoTitulo.setValue("ValorCobradoDiasAtraso", completaStringComZerosEsquerda(13, ""));
		infoTitulo.setValue("DataLimiteConcessaoDesconto", titulo.getDataVencimento().format(FORMATO_DATA));
		infoTitulo.setValue("ValorDeconto", completaStringComZerosEsquerda(13, ""));
		infoTitulo.setValue("ValorIOF", completaStringComZerosEsquerda(13, ""));
		infoTitulo.setValue("ValorAbatimentoASerConcedidoOuCancelado", completaStringComZerosEsquerda(13, ""));

		/** Identificação do tipo inscrição pagador: (01 - CPF; 02 - CNPJ)*/
		infoTitulo.setValue("IdentificacaoTipoInscricaoPagador", "01");

		//TODO verificar problema quando for pessoa estrangeira pois aceita apenas CPF ou CNPJ
		PagadorTitulo pagador = titulo.getPagador();
		infoTitulo.setValue("NumeroInscricaoPagador", completaStringComZerosEsquerda(14, pagador.getCpfPassaporte()));
		infoTitulo.setValue("NomePagador", completaStringComEspacosEsquerda(40, validaStringMaiorPermitidoCampo(40, pagador.getNome())));
		infoTitulo.setValue("EnderecoPagador", completaStringComEspacosEsquerda(40, getEnderecoCompleto(40, pagador.getEndereco())));
		infoTitulo.setValue("PrimeiraMensagem", completaStringComEspacosEsquerda(12, ""));

		String cep = pagador.getEndereco().getCep();
		infoTitulo.setValue("CepPagador", cep.substring(0, 5));
		infoTitulo.setValue("SufixoCepPagador", cep.substring(5, 8));
		infoTitulo.setValue("SegundaMensagem", completaStringComEspacosEsquerda(60, ""));
//		dadosTitulo.setValue("NumeroSequencialRegistro", completaStringComEspacosEsquerda(60, ""));

		return infoTitulo;

	}

	private Record geraTrailler(FlatFile<Record> flatFile) {

		Record trailler = flatFile.createRecord("Trailler");

		trailler.setValue("EspacoBranco", completaStringComEspacosEsquerda(393, ""));

		return trailler;
	}

	private String completaStringComZerosEsquerda(Integer tamanhoCampo, String valor) {
		return completaStringComEspacosEsquerda(tamanhoCampo, valor).replaceAll(" ", "0");
	}

	private String completaStringComEspacosEsquerda(Integer tamanhoCampo, String valor) {
		return String.format("%1$" + tamanhoCampo + "s", valor);
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
