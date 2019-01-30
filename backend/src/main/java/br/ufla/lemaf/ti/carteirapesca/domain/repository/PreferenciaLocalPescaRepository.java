package br.ufla.lemaf.ti.carteirapesca.domain.repository;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.PreferenciaLocalPesca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenciaLocalPescaRepository extends JpaRepository<PreferenciaLocalPesca, Integer> {
}
