package br.ufla.lemaf.ti.carteirapesca.domain.repository;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.TaxaLicenca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaxaLicencaRepository extends JpaRepository<TaxaLicenca, Integer> {

	TaxaLicenca findBylicencaAndVencido(Licenca licenca, Boolean vencido);

	TaxaLicenca findByLicencaAndPago(Licenca licenca, Boolean pago);

	TaxaLicenca findByIdGestaoPagamentos(Integer idGestaoPagamentos);

	List<TaxaLicenca> findByDataVencimentoLessThanAndPagoAndVencido(LocalDate dataVencimento, Boolean pago, Boolean vencido);

}
