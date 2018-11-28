<template lang="pug">
	#registrar-licenca
		h2 {{ $t("message.registerTitle") }}
		card
			el-steps(:space="200" :active="0" simple)
				el-step(title="Identificação" icon="el-icon-search")
				el-step(title="Informações complementares" icon="el-icon-edit-outline")
				el-step(title="Resumo" icon="el-icon-document")
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
