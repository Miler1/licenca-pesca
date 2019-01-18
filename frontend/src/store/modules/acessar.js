import { ACESSAR, CANCELAR, BUSCAR_LICENCAS } from "../actions.type";
import { Solicitante, toSolicitanteDTO } from "../../model/Solicitante";
import { ACTIVE_CADASTRO, SET_ERROR, SET_ERROR_TELA_BUSCA, SET_SOLICITANTE, SET_LISTA_LICENCAS, CLEAN_SOLICITANTE, CLEAN_REGISTRO } from "../mutations.type";
import AcessoService from "../../services/AcessoService";
import { InformacoesComplementaresDTO } from "../../model/InformacoesComplementaresDTO";
import { stat } from "fs";
import Vue from "vue";

const INITIAL_STATE = {
  solicitante: Solicitante,
  cadastroCanActive: false,
  existeSolicitante: false,
  showStepsController: true
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
   */
  solicitante: state => state.solicitante,

  /**
   * Retorna true se existir o solicitante e false se não existir.
   */
  existeSolicitante: state => state.solicitante.nome !== null,

  /**
   * Retorna verdadeiro quando o cadastro do solicitante
   * se faz necessário.
   */
  cadastroCanActive: state => state.cadastroCanActive,

  /**
   * Retorna verdadeiro se a etapa de identificação estiver ok.
   */
  showStepsController: state => state.showStepsController,

  listaLicencas: state => state.listaLicencas
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
   */
  [ACESSAR]: ({ commit }, acessoResource) => {
    AcessoService.acessar(acessoResource)
      .then(({ data }) => {
        commit(SET_SOLICITANTE, data);
        commit(ACTIVE_CADASTRO, data);
      })
      .catch(error => {
        commit(SET_ERROR, error);
      });
  },

  [BUSCAR_LICENCAS]: ({ commit }, acessoResource) => {
    AcessoService.buscarLicensas(acessoResource)
      .then(({ data }) => {
        commit(SET_ERROR_TELA_BUSCA, "");
        commit(SET_SOLICITANTE, data.pessoa);
        commit(SET_LISTA_LICENCAS, data.licencas);
      })
      .catch(error => {
        commit(SET_ERROR_TELA_BUSCA, error.response.data);
      });
  },

  [CANCELAR]: ({ commit }) => {
    commit(ACTIVE_CADASTRO, false);
    commit(CLEAN_SOLICITANTE);
    commit(CLEAN_REGISTRO);
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
   * @param solicitante O solicitante do acesso
   */
  [SET_SOLICITANTE]: (state, solicitante) => {
    if (solicitante !== null) {
      state.solicitante = toSolicitanteDTO(solicitante);
    } else {
      state.solicitante = null;
    }
  },



  [CLEAN_SOLICITANTE]: (state) => {
    state.solicitante = Solicitante;
  },

  [SET_LISTA_LICENCAS]: (state, listaLicencas) => {
    Vue.set(state, 'listaLicencas', [...listaLicencas]);
  },
  /**
   * Verifica se será necessário cadastrar o usuário.
   *
   * @param solicitante O solicitante do acesso
   */
  [ACTIVE_CADASTRO]: (state, solicitante) => {
    solicitante !== null && solicitante.nome === null
      ? (state.cadastroCanActive = true)
      : (state.cadastroCanActive = false);
  }
};

export default {
  state,
  getters,
  actions,
  mutations
};
