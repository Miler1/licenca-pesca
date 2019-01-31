package br.ufla.lemaf.ti.carteirapesca.domain.repository;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.TipoArquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoArquivoRepository extends JpaRepository<TipoArquivo, Integer> {

	TipoArquivo findByCodigo(String codigo);

}
