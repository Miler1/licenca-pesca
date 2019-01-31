package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;


import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.Solicitante;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "endereco_estrangeiro")
public class EnderecoEstrangeiro {
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Getter
	@Column(name = "descricao_endereco")
	private String descricaoEndereco;

	@Getter
	@Column(name = "cidade")
	private String cidade;

	@Getter
	@Column(name = "estado")
	private String estado;

	@Getter
	@ManyToOne
	@JoinColumn(name="id_nacionalidade")
	private Pais nacionalidade;

	@Getter
	@ManyToOne
	@JoinColumn(name="id_pais")
	private Pais pais;

	@Setter
	@OneToOne(mappedBy = "enderecoEstrangeiro")
	private Solicitante solicitante;

	public Boolean isEmpty() {
		return  this.descricaoEndereco == null && this.cidade == null && this.estado == null && this.nacionalidade == null && this.pais == null;
	}
}
