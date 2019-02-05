package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.Arquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "retorno")
public class Retorno implements Entity<Retorno, Integer> {

	@Id
	@SuppressWarnings("unused")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Setter
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_arquivo", referencedColumnName="id")
	private Arquivo arquivo;

	@Column(name = "dt_gravacao_banco")
	private LocalDate dataGravacaoBanco;

	@Column(name = "dt_processamento")
	private LocalDate dataProcessamento;

	@Override
	public boolean sameIdentityAs(Retorno other) {
		return false;
	}

	@Override
	public Integer identity() {
		return null;
	}
}
