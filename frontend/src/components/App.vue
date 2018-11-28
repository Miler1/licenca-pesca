<template lang="pug">
	#app
		main-page

</template>

<script>
import MainPage from "./layouts/MainPage";

export default {
  components: { MainPage },
  data() {
    return {
      langs: ["pt-BR", "en"]
    };
  }
};
</script>

<style lang="sass">
	@import "../theme/tools/variables"
	@import url('https://fonts.googleapis.com/css?family=Roboto')

	// base
	html, body
		height: 100vh
		width: 100vw
		overflow: hidden
		background-color: $--cor-background
		font-size: $--fonte-base
		font-weight: 300
		text-align: left

		font-family: 'Roboto', Helvetica, Arial, sans-serif
		-webkit-font-smoothing: antialiased
		-moz-osx-font-smoothing: grayscale
		color: $--cor-texto-primario

	// Cores
	.alerta
		background-color: $--cor-realce-vermelho

	.sucesso
		background-color: $--cor-realce-verde

	.neutro
		background-color: $--cor-realce-cinza

	.atencao
		background-color: $--cor-realce-amarelo

	.info
		background-color: $--cor-realce-azul

	// Tipografia
	h1, h2, h3, h4, h5, h6, i, p
		margin: 0
		padding: 0
		font-weight: 400

	h1
		font-size: $--fonte-grande

	h2
		font-size: $--fonte-titulo

	h3
		font-size: $--fonte-botao

	h4, p
		font-size: $--fonte-base

	h5, h6
		font-size: $--fonte-pequena

	.hide
		display: none


</style>
