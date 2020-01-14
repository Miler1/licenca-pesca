package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto;

import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.Constants;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.utils.WebServiceUtils;
import br.ufla.lemaf.ti.carteirapesca.infrastructure.webservices.CadastroUnificadoService;
import br.ufla.lemaf.ti.carteirapesca.interfaces.shared.exception.ResourceNotFoundException;
import lombok.var;
import br.ufla.lemaf.beans.pessoa.*;

import java.util.ArrayList;
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
			null,
			setSexo(pessoa.sexo),
			pessoa.nomeMae,
			setEmail(pessoa.contatos),
			searchEndereco(pessoa.enderecos, Constants.ENDERECO_PRINCIPAL),
			searchEndereco(pessoa.enderecos, Constants.ENDERECO_CORRESPONDENCIA),
			null
		);
	}

	/**
	 * Constroi o objeto Pessoa que é usado no Entrada Unica.
	 *
	 * @param pessoaDTO O DTO de Pessoa
	 * @return A Pessoa
	 */
//	public Pessoa toPessoa(final PessoaDTO pessoaDTO) {
//		if (pessoaDTO == null) return null;
//
//		var pessoa = new Pessoa();
//		pessoa.estrangeiro = pessoaDTO.getEstrangeiro();
//		pessoa.cpf = pessoaDTO.getCpf();
//		pessoa.nome = pessoaDTO.getNome();
//		pessoa.passaporte = pessoaDTO.getPassaporte();
//		pessoa.dataNascimento = pessoaDTO.getDataNascimento();
//
//		pessoa.sexo = new Sexo();
//		if (pessoaDTO.getSexo().equals(Constants.MASCULINO)) {
//			pessoa.sexo.codigo = Constants.MASCULINO;
//			pessoa.sexo.nome = "Masculino";
//		} else if (pessoaDTO.getSexo().equals(Constants.FEMININO)) {
//			pessoa.sexo.codigo = Constants.FEMININO;
//			pessoa.sexo.nome = "Feminino";
//		} else {
//			pessoa.sexo.codigo = 2;
//			pessoa.sexo.nome = "Outro";
//		}
//
//		pessoa.nomeMae = pessoaDTO.getNomeMae();
//
//		pessoa.contatos = new ArrayList<>();
//
//		var contato = new Contato();
//		contato.principal = true;
//		contato.tipo = new TipoContato();
//		contato.tipo.id = TipoContato.ID_EMAIL;
//		contato.valor = pessoaDTO.getEmail();
//
//		pessoa.contatos.add(contato);
//
//		pessoa.enderecos = new ArrayList<>();
//		if (enderecoExiste(pessoaDTO.getEnderecoPrincipal())) {
//
//			pessoa.enderecos.add(toEndereco(pessoaDTO.getEnderecoPrincipal()));
//
//		}
//
//		if (enderecoExiste(pessoaDTO.getEnderecoCorrespondencia())) {
//
//			pessoa.enderecos.add(toEndereco(pessoaDTO.getEnderecoCorrespondencia()));
//
//		} else {
//
//			var endereco = toEndereco(pessoaDTO.getEnderecoPrincipal());
//			endereco.tipo.id = Constants.ENDERECO_CORRESPONDENCIA;
//			endereco.zonaLocalizacao.codigo = Constants.ZONA_URBANA;
//			endereco.zonaLocalizacao.nome = "Urbana";
//
//			pessoa.enderecos.add(endereco);
//
//		}
//
//		return pessoa;
//	}

	public Pessoa toPessoa(final PessoaEUDTO pessoaDTO) {
		if (pessoaDTO == null) return null;

		var pessoa = new Pessoa();
		pessoa.estrangeiro = pessoaDTO.getEstrangeiro();
		pessoa.cpf = pessoaDTO.getCpf();
		pessoa.nome = pessoaDTO.getNome();
		pessoa.passaporte = pessoaDTO.getPassaporte();
		pessoa.dataNascimento = pessoaDTO.getDataNascimento();

		pessoa.sexo = new Sexo();
		if (pessoaDTO.getSexo().equals(Constants.MASCULINO)) {
			pessoa.sexo.codigo = Constants.MASCULINO;
			pessoa.sexo.nome = "Masculino";
		} else if (pessoaDTO.getSexo().equals(Constants.FEMININO)) {
			pessoa.sexo.codigo = Constants.FEMININO;
			pessoa.sexo.nome = "Feminino";
		} else {
			pessoa.sexo = null;
		}

		pessoa.nomeMae = pessoaDTO.getNomeMae();

		pessoa.contatos = new ArrayList<>();

		var contato = new Contato();
		contato.principal = true;
		contato.tipo = new TipoContato();
		contato.tipo.id = TipoContato.ID_EMAIL;
		contato.valor = pessoaDTO.getEmail();

		pessoa.contatos.add(contato);

		pessoa.enderecos = new ArrayList<>();
		if (enderecoExiste(pessoaDTO.getEnderecoPrincipal())) {

			pessoa.enderecos.add(toEndereco(pessoaDTO.getEnderecoPrincipal()));

		}

		if (enderecoExiste(pessoaDTO.getEnderecoCorrespondencia())) {
			Endereco endereco = toEndereco(pessoaDTO.getEnderecoPrincipal());
			endereco.tipo.id =  Constants.ENDERECO_PRINCIPAL;
			pessoa.enderecos.add(endereco);
			Endereco endereco2 = toEndereco(pessoaDTO.getEnderecoCorrespondencia());
			endereco2.tipo.id = Constants.ENDERECO_CORRESPONDENCIA;
			pessoa.enderecos.add(endereco2);

		} else {

			var endereco = toEndereco(pessoaDTO.getEnderecoPrincipal());
			endereco.tipo.id = Constants.ENDERECO_CORRESPONDENCIA;
			endereco.zonaLocalizacao.codigo = Constants.ZONA_URBANA;
			endereco.zonaLocalizacao.nome = "Urbana";

			pessoa.enderecos.add(endereco);

		}

		return pessoa;
	}

	/**
	 * Constrói o objeto Endereço usado no Entrada Unica.
	 *
	 * @param enderecoDTO O DTO de Endereço
	 * @return O Endereço
	 */
	private static Endereco toEndereco(EnderecoDTO enderecoDTO) {
		if (enderecoDTO == null) return new Endereco();

		var endereco = new Endereco();
		endereco.tipo = new TipoEndereco();

		endereco.tipo.id = enderecoDTO.getTipo() != null
			? enderecoDTO.getTipo()
			: Constants.ENDERECO_PRINCIPAL;

		endereco.zonaLocalizacao = new ZonaLocalizacao();
		endereco.zonaLocalizacao.codigo = enderecoDTO.getZonaLocalizacao();

		if (enderecoDTO.getZonaLocalizacao().equals(Constants.ZONA_RURAL)) {
			endereco.zonaLocalizacao.nome = "Rural";
		} else {
			endereco.zonaLocalizacao.nome = "Urbana";
		}

		endereco.semNumero = enderecoDTO.getSemNumero();
		endereco.logradouro = enderecoDTO.getLogradouro();
		endereco.numero = enderecoDTO.getNumero();
		endereco.bairro = enderecoDTO.getBairro();
		endereco.complemento = enderecoDTO.getComplemento();
		endereco.cep = enderecoDTO.getCep();

		endereco.municipio = enderecoDTO.getMunicipio();
//		endereco.municipio.estado = new Estado();
//		endereco.municipio.estado.sigla = enderecoDTO.getUf();
//		endereco.municipio.codigoIbge = enderecoDTO.getMunicipio().codigoIbge;

		WebServiceUtils.validarWebService();

//		var municipios = buscarMunicipiosDoEU(enderecoDTO.getUf());
//		endereco.municipio = municipios
//			.stream()
//			.filter(municipio -> municipio.codigoIbge
//				.equals(enderecoDTO.getMunicipio()))
//			.findFirst()
//			.orElseThrow(ResourceNotFoundException::new);

		endereco.descricaoAcesso = enderecoDTO.getDescricaoAcesso();

		endereco.pais = new Pais();
		endereco.pais.id = Constants.BRASIL;

		return endereco;
	}

	private static Endereco toEndereco(EnderecoEUDTO enderecoDTO) {
		if (enderecoDTO == null) return new Endereco();

		var endereco = new Endereco();
		endereco.tipo = new TipoEndereco();

		endereco.tipo.id = enderecoDTO.getTipo() != null
			? enderecoDTO.getTipo()
			: Constants.ENDERECO_PRINCIPAL;

		endereco.zonaLocalizacao = new ZonaLocalizacao();
		endereco.zonaLocalizacao.codigo = enderecoDTO.getZonaLocalizacao();

		if (enderecoDTO.getZonaLocalizacao().equals(Constants.ZONA_RURAL)) {
			endereco.zonaLocalizacao.nome = "Rural";
		} else {
			endereco.zonaLocalizacao.nome = "Urbana";
		}

		endereco.semNumero = enderecoDTO.getSemNumero();
		endereco.logradouro = enderecoDTO.getLogradouro();
		endereco.numero = enderecoDTO.getNumero();
		endereco.bairro = enderecoDTO.getBairro();
		endereco.complemento = enderecoDTO.getComplemento();
		endereco.cep = enderecoDTO.getCep();

		endereco.municipio = new Municipio();
		endereco.municipio.estado = new Estado();
		endereco.municipio.estado.sigla = enderecoDTO.getUf();
		endereco.municipio.codigoIbge = enderecoDTO.getMunicipio();

		WebServiceUtils.validarWebService();

		var municipios = buscarMunicipiosDoEU(enderecoDTO.getUf());
		endereco.municipio = municipios
			.stream()
			.filter(municipio -> municipio.codigoIbge
				.equals(enderecoDTO.getMunicipio()))
			.findFirst()
			.orElseThrow(ResourceNotFoundException::new);

		endereco.descricaoAcesso = enderecoDTO.getDescricaoAcesso();

		endereco.pais = new Pais();
		endereco.pais.id = Constants.BRASIL;

		return endereco;
	}

	/**
	 * Busca os municípios do Entrada Unica.
	 *
	 * @param uf O UF do município
	 * @return A Lista de municípios
	 */
	private static List<Municipio> buscarMunicipiosDoEU(String uf) {

		WebServiceUtils.validarWebService();

		return CadastroUnificadoService
			.webService()
			.getMunicipiosByEstado(uf);

	}

	/**
	 * Verifica se o endereço existe.
	 *
	 * @param enderecoDTO O DTO de Endereço
	 * @return {@code true} se o endereço existe
	 */
	private static boolean enderecoExiste(EnderecoDTO enderecoDTO) {
		return enderecoDTO != null && enderecoDTO.getLogradouro() != null;
	}

	private static boolean enderecoExiste(EnderecoEUDTO enderecoDTO) {
		return enderecoDTO != null && enderecoDTO.getLogradouro() != null;
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

		var endereco = enderecos
			.stream()
			.filter(end -> end.tipo.id.equals(tipoEndereco))
			.findFirst();

		return toEnderecoDTO(endereco.isPresent() ? endereco.get() : null);
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
			endereco.municipio,
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
