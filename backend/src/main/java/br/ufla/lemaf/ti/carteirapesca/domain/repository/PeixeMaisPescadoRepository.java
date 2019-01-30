package br.ufla.lemaf.ti.carteirapesca.domain.repository;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.PeixeMaisPescado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeixeMaisPescadoRepository extends JpaRepository<PeixeMaisPescado, Integer> {
}
