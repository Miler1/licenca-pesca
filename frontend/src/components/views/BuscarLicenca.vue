<template lang="pug">
  #registrar-licenca
    div.buscar
      h2.texto {{ $t(`${consultar_prefix}titulo`) }}
      .buttons
        .right
          el-button(icon="el-icon-plus" type="primary" @click="cadastrar" ) {{ $t(`${consultar_prefix}botoes.cadastrar`) }}
    card
      .acesso
        h4.label-search {{ $t("interface.registrar.identificacao.acesso.label.search") }}
        .search
          input-element(
          :placeholder="$t('interface.registrar.identificacao.acesso.placeholder.cpf')"
          v-model="resource"
          v-if="type_acesso === 'CPF'"
          :mask="maskCPF"
          @enter="acessar")
            el-select(v-model="type_acesso" slot="prepend" @change="limparMensagemErro")
              el-option(:label="$t('interface.registrar.identificacao.acesso.select.cpf')" value="CPF")
              el-option(:label="$t('interface.registrar.identificacao.acesso.select.passaporte')" value="PASSAPORTE")
            el-button.search-button(slot="append" icon="el-icon-search" @click="acessar" type="primary" :disabled="resource === ''")
          input-element(
          :placeholder="$t('interface.registrar.identificacao.acesso.placeholder.passaporte')"
          v-model="resource"
          v-if="type_acesso !== 'CPF'"
          :mask="maskPassport"
          @enter="acessar")
            el-select(v-model="type_acesso" slot="prepend" @change="limparMensagemErro")
              el-option(:label="$t('interface.registrar.identificacao.acesso.select.cpf')" value="CPF")
              el-option(:label="$t('interface.registrar.identificacao.acesso.select.passaporte')" value="PASSAPORTE")
            el-button.search-button(slot="append" icon="el-icon-search" @click="acessar" type="primary" :disabled="resource === ''")
        .block(v-if="$route.name == 'home'")
          .error-pagina-inicial
            | {{errorTelaInicial}}     
            
      validacao-perguntas(v-show="dadosSolicitanteAConfirmar" ref="validacaoPerguntas")
      visualizar-dados-pessoa(:pessoa="solicitante" v-if="existeSolicitante" ref="visualizarDadosPessoa")
      lista-licencas(v-if="existeSolicitante")

</template>

<script>
import { mapGetters } from "vuex";
import Tabela from "../elements/Table";
import Card from "../layouts/Card";
import { buscar, BUSCA_DADOS_VALIDACAO, SET_ERROR_TELA } from "../../store/actions.type";
import Properties from "../../properties";
import InputElement from "../elements/InputElement";
import { REGISTRAR, CANCELAR, BUSCAR_LICENCAS} from "../../store/actions.type";
import { CPF_MASK, PASSAPORT_MASK } from "../../utils/layout/mascaras";
import { translate } from "../../utils/helpers/internationalization";
import { CONSULTAR_GERAL_MESSAGES_PREFIX } from "../../utils/messages/interface/registrar/geral";
import VisualizarDadosPessoa from "../business/identificacao/dadosPessoa/VisualizarDadosPessoa";
import ListaLicencas from "../business/identificacao/dadosPessoa/ListaLicencas";
import ValidacaoPerguntas from "../elements/ValidacaoPerguntas";
import { debug } from 'util';
import { returnStatement, debuggerStatement } from 'babel-types';

export default {
  name: "BuscarLicenca",

  components: {
    InputElement,
    Card,
    Tabela,
    VisualizarDadosPessoa,
    ListaLicencas,
    ValidacaoPerguntas
  },

  data() {
    return {
      step: 0,
      consultar_prefix: CONSULTAR_GERAL_MESSAGES_PREFIX,
      type_acesso: "CPF",
      resource: "",
      maskCPF: CPF_MASK,
      maskPassport: PASSAPORT_MASK,
      tableData:[{

      }]
    };
  },
  created() {
    this.$store.subscribe((mutation, state) => {
      if(mutation.type === "CLEAN_PESQUISA"){
        this.resource = "";
      }
    });
  },
  computed: {
    ...mapGetters(["solicitante", "cadastroCanActive", "existeSolicitante", "dadosSolicitanteAConfirmar", "errorTelaInicial", "buscaMaes", "buscaMunicipios"])
  },

  methods: {
    cadastrar() {
      this.$store.dispatch(CANCELAR).then(p => {
        this.$router.push({name: 'registrar'});
      });
    },

    acessar() {
      this.$store.dispatch(BUSCA_DADOS_VALIDACAO, this.generateAcessoResource(this.resource));
      if(this.$refs.validacaoPerguntas){
          this.$refs.validacaoPerguntas.atualizarCpfPesquisado(this.generateAcessoResource(this.resource));
      }
    },

    generateAcessoResource(resource) {
      
      let cpf = null;
      let passaporte = null;
      if (this.type_acesso === "CPF") {
        cpf = resource;
      } else {
        passaporte = resource;
      }
      debugger
      return { cpf, passaporte };
    },
   
    fecharSolicitante() {
      this.$store.dispatch(CANCELAR);
    },
    limparMensagemErro() {
      this.$store.dispatch(SET_ERROR_TELA, "");
      // SET_ERROR_TELA_BUSCA
      // return this.errorTelaInicial = ""
    }
  }
};
</script>

<style lang="sass">
  @import "../../theme/tools/variables"

  #registrar-licenca
    .error-pagina-inicial
      color: red
      font-size: 14px
      margin-top: 10px

    .block
      display: block
      .close
        font-size: 20px
        float: right
    h1
      font-weight: 500

    .label-search
      margin-top: 10px

    .right
      text-align: right

    .left
      text-align: left

    .buscar
      display: flex

    .texto
      flex: 1
      padding-top: 7px

    .search-button.is-disabled
      background-color: $--cor-background
      color: $--cor-texto-secundario

    .search-button
      background-color: $--cor-tema-primario
      color: white

    .search
      margin-top: 10px
      display: flex

      .el-select
        width: 150px

    .footer-card
      margin-top: 30px
      border-top: $--cor-borda 1px solid
      padding-top: 20px
      display: flex
      align-items: center
      justify-content: space-between

      .footer-label
        font-size: $--fonte-pequena
        color: $--cor-texto-secundario

    .tabela
      padding-top: 50px

</style>
