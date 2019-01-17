import { ACESSAR, CANCELAR, BUSCAR_LICENCAS, BUSCA_DADOS, VALIDA_DADOS } from "../actions.type";
import { Solicitante, toSolicitanteDTO, toSolicitanteBusca } from "../../model/Solicitante";
import { ACTIVE_CADASTRO, SET_ERROR, SET_SOLICITANTE, SET_LISTA_LICENCAS, CLEAN_SOLICITANTE, CLEAN_REGISTRO, SET_BUSCA_MAES, SET_BUSCA_MUNICIPIOS } from "../mutations.type";
import AcessoService from "../../services/AcessoService";
import { InformacoesComplementaresDTO } from "../../model/InformacoesComplementaresDTO";
import { stat } from "fs";

const INITIAL_STATE = {
  solicitante: Solicitante,
  cadastroCanActive: false,
  existeSolicitante: false,
  buscaMaes: Array,
  buscaMunicipios: Array,
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
   * Retorna os nomes de mães gerados.
   */
  buscaMaes: state => state.buscaMaes,

    /**
   * Retorna os municipios gerados.
   */

  buscaMunicipios: state => state.buscaMunicipios,

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
        commit(SET_SOLICITANTE, data.pessoa);
        commit(SET_LISTA_LICENCAS, data.licencas);
      })
      .catch(error => {
        commit(SET_ERROR, error);
      });
  },

  [BUSCA_DADOS]: ({ commit }, acessoResource) => {
    AcessoService.buscaDados(acessoResource)
      .then(({ data }) => {
        commit(SET_BUSCA_MAES, data.maes);
        commit(SET_BUSCA_MUNICIPIOS, data.municipios);
      })
      .catch(error => {
        commit(SET_ERROR, error);
      });
  },

  [VALIDA_DADOS]: ({ commit }, pessoa, listaDadosValidacaoDTO) => {
    AcessoService.verificaDados(pessoa, listaDadosValidacaoDTO)
      .then(({ data }) => {
        debugger
        commit(SET_BUSCA_MAES, data.maes);
        commit(SET_BUSCA_MUNICIPIOS, data.municipios);
      })
      .catch(error => {
        commit(SET_ERROR, error);
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

  [SET_BUSCA_MAES]: (state, solicitante) => {
    if (solicitante !== null) {
      state.buscaMaes = solicitante;
    } else {
      state.buscaMaes = null;
    }
  },

  [SET_BUSCA_MUNICIPIOS]: (state, solicitante) => {
    if (solicitante !== null) {
      state.buscaMunicipios = solicitante;
    } else {
      state.buscaMunicipios = null;
    }
  },

  [CLEAN_SOLICITANTE]: (state) => {
    state.solicitante = Solicitante;
  },

  [SET_LISTA_LICENCAS]: (state, listaLicencas) => {

    state.listaLicencas = listaLicencas;
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
