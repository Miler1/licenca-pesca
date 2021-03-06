package br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.facade;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Licenca;
import br.ufla.lemaf.ti.carteirapesca.domain.model.solicitante.Solicitante;
import br.ufla.lemaf.ti.carteirapesca.interfaces.acesso.web.AcessoResource;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.PessoaDTO;
import br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.ValidacaoDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Serviço de Facade de Acesso da camada de interface.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public interface AcessoServiceFacade {

	/**
	 * Facade de acesso.
	 * <p>
	 * Converte e encapsula os dados da camada de interface,
	 * bem como valida-os.
	 * <p>
	 * O Serviço {@code #acessar()} do facade da interface de
	 * acesso tem como principal objetivo, garantir a integridade
	 * dos dados oriundos da controller e convertê-los na linguagem
	 * em que a camada de application possa entender. Ou seja,
	 * dado o {@link AcessoResource}, valida cada um de seus parâmetros
	 * e ao receber o dado da service de application no formato
	 * {@link br.ufla.lemaf.beans.pessoa.Pessoa} converte-o
	 * em {@link PessoaDTO} com o
	 * {@link br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.PessoaDTOAssembler}.
	 *
	 * @return A DTO de Pessoa.
	 */
	PessoaDTO acessar(AcessoResource acessoResource);

	List<Licenca> buscarLicencasPorPessoaDTO(PessoaDTO pessoa) throws Exception;

	Boolean validaDadosAcessoLicencas(ValidacaoDTO validacaoDTO) throws Exception;

	Boolean solicitanteBloqueado(AcessoResource acessoResource) throws Exception;

}
