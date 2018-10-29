package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.questionario;

import lombok.Getter;
import org.apache.commons.lang3.Validate;
import org.springframework.hateoas.ResourceSupport;

/**
 * DTO de Questionário.
 *
 * @author Highlander
 * @since 1.0
 */
@Getter
public class InformacaoComplementarDTO extends ResourceSupport {

	private ModalidadeDTO modalidade;

	private LocalidadeDTO localizacaoPreferencialPesca;

	private RendaDTO rendaMensal;

	private Integer diasPescaPorAno;

	private Double gastoMedioPesca;

	private FaixaEtariaDTO faixaEtaria;

	private LocalPescaDTO localPesca;

	private MaterialPescaDTO materialPesca;

	private TipoIscaDTO tipoIsca;

	private ModalidadeDTO modalidadeMaisPraticada;

	private Boolean agenciaTurismo;

	/**
	 * Construtor. Nenhum campo pode ser nulo.
	 *
	 * @param modalidade                   A modalidade da pesca para a licença.
	 * @param localizacaoPreferencialPesca A localidade preferencial
	 *                                     no qual o usuario prefere
	 *                                     pescar (Localmente, outros estados,
	 *                                     outros países).
	 * @param rendaMensal                  A renda mensal do usuario.
	 * @param diasPescaPorAno              A média de dias de pesca por ano.
	 * @param gastoMedioPesca              O gasto médio por pesca.
	 * @param faixaEtaria                  A faixa etária do usuário.
	 * @param localPesca                   O local preferencial para
	 *                                     pesca (Mar, água doce).
	 * @param materialPesca                O material de pesca.
	 * @param tipoIsca                     O tipo de isca utilizado.
	 * @param modalidadeMaisPraticada      A modalidade de pesca que o usuário
	 *                                     mais pratica.
	 * @param agenciaTurismo               {@code true} se costuma programar
	 *                                     as pescarias com agências de turismo.
	 */
	public InformacaoComplementarDTO(ModalidadeDTO modalidade,
	                                 LocalidadeDTO localizacaoPreferencialPesca,
	                                 RendaDTO rendaMensal, Integer diasPescaPorAno,
	                                 Double gastoMedioPesca, FaixaEtariaDTO faixaEtaria,
	                                 LocalPescaDTO localPesca, MaterialPescaDTO materialPesca,
	                                 TipoIscaDTO tipoIsca,
	                                 ModalidadeDTO modalidadeMaisPraticada,
	                                 Boolean agenciaTurismo) {

		Validate.notNull(modalidade);
		this.modalidade = modalidade;

		Validate.notNull(localizacaoPreferencialPesca);
		this.localizacaoPreferencialPesca = localizacaoPreferencialPesca;

		Validate.notNull(rendaMensal);
		this.rendaMensal = rendaMensal;

		Validate.notNull(diasPescaPorAno);
		this.diasPescaPorAno = diasPescaPorAno;

		Validate.notNull(gastoMedioPesca);
		this.gastoMedioPesca = gastoMedioPesca;

		Validate.notNull(faixaEtaria);
		this.faixaEtaria = faixaEtaria;

		Validate.notNull(localPesca);
		this.localPesca = localPesca;

		Validate.notNull(materialPesca);
		this.materialPesca = materialPesca;

		Validate.notNull(tipoIsca);
		this.tipoIsca = tipoIsca;

		Validate.notNull(modalidadeMaisPraticada);
		this.modalidadeMaisPraticada = modalidadeMaisPraticada;

		Validate.notNull(agenciaTurismo);
		this.agenciaTurismo = agenciaTurismo;
	}
}
