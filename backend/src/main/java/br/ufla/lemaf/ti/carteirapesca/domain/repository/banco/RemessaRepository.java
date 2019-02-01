package br.ufla.lemaf.ti.carteirapesca.domain.repository.banco;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Remessa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface RemessaRepository extends JpaRepository<Remessa, Integer> {

	Long countByDataCadastroBetween(Date inicio, Date fim);

	@Query(value = "SELECT * FROM carteira_pesca.remessa ORDER BY id DESC LIMIT 1", nativeQuery = true)
	Remessa buscaUltimaRemessaGerada();

}
