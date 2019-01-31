package br.ufla.lemaf.ti.carteirapesca.domain.repository;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.EnderecoEstrangeiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoEstrangeiroRepository extends JpaRepository<EnderecoEstrangeiro, Integer> {
}
