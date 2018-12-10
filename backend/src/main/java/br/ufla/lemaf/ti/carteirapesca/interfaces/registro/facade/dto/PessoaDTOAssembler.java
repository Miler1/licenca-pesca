package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import lombok.var;
import main.java.br.ufla.lemaf.beans.pessoa.*;

import java.util.List;

/**
 * Classe Assembler para PessoaDTO.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class PessoaDTOAssembler {

	/**
	 * Método de Assembler do DTO.
	 *
	 * @param pessoa Entidade de {@link Pessoa}
	 * @return DTO de Pessoa
	 */
	public PessoaDTO toDTO(final Pessoa pessoa) {
		if (pessoa == null) return null;

		return new PessoaDTO(
			pessoa.estrangeiro,
			pessoa.nome,
			pessoa.cpf,
			setPassaporte(pessoa.passaporte),
			pessoa.dataNascimento,
			setSexo(pessoa.sexo),
			pessoa.nomeMae,
			setEmail(pessoa.contatos),
			searchEndereco(pessoa.enderecos, Constants.ENDERECO_PRINCIPAL),
			searchEndereco(pessoa.enderecos, Constants.ENDERECO_CORRESPONDENCIA)
		);
	}

	/**
	 * Setter de passaporte.
	 *
	 * @param passaporte O objeto de passaporte
	 * @return O string de passaporte
	 */
	private static String setPassaporte(Object passaporte) {
		if (passaporte == null) return null;

		return passaporte.toString();
	}

	/**
	 * Setter de sexo.
	 *
	 * @param sexo O objeto {@link Sexo}
	 * @return O código do sexo
	 */
	private static Integer setSexo(final Sexo sexo) {
		if (sexo == null) return null;

		return sexo.codigo;
	}

	/**
	 * Busca o Email do objeto {@link Pessoa}.
	 *
	 * @param contatos O Objeto {@link Contato}
	 * @return A string de email
	 */
	private static String setEmail(List<Contato> contatos) {
		if (contatos == null || contatos.isEmpty()) return null;

		var contato = contatos
			.stream()
			.filter(cont -> cont.tipo.id.equals(Constants.EMAIL))
			.findFirst();

		return contato.isPresent()
			? contato.get().valor
			: null;

	}

	/**
	 * Busca o endereco dado teu tipo.
	 *
	 * @param enderecos    Lista de enderecos
	 * @param tipoEndereco Tipo de endereco
	 * @return O EnderecoDTO
	 */
	private static EnderecoDTO searchEndereco(List<Endereco> enderecos, Integer tipoEndereco) {
		if (enderecos == null || enderecos.isEmpty()) return null;

		return toEnderecoDTO(enderecos.get(tipoEndereco));
	}

	/**
	 * Assembler do DTO de Endereco.
	 *
	 * @param endereco Entidade de {@link Endereco}
	 * @return DTO de endereco.
	 */
	private static EnderecoDTO toEnderecoDTO(final Endereco endereco) {
		if (endereco == null) return null;

		return new EnderecoDTO(
			setTipo(endereco.tipo),
			setZonaLocalizacao(endereco.zonaLocalizacao),
			endereco.semNumero,
			endereco.logradouro,
			endereco.numero,
			endereco.bairro,
			endereco.complemento,
			endereco.cep,
			setUF(endereco.municipio),
			setMunicipio(endereco.municipio),
			setDescricaoAcesso(endereco.descricaoAcesso)
		);

	}

	/**
	 * Setter do tipo de Endereço.
	 *
	 * @param tipoEndereco {@link TipoEndereco}
	 * @return tipo inteiro do endereço
	 */
	private static Integer setTipo(final TipoEndereco tipoEndereco) {
		if (tipoEndereco == null) return null;

		return tipoEndereco.id;
	}

	/**
	 * Setter do tipo de Zona de Localização.
	 *
	 * @param zonaLocalizacao {@link ZonaLocalizacao}
	 * @return Inteiro do tipo de Localização
	 */
	private static Integer setZonaLocalizacao(final ZonaLocalizacao zonaLocalizacao) {
		if (zonaLocalizacao == null) return null;

		return zonaLocalizacao.codigo;
	}

	/**
	 * Setter do código da Unidade Federativa.
	 *
	 * @param municipio O {@link Municipio}
	 * @return O código do UF
	 */
	private static String setUF(final Municipio municipio) {
		if (municipio == null || municipio.estado == null) return null;

		return municipio.estado.sigla;
	}

	/**
	 * Busca o código do IBGE.
	 *
	 * @param municipio {@link Municipio}
	 * @return Integer do código do IBGE
	 */
	private static Integer setMunicipio(final Municipio municipio) {
		if (municipio == null) return null;

		return municipio.codigoIbge;
	}

	/**
	 * Retorna o valor de descrição de acesso.
	 *
	 * @param descricaoAcesso {@link Object} descricaoAcesso
	 * @return String de descrição de acesso
	 */
	private static String setDescricaoAcesso(final Object descricaoAcesso) {
		if (descricaoAcesso == null) return null;

		return descricaoAcesso.toString();
	}

}
