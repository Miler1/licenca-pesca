<template lang="pug">
	#consultar-licenca
		h2 {{$t(`${consultar_prefix}titulos.licenca`)}}
		card
			.licenca
				h2.titulo LPE-0016/18
				.buttons
					el-button(icon="el-icon-download" type="primary") Baixar o boleto
					el-button(icon="el-icon-download" type="primary") Baixar a carteira de pesca


</template>

<script>
import { mapGetters } from "vuex";
import ListaLicenca from "../elements/ListaLicenca";
import { FETCH_LICENCA } from "../../store/actions.type";
import Card from "../layouts/Card";

export default {
  name: "ConsultarLicenca",

  components: { Card, ListaLicenca },

  data() {
    return {
      consultar_prefix: ""
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
    }
  },

  created() {
    this.fetchData;
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

</style>
