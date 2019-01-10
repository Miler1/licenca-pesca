import Properties from "../properties";
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
  acessar: acessoResource => {
    return ApiService.postWithBlock(`${Properties.BASE_URL}/api/acessar`, acessoResource);
  },

  /**
   * Serviço que busca os munícípios de cada UF.
   */
  fetchMunicipios: uf =>
    ApiService.get(`${Properties.IBGE_API}/estados/${uf}/municipios`),

  /**
   * Serviço que busca os UFs.
   */
  fetchUfs: () => ApiService.get(`${Properties.IBGE_API}/estados`)
};

export default AcessoService;
