import Properties from "../properties";
import ApiService from "./shared/ApiService";

const ArquivoService = {
  /**
   * Serviço de upload para arquivos.
  */
  upload: arquivo =>
    ApiService.postWithBlock( '${Properties.BASE_URL}/arquivo/upload', arquivo),
  /**
   * Serviço para download da remessa
  */
  buscarRemessa: protocolo =>
    ApiService.query(`${Properties.BASE_URL}/api/buscarRemessa`, { protocolo })
};

export default ArquivoService;