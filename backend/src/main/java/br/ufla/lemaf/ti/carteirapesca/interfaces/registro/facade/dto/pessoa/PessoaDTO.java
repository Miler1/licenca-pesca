package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto.pessoa;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

/**
 * DTO de Pessoa.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
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
	 * @param estrangeiro             {@code true} se for estrangeiro.
	 * @param nome                    O nome da pessoa.
	 * @param cpf                     O CPF da pessoa.
	 * @param passaporte              O passaporte da pessoa.
	 * @param dataNascimento          A data de nascimento da pessoa.
	 * @param sexo                    O sexo da pessoa.
	 * @param nomeMae                 O nome da mãe.
	 * @param contato                 O contato da pessoa.
	 * @param isUsuario               {@code true} se pessoa for um usuario.
	 * @param enderecoPrincipal       O Endereço principal da passoa.
	 * @param enderecoCorrespondencia O Endereço de correspondência.
	 */
	@JsonCreator
	PessoaDTO(final Boolean estrangeiro,
	          final String nome,
	          final String cpf,
	          final String passaporte,
	          final Date dataNascimento,
	          final SexoDTO sexo,
	          final String nomeMae,
	          final ContatoDTO contato,
	          final Boolean isUsuario,
	          final EnderecoDTO enderecoPrincipal,
	          final EnderecoDTO enderecoCorrespondencia) {
		this.estrangeiro = estrangeiro;
		this.nome = nome;
		this.cpf = cpf;
		this.passaporte = passaporte;
		setDataNascimento(dataNascimento);
		this.sexo = sexo;
		this.nomeMae = nomeMae;
		this.contato = contato;
		this.isUsuario = isUsuario;
		this.enderecoPrincipal = enderecoPrincipal;
		this.enderecoCorrespondencia = enderecoCorrespondencia;
	}

	/**
	 * Getter se é estrangeiro.
	 *
	 * @return Se é estrangeiro
	 */
	public Boolean getEstrangeiro() {
		return estrangeiro;
	}

	/**
	 * Getter do nome.
	 *
	 * @return O nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Getter do cpf.
	 *
	 * @return O cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Getter do passaporte.
	 *
	 * @return O passaporte
	 */
	public String getPassaporte() {
		return passaporte;
	}

	/**
	 * Getter do data de nascimento.
	 *
	 * @return O data de nascimento
	 */
	public Date getDataNascimento() {
		return this.dataNascimento == null
			? null
			: new Date(dataNascimento.getTime());
	}

	/**
	 * Getter do sexo.
	 *
	 * @return O sexo
	 */
	public SexoDTO getSexo() {
		return sexo;
	}

	/**
	 * Getter do nome da mae.
	 *
	 * @return O nome da mae
	 */
	public String getNomeMae() {
		return nomeMae;
	}

	/**
	 * Getter do contato.
	 *
	 * @return O contato
	 */
	public ContatoDTO getContato() {
		return contato;
	}

	/**
	 * Getter do usuario.
	 *
	 * @return O usuario
	 */
	public Boolean getUsuario() {
		return isUsuario;
	}

	/**
	 * Getter do endereco principal.
	 *
	 * @return O endereco principal
	 */
	public EnderecoDTO getEnderecoPrincipal() {
		return enderecoPrincipal;
	}

	/**
	 * Getter do endereco correspondencia.
	 *
	 * @return O endereco correspondencia
	 */
	public EnderecoDTO getEnderecoCorrespondencia() {
		return enderecoCorrespondencia;
	}

	/**
	 * Setter de data de nascimento. Evita erros de vunerabilidade já
	 * que o campo de Data é mutável.
	 *
	 * @param dataNascimento A data de nascimento.
	 */
	private void setDataNascimento(Date dataNascimento) {
		if (dataNascimento == null) {
			this.dataNascimento = null;
		} else {
			this.dataNascimento = new Date(dataNascimento.getTime());
		}
	}
}
