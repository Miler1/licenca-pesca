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

	@Id
	@SuppressWarnings("unused")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Getter
	@Column(name = "caminho_arquivo")
	private String caminhoArquivo;

	@Getter
	@Column(name = "nome")
	private String nome;

	@Getter
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
