const INITIAL_STATE = {};

export const state = Object.assign({}, INITIAL_STATE);

/**
 * Métodos GETTERS da STORE.
 *
 * Buscam o atual estado da store.
 *
 * @type {Function}
 */
export const getters = {};

/**
 * ACTIONS da STORE.
 *
 * Responsável pelas chamadas de serviços,
 * comunicação com os componentes e commitar
 * mutations.
 *
 * @type {Function}
 */
export const actions = {};

/**
 * As MUTATIONS da STORE.
 *
 * Entidade responsável por alterar o estado
 * da STORE.
 *
 * @type {Function}
 */
export const mutations = {};

export default {
  state,
  getters,
  actions,
  mutations
};