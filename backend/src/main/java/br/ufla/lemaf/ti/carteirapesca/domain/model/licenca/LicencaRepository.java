package br.ufla.lemaf.ti.carteirapesca.domain.model.licenca;

import java.util.List;

/**
 * Repository de Licença.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public interface LicencaRepository {

	/**
	 * Busca uma licença pelo seu protocolo.
	 *
	 * @param protocolo O número de protocolo da licença
	 * @return A licença
	 */
	Licenca find(Protocolo protocolo);

	/**
	 * @return Um Protocolo único
	 */
	Protocolo nextProtocolo();

	/**
	 * Salva uma licença.
	 *
	 * @param licenca A licença
	 */
	void store(Licenca licenca);

	/**
	 * Busca todas as licenças.
	 *
	 * @return Todas as Licencas
	 */
	List<Licenca> findAll();

	/**
	 * @return {@code true} Se alguma
	 * das licenças salvas está com status ativo
	 */
	Boolean existeLicencaAtiva();
}
