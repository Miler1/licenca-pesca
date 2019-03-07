import { ACESSAR, CANCELAR, BUSCAR_LICENCAS, BUSCA_DADOS_VALIDACAO, GERAR_REMESSAS, LISTAR_REMESSAS, UPLOAD_ARQUIVO_RETORNO, LISTAR_RETORNOS, SET_ERROR_TELA} from "../actions.type";
import { Solicitante, toSolicitanteDTO } from "../../model/Solicitante";
import { SET_DADOS_SOLICITANTE_CONFIRMAR, ACTIVE_CADASTRO, SET_ERROR, SET_ERROR_TELA_BUSCA, SET_SOLICITANTE, SET_LISTA_LICENCAS, CLEAN_SOLICITANTE, CLEAN_REGISTRO, SET_PASSAPORTE_PESQUISA, SET_CPF_PESQUISA, SET_BUSCA_MAES, CLEAN_PESQUISA, CLEAN_CPF_PESQUISA, SET_LISTA_TODAS_LICENCAS, SET_LISTA_REMESSAS, SET_PAGINACAO, SET_LISTA_ARQUIVOS_RETORNOS } from "../mutations.type";
import AcessoService from "../../services/AcessoService";
import Vue from "vue";
import RegistroService from "../../services/RegistroService";
import ConsultaService from "../../services/ConsultaService";
import ArquivoService from "../../services/ArquivoService";

