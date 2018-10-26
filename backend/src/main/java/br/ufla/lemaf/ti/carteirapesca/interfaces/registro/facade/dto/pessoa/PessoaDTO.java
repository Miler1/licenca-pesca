package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

/**
 * DTO de Pessoa.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class PessoaDTO extends ResourceSupport {

	private Boolean estrangeiro;

	private String nome;

	private String cpf;

	private String passaporte;

	private Date dataNascimento;

	private SexoDTO sexo;

	private String nomeMae;

	private ContatoDTO contato;

	private Boolean isUsuario;

	private EnderecoDTO enderecoPrincipal;

	private EnderecoDTO enderecoCorrespondencia;

	/**
	 * Construtor.
	 *
	 * @param estrangeiro    {@code true} se for estrangeiro.
	 * @param nome           O nome da pessoa.
	 * @param cpf            O CPF da pessoa.
	 * @param dataNascimento A data de nascimento da pessoa.
	 * @param sexo           O sexo da pessoa.
	 * @param nomeMae        O nome da mãe.
	 * @param contato        O contato da pessoa.
	 * @param isUsuario      {@code true} se pessoa for um usuario.
	 */
	public PessoaDTO(final Boolean estrangeiro,
	                 final String nome,
	                 final String cpf,
	                 final Date dataNascimento,
	                 final SexoDTO sexo,
	                 final String nomeMae,
	                 final ContatoDTO contato,
	                 final Boolean isUsuario) {
		this.estrangeiro = estrangeiro;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.nomeMae = nomeMae;
		this.contato = contato;
		this.isUsuario = isUsuario;
	}
}
