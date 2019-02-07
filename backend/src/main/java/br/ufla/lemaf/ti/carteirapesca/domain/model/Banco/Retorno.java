package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.Arquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
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

	@Setter
	@Column(name = "dt_gravacao_banco")
	private LocalDate dataGravacaoBanco;

	@Setter
	@Column(name = "dt_processamento")
	private LocalDate dataProcessamento;

	@Column(name = "qtd_titulo_cobranca")
	private Integer qtdTitulosCobraca;

	@Column(name = "valor_titulo_cobranca")
	private BigDecimal vlrTitulosCobraca;

	@Column(name = "qtd_confirmacao_entrada")
	private Integer qtdConfirmacaoEntrada;

	@Column(name = "valor_confirmacao_entrada")
	private BigDecimal vlrConfirmacaoEntrada;

	@Column(name = "qtd_liquidacao")
	private Integer qtdLiquidacao;

	@Column(name = "valor_liquidacao")
	private BigDecimal vlrLiquidacao;

	@Column(name = "qtd_baixados")
	private Integer qtdBaixados;

	@Column(name = "valor_baixados")
	private BigDecimal vlrBaixados;

	@Column(name = "qtd_abatimento_cancelamento")
	private Integer qtdAbatimentoCancelamento;

	@Column(name = "valor_abatimento_cancelamento")
	private BigDecimal vlrAbatimentoCancelamento;

	@Column(name = "qtd_vencimento_alterado")
	private Integer qtdVencimentoAlterado;

	@Column(name = "valor_vencimento_alterado")
	private BigDecimal vlrVencimentoAlterado;

	@Column(name = "qtd_abatimento_concedido")
	private Integer qtdAbatimentoConcedido;

	@Column(name = "valor_abatimento_concedido")
	private BigDecimal vlrAbatimentoConcedido;

	@Column(name = "qtd_confirmacao_instrucao_protesto")
	private Integer qtdConfirmacaoInstrucaoProtesto;

	@Column(name = "valor_confirmacao_instrucao_protesto")
	private BigDecimal vlrConfirmacaoInstrucaoProtesto;

	@Column(name = "qtd_total_rateio_efetuado")
	private Integer qtdTotalRateioEfetuado;

	@Column(name = "valor_total_rateio_efetuado")
	private BigDecimal vlrTotalRateioEfetuado;

	public Retorno(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	@Override
	public boolean sameIdentityAs(Retorno other) {
		return false;
	}

	@Override
	public Integer identity() {
		return null;
	}
}
