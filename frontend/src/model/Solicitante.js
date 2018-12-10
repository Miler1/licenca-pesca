const SexoDTO = {
  0: "M",
  1: "F",
  2: "O"
};

export const Solicitante = {
  estrangeiro: null,
  nome: null,
  cpf: null,
  passaporte: null,
  dataNascimento: null,
  sexo: null,
  nomeMae: null,
  email: null,
  enderecoPrincipal: {
    tipo: null,
    zonaLocalizacao: null,
    semNumero: false,
    logradouro: null,
    numero: null,
    bairro: null,
    complemento: null,
    cep: null,
    uf: null,
    municipio: null,
    descricaoAcesso: null
  },
  enderecoCorrespondencia: {
    tipo: null,
    zonaLocalizacao: null,
    semNumero: false,
    logradouro: null,
    numero: null,
    bairro: null,
    complemento: null,
    cep: null,
    uf: null,
    municipio: null,
    descricaoAcesso: null
  }
};

export const toSolicitanteDTO = solicitante => {
  return {
    estrangeiro:
      solicitante.estrangeiro === null ? false : solicitante.estrangeiro,
    nome: solicitante.nome,
    cpf: solicitante.cpf,
    passaporte: solicitante.passaporte,
    dataNascimento: deserializarData(solicitante.dataNascimento),
    sexo: toSexoDTO(solicitante.sexo),
    nomeMae: solicitante.nomeMae,
    email: solicitante.email,
    enderecoPrincipal: toEndereco(solicitante.enderecoPrincipal),
    enderecoCorrespondencia: toEndereco(solicitante.enderecoCorrespondencia)
  };
};

const toEndereco = endereco => {
  if (endereco === null) {
    return {
      tipo: null,
      zonaLocalizacao: null,
      semNumero: false,
      logradouro: null,
      numero: null,
      bairro: null,
      complemento: null,
      cep: null,
      uf: null,
      municipio: null,
      descricaoAcesso: null
    };
  }

  return {
    tipo: endereco.tipo,
    zonaLocalizacao: endereco.zonaLocalizacao,
    semNumero: endereco.semNumero == null ? false : endereco.semNumero,
    logradouro: endereco.logradouro,
    numero: endereco.numero,
    bairro: endereco.bairro,
    complemento: endereco.complemento,
    cep: endereco.cep,
    uf: endereco.uf,
    municipio: endereco.municipio,
    descricaoAcesso: endereco.descricaoAcesso
  };
};

const toSexoDTO = sexo => SexoDTO[sexo];

const deserializarData = data => {
  let date = new Date(data);

  return `${date.getDay()}/${date.getMonth()}/${date.getFullYear()}`;
};
