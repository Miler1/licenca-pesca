package br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da agregação de Protocolo.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Repository
public interface SequenceRepository extends JpaRepository<Sequence, Integer> {

	/**
	 * Busca a sequência da modalidade.
	 *
	 * @param modalidade A modalidade
	 * @return A sequência
	 */
	Sequence findByModalidade(Modalidade modalidade);

}
