package br.ufla.lemaf.ti.carteirapesca.domain.repository.banco;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.TipoValorEfetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoValorEfetivoRepository extends JpaRepository<TipoValorEfetivo, Integer> {

	TipoValorEfetivo findByCodigo(Integer codigo);

}
