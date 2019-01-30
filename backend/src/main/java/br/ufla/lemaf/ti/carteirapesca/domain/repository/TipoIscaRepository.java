package br.ufla.lemaf.ti.carteirapesca.domain.repository;


import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.TipoIsca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoIscaRepository extends JpaRepository<TipoIsca, Integer> {
}