const INITIAL_STATE = {
  solicitante: Solicitante,
  maes: Array,
  cadastroCanActive: false,
  existeSolicitante: false,
  dadosSolicitanteAConfirmar: false,
  showStepsController: true,
  buscaMaes: false,
  cpfPesquisa: null,
  passaportePesquisa: null,
  listaRemessasPaginada: null,
  listaArquivosRetornoPaginado: null
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
   * Retorna o cpf.
  */
  cpfPesquisa: state => state.cpfPesquisa,
  /**
   * Retorna o passaporte.
  */
  passaportePesquisa: state => state.passaportePesquisa,

  listaRemessasPaginada: state => state.listaRemessasPaginada,

  listaArquivosRetornoPaginado: state => state.listaArquivosRetornoPaginado,

  /**
   * Retorna os nomes de mães gerados.
  */
  buscaMaes: state => state.buscaMaes,

  /**
   * Retorna true se existir o solicitante e false se não existir.
   */
  existeSolicitante: state => state.solicitante.nome !== null,

  /**
   * Retorna true para validar os dados ou falso caso ainda não tenha pesquisado.
   */
  dadosSolicitanteAConfirmar: state => state.dadosSolicitanteAConfirmar,

  /**
   * Retorna verdadeiro quando o cadastro do solicitante
   * se faz necessário.
   */
  cadastroCanActive: state => state.cadastroCanActive,

  /**
   * Retorna verdadeiro se a etapa de identificação estiver ok.
   */
  showStepsController: state => state.showStepsController,

  listaLicencas: state => state.listaLicencas,

  listaRemessas: state => state.listaRemessas
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
        commit(SET_DADOS_SOLICITANTE_CONFIRMAR, false);
        commit(SET_CPF_PESQUISA, data.cpf);
        commit(SET_PASSAPORTE_PESQUISA, data.passaporte);
      })
      .catch(error => {
        commit(SET_ERROR, error);
      });
  },

  [GERAR_REMESSAS]: ({ commit }) => {
    RegistroService.geraRemessa()
    .then(() => {
      Vue.prototype.$notify.success({
        title: 'Sucesso',
        message: `Remessa gerada com sucesso.`
      });
    })
    .catch(error => {
      commit(SET_ERROR, error.message);
    });
  },

  [UPLOAD_ARQUIVO_RETORNO]: (file) => {
    ArquivoService.upload(file);
  },

  [LISTAR_REMESSAS]:  ({commit}, pagina) => {
    ConsultaService.buscarRemessas(pagina)
    .then(({ data }) => {
      commit(SET_LISTA_REMESSAS, data);
    })
    .catch(error => {
      commit(SET_ERROR, error);
    });
  },

  [LISTAR_RETORNOS]:  ({commit}, pagina) => {
    ConsultaService.buscarArquivosRetornos(pagina)
    .then(({ data }) => {
      commit(SET_LISTA_ARQUIVOS_RETORNOS, data);
    })
    .catch(error => {
      commit(SET_ERROR, error);
    });
  },
  

  [BUSCAR_LICENCAS]: ({ commit }, acessoResource) => {
    AcessoService.buscarLicencas(acessoResource)
      .then(({ data }) => {
        commit(SET_DADOS_SOLICITANTE_CONFIRMAR, false);
        if(data.pessoa && data.pessoa.enderecoEstrangeiro){

          data.pessoa.enderecoEstrangeiro.nacionalidadeNome = data.pessoa.enderecoEstrangeiro.nacionalidade.nome;
          data.pessoa.enderecoEstrangeiro.paisNome = data.pessoa.enderecoEstrangeiro.pais.nome;
        }
        commit(SET_SOLICITANTE, data.pessoa);
        commit(SET_LISTA_LICENCAS, data.licencas);
      })
      .catch(error => {
        if(error.response) {
          commit(SET_ERROR_TELA_BUSCA, error.response.data);
          commit(CLEAN_SOLICITANTE);
        }
      });
  },
  
  [BUSCA_DADOS_VALIDACAO]: ({ commit }, acessoResource) => {
    AcessoService.buscarDados(acessoResource)
      .then(({ data, error }) => {
        if(!data.maes){
          commit(SET_SOLICITANTE, data);
          commit(ACTIVE_CADASTRO, data);
          commit(SET_ERROR_TELA_BUSCA, "Pessoa não encontrada.");
          commit(SET_DADOS_SOLICITANTE_CONFIRMAR, false);
          commit(SET_CPF_PESQUISA, data.cpf);
          commit(SET_PASSAPORTE_PESQUISA, data.passaporte);
        }else {
          commit(SET_DADOS_SOLICITANTE_CONFIRMAR, true);
          commit(CLEAN_SOLICITANTE);
          commit(SET_ERROR_TELA_BUSCA, "");
          commit(SET_BUSCA_MAES, data.maes);
        }
      })
      .catch(error => {
        if (error.response) {
          commit(SET_ERROR_TELA_BUSCA, error.response.data.message);
          commit(CLEAN_SOLICITANTE);
        }else {
          Vue.prototype.$notify.error({
            title: 'Erro do sistema',
            message: `Não foi possível conectar ao servidor.`
          });
        }
      });
  },
  
  [SET_ERROR_TELA]:({ commit }) => {
    commit(SET_ERROR_TELA_BUSCA, "");
  },

  [CANCELAR]: ({ commit }) => {
    commit(ACTIVE_CADASTRO, false);
    commit(SET_DADOS_SOLICITANTE_CONFIRMAR, false);
    commit(CLEAN_SOLICITANTE);
    commit(CLEAN_REGISTRO);
    commit(CLEAN_PESQUISA);
    commit(SET_ERROR_TELA_BUSCA, "");
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

  [CLEAN_PESQUISA]: (state) => { state.errorTelaInicial = ''}, 

  [SET_BUSCA_MAES]: (state, maes) => {
    if (maes !== null) {
      state.buscaMaes = maes;
    } else {
      state.buscaMaes = null;
    }
  },

  [CLEAN_SOLICITANTE]: (state) => {
    state.solicitante = Solicitante;
  },

  [SET_CPF_PESQUISA]: (state, cpf) => {
    state.cpfPesquisa = cpf;
  },

  [SET_PASSAPORTE_PESQUISA]: (state, passaporte) => {
    state.passaportePesquisa = passaporte;
  },

  [SET_LISTA_LICENCAS]: (state, listaLicencas) => {
    Vue.set(state, 'listaLicencas', [...listaLicencas]);
  },

  [SET_LISTA_REMESSAS]: (state, listaRemessas) => {
    state.listaRemessasPaginada = listaRemessas;
  },

  [SET_LISTA_ARQUIVOS_RETORNOS]: (state, listaArquivosRetorno) => {
    state.listaArquivosRetornoPaginado = listaArquivosRetorno;
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
  },

  [SET_DADOS_SOLICITANTE_CONFIRMAR]: (state, dadosSolicitanteAConfirmar) => {
    state.dadosSolicitanteAConfirmar = dadosSolicitanteAConfirmar;

  }
};

export default {
  state,
  getters,
  actions,
  mutations
};
