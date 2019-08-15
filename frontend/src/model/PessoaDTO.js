export const SexoDTO = {
  masculino: 0,
  feminino: 1
};

export const ZonaLocalizacaoDTO = {
  urbana: 0,
  rural: 1
};

export const PessoaDTO = {
  estrangeiro: false,
  nome: null,
  cpf: null,
  passaporte: null,
  dataNascimento: null,
  dataNascimentoString: null,
  sexo: null,
  nomeMae: null,
  email: null,
  confirmarEmail: null,
  enderecoPrincipal: {
    tipo: null,
    zonaLocalizacao: ZonaLocalizacaoDTO.urbana,
    semNumero: null,
    logradouro: null,
    numero: null,
    bairro: null,
    complemento: null,
    cep: null,
    uf: null,
    municipio: null,
    municipioNome: null,
    descricaoAcesso: null
  },
  enderecoCorrespondencia: {
    tipo: null,
    zonaLocalizacao: ZonaLocalizacaoDTO.urbana,
    semNumero: null,
    logradouro: null,
    numero: null,
    bairro: null,
    complemento: null,
    cep: null,
    uf: null,
    municipio: null,
	municipioNome: null,
    descricaoAcesso: null
  }
};
