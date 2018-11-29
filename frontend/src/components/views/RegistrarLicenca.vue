<template lang="pug">
	#registrar-licenca
		h2 {{ $t("message.registerTitle") }}
		card
			el-steps(:active="0" :space="500" simple)
				el-step(:title="$t('message.register.steps.identification')" icon="el-icon-search")
				el-step(:title="$t('message.register.steps.info')" icon="el-icon-edit-outline")
				el-step(:title="$t('message.register.steps.summary')" icon="el-icon-document")
			h4 {{ $t("message.register.access.search") }}

			.search
				input-element(
				:placeholder="$t('message.register.access.placeholder.cpf')"
				v-model="resource"
				v-if="type === '1'"
				:mask="maskCPF")
					el-select(v-model="type" slot="prepend" @change="resource = ''")
						el-option(:label="$t('message.register.access.cpf')" value="1")
						el-option(:label="$t('message.register.access.passport')" value="2")
					el-button(slot="append" icon="el-icon-search" @click="acessar" :disabled="resource === ''")
				input-element(
				:placeholder="$t('message.register.access.placeholder.passport')"
				v-model="resource"
				v-if="type !== '1'"
				:mask="unmask")
					el-select(v-model="type" slot="prepend" @change="resource = ''")
						el-option(:label="$t('message.register.access.cpf')" value="1")
						el-option(:label="$t('message.register.access.passport')" value="2")
					el-button(slot="append" icon="el-icon-search" @click="acessar" :disabled="resource === ''")
</template>

<script>
import Card from "../layouts/Card";
import { ACESSAR } from "../../store/actions.type";
import InputElement from "../elements/InputElement";

export default {
  name: "RegistrarLicenca",
  components: { InputElement, Card },
  data() {
    return {
      resource: "",
      type: "1",
      maskCPF: {
        mask: "000.000.000-00",
        clearIfNotMatch: true
      },
      unmask: {
        mask: "AAAAAAAAAAAAAAAAAAAAA"
      }
    };
  },
  methods: {
    /**
     * Método de acesso.
     */
    acessar() {
      this.$store.dispatch(ACESSAR, this.generateAcessoResource(this.resource));
    },

    generateAcessoResource(resource) {
      let cpf = null;
      let passaporte = null;
      if (resource.toString().length === 11) {
        cpf = resource;
      } else {
        passaporte = resource;
      }

      return { cpf, passaporte };
    },

    formatar() {
      this.resource = "this.resource";
    }
  }
};
</script>

<style lang="sass">
	@import "../../theme/tools/variables"

	#registrar-licenca
		h1
			font-weight: 500

		h4
			margin-top: 30px

		.el-steps
			height: 30px

		.el-input__icon.el-icon-search
			color: $--cor-tema-primario

		.el-step:first-of-type
			flex-basis: 33% !important

		.search
			margin-top: 10px
			display: flex

			.el-select
				width: 150px

</style>
