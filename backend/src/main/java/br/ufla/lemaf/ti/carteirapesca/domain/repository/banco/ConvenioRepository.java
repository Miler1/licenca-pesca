package br.ufla.lemaf.ti.carteirapesca.domain.repository.banco;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.CondicaoConvenio;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Convenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Integer> {

	Convenio findByCodigoBarras(String codigoBarras);

	@Transactional
	@Modifying
	@Query("UPDATE Convenio " +
		"SET condicao = :condicaoVencido " +
		"WHERE pagamento IS NULL " +
		"	AND dataVencimento < now() " +
		"	AND condicao = :condicaoAguardandoPagamento")
	void alteraCondicaoBoletosVencidos(CondicaoConvenio condicaoVencido, CondicaoConvenio condicaoAguardandoPagamento);

}
