package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Tipo de modalidade da Licença de pesca.
 *
 * @author Highlander Paiva
 * @since 1.0
 */

@Getter
@Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "modalidade")
public class Modalidade {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "nome_pt")
	private String nomePT;

	@Column(name = "nome_en")
	private String nomeEN;

	@Column(name = "nome_es")
	private String nomeES;

	public enum Modalidades {

		PESCA_ESPORTIVA(0, "Pesca Esportiva", "Sporting Fishing", "Pesca Deportiva"),
		PESCA_REACREATIVA(1, "Pesca Recreativa", "Reacretional Fishing", "Pesca Recreativa");

		public Integer id;
		public String nomePT;
		public String nomeEN;
		public String nomeES;


		Modalidades(Integer id, String nomePT, String nomeEN, String nomeES) {
			this.id = id;
			this.nomePT = nomePT ;
			this.nomeEN = nomeEN;
			this.nomeES = nomeES;
		}
	}

	public BigDecimal getValor() {

		if(this.id == Modalidade.Modalidades.PESCA_ESPORTIVA.id) {
			return new BigDecimal(45.19);
		} else if(this.id == Modalidade.Modalidades.PESCA_REACREATIVA.id) {
			return new BigDecimal(59.50);
		} else {
			return new BigDecimal(0);
		}

	}

	public String getDescricaoQtdPeixesLimiteCaptura() {

		if(this.id.equals(Modalidades.PESCA_ESPORTIVA.id)) {
			return "0 (ZERO)";
		} else if(this.id.equals(Modalidades.PESCA_REACREATIVA.id)) {
			return "05 KG DE PEIXES INTEIROS";
		} else {
			return "";
		}

	}

}
