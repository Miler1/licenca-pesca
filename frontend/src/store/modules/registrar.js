import Vue from "vue";
import {
  CADASTRAR_INFORMACOES_COMPLEMENTARES,
  CADASTRAR_SOLICITANTE,
  SET_ERROR,
  SET_MODALIDADE_PESCA,
  SET_MUNICIPIOS, SET_PROTOCOLO,
  SET_UFS,
  CLEAN_REGISTRO
} from "../mutations.type";
import {
  FETCH_MODALIDADE_PESCA,
  FETCH_MUNICIPIOS,
  FETCH_UFS,
  REGISTRAR,
  SEND_INFORMACOES_COMPLEMENTARES,
  SEND_SOLICITANTE
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
import { Solicitante, toSolicitanteDTO } from "../../model/Solicitante";
import { InformacoesComplementaresDTO } from "../../model/InformacoesComplementaresDTO";
import RegistroService from "../../services/RegistroService";
import { stat } from "fs";

const INITIAL_STATE = {
  municipios: [],
  ufs: [],
  informacoesComplementaresResource: {
    modalidadePesca: MODALIDADE_PESCA_MOCK,
    localizacaoPreferencialPesca: LOCALIZACAO_PREF_PESCA_MOCK,
    rendaMensal: RENDA_MENSAL_MOCK,
    faixaEtaria: FAIXA_ETARIA_MOCK,
    localPesca: LOCAL_PESCA_MOCK,
    materialPesca: MATERIAL_PESCA_MOCK,
    tipoIsca: TIPO_ISCA_MOCK,
    modalidadeMaisPraticada: MODALIDADE_PESCA_MOCK,
    agenciaTurismo: AGENCIA_TURISMO_MOCK
  },
  registroResource: {
    informacoesComplementares: InformacoesComplementaresDTO,
    solicitante: Solicitante
  },
  protocolo: null
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

  informacoesComplementares: state =>
    state.registroResource.informacoesComplementares,

  /**
   * Retorna os dados das informacoes Complementares
   */
  informacoesComplementaresResource: state =>
    state.informacoesComplementaresResource,

  registroResource: state => state.registroResource,

  protocolo: state => state.protocolo
};

/**
 * ACTIONS da STORE.
 *
 * Responsável pelas chamadas de serviços,
 * comunicação com os componentes e commitar
 * mutations.
 */
export const actions = {
  [REGISTRAR]: ({ commit }, registroResource) => {
    return RegistroService.registrar({
      pessoa: registroResource.solicitante,
      informacaoComplementar: registroResource.informacoesComplementares
    }).then(({ data }) => {
      commit(SET_PROTOCOLO, data.numero);
      Vue.prototype.$message.success(
        `A licença ${data.numero} foi criada com sucesso.`
      );
      return data.numero;
    }).catch(error => {
      Vue.prototype.$message.error(
        `Não foi possível conectar ao servidor.`
      );
    });
  },

  [SEND_SOLICITANTE]: ({ commit }, solicitante) => {
    commit(CADASTRAR_SOLICITANTE, solicitante);
  },

  [SEND_INFORMACOES_COMPLEMENTARES]: ({ commit }, informacoesComplementares) => {
    commit(CADASTRAR_INFORMACOES_COMPLEMENTARES, informacoesComplementares);
  },
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
  [SET_UFS]: (state, ufs) =>
    (state.ufs = ufs.sort(function(ordenaMenor, ordenaMaior) {
        return ordenaMenor.sigla > ordenaMaior.sigla ? 1 : -1;
      })
    ),
  /**
   * Adiciona o solicitante à state.
   *
   * @param solicitante
   */
  [CADASTRAR_SOLICITANTE]: (state, solicitante) => {
    // delete solicitante.confirmarEmail;
    state.registroResource.solicitante = toSolicitanteDTO(solicitante);
  },

  [CLEAN_REGISTRO]: (state) => {
    state.registroResource.informacoesComplementares = InformacoesComplementaresDTO;
    state.registroResource.solicitante = Solicitante;
  },

  /**
   * Adiciona as informações complementares à state.
   *
   * @param informacoesComplementares
   */
  [CADASTRAR_INFORMACOES_COMPLEMENTARES]: (state, informacoesComplementares) =>
    (state.registroResource.informacoesComplementares = informacoesComplementares),

  [SET_PROTOCOLO]: (state, protocolo) => (state.protocolo = protocolo)
};

export default {
  state,
  getters,
  actions,
  mutations
};
