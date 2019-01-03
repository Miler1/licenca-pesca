<template lang="pug">
	#info-select
		el-radio-group(v-model="model" v-if="!changeToSelect(list)")
			el-radio-button(v-for="l in list" :key="l.cod" :label="l.cod") {{ localizeField(l) }}
		el-select(v-model="model" v-if="changeToSelect(list)" :loading="list.length === 0")
			el-option(v-for="l in list" :key="l.cod" :value="l.cod" :label="localizeField(l)")

</template>

<script>
export default {
  name: "InfoSelect",

  props: {
    list: Array,
    model: Number
  },

  watch: {
    model(value) {
      this.$emit("value", value);
    }
  },

  methods: {
    localizeField(field) {
      switch (this.$i18n.locale) {
        case "EN":
          return field.descricao_en;
        case "PT-BR":
          return field.descricao_pt;
        case "ES":
          return field.descricao_es;
      }
    },

    changeToSelect(list) {
      return list.length > 4 || list.length === 0;
    }
  }
};
</script>

<style lang="sass">
	#info-select
</style>
