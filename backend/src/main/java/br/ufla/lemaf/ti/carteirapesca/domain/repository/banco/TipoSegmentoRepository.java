package br.ufla.lemaf.ti.carteirapesca.domain.repository.banco;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.TipoSegmento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoSegmentoRepository extends JpaRepository<TipoSegmento, Integer> {

	TipoSegmento findByCodigo(Integer codigo);

}
