package br.ufla.lemaf.ti.carteirapesca.domain.repository.banco;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Banco.Retorno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RetornoRepository extends JpaRepository<Retorno, Integer> {

	@Query(value = "SELECT nome, dt_cadastro " +
		"FROM carteira_pesca.arquivo " +
		"WHERE id_tipo_arquivo = 3",nativeQuery = true)

	Retorno buscaArquivosDeRetorno();
}
