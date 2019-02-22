package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.Arquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.titulo.CabecalhoRetornoDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.titulo.TraillerRetornoDTO;
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
	private Integer qtdTitulosCobranca;

	@Column(name = "valor_titulo_cobranca")
	private BigDecimal valorTitulosCobranca;

	@Column(name = "qtd_confirmacao_entrada")
	private Integer qtdConfirmacaoEntrada;

	@Column(name = "valor_confirmacao_entrada")
	private BigDecimal valorConfirmacaoEntrada;

	@Column(name = "qtd_liquidacao")
	private Integer qtdLiquidacao;

	@Column(name = "valor_liquidacao")
	private BigDecimal valorLiquidacao;

	@Column(name = "qtd_baixados")
	private Integer qtdBaixados;

	@Column(name = "valor_baixados")
	private BigDecimal valorBaixados;

	@Column(name = "qtd_abatimento_cancelamento")
	private Integer qtdAbatimentoCancelamento;

	@Column(name = "valor_abatimento_cancelamento")
	private BigDecimal valorAbatimentoCancelamento;

	@Column(name = "qtd_vencimento_alterado")
	private Integer qtdVencimentoAlterado;

	@Column(name = "valor_vencimento_alterado")
	private BigDecimal valorVencimentoAlterado;

	@Column(name = "qtd_abatimento_concedido")
	private Integer qtdAbatimentoConcedido;

	@Column(name = "valor_abatimento_concedido")
	private BigDecimal valorAbatimentoConcedido;

	@Column(name = "qtd_confirmacao_instrucao_protesto")
	private Integer qtdConfirmacaoInstrucaoProtesto;

	@Column(name = "valor_confirmacao_instrucao_protesto")
	private BigDecimal valorConfirmacaoInstrucaoProtesto;

	@Column(name = "qtd_total_rateio_efetuado")
	private Integer qtdTotalRateioEfetuado;

	@Column(name = "valor_total_rateio_efetuado")
	private BigDecimal valorTotalRateioEfetuado;

	@Column(name = "numero_aviso_bancario")
	private Integer numeroAvisoBancario;

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

	public void atualizaRetorno(CabecalhoRetornoDTO cabecalho, TraillerRetornoDTO trailler) {

		this.dataGravacaoBanco = cabecalho.getDataGravacaoArquivo();
		this.dataProcessamento = LocalDate.now();

		this.qtdTitulosCobranca = trailler.getQtdTitulosCobranca();
		this.valorTitulosCobranca = trailler.getValorTotalCobranca();
		this.qtdConfirmacaoEntrada = trailler.getQtdConfirmacaoEntrada();
		this.valorConfirmacaoEntrada = trailler.getValorConfirmacaoEntrada();
		this.qtdLiquidacao = trailler.getQtdLiquidacao();
		this.valorLiquidacao = trailler.getValorLiquidacao();
		this.qtdBaixados = trailler.getQtdTitulosBaixado();
		this.valorBaixados = trailler.getValorTitulosBaixado();
		this.qtdAbatimentoCancelamento = trailler.getQtdAbatimentoCancelamento();
		this.valorAbatimentoCancelamento = trailler.getValorAbatimentoCancelamento();
		this.qtdVencimentoAlterado = trailler.getQtdVencimentoAlterado();
		this.valorVencimentoAlterado = trailler.getValorVencimentoAlterado();
		this.qtdAbatimentoConcedido = trailler.getQtdAbatimentoConcedido();
		this.valorAbatimentoConcedido = trailler.getValorAbatimentoConcedido();
		this.qtdConfirmacaoInstrucaoProtesto = trailler.getQtdConfirmacaoInstrucaoProtesto();
		this.valorConfirmacaoInstrucaoProtesto = trailler.getValorConfirmacaoInstrucaoProtesto();
		this.qtdTotalRateioEfetuado = trailler.getQtdTotalRateioEfetuado();
		this.valorTotalRateioEfetuado = trailler.getValorTotalRateioEfetuado();
		this.numeroAvisoBancario = trailler.getNumeroAvisoBancario();

	}

}
