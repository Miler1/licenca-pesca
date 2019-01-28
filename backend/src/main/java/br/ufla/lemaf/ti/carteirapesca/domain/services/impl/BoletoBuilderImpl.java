package br.ufla.lemaf.ti.carteirapesca.domain.services.impl;

import br.com.caelum.stella.boleto.*;
import br.com.caelum.stella.boleto.Beneficiario;
import br.com.caelum.stella.boleto.bancos.Bradesco;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;
import br.com.caelum.stella.format.CNPJFormatter;
import br.ufla.lemaf.ti.carteirapesca.domain.enuns.banco.EspecieDocumentoEnum;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.*;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Endereco;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.*;
import br.ufla.lemaf.ti.carteirapesca.domain.services.BoletoBuilder;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Buider do Boleto.
 * <p>
 * Cria o pdf do boleto para carteira de pesca.
 * Referência: https://github.com/caelum/caelum-stella/wiki/Gerando-boleto
 *
 * @author Highlander Paiva
 * @author Marcio Azevedo
 * @since 1.0
 */
@Slf4j
@Service
public class BoletoBuilderImpl implements BoletoBuilder {

	@Autowired
	private BeneficiarioRepository beneficiarioRepository;

	@Autowired
	private EspecieDocumentoRepository especieDocumentoRepository;

	@Autowired
	private PagadorTituloRepository pagadorTituloRepository;

	@Autowired
	private TituloRepository tituloRepository;

	/**
	 * Cria o boleto da carteira de pesca.
	 *
	 * @param protocolo  O Protocolo
	 * @param modalidade A modalidade
	 * @param pessoa     A PEssoa solicitante
	 * @return O caminho do arquivo do boleto
	 */
	@Override
	public String gerarBoleto(final Protocolo protocolo,
	                          final Modalidade modalidade,
	                          final Pessoa pessoa) {

		try {

			var geradorDeBoleto = new GeradorDeBoleto(
				montarBoleto(pessoa, protocolo, modalidade)
			);

			var caminhoBoleto = Paths.get(
				Properties.pathBoletoPagamentoCarteiraPesca()
					+ protocolo.getProtocoloNaoFormatado()
					+ "/"
					+ protocolo.getProtocoloNaoFormatado()
					+ "-banco-bradesco.pdf"
			);

			var boleto = caminhoBoleto.toFile();

			if (!boleto.exists())
				Files.createDirectories(caminhoBoleto.getParent());

			geradorDeBoleto.geraPDF(caminhoBoleto.toString());

			return caminhoBoleto.toString();

		} catch (IOException | NullPointerException e) {

			log.error(e.getMessage());

			throw new ResourceNotFoundException();

		}

	}

	private Titulo gerarTituloBradesco(Pessoa pessoa) {

		val bradesco = new Bradesco();

		BeneficiarioTitulo beneficiarioTitulo = beneficiarioRepository.findByBancoCodigo(bradesco.getNumeroFormatado());

		EspecieDocumento especieDocumento = especieDocumentoRepository.findByCodigo(EspecieDocumentoEnum.OUTROS.getCodigo());

		PagadorTitulo pagadorTitulo = getPagadorTitulo(pessoa);

		Titulo titulo = new Titulo(beneficiarioTitulo, especieDocumento, pagadorTitulo);

		tituloRepository.save(titulo);

		return titulo;

	}

	private PagadorTitulo getPagadorTitulo(Pessoa pessoa) {

		String cpfPassaporte = (pessoa.cpf == null ? pessoa.passaporte.toString() : pessoa.cpf);

		PagadorTitulo pagadorTitulo = pagadorTituloRepository.findByCpfPassaporte(cpfPassaporte);

		if(pagadorTitulo == null) {
			pagadorTitulo = new PagadorTitulo(pessoa.nome, cpfPassaporte);

			main.java.br.ufla.lemaf.beans.pessoa.Endereco endereco = endereco(pessoa);

			Endereco enderecoPagador = new Endereco(endereco.logradouro,
				endereco.numero.toString(),
				endereco.complemento,
				endereco.bairro,
				endereco.cep,
				endereco.municipio.nome,
				endereco.municipio.estado.sigla);

			pagadorTitulo.setEndereco(enderecoPagador);

			pagadorTituloRepository.save(pagadorTitulo);

		}

		return pagadorTitulo;

	}

