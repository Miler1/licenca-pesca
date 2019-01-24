package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.domain.shared.ValueObject;
import lombok.Getter;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Status da Licença.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Entity
@Getter
public class Status {
	@Id
	@Column(name = "id")
	private Integer id;

	@Column
	private String descricao;

	@Column
	private String codigo;

	public enum StatusEnum{

		AGUARDANDO_PAGAMENTO_BOLETO(0, "Tombado", "AGUARDANDO_PAGAMENTO_BOLETO"),
		ATIVO(1, "Sincronizado", "ATIVO"),
		INVALIDADO(2, "Não Sincronizado", "INVALIDADO"),
		VENCIDO(3, "Exportado", "VENCIDO"),
		RENOVADO(4, "Rascunho", "RENOVADO");

		public Integer id;
		public String descricao;
		public String codigo;

		StatusEnum(Integer id, String descricao, String codigo) {
			this.id = id;
			this.descricao = descricao;
			this.codigo = codigo;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public boolean sameValueAs(Status other) {
		return this.equals(other);
	}
}
