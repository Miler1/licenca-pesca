package br.ufla.lemaf.ti.carteirapesca.application.impl;

import arrecadacao.dtos.ArquivoDocumentoArrecadacaoDTO;
import arrecadacao.dtos.DocumentoArrecadacaoDTO;
import arrecadacao.dtos.DocumentosArrecadacao;
import arrecadacao.dtos.RetornoArrecadacaoDTO;
import arrecadacao.enuns.CondicaoArrecadacaoEnum;
import arrecadacao.services.DocumentoArrecadacaoService;
import br.ufla.lemaf.ti.carteirapesca.application.RegistroApplication;
import br.ufla.lemaf.ti.carteirapesca.application.TaxaApplication;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Status;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.TaxaLicenca;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.StatusRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.TaxaLicencaRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.services.impl.EnderecoBuilderImpl;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.ArquivoUtils;
import main.java.br.ufla.lemaf.beans.pessoa.Endereco;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pessoa.dtos.BeneficiarioDTO;
import pessoa.dtos.EnderecoDTO;
import pessoa.dtos.PagadorDTO;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TaxaApplicationImpl implements TaxaApplication {

	private static final String EXTENSAO_DOCUMENTO_ARRECADACAO = ".pdf";

	@Autowired
	RegistroApplication registroApplication;

	@Autowired
	TaxaLicencaRepository taxaLicencaRepository;

	@Autowired
	EnderecoBuilderImpl enderecoBuilder;

	@Autowired
	StatusRepository statusRepository;

	@Override
	public TaxaLicenca geraDocumentoArrecadacao(Licenca licenca) {

		Pessoa pessoa = registroApplication.buscarDadosSolicitante(licenca.solicitante());

		DocumentoArrecadacaoDTO documentoArrecadacao = dadosDocumentoArrecadacao(licenca, pessoa);

		RetornoArrecadacaoDTO retorno = new DocumentoArrecadacaoService(Properties.gestaoPagamentosUrl(), Properties.gestaoPagamentosCodigoModulo())
			.cadastraDocumentoArrecadacao(documentoArrecadacao);

		TaxaLicenca taxa = new TaxaLicenca(licenca, retorno);

		return taxaLicencaRepository.save(taxa);

	}

	@Override
	public File downloadDocumentoArrecadacao(Licenca licenca) throws IOException {

		TaxaLicenca taxaLicenca = taxaLicencaRepository.findBylicencaAndVencido(licenca, false);

		if(taxaLicenca == null) {
			taxaLicenca = geraDocumentoArrecadacao(licenca);
		}

		ArquivoDocumentoArrecadacaoDTO arquivoDocumentoArrecadacao = new DocumentoArrecadacaoService(Properties.gestaoPagamentosUrl(), Properties.gestaoPagamentosCodigoModulo())
			.downloadDocumentoArrecadacao(taxaLicenca.getIdGestaoPagamentos());

		File documentoArrecadacao = new File(Properties.pathArquivoTemporario() + UUID.randomUUID() + EXTENSAO_DOCUMENTO_ARRECADACAO);

		ArquivoUtils.converteBase64ParaArquivo(arquivoDocumentoArrecadacao.documentoBase64, documentoArrecadacao);

		return documentoArrecadacao;
	}

	@Override
	public void buscaDocumentosArrecadacaoPagos() {

		DocumentosArrecadacao documentosArrecadacao = new DocumentoArrecadacaoService(Properties.gestaoPagamentosUrl(), Properties.gestaoPagamentosCodigoModulo())
			.listaUltimosDocumentosArrecadacaoPagos();

		Status statusAtivo = statusRepository.findByCodigo(Status.StatusEnum.ATIVO.codigo);

		documentosArrecadacao.content.forEach(d -> {

			TaxaLicenca taxaLicenca = taxaLicencaRepository.findByIdGestaoPagamentos(d.idDocumentoArrecadacao);

			if(taxaLicenca != null) {

				taxaLicenca.setPago(d.pago);
				taxaLicenca.setVencido(false);

				taxaLicenca.getLicenca().setDataAtivacao(new Date());
				taxaLicenca.getLicenca().setDataVencimento(LocalDate.now());
				taxaLicenca.getLicenca().setStatus(statusAtivo);

				taxaLicencaRepository.save(taxaLicenca);

			}


		});

	}

	@Override
	public void verificaValidadeTaxas() {

		List<TaxaLicenca> taxas = taxaLicencaRepository.findByDataVencimentoLessThanAndPagoAndVencido(LocalDate.now(), false, false);

		Status statusInvalidado = statusRepository.findByCodigo(Status.StatusEnum.INVALIDADO.codigo);

		taxas.forEach(t -> {

			RetornoArrecadacaoDTO retorno = new DocumentoArrecadacaoService(Properties.gestaoPagamentosUrl(), Properties.gestaoPagamentosCodigoModulo())
				.buscaDocumentoArrecadacaoPorId(t.getIdGestaoPagamentos());

			if(retorno != null
				&& (retorno.condicao.codigo.equals(CondicaoArrecadacaoEnum.VENCIDO.codigo)
					|| retorno.condicao.codigo.equals(CondicaoArrecadacaoEnum.VENCIDO_AGUARDANDO_PAGAMENTO.codigo))) {

				t.setVencido(true);
				t.getLicenca().setStatus(statusInvalidado);
				taxaLicencaRepository.save(t);

			}

		});

	}

	private DocumentoArrecadacaoDTO dadosDocumentoArrecadacao(Licenca licenca, Pessoa pessoa) {

		DocumentoArrecadacaoDTO documentoArrecadacao = new DocumentoArrecadacaoDTO();

		documentoArrecadacao.valor = licenca.getModalidade().getValor();
		documentoArrecadacao.dataVencimento =  Date.from(geraDataVencimento()
			.atStartOfDay(ZoneId.systemDefault())
			.toInstant());

		documentoArrecadacao.pagador = dadosPagador(pessoa);
		documentoArrecadacao.beneficiario = dadosBeneficiario();

		return documentoArrecadacao;

	}

	private LocalDate geraDataVencimento() {

		Long qtdDiasVencimentoAposEmissao = 3l;

		LocalDate vencimento = LocalDate.now().plusDays(qtdDiasVencimentoAposEmissao);

		if(vencimento.getDayOfWeek() == DayOfWeek.SATURDAY) {
			vencimento = vencimento.plusDays(2);
		} else if(vencimento.getDayOfWeek() == DayOfWeek.SATURDAY) {
			vencimento = vencimento.plusDays(1);
		}

		return vencimento;

	}

	private PagadorDTO dadosPagador(Pessoa pessoa) {

		PagadorDTO pagador = new PagadorDTO();

		pagador.cpfCnpj = (pessoa.cpf != null ? pessoa.cpf : pessoa.cnpj);
		pagador.passaporte = (pessoa.passaporte != null ? pessoa.passaporte.toString() : null);
		pagador.nome = pessoa.nome;

		pagador.endereco = dadosEndereco(pessoa);

		return pagador;

	}

	private EnderecoDTO dadosEndereco(Pessoa pessoa) {

		Endereco enderecoEU = enderecoBuilder.getEnderecoPessoa(pessoa);

		EnderecoDTO endereco = new EnderecoDTO();
		endereco.logradouro = enderecoEU.logradouro;
		endereco.numero = enderecoEU.numero.toString();
		endereco.bairro = enderecoEU.bairro;
		endereco.complemento = enderecoEU.complemento;
		endereco.cep = enderecoEU.cep;
		endereco.municipio = enderecoEU.municipio.nome;
		endereco.estado = enderecoEU.municipio.estado.sigla;

		return endereco;

	}

	private BeneficiarioDTO dadosBeneficiario() {

		BeneficiarioDTO beneficiario = new BeneficiarioDTO();
		beneficiario.codigo = Properties.gestaoPagamentosCodigoBeneficiario();

		return beneficiario;

	}

}
