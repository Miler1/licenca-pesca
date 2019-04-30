package br.ufla.lemaf.ti.carteirapesca.domain.model.Banco;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.Arquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.shared.Entity;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.convenio.CabecalhoRetornoDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.Banco.facade.dto.convenio.TraillerRetornoDTO;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@javax.persistence.Entity
@Table(schema = Constants.SCHEMA_CARTEIRA_PESCA, name = "retorno_convenio")
public class RetornoConvenio implements Entity<RetornoConvenio, Integer> {

	@Id
	@SuppressWarnings("unused")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_arquivo", referencedColumnName="id", nullable = false)
	private Arquivo arquivo;

	@Column(name = "dt_geracao_arquivo", nullable = false)
	private LocalDate dataGeracaoArquivo;

	@Column(name = "dt_processamento", insertable = false, updatable = false)
	private LocalDate dataProcessamento;

	@Column(name = "numero_arquivo", nullable = false)
	private Integer numeroArquivo;

	@Column(name = "qtd_registros", nullable = false)
	private Integer qtdRegistros;

	@Column(name = "valor_recebido", nullable = false)
	private BigDecimal valorRecebido;

	public RetornoConvenio() {}

	public RetornoConvenio(CabecalhoRetornoDTO cabecalho, TraillerRetornoDTO trailler, Arquivo arquivo) {

		this.arquivo = arquivo;
		this.dataGeracaoArquivo = cabecalho.getDataGeracaoArquivo();
		this.numeroArquivo = cabecalho.getSequencialArquivo();
		this.dataProcessamento = LocalDate.now();

		this.qtdRegistros = trailler.getQtdRegistros();
		this.valorRecebido = trailler.getVlrRegistros();

	}

	@Override
	public boolean sameIdentityAs(RetornoConvenio other) {
		return false;
	}

	@Override
	public Integer identity() {
		return null;
	}

}
