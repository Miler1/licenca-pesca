package br.ufla.lemaf.ti.carteirapesca.interfaces.cadastro.facade.dto.pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * DTO de Pessoa.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class PessoaDTO {

	private Boolean estrangeiro;

	private UsuarioDTO usuario;

	private String nome;

	private String cpf;

	private Date dataNascimento;

	private SexoDTO sexo;

	private String nomeMae;

	private String naturalidade;

	private Boolean isUsuario;

	private List<EnderecoDTO> enderecos;

}
