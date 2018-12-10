<template lang="pug">
	#lista-licenca
		.nome {{ licenca.protocolo }}
		.status


</template>

<script>
export default {
  name: "ListaLicenca",

  props: {
    licenca: Object
  }
};
</script>

<style lang="sass">
	#lista-licenca
</style>
