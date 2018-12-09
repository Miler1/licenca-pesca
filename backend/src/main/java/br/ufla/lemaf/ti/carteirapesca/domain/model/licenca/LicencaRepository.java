package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository de Licença.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Repository
public interface LicencaRepository extends JpaRepository<Licenca, Integer> {

	/**
	 * Busca uma licença pelo seu protocolo.
	 *
	 * @param protocolo O número de protocolo da licença
	 * @return A licença
	 */
	Licenca findByProtocolo(Protocolo protocolo);
}
