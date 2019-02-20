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
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.BeneficiarioRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.EspecieDocumentoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.PagadorTituloRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.TituloRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.services.BoletoBuilder;
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
public class BoletoBuilderImpl implements BoletoBuilder {

	@Autowired
	private BeneficiarioRepository beneficiarioRepository;

	@Autowired
	private EspecieDocumentoRepository especieDocumentoRepository;

	@Autowired
	private PagadorTituloRepository pagadorTituloRepository;

	@Autowired
	private TituloRepository tituloRepository;

	@Autowired
	private TipoArquivoRepository tipoArquivoRepository;

	@Override
	public Titulo gerarBoleto(final Protocolo protocolo,
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

		BeneficiarioTitulo beneficiarioTitulo = beneficiarioRepository.findByBancoCodigo(bradesco.getNumeroFormatado());

		EspecieDocumento especieDocumento = especieDocumentoRepository.findByCodigo(EspecieDocumentoEnum.DUPLICATA_MERCANTIL.getCodigo());

		PagadorTitulo pagadorTitulo = getPagadorTitulo(pessoa);

		Titulo titulo = new Titulo(beneficiarioTitulo, especieDocumento, pagadorTitulo, getValorTitulo(modalidade));

		titulo.setNossoNumero(tituloRepository.count());

		return titulo;

	}

	private PagadorTitulo getPagadorTitulo(Pessoa pessoa) {

		String cpfPassaporte = (pessoa.cpf == null ? pessoa.passaporte.toString() : pessoa.cpf);

		PagadorTitulo pagadorTitulo = pagadorTituloRepository.findByCpfPassaporte(cpfPassaporte);

		if(pagadorTitulo == null) {
			pagadorTitulo = new PagadorTitulo(pessoa.nome, cpfPassaporte);

			main.java.br.ufla.lemaf.beans.pessoa.Endereco endereco = endereco(pessoa);

			Endereco enderecoPagador = new Endereco(endereco.logradouro,
				(endereco.numero == null ? null : endereco.numero.toString()),
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

	private BigDecimal getValorTitulo(Modalidade modalidade) {

		if(modalidade.getId().equals(Modalidade.Modalidades.PESCA_ESPORTIVA.id)) {

			return new BigDecimal(41.21);
		} else if(modalidade.getId().equals(Modalidade.Modalidades.PESCA_REACREATIVA.id)) {

			return new BigDecimal(57.21);
		} else {
			return new BigDecimal(0);
		}
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
