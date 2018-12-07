<template lang="pug">
	#resumo-step
		visualizar-dados-pessoa(:pessoa="registroResource.solicitante")
		visualizar-info-complementares(:info="registroResource.informacoesComplementares")
</template>

<script>
import { mapGetters } from "vuex";
import VisualizarDadosPessoa from "../identificacao/dadosPessoa/VisualizarDadosPessoa";
import VisualizarInfoComplementares from "../informacoesComplementares/dadosInformacoes/VisualizarInfoComplementares";

export default {
  name: "ResumoStep",
  components: { VisualizarInfoComplementares, VisualizarDadosPessoa },
  computed: {
    ...mapGetters(["registroResource"])
  }
};
</script>

<style lang="sass">
	#resumo-step
</style>
