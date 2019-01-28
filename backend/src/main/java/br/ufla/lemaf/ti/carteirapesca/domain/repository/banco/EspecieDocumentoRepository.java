package br.ufla.lemaf.ti.carteirapesca.domain.repository.banco;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.EspecieDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecieDocumentoRepository extends JpaRepository<EspecieDocumento, Integer> {

	EspecieDocumento findByCodigo(String codigo);

}
