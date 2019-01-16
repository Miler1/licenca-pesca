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
          @enter="")
            el-select(v-model="type_acesso" slot="prepend" @change="resource = ''")
              el-option(:label="$t('interface.registrar.identificacao.acesso.select.cpf')" value="CPF")
              el-option(:label="$t('interface.registrar.identificacao.acesso.select.passaporte')" value="PASSAPORTE")
            el-button.search-button(slot="append" icon="el-icon-search" @click="acessar" type="primary" :disabled="resource === ''")
          input-element(
          :placeholder="$t('interface.registrar.identificacao.acesso.placeholder.passaporte')"
          v-model="resource"
          v-if="type_acesso !== 'CPF'"
          :mask="maskPassport"
          @enter="")
            el-select(v-model="type_acesso" slot="prepend" @change="resource = ''")
              el-option(:label="$t('interface.registrar.identificacao.acesso.select.cpf')" value="CPF")
              el-option(:label="$t('interface.registrar.identificacao.acesso.select.passaporte')" value="PASSAPORTE")
            el-button.search-button(slot="append" icon="el-icon-search" @click="acessar" type="primary" :disabled="resource === ''")
      
      validacao-perguntas(ref="validacaoPerguntas")
      //- visualizar-dados-pessoa(:pessoa="solicitante" v-if="existeSolicitante", ref="visualizarDadosPessoa")
      lista-licencas(v-if="existeSolicitante")
      

      
</template>

<script>
import { mapGetters } from "vuex";
import Tabela from "../elements/Table";
import Card from "../layouts/Card";
import { buscar, BUSCA_DADOS } from "../../store/actions.type";
import Properties from "../../properties";
import InputElement from "../elements/InputElement";
import { REGISTRAR, CANCELAR, BUSCAR_LICENCAS} from "../../store/actions.type";
import { CPF_MASK, PASSAPORT_MASK } from "../../utils/layout/mascaras";
import { translate } from "../../utils/helpers/internationalization";
import { CONSULTAR_GERAL_MESSAGES_PREFIX } from "../../utils/messages/interface/registrar/geral";
import VisualizarDadosPessoa from "../business/identificacao/dadosPessoa/VisualizarDadosPessoa";
import ListaLicencas from "../business/identificacao/dadosPessoa/ListaLicencas";
import ValidacaoPerguntas from "../elements/ValidacaoPerguntas";

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
      resource: "",
      type_acesso: "CPF",
      maskCPF: CPF_MASK,
      maskPassport: PASSAPORT_MASK,
      tableData:[{
        
      }]
    };
  },
  computed: {
    ...mapGetters(["solicitante", "cadastroCanActive", "existeSolicitante", "buscaDados"])
  },

  methods: {
    cadastrar() {
      this.$store.dispatch(CANCELAR).then(p => {
        this.$router.push({name: 'registrar'});
      });
    },
    acessar() {
      // this.$store.dispatch(BUSCAR_LICENCAS, this.generateAcessoResource(this.resource));
      this.$store.dispatch(BUSCA_DADOS, this.generateAcessoResource(this.resource));
    },
    generateAcessoResource(resource) {
      let cpf = null;
      let passaporte = null;
      if (this.type_acesso === "CPF") {
        cpf = resource;
      } else {
        passaporte = resource;
      }

      return { cpf, passaporte };
    }
  }
};
</script>

<style lang="sass">
  @import "../../theme/tools/variables"

  #registrar-licenca
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
