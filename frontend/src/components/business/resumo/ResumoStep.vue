<template lang="pug">
	#resumo-step
		visualizar-dados-pessoa(:pessoa="registroResource.solicitante")
		visualizar-info-complementares(:info="registroResource.informacoesComplementares")
</template>

<script>
import { mapGetters } from "vuex";
import VisualizarDadosPessoa from "../identificacao/dadosPessoa/VisualizarDadosPessoa";
import VisualizarInfoComplementares from "../informacoesComplementares/dadosInformacoes/VisualizarInfoComplementares";
import VueScrollTo from 'vue-scrollto';

export default {
  name: "ResumoStep",
  components: { VisualizarInfoComplementares, VisualizarDadosPessoa },
  computed: {
    ...mapGetters(["registroResource"])
  },

  data() {
    return {
      	options: {
				container: '#container',
				easing: 'ease-in',
				offset: -60,
				cancelable: true,
				onStart: function (element) {
				// scrolling started
				},
				onDone: function (element) {
				// scrolling is done
				},
				onCancel: function () {
				// scrolling has been interrupted
				},
				x: false,
				y: true
			}
    }
  },
  methods: {

    scroll () {
      VueScrollTo.scrollTo('#container', 1, this.options);
    }
  }
};
</script>

<style lang="sass">
	#resumo-step
</style>
