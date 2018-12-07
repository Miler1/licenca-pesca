import ApiService from "./shared/ApiService";

const InformacoesComplementaresService = {
  /**
   * Serviço que busca as modalidades de pesca.
   */
  fetchModalidadePesca: () => ApiService.get("api/modalidade-pesca"),

  /**
   * Serviço que busca a localização preferencial de pesca.
   */
  fetchLocalizacaoPreferencialPesca: () =>
    ApiService.get("api/localizacao-pesca"),

  /**
   * Serviço que busca a renda mensal.
   */
  fetchRendaMensal: () => ApiService.get("api/renda-mensal"),

  /**
   * Serviço que busca a faixa etaria.
   */
  fetchFaixaEtaria: () => ApiService.get("api/faixa-etaria"),

  /**
   * Serviço que busca o local de pesca.
   */
  fetchLocalPesca: () => ApiService.get("api/local-pesca"),

  /**
   * Serviço que busca o material de pesca.
   */
  fetchMaterialPesca: () => ApiService.get("api/material-pesca"),

  /**
   * Serviço que busca o tipo de isca.
   */
  fetchTipoIsca: () => ApiService.get("api/tipo-isca")
};

export default InformacoesComplementaresService;
