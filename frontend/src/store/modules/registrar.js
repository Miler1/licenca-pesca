import { SET_ERROR, SET_MUNICIPIOS, SET_UFS } from "../mutations.type";
import { FETCH_MUNICIPIOS, FETCH_UFS } from "../actions.type";
import AcessoService from "../../services/AcessoService";

const INITIAL_STATE = {
  municipios: [],
  ufs: []
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
  ufs: state => state.ufs
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
