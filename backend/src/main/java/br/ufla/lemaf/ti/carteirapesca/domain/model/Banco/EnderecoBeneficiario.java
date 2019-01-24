package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.Table;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "endereco_beneficiario")
public class EnderecoBeneficiario implements Entity<EnderecoBeneficiario, Long> {

	private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;

	private String cep;

	private String municipio;

	private String estado;

	@Override
	public boolean sameIdentityAs(EnderecoBeneficiario other) {
		return false;
	}

	@Override
	public Long identity() {
		return null;
	}
}
