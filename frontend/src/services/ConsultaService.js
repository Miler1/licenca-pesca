import Properties from "../properties";
import ApiService from "./shared/ApiService";

const ConsultaService = {
  /**
   * Serviço para consultar uma licença
   * dado o protocolo.
   */
  consultar: protocolo =>
    ApiService.query(`${Properties.BASE_URL}/api/consultar`, { protocolo }),

  
  consultarLicenca: protocolo =>
    ApiService.query(`${Properties.BASE_URL}/api/consultarCarteira`, {protocolo}),
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

  buscarRemessas: paginaAtual => {
    return ApiService.getWithBlock(`${Properties.BASE_URL}/banco/lista-remessa?page=` + (paginaAtual - 1), true);
  },
  buscarArquivosRetornos: paginaAtual => {
    return ApiService.getWithBlock(`${Properties.BASE_URL}/banco/lista-retornos?page=` + (paginaAtual - 1), true);
  },
  /**
   * Serviço para buscar dados da carteira
   * dado o protocolo.
   */
  buscarDadosCarteira: protocolo =>
    ApiService.query(`${Properties.BASE_URL}/api/informacao-carteira`, { protocolo }),

  fetchPaises: () =>
    ApiService.query(`${Properties.BASE_URL}/api/fetch-paises`)
};

export default ConsultaService;
