import Properties from "../properties";
import ApiService from "./shared/ApiService";

const ArquivoService = {
  /**
   * Serviço de upload para arquivos.
  */
  upload: arquivo =>
    ApiService.postWithBlock(`${Properties.BASE_URL}/api/upload-retorno/` + arquivo)
  };

export default ArquivoService;