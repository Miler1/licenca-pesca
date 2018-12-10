<template lang="pug">
	#status-badge
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "StatusBadge",

  props: {
    status: Number
  },

  computed: {
    ...mapGetters(["status"])
  },

  methods: {
    handleStatus(status) {
      return true;
    }
  }
};
</script>

<style lang="sass">
	#status-badge
</style>
