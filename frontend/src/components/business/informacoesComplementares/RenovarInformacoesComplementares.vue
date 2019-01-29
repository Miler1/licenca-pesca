<template lang="pug">
	#informacoes-complementares-step
		.info-wrapper
			renovar-info-complementares(ref="renovarInfoComplementares")
</template>

<script>
import RenovarInfoComplementares from "./dadosInformacoes/RenovarInfoComplementares";

export default {
  name: "InformacaoesComplementaresStep",

  components: { RenovarInfoComplementares },
  methods: {
	validate() {
		this.$refs.renovarInfoComplementares.validate();
		return this.$refs.renovarInfoComplementares.getValid();
	},
	enviarParaStore() {
		this.$refs.renovarInfoComplementares.enviarParaStore();
	},
	scroll() {
		this.$refs.renovarInfoComplementares.scroll();
	},
	setInformacao() {
		this.$refs.renovarInfoComplementares.setInformacao();
	}
  }
};
</script>

<style lang="sass">
	#informacoes-complementares-step
		display: flex
		justify-content: center

		.info-wrapper
			width: 520px

</style>
