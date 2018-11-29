import { ACESSAR } from "../actions.type";
import AcessoService from "../../services/AcessoService";
import { SET_ERROR, SET_SOLICITANTE } from "../mutations.type";

const INITIAL_STATE = {
  solicitante: null,
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
   * Retorna o solicitante.
   *
   * @param state
   * @return {getters.solicitante|(function(*))|null}
   */
  solicitante: state => state.solicitante,

  /**
   * Retorna a lista de erros.
   *
   * @param state
   * @return {*}
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
export const actions = {
  /**
   * Action de acesso da aplicação.
   *
   * Busca o solicitante dado o CPF ou passaporte.
   *
   * @param commit
   * @param acessoResource
   */
  [ACESSAR]: ({ commit }, acessoResource) => {
    AcessoService.acessar(acessoResource)
      .then(({ data }) => {
        commit(SET_SOLICITANTE, data);
      })
      .catch(error => {
        console.log({ error });
        // throw new Error(error);
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
   * Inclui o solicitante na store.
   *
   * @param state
   * @param data
   * @return {*}
   */
  [SET_SOLICITANTE]: (state, data) => (state.solicitante = data),

  /**
   * Adiciona à lista de erros.
   *
   * @param state
   * @param error
   * @return {*}
   */
  [SET_ERROR]: (state, error) => state.errors.add(error)
};

export default {
  state,
  getters,
  actions,
  mutations
};
