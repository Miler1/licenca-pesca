package br.ufla.lemaf.ti.carteirapesca.domain.repository.banco;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Integer> {

	List<Titulo> findByDataGeracaoRemessaIsNull();

}
