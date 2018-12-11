package br.ufla.lemaf.ti.carteirapesca.infrastructure.boletos;

import br.com.caelum.stella.boleto.*;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * **** Implementação relizada especificamente para o ambiente de apresentação. ****
 *
 * Gerador de boleto (Banco do brasil)
 *
 */
public class GeradorBoleto implements Serializable {

	private Pessoa pessoa;
	private String protocolo;

	public GeradorBoleto(Pessoa pessoa, String protocolo) {

		this.pessoa = pessoa;
		this.protocolo = protocolo.replace("-", "").replace("/", "");
	}

	/**
	 * Gera o boleto:
	 * Ref: https://github.com/caelum/caelum-stella/wiki/Gerando-boleto
	 * Retorna o caminho onde o PDF foi gravado
	 */
	public String gerarBoleto() {

		try {

			LocalDate date = LocalDate.now();

			// TODO - Montar estrutura para cálculo de datas a partir do processamento do banco
			int day = date.getDayOfMonth();
			int month = date.getMonthValue();
			int year = date.getYear();

			Datas datas = Datas.novasDatas()
				.comDocumento(day, month, year)
				.comProcessamento(day, month, year)
				.comVencimento(day, month, year);

			// TODO - Definir os dados de endereço para o boleto
			Endereco enderecoBeneficiario = Endereco.novoEndereco()
				.comLogradouro("Av das Empresas, 777")
				.comBairro("Bairro Centro")
				.comCep("37200-000")
				.comCidade("Lavras")
				.comUf("MG");

			// Quem emite o boleto
			Beneficiario beneficiario = Beneficiario.novoBeneficiario()
				.comNomeBeneficiario("Universidade Federal de Lavras")
				.comAgencia("1824").comDigitoAgencia("4")
				.comCodigoBeneficiario("76000")
				.comDigitoCodigoBeneficiario("5")
				.comNumeroConvenio("1207113")
				.comCarteira("18")
				.comEndereco(enderecoBeneficiario)
				.comNossoNumero(pessoa.cpf);

			main.java.br.ufla.lemaf.beans.pessoa.Endereco endereco = this.pessoa.enderecos
				.stream()
				.filter(e -> e.logradouro != null && e.numero != null)
				.findAny()
				.orElse(null);

			String logradouro = endereco.logradouro + ", Nº " + endereco.numero + " " + (endereco.complemento != null ? endereco.complemento : "");

			Endereco enderecoPagador = Endereco.novoEndereco()
				.comLogradouro(logradouro)
				.comBairro(endereco.bairro)
				.comCep(endereco.cep)
				.comCidade(endereco.municipio.nome)
				.comUf(endereco.municipio.estado.sigla);

			// Quem paga o boleto
			Pagador pagador = Pagador.novoPagador()
				.comNome(pessoa.nome)
				.comDocumento(pessoa.cpf)
				.comEndereco(enderecoPagador);

			Banco banco = new BancoDoBrasil();

			Boleto boleto = Boleto.novoBoleto()
				.comBanco(banco)
				.comDatas(datas)
				.comBeneficiario(beneficiario)
				.comPagador(pagador)
				// TODO - Definir como será obtido o valor do boleto
				.comValorBoleto("41.20")
				.comNumeroDoDocumento(this.protocolo)
				.comInstrucoes("Boleto gerado somente para testes", "Boleto gerado somente para testes", "Boleto gerado somente para testes", "Boleto gerado somente para testes", "Boleto gerado somente para testes")
				.comLocaisDePagamento("Boleto não pode ser pago", "Boleto não pode ser pago");

			GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);

			Path caminhoBoletoCarteiraPesca = Paths.get(Properties.pathBoletoPagamentoCarteiraPesca() + this.protocolo + "/" + protocolo + "-banco-brasil.pdf");
			File boletoCarteiraPesca = caminhoBoletoCarteiraPesca.toFile();

			if (!boletoCarteiraPesca.exists()) {
				Files.createDirectories(caminhoBoletoCarteiraPesca.getParent());
			}

			// Para gerar um boleto em PDF
			gerador.geraPDF(caminhoBoletoCarteiraPesca.toString());

			return caminhoBoletoCarteiraPesca.toString();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao tentar gerar o boleto para pagamento da carteira de pesca");
		}
	}


}
