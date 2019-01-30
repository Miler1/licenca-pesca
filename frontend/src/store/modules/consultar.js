import { STATUS_MOCK } from "../../utils/layout/mockData";
import { SET_LICENCA, SET_LICENCA_ATIVA, SET_DADOS_CARTEIRA, SET_REGISTRO_SOLICITANTE } from "../mutations.type";
import { ATIVAR_LICENCA, FETCH_LICENCA, FETCH_DADOS_CARTEIRA, FETCH_INFORMACAO_LICENCA, CADASTRAR_SOLICITANTE } from "../actions.type";
import ConsultaService from "../../services/ConsultaService";

const INITIAL_STATE = {
  licenca: null,
  licencaPesca: null,
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

  licenca: state => state.licenca,

  licencaPesca: state => state.licencaPesca,
  
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
  },
  [FETCH_INFORMACAO_LICENCA]: ({ commit }, protocolo) => {
    ConsultaService.consultarLicenca(protocolo).then(({ data }) => {
      commit(SET_LICENCA, data.licenca);
      commit(SET_REGISTRO_SOLICITANTE, data.pessoaDTO);
    });
  },
  
  
  [ATIVAR_LICENCA]: ({ commit }) => {
    commit(SET_LICENCA_ATIVA);
  },

  [FETCH_DADOS_CARTEIRA]: ({ commit }, protocolo) => {
    ConsultaService.buscarDadosCarteira(protocolo).then(({ data }) => {
      commit(SET_DADOS_CARTEIRA, data);
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
  [SET_LICENCA]: (state, licenca) => (state.licenca = licenca),

  [SET_LICENCA_ATIVA]: state =>
    state.licenca === null ? null : (state.licenca.status = 1),

  [SET_DADOS_CARTEIRA]: (state, licencaPesca) => (state.licencaPesca = licencaPesca)
};

export default {
  state,
  getters,
  actions,
  mutations
};
