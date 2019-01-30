<template lang="pug">
	#informacoes-complementares-step
		.info-wrapper
			cadastro-info-complementares(ref="cadastroInfoComplementares")
</template>

<script>
import CadastroInfoComplementares from "./dadosInformacoes/CadastroInfoComplementares";

export default {
  name: "InformacaoesComplementaresStep",

  components: { CadastroInfoComplementares },
  methods: {
	validate() {
		this.$refs.cadastroInfoComplementares.validate();
		return this.$refs.cadastroInfoComplementares.getValid();
	},
	enviarParaStore() {
		this.$refs.cadastroInfoComplementares.enviarParaStore();
	},
	scroll() {
		this.$refs.cadastroInfoComplementares.scroll();
	},
	setInformacao() {
		this.$refs.cadastroInfoComplementares.setInformacao();
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
