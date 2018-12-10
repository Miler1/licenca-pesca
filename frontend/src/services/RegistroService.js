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
  registrar: registroResource =>
    ApiService.post(`${Properties.BASE_URL}/api/registrar`, registroResource)
};

export default RegistroService;
