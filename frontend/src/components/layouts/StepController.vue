<template lang="pug">
	#step-controller
		.left
			el-button(icon="el-icon-arrow-left" type="primary" plain @click="prevStep" v-if="!activeStep('IDENTIFICACAO')") {{ $t(`${registrar_prefix}steps.botoes.voltar`) }}
			el-button(icon="el-icon-close" @click="cancelar") {{ $t(`${registrar_prefix}steps.botoes.cancelar`) }}
		.center(v-if="enabled()")
			h4.footer-label {{ $t(`${registrar_prefix}steps.label`, [step + 1, totalSteps]) }}
		.right(v-if="enabled()")
			el-button(icon="el-icon-check" type="primary" v-if="activeStep('RESUMO')" @click="concluir") {{ $t(`${registrar_prefix}steps.botoes.concluir`) }}
			el-button(icon="el-icon-arrow-right" type="primary" @click="nextStep" v-if="!activeStep('RESUMO')") {{ $t(`${registrar_prefix}steps.botoes.proxima`) }}


</template>

<script>
import { mapGetters } from "vuex";
import Properties from "../../properties";
import { REGISTRAR_GERAL_MESSAGES_PREFIX } from "../../utils/messages/interface/registrar/geral";
import { numero } from '../../utils/validations/pessoa/pessoa_validations';

export default {
  name: "StepController",

  props: {
    step: Number
  },

  data() {
    return {
      registrar_prefix: REGISTRAR_GERAL_MESSAGES_PREFIX,
      totalSteps: Object.keys(Properties.STEPS).length
    };
  },

  computed: {
    ...mapGetters(["solicitante", "cadastroCanActive", "existeSolicitante"])
  },

  methods: {
    activeStep(step) {
      const steps = Properties.STEPS;

      return this.step === steps[step];
    },
    enabled() {
      return this.cadastroCanActive || (this.existeSolicitante && !this.cadastroCanActive)
    },

    nextStep() {
      this.$emit("nextStep");
    },

    prevStep() {
      this.$emit("prevStep");
    },

    cancelar() {
      this.$emit("cancelar");
    },

    concluir() {
      this.$emit("concluir");
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
