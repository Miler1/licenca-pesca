package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.InformacaoComplementar;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.InformacaoComplementarDTO;

public interface InformacaoComplementarService {

	InformacaoComplementar toInformacaoComplementar(InformacaoComplementarDTO informacaoComplementarDTO);
}
