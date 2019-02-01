package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.Arquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "remessa")
public class Remessa implements Entity<Remessa, Integer> {

	@Id
	@SuppressWarnings("unused")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Setter
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_arquivo", referencedColumnName="id")
	private Arquivo arquivo;

	@Column(name = "numero_sequencial")
	private Integer sequencia;

	@Column(name = "sequencia_nome_arquivo")
	private String sequencialNomeArquivo;

	@Column(name = "dt_cadastro", insertable = false, updatable = false)
	private Date dataCadastro;

	public Remessa() {}

	public Remessa(Integer sequencia) {
		this.sequencia = sequencia;
		this.sequencialNomeArquivo = String.format("%02d", sequencia);
	}

	@Override
	public boolean sameIdentityAs(Remessa other) {
		return false;
	}

	@Override
	public Integer identity() {
		return null;
	}
}
