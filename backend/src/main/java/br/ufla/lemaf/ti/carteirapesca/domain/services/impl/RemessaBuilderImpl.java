package br.ufla.lemaf.ti.carteirapesca.domain.services.impl;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.BeneficiarioTitulo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Titulo;
import br.ufla.lemaf.ti.carteirapesca.domain.services.Remessa240Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class RemessaBuilderImpl implements Remessa240Builder {

	private static final String PATH_TEMPLATE_REMESSA = "/home/hiagosouza/git/amazonas/carteira-de-pesca/backend/src/main/resources/templates/banco/remessa/bradesco_remessa_cnab400.txg.xml";

	@Override
	public String geraRemessa(List<Titulo> titulos) {

		File file = new File(PATH_TEMPLATE_REMESSA);
		FlatFile<Record> ff = Texgit.createFlatFile(file);

		for (Titulo titulo : titulos) {
			ff.addRecord(geraCabecalho(ff, titulo));
//			ff.addRecord(createDadosTitulo(ff, titulo));
//			ff.addRecord(createTrailer(ff, titulo));
		}

		String remessa = StringUtils.join(ff.write(), "\r\n");

		return remessa;

	}

	private Record geraCabecalho(FlatFile<Record> flatFile, Titulo titulo) {
		BeneficiarioTitulo beneficiario = titulo.getBeneficiario();
//		Conta conta = boleto.getContaReceber().getConta();

		Record header = flatFile.createRecord("Header");

		header.setValue("CodigoDaEmpresa", beneficiario.getConvenio());
		header.setValue("NomeDaEmpresa", beneficiario.getNome());

		LocalDate hoje = LocalDate.now();
		header.setValue("DataGravacaoArquivo", hoje.getDayOfMonth() + hoje.getMonthValue() + hoje.getYear());
//		header.setValue("Filler1", String.format("%7$"));
		header.setValue("NumeroSequencialRemessa", String.format("%1$7s", ""));
		header.setValue("sequencia", String.format("%1$6s", ""));

		return header;
	}

	private Record createDadosTitulo(FlatFile<Record> flatFile, Boleto boleto) throws SQLException, ClassNotFoundException {

		Endereco enderecoPadrao = boleto.getContaReceber().getCliente().getEnderecoPadrao();
		Conta dadosConta = boleto.getContaReceber().getConta();
		Emitente emitente = EmitenteDAO.getInstance().getEmitente();

//		Record dadosTitulo = flatFile.createRecord("DetalheDadosTitulo");
//
//		dadosTitulo.setValue("TipoInscricao", "2"); // 2- CNPJ; NE011
//		dadosTitulo.setValue("NumeroInscricao", emitente.getCpfCnpj()); //NE012
//		dadosTitulo.setValue("CodigoAgencia", dadosConta.getNumeroAgencia()); //NE003
//		dadosTitulo.setValue("CodigoBeneficiario", dadosConta.getNumeroConta()); //NE004
//		dadosTitulo.setValue("IdEmissao", "2"); // 2-CLIENTE EMITE; NE027
//		dadosTitulo.setValue("IdPostagem", "0"); // 0- POSTAGEM BENEFICIARIO; NE028
//		dadosTitulo.setValue("TaxaPermanencia", "00"); //NE013
//		dadosTitulo.setValue("UsoEmpresaBeneficiario1", boleto.getNumero()); //NUMERO BOLETO: NE014
//
//		// <Nosso Numero>
//		dadosTitulo.setValue("Modalidade", String.valueOf(boleto.getCarteira()) + "4"); //NE015
//		dadosTitulo.setValue("CampoLivre", Formata.formatarValores(boleto.getNossoNumero(), Formata.Padrao._15_DIG)); //NE015
//		// </Nosso Numero>
//
//		dadosTitulo.setValue("Brancos", "");  //PREENCHER COM ESPACOS
//		dadosTitulo.setValue("UsoLivreBancoEmpresa1", ""); //branco ou 1 = não autoriza agamento parcial //NE055
//		dadosTitulo.setValue("Mensagem", "Arquivo de teste");
////        dadosTitulo.setValue("Carteira", "01"); //NE016
//		dadosTitulo.setValue("Carteira", boleto.getCarteira()); //NE016
//		dadosTitulo.setValue("CodigoOcorrencia", "1"); //NE017
//		dadosTitulo.setValue("UsoEmpresaBeneficiario2", boleto.getNumero());// Numero do boleto //NE018
//		dadosTitulo.setValue("Vencimento", Formata.formatData(boleto.getContaReceber().getVencimento(), "ddMMyy")); //NE019
//		dadosTitulo.setValue("ValorTitulo", boleto.getContaReceber().getValor()); //NE020
//		dadosTitulo.setValue("CodigoBanco", CODIGO_BANCO); //NE006
//		dadosTitulo.setValue("AgenciaCobradora", "0"); //NE021
//		dadosTitulo.setValue("EspecieTitulo", "2"); //NE022
//		dadosTitulo.setValue("Aceite", boleto.getAceite()); //NE023
//		dadosTitulo.setValue("DataEmissaoTitulo", Formata.formatData(boleto.getContaReceber().getEmissao(), "ddMMyy")); //NE056
//		dadosTitulo.setValue("Instrucao1", "2"); // Devolver //NE024
//		dadosTitulo.setValue("Instrucao2", "0");
//		dadosTitulo.setValue("JurosMora", "");
//
//		//Em branco = sem desconto
//		dadosTitulo.setValue("DataDesconto", "");//NE057
//		dadosTitulo.setValue("ValorDesconto", "");
//		dadosTitulo.setValue("ValorIOF", "");
//		dadosTitulo.setValue("Abatimento", "");
//		dadosTitulo.setValue("TipoInscricao", ""); //NE011
//		dadosTitulo.setValue("NumeroInscricao", ""); //NE012
//
//		// Dados sacado
//		dadosTitulo.setValue("Nome", boleto.getContaReceber().getCliente().getNome()); //NE058
//		dadosTitulo.setValue("Endereco", enderecoPadrao.getEndereco());
//		dadosTitulo.setValue("Bairro", enderecoPadrao.getBairro().getNome());
//		dadosTitulo.setValue("CEP", enderecoPadrao.getCep());
//		dadosTitulo.setValue("Municipio", enderecoPadrao.getBairro().getMunicipio().getNome());
//		dadosTitulo.setValue("UF", enderecoPadrao.getBairro().getMunicipio().getEstado().getUf());
//
//		dadosTitulo.setValue("DataMulta", ""); //NE059
//		dadosTitulo.setValue("ValorMulta", "");
//
//		dadosTitulo.setValue("SacadorAvalista", "");
//
//		dadosTitulo.setValue("Instrucao3", "02"); //NE029
//		dadosTitulo.setValue("Prazo", ""); //NE025
//		dadosTitulo.setValue("CodigoMoeda", "1"); //NE026
//		dadosTitulo.setValue("NumeroSequencial", "000001"); //Numero do lote //Numero do lote

		return dadosTitulo;
	}

}
