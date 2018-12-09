import ApiService from "./shared/ApiService";
import Properties from "../properties";

const InformacoesComplementaresService = {
  /**
   * Serviço que busca as modalidades de pesca.
   */
  fetchModalidadePesca: () =>
    ApiService.get(`${Properties.BASE_URL}/api/modalidade-pesca`),

  /**
   * Serviço que busca a localização preferencial de pesca.
   */
  fetchLocalizacaoPreferencialPesca: () =>
    ApiService.get(`${Properties.BASE_URL}/api/localizacao-pesca`),

  /**
   * Serviço que busca a renda mensal.
   */
  fetchRendaMensal: () =>
    ApiService.get(`${Properties.BASE_URL}/api/renda-mensal`),

  /**
   * Serviço que busca a faixa etaria.
   */
  fetchFaixaEtaria: () =>
    ApiService.get(`${Properties.BASE_URL}/api/faixa-etaria`),

  /**
   * Serviço que busca o local de pesca.
   */
  fetchLocalPesca: () =>
    ApiService.get(`${Properties.BASE_URL}/api/local-pesca`),

  /**
   * Serviço que busca o material de pesca.
   */
  fetchMaterialPesca: () =>
    ApiService.get(`${Properties.BASE_URL}/api/material-pesca`),

  /**
   * Serviço que busca o tipo de isca.
   */
  fetchTipoIsca: () => ApiService.get(`${Properties.BASE_URL}/api/tipo-isca`)
};

export default InformacoesComplementaresService;
