package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
	Licenca findByProtocolo_CodigoFormatado(String protocolo);
}
