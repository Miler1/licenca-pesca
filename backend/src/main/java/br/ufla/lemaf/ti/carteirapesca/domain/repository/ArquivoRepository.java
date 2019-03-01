package br.ufla.lemaf.ti.carteirapesca.domain.repository;

import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.Arquivo;
import br.ufla.lemaf.ti.carteirapesca.domain.model.Arquivo.TipoArquivo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Integer> {

	Arquivo findByNome(String nomeArquivo);


	@Query(value = "SELECT * " +
		"FROM carteira_pesca.arquivo " +
		"WHERE id_tipo_arquivo = 3 " +
		"ORDER BY dt_cadastro DESC ", nativeQuery = true)
	Page<Arquivo> findByTipoArquivoOrderBy(TipoArquivo tipoArquivo, Pageable pageable);

}
