<template lang="pug">
	#lista-licenca
		.nome {{ licenca ? licenca.protocolo : null}}
		.status
			status-badge(:status="licenca ? licenca.status : null")
		.boleto Baixar TODO


</template>

<script>
import StatusBadge from "./StatusBadge";
import { Licenca } from "../../model/Licenca";
export default {
  name: "ListaLicenca",
  components: { StatusBadge },
  props: {
    licenca: {
      type: Licenca,
      default: null
    }
  }
};
</script>

<style lang="sass">
	#lista-licenca
</style>
