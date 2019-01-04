<template lang="pug">
	#menu-header
		.menu
			.left
				router-link(to="/")
					.logo-image
					.logo-text IPAAM
			.right
				.locale
					i.mdi.mdi-translate
					el-select(v-model="$i18n.locale" @change="handleLocale")
						el-option(v-for="(lang, i) in langs" :key="i" :value="lang") {{ lang }}


</template>

<script>
import { localizeValidation } from "../../configs/validator";

export default {
  name: "MenuHeader",
  mixins: [],
  data() {
    return { langs: ["PT-BR", "EN"] };
  },
  methods: {
    handleLocale() {
      localizeValidation();
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

				.router-link-active
					display: flex
					align-items: center
					text-decoration: none

				.logo-image
					color: white
					height: 50px
					width: 50px
					margin-right: 10px
					margin-left: 10px
					background: url("../../assets/ipaam-logo.png") no-repeat center
					background-size: contain

				.logo-text
					font-family: "Lato", Helvetica, sans-serif
					font-weight: bold
					color: $--cor-tema-primario
					font-size: $--fonte-titulo

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
