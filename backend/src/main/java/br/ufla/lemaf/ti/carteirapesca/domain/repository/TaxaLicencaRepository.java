package br.ufla.lemaf.ti.carteirapesca.domain.repository;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.TaxaLicenca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxaLicencaRepository extends JpaRepository<TaxaLicenca, Integer> {
}
