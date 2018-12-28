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
		console.log('teste', this.$refs.cadastroInfoComplementares.getValid());
		return this.$refs.cadastroInfoComplementares.getValid();
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
