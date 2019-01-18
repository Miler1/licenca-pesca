package br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da agregação de Solicitante.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Repository
public interface SolicitanteRopository extends JpaRepository<Solicitante, Integer> {

	/**
	 * Busca um solicitante pelo seu CPF.
	 *
	 * @param cpf O CPF buscado
	 * @return O Solicitante
	 */
	Solicitante findByIdentityCpfNumero(String cpf);

	/**
	 * Busca o solicitante pelo seu passaporte.
	 *
	 * @param passaporte O passaporte buscado
	 * @return O Solicitante
	 */
	Solicitante findByIdentityPassaporteNumero(String passaporte);

}
