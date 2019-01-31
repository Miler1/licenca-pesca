package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto;

import br.ufla.lemaf.ti.carteirapesca.domain.model.licenca.EnderecoEstrangeiro;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.CPFUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

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

	private Date dataNascimento;

	private Integer sexo;

	private String nomeMae;

	private String email;

	private EnderecoDTO enderecoPrincipal;

	private EnderecoDTO enderecoCorrespondencia;

	@Setter
	private EnderecoEstrangeiro enderecoEstrangeiro;

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
	          @JsonProperty("enderecoCorrespondencia") final EnderecoDTO enderecoCorrespondencia,
	          @JsonProperty("enderecoEstrangeiro") final EnderecoEstrangeiro enderecoEstrangeiro) {
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
		this.enderecoEstrangeiro = enderecoEstrangeiro;
	}

	/**
	 * Construtor.
	 * <p>
	 * Usado para criar PessoaDTO com CPF formatado.
	 *
	 * @param pessoa A Pessoa
	 */
	public PessoaDTO(final PessoaDTO pessoa) {
		this.estrangeiro = pessoa.estrangeiro;
		this.nome = pessoa.nome;
		this.cpf = (pessoa.getCpf() != null ? CPFUtils.unformat(pessoa.getCpf()) : null);
		this.passaporte = pessoa.passaporte;
		setDataNascimento(pessoa.dataNascimento);
		this.sexo = pessoa.sexo;
		this.nomeMae = pessoa.nomeMae;
		this.email = pessoa.email;
		this.enderecoPrincipal = pessoa.enderecoPrincipal;
		this.enderecoCorrespondencia = pessoa.enderecoCorrespondencia;
		this.enderecoEstrangeiro = pessoa.enderecoEstrangeiro;
	}

	public PessoaDTO(String cpf, String passaporte) {
		this.cpf = (cpf != null ? CPFUtils.unformat(cpf) : null);
		this.passaporte = passaporte;
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

	public PessoaEUDTO toPessoaEUDTO(){
		return new PessoaEUDTO(this);
	}

	public EnderecoEstrangeiro getEnderecoEstrangeiro() {
		return enderecoEstrangeiro;
	}
}
