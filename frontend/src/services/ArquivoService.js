import Properties from "../properties";
import ApiService from "./shared/ApiService";

const ArquivoService = {
  /**
   * Serviço de upload para arquivos.
   */
//     buscarLicencas: validacaoDTO => {
//     return ApiService.postWithBlock(`${Properties.BASE_URL}/api/buscarLicencas`, validacaoDTO, false);
//     },

  upload: arquivo => {
   
   return ApiService.postWithBlock( '${Properties.BASE_URL}/arquivo/upload', arquivo);
  }
};

export default ArquivoService;
