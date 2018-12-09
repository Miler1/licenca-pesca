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
    semNumero: null,
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
    semNumero: null,
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
    estrangeiro: solicitante.estrangeiro,
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
      semNumero: null,
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
    semNumero: endereco.semNumero,
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
