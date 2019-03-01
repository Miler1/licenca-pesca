<template lang="pug">
	#menu-header
		.menu
			.flex-item
				.left
					.home(v-on:click="goHome()")
						.logo-image
						.logo-text 
							.sigla
								| IPAAM
							.completo
								| Instituto de Proteção Ambiental do Amazonas
					el-menu.el-menu-demo(:default-active='activeIndex', mode='horizontal' background-color='#f5f7fa',text-color='black' v-if="$route.name == 'envioListagemRetorno' || $route.name == 'listagemRemessa' ")
						el-submenu(index='2')
							template(slot='title') Arquivos
							el-menu-item(index="2-1", @click="acessarArquivosRemessa") Remessa
							el-menu-item(index="2-2", @click="acessarArquivosRetorno") Retorno
				
					//- TODO menus que serão implementados no futuro
					//- menu-item(titulo="Remessas"  @click="acessarLicencas" :active="!acessarLicencas")
					//- menu-item( titulo="Retorno"  @click="acessarRelatorios" :active="!acessarRelatorios" )
				
			.right
				.flex-item
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
  props: ['index'],
  data() {
	return { 
		langs: ["PT-BR", "EN"],
		activeName: 'first' ,
		activeIndex: '1'
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
	acessarArquivosRetorno(){
		this.$router.push({
			name: 'envioListagemRetorno'
		});
	},
	teste(){
		location.reload();
	},
	acessarArquivosRemessa(){
		this.$router.push({
			name: 'listagemRemessa'
		});
	},

	acessarLicencas(){
		this.$router.push({
			// name: 'envioRetornoRemessa'
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

			.flex-item
				flex: 1
				display: grid

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
			.left-t
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

		.el-menu--horizontal .el-menu .el-menu-item
			color: #409EFF !important
			font-size: 15px !important

		.el-menu--horizontal .el-menu-item:not(.is-disabled):hover, .el-menu--horizontal .el-menu-item:not(.is-disabled):focus
			background-color: #f5f7fa

		.el-menu--horizontal .el-menu-item:not(.is-disabled):hover, .el-menu--horizontal .el-menu-item:not(.is-disabled):focus
			color: #409EFF !important

		.el-menu--horizontal > .el-submenu .el-submenu__title
			font-size: 16px
			height: 53px
			color: black
			font-weight: bold
		
		.el-menu--horizontal > .el-submenu .el-submenu__icon-arrow
			color: #409EFF !important
			font-size: 24px
			padding-right: 0


		.el-menu.el-menu--horizontal
			border-bottom-color: #fff !important

		.el-submenu.is-active .el-submenu__title
			border-bottom-color: #fff !important

		@media screen and (max-width: 600px) 
			.menu 
				padding: 0 10px 0 5px	!important
			.logo-image
					height: 80px !important
					width: 120px !important
					margin-right: 0px !important
					margin-left: 0px !important
			.logo-text
				.completo
					font-size: 9px !important
					width: 120px !important
				.sigla
					font-size: 19px
			.right
				width: 100px !important
				.locale
					i
						color: $--cor-tema-primario
						font-size: 16px !important
						padding-right: 0

					.el-input__inner
						border: none

					.el-select
						width: 85px !important
</style>
