import { STATUS_MOCK } from "../../utils/layout/mockData";

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
  status: state => state.status
};

/**
 * ACTIONS da STORE.
 *
 * Responsável pelas chamadas de serviços,
 * comunicação com os componentes e commitar
 * mutations.
 */
export const actions = {};

/**
 * As MUTATIONS da STORE.
 *
 * Entidade responsável por alterar o estado
 * da STORE.
 */
export const mutations = {};

export default {
  state,
  getters,
  actions,
  mutations
};
