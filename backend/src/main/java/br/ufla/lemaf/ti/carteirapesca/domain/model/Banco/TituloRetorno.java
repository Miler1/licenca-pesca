package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.*;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "titulo_retorno")
public class TituloRetorno implements Entity<Banco, Integer> {

	@Id
	@SuppressWarnings("unused")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name="id_titulo")
	private Titulo titulo;

	@ManyToMany
	@Column(name = "id_retorno")
	private Retorno retorno;

	@ManyToMany
	@Column(name = "id_motivo_ocorrencia")
	private MotivoOcorrencia motivoOcorrencia;

	public TituloRetorno(Titulo titulo, Retorno retorno, MotivoOcorrencia motivoOcorrencia) {

		this.titulo = titulo;
		this.retorno = retorno;
		this.motivoOcorrencia = motivoOcorrencia;

	}

	@Override
	public boolean sameIdentityAs(Banco other) {
		return false;
	}

	@Override
	public Integer identity() {
		return null;
	}
}
