import ApiService from "./shared/ApiService";

const AcessoService = {
  /**
   * Serviço de acesso.
   *
   * Dado o recurso de acesso: CPF ou Passaporte,
   * verifica se o usuário existe, trazendo-o caso
   * positivo, e trazendo um objeto Pessoa vazio em
   * caso negativo.
   *
   * @param acessoResource
   * @return {*|Promise<*>}
   */
  acessar: acessoResource => ApiService.post("/api/acessar", acessoResource)
};

export default AcessoService;
