<template lang="pug">
  #registrar-licenca
    h2.texto {{ $t(`${consultar_prefix}titulo`) }}
    .buttons
      .right
        el-button(icon="el-icon-plus" type="primary" @click="cadastrar" ) {{ $t(`${consultar_prefix}botoes.cadastrar`) }}
    card
      identification-step
    
</template>

<script>
import { mapGetters } from "vuex";
import Card from "../layouts/Card";
import Properties from "../../properties";
import InputElement from "../elements/InputElement";
import { REGISTRAR, CANCELAR } from "../../store/actions.type";
import StepController from "../layouts/StepController";
import ResumoStep from "../business/resumo/ResumoStep";
import { translate } from "../../utils/helpers/internationalization";
import IdentificationStep from "../business/identificacao/IdentificacaoStep";
import { CONSULTAR_GERAL_MESSAGES_PREFIX } from "../../utils/messages/interface/registrar/geral";
import InformacaoesComplementaresStep from "../business/informacoesComplementares/informacoesComplementaresStep";

export default {
  name: "ConsultarLicenca",

  components: {
    ResumoStep,
    InformacaoesComplementaresStep,
    StepController,
    IdentificationStep,
    InputElement,
    Card
  },

  data() {
    return {
      step: 0,
      consultar_prefix: CONSULTAR_GERAL_MESSAGES_PREFIX
    };
  },

  methods: {

    cadastrar(){
      this.$router.push({name: 'registrar'});
      this.cadastroCanActive = false;
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

</style>
