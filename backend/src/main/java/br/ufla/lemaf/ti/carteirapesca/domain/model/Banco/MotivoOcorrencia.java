package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.*;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "motivo_ocorrencia")
public class MotivoOcorrencia implements Entity<MotivoOcorrencia, Integer> {

	@Id
	@SuppressWarnings("unused")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "id_motivo")
	private Motivo motivo;

	@OneToOne
	@JoinColumn(name="id_ocorrencia")
	private Ocorrencia ocorrencia;

	@Column(name = "codigo_motivo")
	private Integer codigoMotivo;

	@Override
	public boolean sameIdentityAs(MotivoOcorrencia other) {
		return false;
	}

	@Override
	public Integer identity() {
		return null;
	}
}
