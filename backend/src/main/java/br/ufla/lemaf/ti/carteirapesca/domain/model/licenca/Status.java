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

		AGUARDANDO_PAGAMENTO(0, "Aguardando pagamento do boleto", "AGUARDANDO_PAGAMENTO"),
		ATIVO(1, "Ativo", "ATIVO"),
		INVALIDADO(2, "Invalidado", "INVALIDADO"),
		VENCIDO(3, "Vencido", "VENCIDO"),
		RENOVADO(4, "Renovado", "RENOVADO"),
		ATIVO_AGUARDANDO_PAGAMENTO(5, "Ativo aguardando pagamento do boleto", "ATIVO_AGUARDANDO_PAGAMENTO");

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
