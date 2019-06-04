package br.ufla.lemaf.ti.carteirapesca.application.impl;

import br.ufla.lemaf.ti.carteirapesca.application.ConsultaApplication;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Pais;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.LicencaRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.PaisRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Camada de aplicação da etapa de consulta.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Slf4j
@Service
@Transactional
public class ConsultaAplicationImpl implements ConsultaApplication {

	private LicencaRepository licencaRepository;

	@Autowired
	private PaisRepository paisRepository;

	/**
	 * Injetando dependências.
	 *
	 * @param licencaRepository O repositório da licença.
	 */
	@Autowired
	public ConsultaAplicationImpl(LicencaRepository licencaRepository) {
		this.licencaRepository = licencaRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Licenca consulta(Protocolo protocolo) {

		var licenca =  licencaRepository.findByProtocoloCodigoFormatado(protocolo.getCodigoFormatado());

		if (licenca == null) return null;

		return licenca;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String buscarCaminhoBoleto(Protocolo protocolo) {

		var licenca = licencaRepository.findByProtocoloCodigoFormatado(protocolo.getCodigoFormatado());

		if (licenca == null) return null;

		return null; // licenca.getTitulo().getArquivoBoleto().getCaminhoArquivo();

	}

	@Override
	public List<Pais> fetchPaises() {
		return paisRepository.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String buscarCaminhoCarteira(Protocolo protocolo) {

		Licenca licenca = licencaRepository.findByProtocoloCodigoFormatado(protocolo.getCodigoFormatado());

		if (licenca == null) return null;

		return licenca.getCaminhoCarteira();

	}

}
