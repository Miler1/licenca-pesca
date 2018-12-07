import {
  SET_ERROR,
  SET_MODALIDADE_PESCA,
  SET_MUNICIPIOS,
  SET_UFS
} from "../mutations.type";
import {
  FETCH_MODALIDADE_PESCA,
  FETCH_MUNICIPIOS,
  FETCH_UFS
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

const INITIAL_STATE = {
  municipios: [],
  ufs: [],
  informacoesComplementares: {
    modalidadePesca: MODALIDADE_PESCA_MOCK,
    localizacaoPreferencialPesca: LOCALIZACAO_PREF_PESCA_MOCK,
    rendaMensal: RENDA_MENSAL_MOCK,
    faixaEtaria: FAIXA_ETARIA_MOCK,
    localPesca: LOCAL_PESCA_MOCK,
    materialPesca: MATERIAL_PESCA_MOCK,
    tipoIsca: TIPO_ISCA_MOCK,
    modalidadeMaisPraticada: MODALIDADE_PESCA_MOCK,
    agenciaTurismo: AGENCIA_TURISMO_MOCK
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
  informacoesComplementaresResource: state => state.informacoesComplementares
};

/**
 * ACTIONS da STORE.
 *
 * Responsável pelas chamadas de serviços,
 * comunicação com os componentes e commitar
 * mutations.
 */
export const actions = {
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
  [SET_UFS]: (state, ufs) => (state.ufs = ufs)
};

export default {
  state,
  getters,
  actions,
  mutations
};
