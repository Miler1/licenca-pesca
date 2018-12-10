<template lang="pug">
	#identificacao-step
		.acesso
			h4.label-search {{ $t("interface.registrar.identificacao.acesso.label.search") }}
			.search
				input-element(
				:placeholder="$t('interface.registrar.identificacao.acesso.placeholder.cpf')"
				v-model="resource"
				v-if="type_acesso === 'CPF'"
				:mask="maskCPF"
				@enter="acessar")
					el-select(v-model="type_acesso" slot="prepend" @change="resource = ''")
						el-option(:label="$t('interface.registrar.identificacao.acesso.select.cpf')" value="CPF")
						el-option(:label="$t('interface.registrar.identificacao.acesso.select.passaporte')" value="PASSAPORTE")
					el-button.search-button(slot="append" icon="el-icon-search" @click="acessar" type="primary" :disabled="resource === ''")
				input-element(
				:placeholder="$t('interface.registrar.identificacao.acesso.placeholder.passaporte')"
				v-model="resource"
				v-if="type_acesso !== 'CPF'"
				:mask="maskPassport"
				@enter="acessar")
					el-select(v-model="type_acesso" slot="prepend" @change="resource = ''")
						el-option(:label="$t('interface.registrar.identificacao.acesso.select.cpf')" value="CPF")
						el-option(:label="$t('interface.registrar.identificacao.acesso.select.passaporte')" value="PASSAPORTE")
					el-button.search-button(slot="append" icon="el-icon-search" @click="acessar" type="primary" :disabled="resource === ''")

			.data
				cadastrar-dados-pessoa(v-if="showCadastro()")
				visualizar-dados-pessoa(:pessoa="solicitante" v-if="showVisualizar()")
</template>

<script>
import { mapGetters } from "vuex";
import { ACESSAR } from "../../../store/actions.type";
import { CPF_MASK, PASSAPORT_MASK } from "../../../utils/layout/mascaras";
import InputElement from "../../elements/InputElement";
import CadastrarDadosPessoa from "./dadosPessoa/CadastrarDadosPessoa";
import VisualizarDadosPessoa from "./dadosPessoa/VisualizarDadosPessoa";

export default {
  name: "IdentificationStep",

  components: {
    InputElement,
    CadastrarDadosPessoa,
    VisualizarDadosPessoa
  },

  data() {
    return {
      resource: "",
      type_acesso: "CPF",
      maskCPF: CPF_MASK,
      maskPassport: PASSAPORT_MASK
    };
  },

  computed: {
    ...mapGetters(["solicitante", "cadastroCanActive", "existeSolicitante"])
  },

  methods: {
    acessar() {
      this.$store.dispatch(ACESSAR, this.generateAcessoResource(this.resource));
    },

    generateAcessoResource(resource) {
      let cpf = null;
      let passaporte = null;
      if (this.type_acesso === "CPF") {
        cpf = resource;
      } else {
        passaporte = resource;
      }

      return { cpf, passaporte };
    },

    showCadastro() {
      return this.cadastroCanActive;
    },

    showVisualizar() {
      return this.existeSolicitante && !this.cadastroCanActive;
    }
  }
};
</script>

<style lang="sass">
	#identificacao-step
</style>
