import Properties from "../properties";
import ApiService from "./shared/ApiService";

const ArquivoService = {
  /**
   * Serviço de upload para arquivos.
  */
  upload: multipartFile =>
    ApiService.postWithBlock(`${Properties.BASE_URL}/api/upload-retorno/` + multipartFile)
  };

export default ArquivoService;