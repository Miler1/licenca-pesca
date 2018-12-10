<template lang="pug">
	#visualizar-info-complementares
		h3.title {{ $t(`${visualizar_prefix}titulos.informacoesComplementares`) }}

		el-row.section(:gutter="20")
			el-col(:span="24")
				.align
					h4.label {{ $t(`${visualizar_prefix}labels.modalidadePesca`) }}
					h4(:class="{'not-informed': !exist(info.modalidade)}") {{ localeData(informacoesComplementaresResource.modalidadePesca, info.modalidade) | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

		el-row.section(:gutter="20")
			el-col(:span="24")
				.align
					h4.label {{ $t(`${visualizar_prefix}labels.localizacaoPreferencialPesca`) }}
					h4(:class="{'not-informed': !exist(info.localizacaoPreferencialPesca)}") {{ localeData(informacoesComplementaresResource.localizacaoPreferencialPesca, info.localizacaoPreferencialPesca) | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

		el-row.section(:gutter="20")
			el-col(:span="24")
				.align
					h4.label {{ $t(`${visualizar_prefix}labels.rendaMensal`) }}
					h4(:class="{'not-informed': !exist(info.rendaMensal)}") {{ localeData(informacoesComplementaresResource.rendaMensal, info.rendaMensal) | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

		el-row.section(:gutter="20")
			el-col(:span="24")
				.align
					h4.label {{ $t(`${visualizar_prefix}labels.diasPescaPorAno`) }}
					h4(:class="{'not-informed': !exist(info.diasPescaPorAno)}") {{ exist(info.diasPescaPorAno) ? `${info.diasPescaPorAno} ${$t(`${visualizar_prefix}dia`)}${pluralize(info.diasPescaPorAno)}` : $t(`${visualizar_prefix}naoInformado`) }}

		el-row.section(:gutter="20")
			el-col(:span="24")
				.align
					h4.label {{ $t(`${visualizar_prefix}labels.gastoMedioPesca`) }}
					h4(:class="{'not-informed': !exist(info.gastoMedioPesca)}") {{ exist(info.gastoMedioPesca) ? `R$ ${info.gastoMedioPesca}` : $t(`${visualizar_prefix}naoInformado`) }}

		el-row.section(:gutter="20")
			el-col(:span="24")
				.align
					h4.label {{ $t(`${visualizar_prefix}labels.faixaEtaria`) }}
					h4(:class="{'not-informed': !exist(info.faixaEtaria)}") {{ localeData(informacoesComplementaresResource.faixaEtaria, info.faixaEtaria) | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

		el-row.section(:gutter="20")
			el-col(:span="24")
				.align
					h4.label {{ $t(`${visualizar_prefix}labels.localizacaoPreferencialPesca`) }}
					h4(:class="{'not-informed': !exist(info.localizacaoPreferencialPesca)}") {{ localeData(informacoesComplementaresResource.localizacaoPreferencialPesca, info.localizacaoPreferencialPesca) | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

		el-row.section(:gutter="20")
			el-col(:span="24")
				.align
					h4.label {{ $t(`${visualizar_prefix}labels.materialPesca`) }}
					h4(:class="{'not-informed': !exist(info.materialPesca)}") {{ localeData(informacoesComplementaresResource.materialPesca, info.materialPesca) | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

		el-row.section(:gutter="20")
			el-col(:span="24")
				.align
					h4.label {{ $t(`${visualizar_prefix}labels.tipoIsca`) }}
					h4(:class="{'not-informed': !exist(info.tipoIsca)}") {{ localeData(informacoesComplementaresResource.tipoIsca, info.materialPesca) | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

		el-row.section(:gutter="20")
			el-col(:span="24")
				.align
					h4.label {{ $t(`${visualizar_prefix}labels.agenciaTurismo`) }}
					h4(:class="{'not-informed': !exist(info.agenciaTurismo)}") {{ localeData(informacoesComplementaresResource.agenciaTurismo, info.materialPesca) | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

</template>

<script>
import { mapGetters } from "vuex";
import * as _ from "lodash";
import { INFORMACOES_PREFIX } from "../../../../utils/messages/interface/registrar/informacoes/informacoes";

export default {
  name: "VisualizarInfoComplementares",

  props: {
    info: Object
  },

  data() {
    return {
      visualizar_prefix: INFORMACOES_PREFIX
    };
  },

  computed: {
    ...mapGetters(["informacoesComplementaresResource"])
  },

  methods: {
    exist(attr) {
      return attr !== null || !_.isEmpty(attr) || !_.isNil(attr);
    },

    localeData(resource, data) {
      if (data === null) return null;

      switch (this.$i18n.locale) {
        case "EN":
          return resource[data].descricao_en;
        case "PT-BR":
          return resource[data].descricao_pt;
        case "ES":
          return resource[data].descricao_es;
      }
    },

    pluralize(value) {
      return value > 1 ? "s" : "";
    }
  }
};
</script>

<style lang="sass">
	@import "../../../../theme/tools/variables"

	#visualizar-info-complementares
		.title
			margin-top: 30px

		.section
			margin-top: 20px

		.align
			display: flex
			flex-direction: column

			.label
				margin-bottom: 5px
				color: $--cor-texto-secundario

		.not-informed
			font-style: italic
</style>
