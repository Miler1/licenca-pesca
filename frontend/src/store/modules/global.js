import { SET_ERROR } from "../mutations.type";

const INITIAL_STATE = {
  errors: []
};

export const state = Object.assign({}, INITIAL_STATE);

/**
 * Métodos GETTERS da STORE.
 *
 * Buscam o atual estado da store.
 */
export const getters = {
  /**
   * Retorna a lista de erros.
   */
  errors: state => state.errors
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
export const mutations = {
  /**
   * Adiciona o erro à lista de erros.
   */
  [SET_ERROR]: (state, error) => state.errors.push(error)
};

export default {
  state,
  getters,
  actions,
  mutations
};