	/**
	 * Monta os campos do boleto.
	 *
	 * @param pessoa A pessoa solicitante
	 * @param protocolo O protocolo da licença
	 * @param modalidade A modalidade da licença
	 * @return O boleto
	 */
	private Boleto montarBoleto(final Pessoa pessoa,
							   	final Protocolo protocolo,
							   	final Modalidade modalidade) {
		val bradesco = new Bradesco();

		Titulo titulo = gerarTituloBradesco(pessoa);

		return Boleto.novoBoleto()
			.comBanco(bradesco)
			.comDatas(montarDatas(titulo))
			.comBeneficiario(montarBeneficiario(titulo))
			.comPagador(montarPagador(titulo))
			.comValorBoleto(montarValorBoleto(modalidade))
			.comNumeroDoDocumento(protocolo.getCodigoFormatado())
			.comEspecieDocumento(titulo.getEspecieDocumento().getCodigo())
			.comInstrucoes(titulo.getInstrucoes())
			.comLocaisDePagamento(titulo.getLocalPagamento());

	}

	/**
	 * Monta os dados de data do boleto.
	 *
	 * @return Objeto com as datas para o boleto
	 */
	private Datas montarDatas(Titulo titulo) {

		return Datas.novasDatas()
			.comDocumento(titulo.getDataCriacao().getDayOfMonth(), titulo.getDataCriacao().getMonthValue(), titulo.getDataCriacao().getYear())
			.comProcessamento(titulo.getDataProcessamento().getDayOfMonth(), titulo.getDataProcessamento().getMonthValue(), titulo.getDataProcessamento().getYear())
			.comVencimento(titulo.getDataVencimento().getDayOfMonth(), titulo.getDataVencimento().getMonthValue(), titulo.getDataVencimento().getYear());
	}

	/**
	 * Constroi o campo valor carteira.
	 * <p>
	 *
	 * @param modalidade A modalidade da carteira
	 * @return O campo de valor da carteira
	 */
	private String montarValorBoleto(Modalidade modalidade) {
		switch (modalidade) {
			case ESPORTIVA:
				return "41.21";
			case RECREATIVA:
				return "57.21";
			default:
				return "";
		}

	}

	/**
	 * Busca o endereço do solicitante
	 * da carteira.
	 *
	 * @param pessoa A Pessoa solicitante
	 * @return O endereço
	 */
	private main.java.br.ufla.lemaf.beans.pessoa.Endereco endereco(Pessoa pessoa) {
		return pessoa.enderecos
			.stream()
			.filter(endereco ->
				StringUtils.isNotBlank(endereco.logradouro) && StringUtils.isNotBlank(endereco.cep))
			.findFirst()
			.orElseThrow(ResourceNotFoundException::new);
	}

	/**
	 * Monta o beneficiario.
	 * <p>
	 * Dado fantasia por falta de definições.
	 *
	 * @return O BeneficiarioTitulo
	 * @implNote Reimplementar com dados reais
	 */
	private Beneficiario montarBeneficiario(Titulo titulo) {

		BeneficiarioTitulo beneficiarioTitulo = titulo.getBeneficiario();

		Beneficiario beneficiario = Beneficiario.novoBeneficiario()
			.comNomeBeneficiario(beneficiarioTitulo.getNome())
			.comAgencia(beneficiarioTitulo.getAgencia())
			.comDigitoAgencia(beneficiarioTitulo.getDigitoAgencia())
			.comNumeroConvenio(beneficiarioTitulo.getConvenio())
			.comCarteira(beneficiarioTitulo.getCarteira())
			.comCodigoBeneficiario(beneficiarioTitulo.getCodigoBeneficiario())
			.comDigitoCodigoBeneficiario(beneficiarioTitulo.getDigitoCodigoBeneficiario())
			.comNossoNumero(beneficiarioTitulo.getNossoNumero())
			.comDocumento(new CNPJFormatter().format(beneficiarioTitulo.getCpfCnpj()))
			.comDigitoNossoNumero(beneficiarioTitulo.getDigitoNossoNumero())
			.comEndereco(montarEndereco(beneficiarioTitulo.getEndereco()));

		return beneficiario;

	}

	private Pagador montarPagador(Titulo titulo) {

		PagadorTitulo pagador = titulo.getPagador();

		return Pagador.novoPagador()
			.comNome(pagador.getNome())
			.comDocumento(pagador.getCpfPassaporte())
			.comEndereco(montarEndereco(pagador.getEndereco()));
	}

	private br.com.caelum.stella.boleto.Endereco montarEndereco(Endereco endereco) {

		String descricaoEndereco = endereco.getLogradouro() +
			(endereco.getNumero() == null ? "" : " Nº " + endereco.getNumero()) +
			(endereco.getComplemento() == null ? "" : ", " + endereco.getComplemento());

		return br.com.caelum.stella.boleto.Endereco.novoEndereco()
			.comLogradouro(descricaoEndereco)
			.comBairro(endereco.getBairro())
			.comCep(endereco.getCep())
			.comCidade(endereco.getMunicipio())
			.comUf(endereco.getEstado());

	}
}
