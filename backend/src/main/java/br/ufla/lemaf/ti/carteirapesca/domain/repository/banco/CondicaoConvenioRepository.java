package br.ufla.lemaf.ti.carteirapesca.domain.repository.banco;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.CondicaoConvenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondicaoConvenioRepository extends JpaRepository<CondicaoConvenio, Integer> {

	CondicaoConvenio findByCodigo(String codigo);

}
