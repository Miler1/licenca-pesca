package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import main.java.br.ufla.lemaf.beans.pessoa.*;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.Date;

/**
 * DTO de Pessoa.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = false, doNotUseGetters = true)
public final class PessoaDTO extends ResourceSupport {

	private Boolean estrangeiro;

	private String nome;

	private String cpf;

	private String passaporte;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;

	private Integer sexo;

	private String nomeMae;

	private String email;

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
	 * @param email                   O email da pessoa.
	 * @param enderecoPrincipal       O Endereço principal da passoa.
	 * @param enderecoCorrespondencia O Endereço de correspondência.
	 */
	@JsonCreator
	PessoaDTO(@JsonProperty("estrangeiro") final Boolean estrangeiro,
	          @JsonProperty("nome") final String nome,
	          @JsonProperty("cpf") final String cpf,
	          @JsonProperty("passaporte") final String passaporte,
	          @JsonProperty("dataNascimento") final Date dataNascimento,
	          @JsonProperty("sexo") final Integer sexo,
	          @JsonProperty("nomeMae") final String nomeMae,
	          @JsonProperty("email") final String email,
	          @JsonProperty("enderecoPrincipal") final EnderecoDTO enderecoPrincipal,
	          @JsonProperty("enderecoCorrespondencia") final EnderecoDTO enderecoCorrespondencia) {
		this.estrangeiro = estrangeiro;
		this.nome = nome;
		this.cpf = cpf;
		this.passaporte = passaporte;
		setDataNascimento(dataNascimento);
		this.sexo = sexo;
		this.nomeMae = nomeMae;
		this.email = email;
		this.enderecoPrincipal = enderecoPrincipal;
		this.enderecoCorrespondencia = enderecoCorrespondencia;
	}

	/**
	 * Construtor.
	 * <p>
	 * Usado para criar PessoaDTO com CPF formatado.
	 *
	 * @param pessoa A Pessoa
	 * @param cpf    O CPF
	 */
	public PessoaDTO(final PessoaDTO pessoa, final String cpf) {
		this.estrangeiro = pessoa.estrangeiro;
		this.nome = pessoa.nome;
		this.cpf = cpf;
		this.passaporte = pessoa.passaporte;
		setDataNascimento(pessoa.dataNascimento);
		this.sexo = pessoa.sexo;
		this.nomeMae = pessoa.nomeMae;
		this.email = pessoa.email;
		this.enderecoPrincipal = pessoa.enderecoPrincipal;
		this.enderecoCorrespondencia = pessoa.enderecoCorrespondencia;
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
	public Integer getSexo() {
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
	 * Getter de email.
	 *
	 * @return O email
	 */
	public String getEmail() {
		return email;
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

	/**
	 * Converte pessoaDTO para Pessoa do cadastro únificado.
	 *
	 * @param municipios A lista de municípios
	 * @return
	 */
	public Pessoa toPessoaEU(Municipio[] municipios) {

		Pessoa pessoaEU = new Pessoa();
		pessoaEU.estrangeiro = this.estrangeiro;
		pessoaEU.cpf = this.cpf;
		pessoaEU.nome = this.nome;
		pessoaEU.passaporte = this.passaporte;
		pessoaEU.dataNascimento = this.dataNascimento;
		pessoaEU.sexo = new Sexo();

		if (this.sexo.equals(0)) {
			pessoaEU.sexo.codigo = 0;
			pessoaEU.sexo.nome = "Masculino";
		} else {
			pessoaEU.sexo.codigo = 1;
			pessoaEU.sexo.nome = "Feminino";
		}

		pessoaEU.nomeMae = this.nomeMae;
		pessoaEU.contatos = new ArrayList<>();

		// Contato e-mail
		Contato contatoEmail = new Contato();
		contatoEmail.principal = true;
		contatoEmail.tipo = new TipoContato();
		contatoEmail.tipo.id = TipoContato.ID_EMAIL;
		contatoEmail.valor = this.email;
		pessoaEU.contatos.add(contatoEmail);

		// Endereços
		pessoaEU.enderecos = new ArrayList<>();

		if (this.enderecoPrincipal != null && this.enderecoPrincipal.getLogradouro() != null) {
			pessoaEU.enderecos.add(this.enderecoPrincipal.toEnderecoEU(municipios));
		}

		if (this.enderecoCorrespondencia != null && this.enderecoCorrespondencia.getLogradouro() != null) {
			pessoaEU.enderecos.add(this.enderecoCorrespondencia.toEnderecoEU(municipios));
		}
		else {

			Endereco endereco = this.enderecoPrincipal.toEnderecoEU(municipios);
			endereco.tipo.id = 2;
			endereco.zonaLocalizacao.codigo = 0;
			endereco.zonaLocalizacao.nome = "Urbana";
			pessoaEU.enderecos.add(endereco);
		}

		return pessoaEU;
	}

	@Override
	public String toString() {
		return "PessoaDTO{"
			+ "estrangeiro=" + estrangeiro
			+ ", nome='" + nome + '\''
			+ ", cpf='" + cpf + '\''
			+ ", passaporte='" + passaporte + '\''
			+ ", dataNascimento=" + dataNascimento
			+ ", sexo=" + sexo
			+ ", nomeMae='" + nomeMae + '\''
			+ ", email=" + email
			+ ", enderecoPrincipal=" + enderecoPrincipal
			+ ", enderecoCorrespondencia=" + enderecoCorrespondencia
			+ '}';
	}
}
