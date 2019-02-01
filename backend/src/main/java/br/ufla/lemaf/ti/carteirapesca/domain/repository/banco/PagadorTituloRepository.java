package br.ufla.lemaf.ti.carteirapesca.domain.repository.banco;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.PagadorTitulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagadorTituloRepository extends JpaRepository<PagadorTitulo, Integer> {

	PagadorTitulo findByCpfPassaporte(String cpfPassaporte);

}
