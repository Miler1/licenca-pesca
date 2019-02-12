import Properties from "../properties";
import ApiService from "./shared/ApiService";

const ArquivoService = {
  /**
   * Serviço de upload para arquivos.
  */
  upload: arquivo =>
    ApiService.postWithBlock( '${Properties.BASE_URL}/upload-retorno', arquivo),

  download: idRemessa =>
    ApiService.download( '${Properties.BASE_URL}/download-remessa', idRemessa),
  /**
   * Serviço para listar da remessa
  */
  listarRemessa: protocolo =>
    ApiService.query(`${Properties.BASE_URL}/api/lista-remessa`, { protocolo })
};

export default ArquivoService;