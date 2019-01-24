package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.internal;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.InformacaoComplementar;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.*;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.InformacaoComplementarService;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.InformacaoComplementarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformacaoComplementarServiceImpl implements  InformacaoComplementarService {


	@Autowired
	private ModalidadeRepository modalidadeRepository;

	@Autowired
	private FaixaEtariaRepository faixaEtariaRepository;

	@Autowired
	private PreferenciaLocalPescaRepository preferenciaLocalPescaRepository;

	@Autowired
	private RendaMensalRepository rendaMensalRepository;

	@Autowired
	private TipoIscaRepository tipoIscaRepository;

	@Override
	public InformacaoComplementar toInformacaoComplementar(InformacaoComplementarDTO informacaoComplementarDTO) {
		InformacaoComplementar informacaoComplementar = new InformacaoComplementar();

		informacaoComplementar.setAgenciaTurismo(informacaoComplementarDTO.getAgenciaTurismo());
		informacaoComplementar.setFaixaEtaria(faixaEtariaRepository.findById(informacaoComplementarDTO.getFaixaEtaria()).get());
		informacaoComplementar.setLocalPesca(preferenciaLocalPescaRepository.findById(informacaoComplementarDTO.getLocalPesca()).get());
		informacaoComplementar.setModalidadePesca(modalidadeRepository.findById(informacaoComplementarDTO.getModalidadePesca()).get());
		informacaoComplementar.setModalidadeMaisPraticada(modalidadeRepository.findById(informacaoComplementarDTO.getModalidadeMaisPraticada()).get());
		informacaoComplementar.setRendaMensal(rendaMensalRepository.findById(informacaoComplementarDTO.getRendaMensal()).get());
		informacaoComplementar.setTipoIsca(tipoIscaRepository.findById(informacaoComplementarDTO.getTipoIsca()).get());
		informacaoComplementar.setDiasPescaPorAno(informacaoComplementarDTO.getDiasPescaPorAno());
		informacaoComplementar.setGastoMedioPesca(informacaoComplementarDTO.getGastoMedioPesca());

		return informacaoComplementar;
	}
}
