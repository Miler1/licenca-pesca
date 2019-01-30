package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarteiraDTO {

	private Licenca licenca;
	private PessoaDTO pessoaDTO;
}
