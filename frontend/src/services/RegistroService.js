import Properties from "../properties";
import ApiService from "./shared/ApiService";

const RegistroService = {
  /**
   * Serviço de acesso.
   *
   * Dado o recurso de acesso: CPF ou Passaporte,
   * verifica se o usuário existe, trazendo-o caso
   * positivo, e trazendo um objeto PessoaDTO vazio em
   * caso negativo.
   */
  registrar: registroResource => {
    return ApiService.postWithBlock(`${Properties.BASE_URL}/api/registrar`, registroResource);
  },
  
  geraRemessa: () => {
    return ApiService.getWithBlock(`${Properties.BASE_URL}/banco/gera-remessa`, true);
  },

  renovar: registroResource => {
    return ApiService.postWithBlock(`${Properties.BASE_URL}/api/renovar`, registroResource);
  }
};

export default RegistroService;
