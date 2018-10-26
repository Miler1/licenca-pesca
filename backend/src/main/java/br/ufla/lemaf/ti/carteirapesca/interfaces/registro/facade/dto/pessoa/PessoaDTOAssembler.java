package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.pessoa;

import main.java.br.ufla.lemaf.beans.pessoa.Contato;
import main.java.br.ufla.lemaf.beans.pessoa.Endereco;
import main.java.br.ufla.lemaf.beans.pessoa.Pessoa;

/**
 * Classe Assembler para PessoaDTO.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
public class PessoaDTOAssembler {

	private static final Integer ID_EMAIL = 1;
	private static final Integer ID_ENDERECO_PRINCIPAL = 1;
	private static final Integer ID_ENDERECO_CORRESPONDENCIA = 2;

	/**
	 * Método de Assembler do DTO.
	 *
	 * @param pessoa Entidade de {@link Pessoa}
	 * @return DTO de Pessoa
	 */
	public PessoaDTO toDTO(final Pessoa pessoa) {

		return new PessoaDTO(
			pessoa.estrangeiro,
			pessoa.nome,
			pessoa.cpf,
			pessoa.passaporte == null
				? null
				: pessoa.passaporte.toString(),
			pessoa.dataNascimento,
			pessoa.sexo == null
				? null
				: new SexoDTO(
				pessoa.sexo.nome,
				pessoa.sexo.codigo,
				pessoa.sexo.descricao
			),
			pessoa.nomeMae,
			pessoa.contatos == null
				? null
				: toContatoDTO(pessoa.contatos.get(ID_EMAIL)),
			pessoa.isUsuario,
			pessoa.enderecos == null
				? null
				: toEnderecoDTO(pessoa.enderecos.get(ID_ENDERECO_PRINCIPAL)),
			pessoa.enderecos == null
				? null
				: toEnderecoDTO(pessoa.enderecos.get(ID_ENDERECO_CORRESPONDENCIA))
		);
	}

	/**
	 * Assembler do DTO de contato.
	 *
	 * @param contato Entidade de {@link Contato}
	 * @return DTO de Contato
	 */
	private static ContatoDTO toContatoDTO(final Contato contato) {

		return new ContatoDTO(
			contato.id,
			contato.tipo == null
				? null
				: new TipoContatoDTO(
				contato.tipo.id,
				contato.tipo.descricao
			),
			contato.valor,
			contato.principal
		);

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
			endereco.tipo.id,
			endereco.zonaLocalizacao == null
				? null
				: new ZonaLocalizacaoDTO(
				endereco.zonaLocalizacao.nome,
				endereco.zonaLocalizacao.codigo,
				endereco.zonaLocalizacao.descricao
			),
			endereco.pais == null
				? null
				: new PaisDTO(
				endereco.pais.id,
				endereco.pais.nome,
				endereco.pais.sigla
			),
			endereco.semNumero,
			endereco.logradouro,
			endereco.numero,
			endereco.bairro,
			endereco.complemento,
			endereco.cep,
			endereco.municipio == null
				? null
				: new MunicipioDTO(
				endereco.municipio.id,
				endereco.municipio.nome,
				endereco.municipio.estado == null
					? null
					: new EstadoDTO(
					endereco.municipio.estado.id,
					endereco.municipio.estado.sigla,
					endereco.municipio.estado.nome
				),
				endereco.municipio.codigoIbge
			)
		);

	}

}
