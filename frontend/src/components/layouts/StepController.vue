<template lang="pug">
	#step-controller
		.left
			el-button(icon="el-icon-arrow-left" type="primary" plain @click="prevStep" v-if="!activeStep('IDENTIFICACAO')") {{ $t(`${registrar_prefix}steps.botoes.voltar`) }}
			el-button(icon="el-icon-close") {{ $t(`${registrar_prefix}steps.botoes.cancelar`) }}
		.center
			h4.footer-label {{ $t(`${registrar_prefix}steps.label`, [step + 1, totalSteps]) }}
		.right
			el-button(icon="el-icon-check" type="primary" v-if="activeStep('RESUMO')") {{ $t(`${registrar_prefix}steps.botoes.concluir`) }}
			el-button(icon="el-icon-arrow-right" type="primary" @click="nextStep" v-if="!activeStep('RESUMO')") {{ $t(`${registrar_prefix}steps.botoes.proxima`) }}


</template>

<script>
import Config from "../../config";
import { REGISTRAR_GERAL_MESSAGES_PREFIX } from "../../utils/messages/interface/registrar/geral";

export default {
  name: "StepController",

  props: {
    step: Number
  },

  data() {
    return {
      registrar_prefix: REGISTRAR_GERAL_MESSAGES_PREFIX,
      totalSteps: Object.keys(Config.STEPS).length
    };
  },

  methods: {
    activeStep(step) {
      const steps = Config.STEPS;

      return this.step === steps[step];
    },

    nextStep() {
      this.$emit("nextStep");
    },

    prevStep() {
      this.$emit("prevStep");
    }
  }
};
</script>

<style lang="sass">
	@import "../../theme/tools/variables"

	#step-controller
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
