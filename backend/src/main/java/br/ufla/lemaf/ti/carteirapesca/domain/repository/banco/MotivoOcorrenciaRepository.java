package br.ufla.lemaf.ti.carteirapesca.domain.repository.banco;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.MotivoOcorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoOcorrenciaRepository extends JpaRepository<MotivoOcorrencia, Integer> {

	@Query(value = "", nativeQuery = true)
	MotivoOcorrencia buscaPorOcorrenciaEMotivo(String codigoOcorrencia, String codigoMotivo);

}
