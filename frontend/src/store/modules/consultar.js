import { STATUS_MOCK } from "../../utils/layout/mockData";
import { SET_LICENCA } from "../mutations.type";
import { FETCH_LICENCA } from "../actions.type";
import ConsultaService from "../../services/ConsultaService";

const INITIAL_STATE = {
  licencas: [],
  status: STATUS_MOCK
};

export const state = Object.assign({}, INITIAL_STATE);

/**
 * Métodos GETTERS da STORE.
 *
 * Buscam o atual estado da store.
 */
export const getters = {
  status: state => state.status,

  licenca: state => state.licencas
};

/**
 * ACTIONS da STORE.
 *
 * Responsável pelas chamadas de serviços,
 * comunicação com os componentes e commitar
 * mutations.
 */
export const actions = {
  [FETCH_LICENCA]: ({ commit }, protocolo) => {
    ConsultaService.consultar(protocolo).then(({ data }) => {
      commit(SET_LICENCA, data);
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
  [SET_LICENCA]: (state, licencas) => (state.licencas = licencas)
};

export default {
  state,
  getters,
  actions,
  mutations
};
