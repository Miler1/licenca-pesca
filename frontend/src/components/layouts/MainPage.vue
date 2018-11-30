<template lang="pug">
	#main-wrapper
		el-container
			el-header(height="70px")
				menu-header
			el-main
				view-layout
					router-view
			el-footer {{ $t("interface.geral.footer") }}
</template>

<script>
import MenuHeader from "./MenuHeader";
import ViewLayout from "./ViewLayout";
export default {
  name: "MainPage",
  components: { ViewLayout, MenuHeader }
};
</script>

<style lang="sass">
	@import '../../theme/tools/variables'

	#main-wrapper, .el-container
		height: 100vh
		width: 100vw

	.el-footer
		display: flex
		align-items: center
		justify-content: center
		color: $--cor-texto-primario
		margin-top: 15px

	.el-header
		background-color: white
		padding: 0 !important
		z-index: 1000

</style>
