package br.ufla.lemaf.ti.carteirapesca.domain.repository;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Repository de Licença.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Repository
public interface LicencaRepository extends JpaRepository<Licenca, Integer> {

	/**
	 * Busca uma licença pelo seu protocolo formatado.
	 *
	 * @param protocolo O número de protocolo formatado
	 * @return A licença
	 */
	Licenca findByProtocoloCodigoFormatado(String protocolo);

	@Transactional
	@Modifying
	@Query("update Licenca l set l.status = 3 where l.dataVencimento < :date and l.status = 1")
	void alterarVencimento(Date date);


	@Transactional
	@Modifying
	@Query("update Licenca l set l.status = 2 where l.dataVencimentoBoleto < :date and l.status = 0")
	void alterarInvalidado(Date date);
}
