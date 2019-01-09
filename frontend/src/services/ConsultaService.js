import Properties from "../properties";
import ApiService from "./shared/ApiService";

const ConsultaService = {
  /**
   * Serviço para consultar uma licença
   * dado o protocolo.
   */
  consultar: protocolo =>
    ApiService.query(`${Properties.BASE_URL}/api/consultar`, { protocolo }),

  /**
   * Serviço para download do boleto
   * dado o protocolo.
   */
  buscarBoleto: protocolo =>
    ApiService.query(`${Properties.BASE_URL}/api/boleto`, { protocolo }),

  /**
   * Serviço para dawnload da carteira
   * dado o protocolo.
   */
  buscarCarteira: protocolo =>
    ApiService.query(`${Properties.BASE_URL}/api/carteira`, { protocolo }),

  /**
   * Serviço para buscar dados da carteira
   * dado o protocolo.
   */
  buscarDadosCarteira: protocolo =>
    ApiService.query(`${Properties.BASE_URL}/api/buscar`, { protocolo })
};

export default ConsultaService;
