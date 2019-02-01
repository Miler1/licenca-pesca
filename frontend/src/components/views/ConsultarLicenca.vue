<template lang="pug">
	#consultar-licenca
		h2 {{ $t(`${consultar_prefix}titulos.licenca`) }}
		card(v-if="licenca !== null")
			.licenca
				h2.titulo {{ licenca.protocolo }}
				.buttons
					el-button(icon="el-icon-download" type="primary" @click="baixarBoleto(licenca.protocolo)") {{ $t(`${consultar_prefix}botoes.downloadBoleto`) }}
		.no-data(v-if="licenca === null")
			h2 {{ $t(`${consultar_prefix}data.semLicenca`) }}

</template>

<script>
import { mapGetters } from "vuex";
import ListaLicenca from "../elements/ListaLicenca";
import { ATIVAR_LICENCA, FETCH_LICENCA, FETCH_CARTEIRA } from "../../store/actions.type";
import Card from "../layouts/Card";
import { INTERFACE_CONSULTA_PREFIX } from "../../utils/messages/interface/consulta/consulta";
import Properties from "../../properties";

export default {
  name: "ConsultarLicenca",

  components: { Card, ListaLicenca },

  data() {
    return {
      consultar_prefix: INTERFACE_CONSULTA_PREFIX
    };
  },

  watch: {
    "$route": "fetchData"
  },

  computed: {
    ...mapGetters(["licenca"])
  },

  methods: {
    fetchData() {
      this.$store.dispatch(FETCH_LICENCA, this.$route.params.protocolo);
    },

    baixarBoleto(protocolo) {
      if(protocolo.codigoFormatado) {
        protocolo = protocolo.codigoFormatado;
      }
      let protocoloDesformatado = protocolo.replace("/", "").replace("-", "").replace("-","");
      this.$store.dispatch(ATIVAR_LICENCA);

      const href =
        `${Properties.BASE_URL}/api/boleto?protocolo=` + protocoloDesformatado;

      window.open(href, "_blank");
    },

    baixarCarteira(protocolo) {
      let protocoloDesformatado = protocolo.replace("/", "").replace("-", "");

      const href =
        `${Properties.BASE_URL}/api/carteira?protocolo=` +
        protocoloDesformatado;

      window.open(href, "_blank");
    }
  },

  created() {
    this.fetchData();
  }
};
</script>

<style lang="sass">
	#consultar-licenca
		.licenca
			display: flex
			align-items: center
			justify-content: space-between

			.titulo
				padding: 10px 0 10px 0

		.no-data
			height: 75vh
			display: flex
			justify-content: center
			align-items: center

			h2
				color: #a9a9a9

</style>
