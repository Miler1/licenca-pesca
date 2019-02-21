package br.ufla.lemaf.ti.carteirapesca.domain.repository.banco;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.TipoArrecadacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoArrecadacaoRepository extends JpaRepository<TipoArrecadacao, Integer> {

	TipoArrecadacao findByCodigo(String codigo);

}
