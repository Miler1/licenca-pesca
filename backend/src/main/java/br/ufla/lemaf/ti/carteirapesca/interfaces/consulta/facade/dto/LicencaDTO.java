package br.ufla.lemaf.ti.carteirapesca.interfaces.consulta.facade.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

/**
 * DTO de Licença.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public class LicencaDTO extends ResourceSupport {

	private String protocolo;

	private Integer modalidade;

	private Integer status;

	private Date dataCriacao;

	private Date dataAtivacao;

	/**
	 * Construtor do DTO de Licença.
	 *
	 * @param protocolo    O protocolo
	 * @param modalidade   A modalidade
	 * @param status       O status
	 * @param dataCriacao  A data de criação
	 * @param dataAtivacao A data de ativação
	 */
	public LicencaDTO(@JsonProperty("protocolo") String protocolo,
	                  @JsonProperty("modalidade") Integer modalidade,
	                  @JsonProperty("status") Integer status,
	                  @JsonProperty("dataCriacao") Date dataCriacao,
	                  @JsonProperty("dataAtivacao") Date dataAtivacao) {
		this.protocolo = protocolo;
		this.modalidade = modalidade;
		this.status = status;
		setDataCriacao(dataCriacao);
		setDataAtivacao(dataAtivacao);
	}

	/**
	 * Gets protocolo.
	 *
	 * @return the protocolo
	 */
	public String getProtocolo() {
		return protocolo;
	}

	/**
	 * Sets protocolo.
	 *
	 * @param protocolo the protocolo
	 */
	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	/**
	 * Gets modalidade.
	 *
	 * @return the modalidade
	 */
	public Integer getModalidade() {
		return modalidade;
	}

	/**
	 * Sets modalidade.
	 *
	 * @param modalidade the modalidade
	 */
	public void setModalidade(Integer modalidade) {
		this.modalidade = modalidade;
	}

	/**
	 * Gets status.
	 *
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Sets status.
	 *
	 * @param status the status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * Gets data criacao.
	 *
	 * @return the data criacao
	 */
	public Date getDataCriacao() {
		return this.dataCriacao == null
			? null
			: new Date(dataCriacao.getTime());
	}

	/**
	 * Setter de data de criação. Evita erros de vunerabilidade já
	 * que o campo de Data é mutável.
	 *
	 * @param dataCriacao A data de nascimento.
	 */
	private void setDataCriacao(Date dataCriacao) {
		if (dataCriacao == null) {
			this.dataCriacao = null;
		} else {
			this.dataCriacao = new Date(dataCriacao.getTime());
		}
	}

	/**
	 * Gets data ativacao.
	 *
	 * @return the data ativacao
	 */
	public Date getDataAtivacao() {
		return this.dataAtivacao == null
			? null
			: new Date(dataAtivacao.getTime());
	}

	/**
	 * Sets data ativacao.
	 *
	 * @param dataAtivacao the data ativacao
	 */
	public void setDataAtivacao(Date dataAtivacao) {
		if (dataAtivacao == null) {
			this.dataAtivacao = null;
		} else {
			this.dataAtivacao = new Date(dataAtivacao.getTime());
		}
	}
}
