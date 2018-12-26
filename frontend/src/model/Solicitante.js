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
    tipo: 1,
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
    tipo: 2,
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
    dataNascimento: solicitante.dataNascimento,
    sexo: solicitante.sexo,
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
  const DATE_PATTERN = new RegExp("([\\d]{2})\\/([\\d]{2})\\/([\\d]{4})");

  if (DATE_PATTERN.test(data)) {
    return data;
  } else {
    let date = new Date(data);

    return `${date.getDay()}/${date.getMonth()}/${date.getFullYear()}`;
  }
};

// const serializarData = data => {
//   return `${date.getDay()}/${date.getMonth()}/${date.getFullYear()}`;
// };
