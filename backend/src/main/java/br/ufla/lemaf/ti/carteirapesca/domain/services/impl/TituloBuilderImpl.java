package br.ufla.lemaf.ti.carteirapesca.domain.services.impl;

import br.com.caelum.stella.boleto.Beneficiario;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Pagador;
import br.com.caelum.stella.boleto.bancos.Bradesco;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;
import br.com.caelum.stella.format.CNPJFormatter;
import br.ufla.lemaf.ti.carteirapesca.domain.enuns.EspecieDocumentoEnum;
import br.ufla.lemaf.ti.carteirapesca.domain.enuns.TipoArquivoEnum;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.Arquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.TipoArquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.*;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.TipoArquivoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.BeneficiarioTituloRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.EspecieDocumentoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.PagadorTituloRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.TituloRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.services.PagadorBuilder;
import br.ufla.lemaf.ti.carteirapesca.domain.services.TituloBuilder;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.ProtocoloFormatter;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.ProtocoloValidator;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
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
public class TituloBuilderImpl implements TituloBuilder {

	@Autowired
	private BeneficiarioTituloRepository beneficiarioTituloRepository;

	@Autowired
	private EspecieDocumentoRepository especieDocumentoRepository;

	@Autowired
	private TituloRepository tituloRepository;

	@Autowired
	private TipoArquivoRepository tipoArquivoRepository;

	@Autowired
	PagadorBuilderImpl pagadorBuilder;

	@Override
	public Titulo gerarDocumentoPagamento(final Protocolo protocolo,
							  final Modalidade modalidade,
							  final Pessoa pessoa) {

		try {

			Titulo titulo = gerarTituloBradesco(pessoa, modalidade);

			var geradorDeBoleto = new GeradorDeBoleto(montarBoleto(titulo));

			var formatterNovo = new ProtocoloFormatter("$1-$2/$3-$4", ProtocoloValidator.FORMATED_RENOVADO, "$1$2$3$4", ProtocoloValidator.UNFORMATED_RENOVADO);

			String codigoProtocolo;

			if(protocolo.getProtocoloNaoFormatado().length() != 9){
				codigoProtocolo = formatterNovo.unformat(protocolo.getCodigo());
			} else {
				codigoProtocolo = protocolo.getProtocoloNaoFormatado();
			}

			String nomeBoleto = codigoProtocolo + "-banco-bradesco.pdf";

			var caminhoBoleto = Paths.get(
				Properties.pathBoletoPagamentoCarteiraPesca()
					+ codigoProtocolo
					+ "/"
					+ nomeBoleto
			);

			var boleto = caminhoBoleto.toFile();

			if (!boleto.exists()) {
				Files.createDirectories(caminhoBoleto.getParent());
			}

			geradorDeBoleto.geraPDF(caminhoBoleto.toString());

			TipoArquivo tipoArquivo = tipoArquivoRepository.findByCodigo(TipoArquivoEnum.BOLETO.getCodigo());

			titulo.setArquivoBoleto(new Arquivo(caminhoBoleto.toString(), nomeBoleto, tipoArquivo));

			return titulo;

		} catch (IOException | NullPointerException e) {

			log.error(e.getMessage());

			throw new ResourceNotFoundException();

		}

	}

	private Titulo gerarTituloBradesco(Pessoa pessoa, Modalidade modalidade) {

		val bradesco = new Bradesco();

		BeneficiarioTitulo beneficiarioTitulo = beneficiarioTituloRepository.findByBancoCodigo(bradesco.getNumeroFormatado());

		EspecieDocumento especieDocumento = especieDocumentoRepository.findByCodigo(EspecieDocumentoEnum.DUPLICATA_MERCANTIL.getCodigo());

		PagadorTitulo pagadorTitulo = pagadorBuilder.transformarPessoaEmPagador(pessoa);

		Titulo titulo = new Titulo(beneficiarioTitulo, especieDocumento, pagadorTitulo, modalidade.getValor());

		titulo.setNossoNumero(tituloRepository.count());

		return titulo;

	}

	private Boleto montarBoleto(Titulo titulo) {

		val bradesco = new Bradesco();

		return Boleto.novoBoleto()
			.comBanco(bradesco)
			.comDatas(montarDatas(titulo))
			.comBeneficiario(montarBeneficiario(titulo))
			.comPagador(montarPagador(titulo))
			.comValorBoleto(titulo.getValor().setScale(2, BigDecimal.ROUND_HALF_EVEN))
			.comNumeroDoDocumento(titulo.getNossoNumero())
			.comEspecieDocumento(titulo.getEspecieDocumento().getCodigo())
			.comInstrucoes(titulo.getInstrucoes())
			.comLocaisDePagamento(titulo.getLocalPagamento());

	}

	private Datas montarDatas(Titulo titulo) {

		return Datas.novasDatas()
			.comDocumento(titulo.getDataEmissao().getDayOfMonth(), titulo.getDataEmissao().getMonthValue(), titulo.getDataEmissao().getYear())
			.comProcessamento(titulo.getDataProcessamento().getDayOfMonth(), titulo.getDataProcessamento().getMonthValue(), titulo.getDataProcessamento().getYear())
			.comVencimento(titulo.getDataVencimento().getDayOfMonth(), titulo.getDataVencimento().getMonthValue(), titulo.getDataVencimento().getYear());
	}

	private main.java.br.ufla.lemaf.beans.pessoa.Endereco endereco(Pessoa pessoa) {

		return pessoa.enderecos
			.stream()
			.filter(endereco ->
				StringUtils.isNotBlank(endereco.logradouro) && StringUtils.isNotBlank(endereco.cep))
			.findFirst()
			.orElseThrow(ResourceNotFoundException::new);
	}

	private Beneficiario montarBeneficiario(Titulo titulo) {

		BeneficiarioTitulo beneficiarioTitulo = titulo.getBeneficiario();

		Beneficiario beneficiario = Beneficiario.novoBeneficiario()
			.comNomeBeneficiario(beneficiarioTitulo.getBeneficiario().getNome())
			.comAgencia(beneficiarioTitulo.getAgencia())
			.comDigitoAgencia(beneficiarioTitulo.getDigitoAgencia())
			.comNumeroConvenio(beneficiarioTitulo.getConvenio())
			.comCarteira(beneficiarioTitulo.getCarteira())
			.comCodigoBeneficiario(beneficiarioTitulo.getCodigoBeneficiario())
			.comDigitoCodigoBeneficiario(beneficiarioTitulo.getDigitoCodigoBeneficiario())
			.comNossoNumero(titulo.getNossoNumero())
			.comDocumento(new CNPJFormatter().format(beneficiarioTitulo.getBeneficiario().getCpfCnpj()))
			.comEndereco(montarEndereco(beneficiarioTitulo.getBeneficiario().getEndereco()));

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

		return br.com.caelum.stella.boleto.Endereco.novoEndereco()
			.comLogradouro(endereco.getDescricaoEndereco())
			.comBairro(endereco.getBairro())
			.comCep(endereco.getCep())
			.comCidade(endereco.getMunicipio())
			.comUf(endereco.getEstado());

	}

}
