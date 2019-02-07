<template lang="pug">
	#menu-header
		.menu
			.left
				.home(v-on:click="goHome()")
					.logo-image
					.logo-text 
						.sigla
							| IPAAM
						.completo
							| Instituto de Proteção Ambiental do Amazonas
				menu-item(v-if='remessa()' titulo="Licenças"  @click="acessarLicencas" :active="acessarLicencas")
				menu-item(v-if='remessa()' titulo="Relatórios"  @click="acessarRelatorios" :active="!acessarRelatorios" )
			.right
				.locale
					i.mdi.mdi-translate
					el-select(v-model="$i18n.locale" @change="handleLocale")
						el-option(v-for="(lang, i) in langs" :key="i" :value="lang") {{ lang }}

</template>

<script>
import { localizeValidation } from "../../configs/validator";
import  MenuItem  from "./MenuItem" ;
import { CANCELAR } from "../../store/actions.type";

export default {
  name: "MenuHeader",
  components: { MenuItem },
  mixins: [],
  data() {
	return { 
		langs: ["PT-BR", "EN"],
		activeName: 'first' 
	};
  },
  methods: {
	handleClick(tab, event) {
    	console.log(tab, event);
    },
	handleLocale() {
	  localizeValidation();
	},
	goHome() {
		this.$store.dispatch(CANCELAR).then(p => {
			this.$router.push({name: 'home'});
		});
	},
	remessa(){
		return this.$router.history.current.name == 'listagemLicencasRelatorios'	
	},
	acessarLicencas(){
		this.$router.push({
			name: 'listagemLicencasRelatorios'
		});
	},
	acessarRelatorios(){
		this.$router.push({
			// name: 'relatorios'
		});
	}
  }
};
</script>

<style lang="sass">
	@import "../../theme/tools/variables"

	@import url('https://fonts.googleapis.com/css?family=Lato:900')

	#menu-header
		width: 100%
		height: 100%

		.menu
			display: flex
			align-items: center
			height: 100%
			padding: 0 20px 0 20px
			justify-content: space-between
			box-shadow: 0 4px 4px rgba(0, 0, 0, .1)

			.left
				display: flex
				align-items: center

				.home
					display: flex
					align-items: center
					text-decoration: none
					cursor: pointer

				.logo-image
					color: white
					height: 100px
					width: 170px
					margin-right: 10px
					margin-left: 10px
					background: url("../../assets/logo-Governo-2018.png") no-repeat center
					background-size: contain

				.logo-text
					padding-left: 20px
					font-family: "Lato", Helvetica, sans-serif
					font-weight: bold
					color: black
					font-size: $--fonte-titulo
					border-left: 1px solid #999
					.completo
						font-size: 12px
						width: 190px
					.sigla
						font-size: 19px

			.right
				display: flex
				align-items: center

				.locale
					display: flex
					align-items: center

					i
						color: $--cor-tema-primario
						font-size: $--fonte-titulo
						padding-right: 0

					.el-input__inner
						border: none

					.el-select
						width: 90px

</style>
