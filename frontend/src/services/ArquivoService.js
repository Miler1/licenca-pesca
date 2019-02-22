import Properties from "../properties";
import ApiService from "./shared/ApiService";

const ArquivoService = {
  /**
   * Serviço de upload para arquivos.
  */
  upload: multipartFile =>
    ApiService.upload( `${Properties.BASE_URL}/upload-retorno`, multipartFile),
  };

export default ArquivoService;