import Properties from "../properties";
import ApiService from "./shared/ApiService";

const ConsultaService = {
  /**
   * Serviço para consultar uma licença
   * dado o protocolo.
   */
  consultar: protocolo =>
    ApiService.query(`${Properties.BASE_URL}/api/consultar`, { protocolo })
};

export default ConsultaService;
