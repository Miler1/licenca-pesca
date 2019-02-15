package br.ufla.lemaf.ti.carteirapesca.domain.repository.banco;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.MotivoOcorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoOcorrenciaRepository extends JpaRepository<MotivoOcorrencia, Integer> {

	@Query(value = "SELECT * " +
		"FROM carteira_pesca.motivo_ocorrencia mc " +
		"  INNER JOIN carteira_pesca.ocorrencia o " +
		"    ON mc.id_ocorrencia = o.id " +
		"WHERE o.codigo = :codigoOcorrencia " +
		"  AND mc.num_codigo_motivo = :codigoMotivo", nativeQuery = true)
	MotivoOcorrencia buscaPorOcorrenciaEMotivo(Integer codigoOcorrencia, Integer codigoMotivo);

}
