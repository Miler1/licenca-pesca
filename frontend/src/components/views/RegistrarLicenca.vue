<template lang="pug">
	#registrar-licenca
		h2 {{ $t("message.registerTitle") }}
		card
</template>

<script>
import Card from "../layouts/Card";
export default {
  name: "RegistrarLicenca",
  components: { Card }
};
</script>

<style lang="sass">
	#registrar-licenca
		h1
			font-weight: 500
</style>
