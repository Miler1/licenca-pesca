import ApiService from "./shared/ApiService";

const AcessoService = {
  /**
   * Serviço de acesso.
   *
   * Dado o recurso de acesso: CPF ou Passaporte,
   * verifica se o usuário existe, trazendo-o caso
   * positivo, e trazendo um objeto PessoaDTO vazio em
   * caso negativo.
   */
  acessar: acessoResource => ApiService.post("/api/acessar", acessoResource),

  /**
   * Serviço que busca os munícípios de cada UF.
   */
  fetchMunicipios: uf => ApiService.get("api/municipios", uf),

  /**
   * Serviço que busca os UFs.
   */
  fetchUfs: () => ApiService.get("api/ufs")
};

export default AcessoService;
