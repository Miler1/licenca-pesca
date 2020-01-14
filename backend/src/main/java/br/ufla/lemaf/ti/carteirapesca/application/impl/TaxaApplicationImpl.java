package br.ufla.lemaf.ti.carteirapesca.application.impl;

import arrecadacao.dtos.ArquivoDocumentoArrecadacaoDTO;
import arrecadacao.dtos.CustomizacaoDocCarteiraPesca;
import arrecadacao.dtos.DocumentoArrecadacaoDTO;
import arrecadacao.dtos.RetornoArrecadacaoDTO;
import arrecadacao.services.DocumentoArrecadacaoService;
import br.ufla.lemaf.ti.carteirapesca.application.RegistroApplication;
import br.ufla.lemaf.ti.carteirapesca.application.TaxaApplication;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Status;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.TaxaLicenca;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.LicencaRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.StatusRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.TaxaLicencaRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.services.impl.EnderecoBuilderImpl;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.ArquivoUtils;
import br.ufla.lemaf.beans.pessoa.Endereco;
import br.ufla.lemaf.beans.pessoa.Pessoa;
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

	@Autowired
	LicencaRepository licencaRepository;

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

		TaxaLicenca taxaLicenca = buscaTaxaLicenca(licenca);

		CustomizacaoDocCarteiraPesca docCarteiraPesca = new CustomizacaoDocCarteiraPesca();
		docCarteiraPesca.idDocumentoArreacadacao = taxaLicenca.getIdGestaoPagamentos();

		docCarteiraPesca.numeroLicenca = taxaLicenca.getLicenca().getProtocolo().toString();
		docCarteiraPesca.modalidade = licenca.getModalidade().getNomePT();
		docCarteiraPesca.limiteCaptura = licenca.getModalidade().getDescricaoQtdPeixesLimiteCaptura();

		ArquivoDocumentoArrecadacaoDTO arquivoDocumentoArrecadacao = new DocumentoArrecadacaoService(Properties.gestaoPagamentosUrl(), Properties.gestaoPagamentosCodigoModulo())
			.downloadDocumentoArrecadacaoCarteiraPesca(docCarteiraPesca);

		File documentoArrecadacao = new File(Properties.pathArquivoTemporario() + UUID.randomUUID() + EXTENSAO_DOCUMENTO_ARRECADACAO);

		ArquivoUtils.converteBase64ParaArquivo(arquivoDocumentoArrecadacao.documentoBase64, documentoArrecadacao);

		return documentoArrecadacao;
	}

	private TaxaLicenca buscaTaxaLicenca(Licenca licenca) {

		TaxaLicenca taxaLicenca = taxaLicencaRepository.findBylicencaAndVencido(licenca, false);

		if(taxaLicenca == null) {

			Status statusAguardandoVencimento = statusRepository.findByCodigo(Status.StatusEnum.AGUARDANDO_PAGAMENTO.codigo);

			licenca.setStatus(statusAguardandoVencimento);

			licencaRepository.save(licenca);

			taxaLicenca = geraDocumentoArrecadacao(licenca);
		}

		return taxaLicenca;

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

		if (enderecoEU.numero != null) {
			endereco.numero = enderecoEU.numero.toString();
		}

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
