package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListaLicencaDTO {

	private PessoaDTO pessoa;

	private List<Licenca> licencas;
}
