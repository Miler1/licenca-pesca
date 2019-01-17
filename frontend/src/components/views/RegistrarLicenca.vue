<template lang="pug">
  #registrar-licenca
    h2 {{ $t(`${registrar_prefix}titulo`) }}
    card
      el-steps(:active="step" :space="500" simple)
        el-step(:title="$t(`${registrar_prefix}steps.indices.identificacao`)" icon="el-icon-search")
        el-step(:title="$t(`${registrar_prefix}steps.indices.informacoes`)" icon="el-icon-edit-outline")
        el-step(:title="$t(`${registrar_prefix}steps.indices.resumo`)" icon="el-icon-document")

      identification-step(v-show="activeStep('IDENTIFICACAO')", ref="identificationStep")
      informacaoes-complementares-step(v-show="activeStep('INFORMACOES_COMPLEMENTARES')", ref="informacoesComplementaresStep")
      resumo-step(v-show="activeStep('RESUMO')", ref="resumo")

      step-controller(v-if="showStepsController" :step="step" @prevStep="prevStep" @nextStep="nextStep" @concluir="concluir" @cancelar="cancelar")

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
import { REGISTRAR_GERAL_MESSAGES_PREFIX } from "../../utils/messages/interface/registrar/geral";
import InformacaoesComplementaresStep from "../business/informacoesComplementares/informacoesComplementaresStep";

export default {
  name: "RegistrarLicenca",

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
      registrar_prefix: REGISTRAR_GERAL_MESSAGES_PREFIX
    };
  },

  computed: {
    ...mapGetters(["showStepsController", "cadastroCanActive", "registroResource", "protocolo"])
  },

  methods: {
    nextStep() {
      if(this.activeStep('IDENTIFICACAO')) {
        if(this.checkValidation()) {
          this.$refs.identificationStep.enviarParaStore();
          this.step++;
          this.$refs.informacoesComplementaresStep.scroll();
        }
      } else if(this.activeStep('INFORMACOES_COMPLEMENTARES')) {
        if(this.checkValidationInformacoesComplementares()) {
          this.$refs.informacoesComplementaresStep.enviarParaStore();
          this.$refs.resumo.scroll();
          this.step++;
        }
      } else {
        if (this.step++ >= 2) this.step = 2;
      }
    },

    prevStep() {
      if (this.step-- <= 0) this.step = 0;
    },
    checkValidationInformacoesComplementares() {
      
      return this.$refs.informacoesComplementaresStep.validate();
    },
    checkValidation() {
      
      return this.$refs.identificationStep.getValidated();
    },

    activeStep(step) {
      const steps = Properties.STEPS;

      return this.step === steps[step];
    },

    concluir() {
      this.$confirm(
        translate(`${this.registrar_prefix}confirm.mensagem`),
        translate(`${this.registrar_prefix}confirm.titulo`),
        {
          confirmButtonText: translate(
            `${this.registrar_prefix}confirm.botoes.confirm`
          ),
          cancelButtonText: translate(
            `${this.registrar_prefix}confirm.botoes.cancel`
          )
        }
      )
        .then(() => {
          let registro = this.registroResource;
          let date = this.registroResource.solicitante.dataNascimento;
          if(typeof(date) !== "string"){

            registro.solicitante.dataNascimento =  date.getDate() + '/' + (date.getMonth() + 1)+ '/' + date.getFullYear();
          }

          this.$store.dispatch(REGISTRAR, registro).then(p => {
            let protocolo = p.replace("/", "").replace("-", "");
            this.$router.push({
              name: "consultar",
              params: { protocolo: protocolo }
            });
          });
        })
        .catch(() => {
          // DO nothing!
        });
    },

    cancelar() {
      this.$confirm(
        translate(`${this.registrar_prefix}cancel.mensagem`),
        translate(`${this.registrar_prefix}cancel.titulo`),
        {
          confirmButtonText: translate(
            `${this.registrar_prefix}cancel.botoes.confirm`
          ),
          cancelButtonText: translate(
            `${this.registrar_prefix}cancel.botoes.cancel`
          )
        }
      )
          .then(() => {
            this.$store.dispatch(CANCELAR).then(p => {
              this.step = 0;
              this.$router.push({name: 'home'});
            });
          })
          .catch(() => {
            // DO nothing!
          });
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
      margin-top: 30px

    .el-steps
      height: 30px

    .el-input__icon.el-icon-search
      color: $--cor-tema-primario

    .el-step:first-of-type
      flex-basis: 33% !important

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
