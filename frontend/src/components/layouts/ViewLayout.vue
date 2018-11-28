<template lang="pug">
	#view-layout
		.main
			slot
</template>

<script>
export default {
  name: "viewLayout"
};
</script>

<style lang="sass">

	#view-layout
		display: flex
		justify-content: center
		height: 100%

		.main
			margin-top: 15px
			width: 1200px
			height: 100%
			padding: 0 10px

</style>
