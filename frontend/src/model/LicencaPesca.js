export const ZonaLocalizacaoDTO = {
  urbana: 0,
  rural: 1
};

export const licencaPesca = {
  pessoa: {
    estrangeiro: false,
    nome: null,
    cpf: null,
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
    }
  }
};
