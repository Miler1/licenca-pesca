package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.InformacaoComplementar;
import br.ufla.lemaf.ti.carteirapesca.domain.repository.ModalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformacaoComplementarAssembler {

	@Autowired
	ModalidadeRepository modalidadeRepository;


//	public InformacaoComplementar toInformacaoComplementar(InformacaoComplementarDTO informacaoComplementarDTO){
//		InformacaoComplementar informacaoComplementar = new InformacaoComplementar();
//
//		informacaoComplementar.setModalidadePesca(modalidadeRepository.findById(informacaoComplementarDTO.getModalidadePesca()).get());
//
//
//
//		return informacaoComplementar;
//	}
}

