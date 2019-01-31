package br.ufla.lemaf.ti.carteirapesca.domain.repository.banco;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Remessa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RemessaRepository extends JpaRepository<Remessa, Integer> {

	Long countByDataCadastroBetween(Date inicio, Date fim);

}
