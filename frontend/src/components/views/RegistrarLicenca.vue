<template lang="pug">
	#registrar-licenca
		h2 {{ $t("interface.registrar.geral.titulo") }}
		card
			el-steps(:active="step" :space="500" simple)
				el-step(:title="$t('interface.registrar.geral.steps.indices.identificacao')" icon="el-icon-search")
				el-step(:title="$t('interface.registrar.geral.steps.indices.informacoes')" icon="el-icon-edit-outline")
				el-step(:title="$t('interface.registrar.geral.steps.indices.resumo')" icon="el-icon-document")

			h4.label-search {{ $t("interface.registrar.identificacao.acesso.label.search") }}
			.search
				input-element(
				:placeholder="$t('interface.registrar.identificacao.acesso.placeholder.cpf')"
				v-model="resource"
				v-if="type === '1'"
				:mask="maskCPF"
				@enter="acessar")
					el-select(v-model="type" slot="prepend" @change="resource = ''")
						el-option(:label="$t('interface.registrar.identificacao.acesso.select.cpf')" value="1")
						el-option(:label="$t('interface.registrar.identificacao.acesso.select.passaporte')" value="2")
					el-button.search-button(slot="append" icon="el-icon-search" @click="acessar" type="primary" :disabled="resource === ''")
				input-element(
				:placeholder="$t('interface.registrar.identificacao.acesso.placeholder.passaporte')"
				v-model="resource"
				v-if="type !== '1'"
				:mask="unmask"
				@enter="acessar")
					el-select(v-model="type" slot="prepend" @change="resource = ''")
						el-option(:label="$t('interface.registrar.identificacao.acesso.select.cpf')" value="1")
						el-option(:label="$t('interface.registrar.identificacao.acesso.select.passaporte')" value="2")
					el-button.search-button(slot="append" icon="el-icon-search" @click="acessar" type="primary" :disabled="resource === ''")

			.data
				cadastrar-dados-pessoa
				visualizar-dados-pessoa(:pessoa="solicitante" v-if="existSolicitante && step === 0")

			.footer-card
				.left
					el-button(icon="el-icon-arrow-left" type="primary" plain @click="prevStep" v-if="step !== 0") {{ $t("interface.registrar.geral.steps.botoes.voltar") }}
					el-button(icon="el-icon-close") {{ $t("interface.registrar.geral.steps.botoes.cancelar") }}
				.center
					h4.footer-label {{ $t("interface.registrar.geral.steps.label", [step + 1, 3]) }}
				.right
					el-button(icon="el-icon-check" type="primary" v-if="step === 2") {{ $t("interface.registrar.geral.steps.botoes.concluir") }}
					el-button(icon="el-icon-arrow-right" type="primary" @click="nextStep" v-if="step !== 2") {{ $t("interface.registrar.geral.steps.botoes.proxima") }}
</template>

<script>
import Card from "../layouts/Card";
import { ACESSAR } from "../../store/actions.type";
import InputElement from "../elements/InputElement";
import VisualizarDadosPessoa from "../data/VisualizarDadosPessoa";
import { mapGetters } from "vuex";
import CadastrarDadosPessoa from "../data/CadastrarDadosPessoa";

export default {
  name: "RegistrarLicenca",
  components: {
    CadastrarDadosPessoa,
    VisualizarDadosPessoa,
    InputElement,
    Card
  },
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
      },
      step: 0
    };
  },

  computed: {
    ...mapGetters(["solicitante", "existSolicitante"])
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
      if (this.type === "1") {
        cpf = resource;
      } else {
        passaporte = resource;
      }

      return { cpf, passaporte };
    },

    nextStep() {
      if (this.step++ >= 2) this.step = 2;
    },

    prevStep() {
      if (this.step-- <= 0) this.step = 0;
    }
  }
};
</script>

<style lang="sass">
	@import "../../theme/tools/variables"

	#registrar-licenca
		h1
			font-weight: 500

		.label-search
			margin-top: 30px

		.el-steps
			height: 30px

		.el-input__icon.el-icon-search
			color: $--cor-tema-primario

		.el-step:first-of-type
			flex-basis: 33% !important

		.search-button.is-disabled
			background-color: $--cor-background
			color: $--cor-texto-secundario

		.search-button
			background-color: $--cor-tema-primario
			color: white

		.search
			margin-top: 10px
			display: flex

			.el-select
				width: 150px

		.footer-card
			margin-top: 30px
			border-top: $--cor-borda 1px solid
			padding-top: 20px
			display: flex
			align-items: center
			justify-content: space-between

			.footer-label
				font-size: $--fonte-pequena
				color: $--cor-texto-secundario

</style>
