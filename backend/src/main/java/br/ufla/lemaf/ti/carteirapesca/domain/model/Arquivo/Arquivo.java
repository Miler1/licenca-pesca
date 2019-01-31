package br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "arquivo")
public class Arquivo implements Entity<Arquivo, Integer> {

	private static final String SEQUENCIA = Constants.SCHEMA_CARTEIRA_PESCA + ".arquivo_id_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCIA)
	@SequenceGenerator(name = SEQUENCIA,
		sequenceName = SEQUENCIA,
		allocationSize=1)
	private Integer id;

	@Column(name = "caminho_arquivo")
	private String caminhoArquivo;

	private String nome;

	@Column(name = "dt_cadastro", insertable = false, updatable = false)
	private Date dataCadastro;

	@ManyToOne
	@JoinColumn(name = "id_tipo_arquivo", referencedColumnName="id")
	private TipoArquivo tipoArquivo;

	public Arquivo() {}

	public Arquivo(String caminhoArquivo, String nome, TipoArquivo tipoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
		this.nome = nome;
		this.tipoArquivo = tipoArquivo;
	}

	@Override
	public boolean sameIdentityAs(Arquivo other) {
		return false;
	}

	@Override
	public Integer identity() {
		return null;
	}
}
