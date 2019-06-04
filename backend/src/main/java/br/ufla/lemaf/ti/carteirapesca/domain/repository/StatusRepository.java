package br.ufla.lemaf.ti.carteirapesca.domain.repository;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {

	Status findByCodigo(String codigoStatus);

}
