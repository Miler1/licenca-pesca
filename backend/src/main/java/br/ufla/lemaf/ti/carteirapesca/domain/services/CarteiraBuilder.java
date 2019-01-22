package br.ufla.lemaf.ti.carteirapesca.domain.services;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.Modalidade;
import br.ufla.lemaf.ti.carteirapesca.domain.model.protocolo.Protocolo;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;

/**
 * Interface do serviço de gerar a carteira de pesca.
 *
 * @author Highlander Paiva
 * @author Marcio Azevedo
 * @since 1.0
 */
public interface CarteiraBuilder {

	/**
	 * Cria a carteira de pesca.
	 *
	 * @param protocolo O Protocolo
	 * @param modalidade A modalidade
	 * @param pessoa A PEssoa solicitante
	 * @return O caminho do arquivo da carteira
	 */
	String gerarCarteira(Protocolo protocolo, Modalidade modalidade, Pessoa pessoa);

}
