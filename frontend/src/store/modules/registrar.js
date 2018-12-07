import {
  CADASTRAR_INFORMACOES_COMPLEMENTARES,
  CADASTRAR_SOLICITANTE,
  SET_ERROR,
  SET_MODALIDADE_PESCA,
  SET_MUNICIPIOS,
  SET_UFS
} from "../mutations.type";
import {
  FETCH_MODALIDADE_PESCA,
  FETCH_MUNICIPIOS,
  FETCH_UFS, REGISTRAR, SEND_INFORMACOES_COMPLEMENTARES, SEND_SOLICITANTE
} from "../actions.type";
import AcessoService from "../../services/AcessoService";
import {
  AGENCIA_TURISMO_MOCK,
  FAIXA_ETARIA_MOCK,
  LOCAL_PESCA_MOCK,
  LOCALIZACAO_PREF_PESCA_MOCK,
  MATERIAL_PESCA_MOCK,
  MODALIDADE_PESCA_MOCK,
  RENDA_MENSAL_MOCK,
  TIPO_ISCA_MOCK
} from "../../utils/layout/mockData";
import InformacoesComplementaresService from "../../services/InformacoesComplementaresService";
import { Solicitante } from "../../model/Solicitante";
import { InformacoesComplementaresDTO } from "../../model/InformacoesComplementaresDTO";

const INITIAL_STATE = {
  municipios: [],
  ufs: [],
  informacoesComplementaresResource: {
    modalidadePesca: MODALIDADE_PESCA_MOCK,
    localizacaoPreferencialPesca: LOCALIZACAO_PREF_PESCA_MOCK,
    rendaMensal: RENDA_MENSAL_MOCK,
    faixaEtaria: FAIXA_ETARIA_MOCK,
    localPesca: LOCAL_PESCA_MOCK,
    materialPesca: MATERIAL_PESCA_MOCK,
    tipoIsca: TIPO_ISCA_MOCK,
    modalidadeMaisPraticada: MODALIDADE_PESCA_MOCK,
    agenciaTurismo: AGENCIA_TURISMO_MOCK
  },
  registroResource: {
    solicitante: Solicitante,
    informacoesComplementares: InformacoesComplementaresDTO
  }
};

export const state = Object.assign({}, INITIAL_STATE);

/**
 * Métodos GETTERS da STORE.
 *
 * Buscam o atual estado da store.
 */
export const getters = {
  /**
   * Retorna a lista de municipios.
   */
  municipios: state => state.municipios,

  /**
   * Retorna a lista de UFs.
   */
  ufs: state => state.ufs,

  /**
   * Retorna os dados das informacoes Complementares
   */
  informacoesComplementaresResource: state =>
    state.informacoesComplementaresResource,

  registroResource: state => state.registroResource
};

/**
 * ACTIONS da STORE.
 *
 * Responsável pelas chamadas de serviços,
 * comunicação com os componentes e commitar
 * mutations.
 */
export const actions = {
  [REGISTRAR]: ({ commit }) => {

  },

  [SEND_SOLICITANTE]: ({ commit }, solicitante) => {
    commit(CADASTRAR_SOLICITANTE, solicitante);
  },

  [SEND_INFORMACOES_COMPLEMENTARES]: ({ commit }, informacoesComplementares) => {
    commit(CADASTRAR_INFORMACOES_COMPLEMENTARES, informacoesComplementares);
  },

  /**
   * Busca a lista de municípios de determinado UF.
   *
   * @param uf O UF dos municípios
   */
  [FETCH_MUNICIPIOS]: ({ commit }, uf) => {
    AcessoService.fetchMunicipios(uf)
      .then(({ data }) => {
        commit(SET_MUNICIPIOS, data);
      })
      .catch(error => {
        commit(SET_ERROR, error);
      });
  },

  /**
   * Busca e popula a lista de UFs.
   */
  [FETCH_UFS]: ({ commit }) => {
    AcessoService.fetchUfs()
      .then(({ data }) => {
        commit(SET_UFS, data);
      })
      .catch(error => {
        commit(SET_ERROR, error);
      });
  },

  [FETCH_MODALIDADE_PESCA]: ({ commit }) => {
    InformacoesComplementaresService.fetchModalidadePesca()
      .then(({ data }) => {
        commit(SET_MODALIDADE_PESCA, data);
      })
      .catch(error => {
        commit(SET_ERROR, error);
      });
  }
};

/**
 * As MUTATIONS da STORE.
 *
 * Entidade responsável por alterar o estado
 * da STORE.
 */
export const mutations = {
  /**
   * Adiciona os municípios à state
   */
  [SET_MUNICIPIOS]: (state, municipios) => (state.municipios = municipios),

  /**
   * Adiciona os UFs à state.
   */
  [SET_UFS]: (state, ufs) => (state.ufs = ufs),

  /**
   * Adiciona o solicitante à state.
   *
   * @param solicitante
   */
  [CADASTRAR_SOLICITANTE]: (state, solicitante) =>
    (state.registroResource.solicitante = solicitante),

  /**
   * Adiciona as informações complementares à state.
   *
   * @param informacoesComplementares
   */
  [CADASTRAR_INFORMACOES_COMPLEMENTARES]: (state, informacoesComplementares) =>
    (state.registroResource.informacoesComplementares = informacoesComplementares)
};

export default {
  state,
  getters,
  actions,
  mutations
};