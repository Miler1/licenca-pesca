import Properties from "../properties";
import ApiService from "./shared/ApiService";

const ArquivoService = {
  /**
   * Serviço de upload para arquivos.
  */
  upload: arquivo =>
    ApiService.postWithBlock( '${Properties.BASE_URL}/upload-retorno', arquivo),

  downloadArquivoRemessa: idRemessa =>
    ApiService.getWithBlock( '${Properties.BASE_URL}/download-remessa', idRemessa)
};

export default ArquivoService;