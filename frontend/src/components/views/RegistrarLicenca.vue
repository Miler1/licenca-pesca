<template lang="pug">
	#registrar-licenca
		h2 {{ $t(`${registrar_prefix}titulo`) }}
		card
			el-steps(:active="step" :space="500" simple)
				el-step(:title="$t(`${registrar_prefix}steps.indices.identificacao`)" icon="el-icon-search")
				el-step(:title="$t(`${registrar_prefix}steps.indices.informacoes`)" icon="el-icon-edit-outline")
				el-step(:title="$t(`${registrar_prefix}steps.indices.resumo`)" icon="el-icon-document")

			identification-step(v-if="activeStep('IDENTIFICACAO')")
			informacaoes-complementares-step(v-if="activeStep('INFORMACOES_COMPLEMENTARES')")

			step-controller(v-if="showStepsController" :step="step" @prevStep="prevStep" @nextStep="nextStep")

</template>

<script>
import * as _ from "lodash";
import { mapGetters } from "vuex";

import Properties from "../../properties";
import Card from "../layouts/Card";
import InputElement from "../elements/InputElement";
import IdentificationStep from "../business/identificacao/IdentificacaoStep";
import { REGISTRAR_GERAL_MESSAGES_PREFIX } from "../../utils/messages/interface/registrar/geral";
import StepController from "../layouts/StepController";
import InformacaoesComplementaresStep from "../business/informacoesComplementares/informacoesComplementaresStep";

export default {
  name: "RegistrarLicenca",

  components: {
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
    ...mapGetters(["showStepsController"])
  },

  methods: {
    nextStep() {
      if (this.checkValidation()) {
        if (this.step++ >= 2) this.step = 2;
      }
    },

    prevStep() {
      if (this.step-- <= 0) this.step = 0;
    },

    checkValidation() {
      let isValid = true;

      if (
        this.$cadastro &&
        !_.isEmpty(this.$cadastro.$refs && this.$cadastro.$refs.pessoa)
      ) {
        this.$cadastro.$refs.pessoa.validate(v => {
          console.log(v);

        });
      }

      return isValid;
    },

    activeStep(step) {
      const steps = Properties.STEPS;

      return this.step === steps[step];
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
