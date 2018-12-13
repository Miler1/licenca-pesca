package br.ufla.lemaf.ti.carteirapesca.application.impl;

import br.ufla.lemaf.ti.carteirapesca.application.ConsultaApplication;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.LicencaRepository;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

		var licenca =  licencaRepository.findByProtocolo_CodigoFormatado(protocolo.getCodigoFormatado());

		if (licenca == null) return null;

		return licenca;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String buscarCaminhoBoleto(Protocolo protocolo) {

		var licenca = licencaRepository.findByProtocolo_CodigoFormatado(protocolo.getCodigoFormatado());

		if (licenca == null) return null;

		return licenca.getCaminhoBoleto();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String buscarCaminhoCarteira(Protocolo protocolo) {

		var licenca = licencaRepository.findByProtocolo_CodigoFormatado(protocolo.getCodigoFormatado());

		if (licenca == null) return null;

		return licenca.getCaminhoCarteira();

	}
}
