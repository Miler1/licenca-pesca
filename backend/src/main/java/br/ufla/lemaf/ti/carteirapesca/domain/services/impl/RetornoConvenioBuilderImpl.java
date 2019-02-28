package br.ufla.lemaf.ti.carteirapesca.domain.services.impl;

import br.ufla.lemaf.ti.carteirapesca.domain.enuns.TipoArquivoEnum;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.Arquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.TipoArquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.*;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.LicencaRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.TipoArquivoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.ConvenioRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.RetornoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.TipoArrecadacaoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.banco.TipoPagamentoRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.services.RetornoConvenioBuilder;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.config.Properties;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.ArquivoUtils;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.convenio.CabecalhoRetornoDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.convenio.TraillerRetornoDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.convenio.TransacaoRetornoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RetornoConvenioBuilderImpl implements RetornoConvenioBuilder {

	@Autowired
	ConvenioRepository convenioRepository;

	@Autowired
	TipoPagamentoRepository tipoPagamentoRepository;

	@Autowired
	TipoArrecadacaoRepository tipoArrecadacaoRepository;

	@Autowired
	TipoArquivoRepository tipoArquivoRepository;

	@Autowired
	RetornoRepository retornoRepository;

	@Autowired
	LicencaRepository licencaRepository;

	@Override
	public void processaRetorno(File arquivoRetorno) throws Exception {

		Retorno retorno = salvaArquivo(arquivoRetorno);

		List<String> linhasArquivoRetorno = Files.lines(Paths.get(retorno.getArquivo().getCaminhoArquivo()))
			.collect(Collectors.toList());

		CabecalhoRetornoDTO cabecalho = new CabecalhoRetornoDTO(linhasArquivoRetorno.get(0));
		TraillerRetornoDTO trailler  = new TraillerRetornoDTO(linhasArquivoRetorno.get(linhasArquivoRetorno.size() - 1));

		retorno.atualizaRetorno(cabecalho, trailler);

		retorno = retornoRepository.save(retorno);

		processaRegistros(linhasArquivoRetorno, retorno);

	}

	private Retorno salvaArquivo(File arquivoRetorno) throws Exception {

		arquivoRetorno = salvaArquivoDiretorio(arquivoRetorno);

		TipoArquivo tipoArquivo = tipoArquivoRepository.findByCodigo(TipoArquivoEnum.RETORNO.getCodigo());

		Arquivo arquivo = new Arquivo(arquivoRetorno.getPath(), arquivoRetorno.getName(), tipoArquivo);
		Retorno retorno = new Retorno(arquivo);

		return retorno;

	}

	private File salvaArquivoDiretorio(File arquivoRetorno) throws Exception {

		final DateTimeFormatter FORMATO_DATA_MES_ANO = DateTimeFormatter.ofPattern("MM-YYYY");

		String pathSalvarArquivo = Properties.pathArquivoRetorno() +
			File.separator + "convenio" +
			File.separator + LocalDate.now().format(FORMATO_DATA_MES_ANO) +
			File.separator + UUID.randomUUID();

		return ArquivoUtils.salvaArquivoDiretorio(arquivoRetorno, pathSalvarArquivo);

	}

	private void processaRegistros(List<String> linhasArquivoRetorno, Retorno retorno) {

		List<TransacaoRetornoDTO> transacoes = getTransacoes(linhasArquivoRetorno);

		transacoes.forEach(t -> {

			Convenio convenio = convenioRepository.findByNossoNumero(1);

			Licenca licenca = licencaRepository.findByConvenio(convenio);

			if(convenio != null) {

				TipoArrecadacao tipoArrecadacao = tipoArrecadacaoRepository.findByCodigo(t.getFormaArrecadacao());
				Optional<TipoPagamento> tipoPagamento = tipoPagamentoRepository.findById(t.getFormaParamento());

				PagamentoConvenio pagamento = new PagamentoConvenio(t, tipoArrecadacao, tipoPagamento.get(), retorno);

				licenca.getConvenio().setPagamento(pagamento);

				licenca.setDataVencimento(pagamento.getDataCredito());

				convenioRepository.save(convenio);

			}

		});

	}

	public List<TransacaoRetornoDTO> getTransacoes(final List<String> linhasArquivoRetorno) {

		linhasArquivoRetorno.remove(0);
		linhasArquivoRetorno.remove(linhasArquivoRetorno.size() - 1);

		List<TransacaoRetornoDTO> transacoes = new ArrayList<>();

		linhasArquivoRetorno.forEach(l -> {
			transacoes.add(new TransacaoRetornoDTO(l));
		});

		return transacoes;

	}
}
