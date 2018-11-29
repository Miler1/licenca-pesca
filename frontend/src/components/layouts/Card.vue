<template lang="pug">
	#card
		slot
</template>

<script>
export default {
  name: "Card"
};
</script>

<style lang="sass">
	@import "../../theme/tools/variables"

	#card
		height: 100%
		border-radius: 5px
		background-color: white
		border: solid 1px $--cor-borda
		padding: 20px
		margin-top: 20px

</style>
