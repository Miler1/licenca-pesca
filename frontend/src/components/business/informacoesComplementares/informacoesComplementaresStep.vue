<template lang="pug">
	#informacoes-complementares-step
		.info-wrapper
			cadastro-info-complementares
</template>

<script>
import CadastroInfoComplementares from "./dadosInformacoes/CadastroInfoComplementares";

export default {
  name: "InformacaoesComplementaresStep",

  components: { CadastroInfoComplementares }
};
</script>

<style lang="sass">
	#informacoes-complementares-step
		display: flex
		justify-content: center

		.info-wrapper
			width: 520px

</style>
