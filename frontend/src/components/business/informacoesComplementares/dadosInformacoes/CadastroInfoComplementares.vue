<template lang="pug">
	#cadastro-info-complementares
		el-form(:model="informacoesComplementares" :rules="infoRules" label-position="top" ref="informacoesComplementares")

			el-row(:gutter="20")

				el-col(:span="24")

					el-form-item(:label="$t(`${cadastrar_info_prefix}labels.modalidadePesca`)" prop="modalidadePesca")
						h5.label-notes {{ $t(`${cadastrar_info_prefix}notas.modalidadePesca`) }}
						info-select(@value="informacoesComplementares.modalidadePesca = $event" :list="informacoesComplementaresResource.modalidadePesca")

				el-col(:span="24")

					el-form-item(:label="$t(`${cadastrar_info_prefix}labels.localizacaoPreferencialPesca`)" prop="localizacaoPreferencialPesca")
						info-select(@value="informacoesComplementares.localizacaoPreferencialPesca = $event" :list="informacoesComplementaresResource.localizacaoPreferencialPesca")

				el-col(:span="24")

					el-form-item(:label="$t(`${cadastrar_info_prefix}labels.rendaMensal`)" prop="rendaMensal")
						info-select(@value="informacoesComplementares.rendaMensal = $event" :list="informacoesComplementaresResource.rendaMensal")

				el-col(:span="24")

					el-form-item(:label="$t(`${cadastrar_info_prefix}labels.diasPescaPorAno`)" prop="diasPescaPorAno")
						el-input-number(v-model="informacoesComplementares.diasPescaPorAno" :min="0" :max="365")

				el-col(:span="24")

					el-form-item(:label="$t(`${cadastrar_info_prefix}labels.gastoMedioPesca`)" prop="gastoMedioPesca")
						h5.label-notes {{ $t(`${cadastrar_info_prefix}notas.gastoMedioPesca`) }}
						.money-input.el-input.el-input-group.el-input-group--prepend
							.el-input-group__prepend R$
							money.v-money.el-input__inner.money-input(v-model="informacoesComplementares.gastoMedioPesca" , v-bind='money')

				el-col(:span="24")

					el-form-item(:label="$t(`${cadastrar_info_prefix}labels.faixaEtaria`)" prop="faixaEtaria")
						info-select(@value="informacoesComplementares.faixaEtaria = $event" :list="informacoesComplementaresResource.faixaEtaria")

				el-col(:span="24")

					el-form-item(:label="$t(`${cadastrar_info_prefix}labels.localPesca`)" prop="localPesca")
						info-select(@value="informacoesComplementares.localPesca = $event" :list="informacoesComplementaresResource.localPesca")

				el-col(:span="24")

					el-form-item(:label="$t(`${cadastrar_info_prefix}labels.materialPesca`)" prop="materialPesca")
						info-select(@value="informacoesComplementares.materialPesca = $event" :list="informacoesComplementaresResource.materialPesca")

				el-col(:span="24")

					el-form-item(:label="$t(`${cadastrar_info_prefix}labels.tipoIsca`)" prop="tipoIsca")
						info-select(@value="informacoesComplementares.tipoIsca = $event" :list="informacoesComplementaresResource.tipoIsca")

				el-col(:span="24")

					el-form-item(:label="$t(`${cadastrar_info_prefix}labels.modalidadeMaisPraticada`)" prop="modalidadeMaisPraticada")
						info-select(@value="informacoesComplementares.modalidadeMaisPraticada = $event" :list="informacoesComplementaresResource.modalidadeMaisPraticada")

				el-col(:span="24")

					el-form-item(:label="$t(`${cadastrar_info_prefix}labels.agenciaTurismo`)" prop="agenciaTurismo")
						el-radio-group(v-model="informacoesComplementares.agenciaTurismo")
							el-radio-button(v-for="l in informacoesComplementaresResource.agenciaTurismo" :key="l.cod" :label="l.value") {{ localizeField(l) }}

</template>

<script>
import Vue from "vue";
import { Money } from "v-money";

import {
  InformacoesComplementaresDTO,
  ModalidadeResource
} from "../../../../model/InformacoesComplementaresDTO";
import { mapGetters } from "vuex";
import InfoSelect from "../../../elements/InfoSelect";
import { INFORMACOES_PREFIX } from "../../../../utils/messages/interface/registrar/informacoes/informacoes";
import { INFORMACOES_RULES } from "../../../../utils/validations/informacoes/informacoes_rules";
import { SEND_INFORMACOES_COMPLEMENTARES } from "../../../../store/actions.type";

export default {
  name: "CadastroInfoComplementares",
	components: { InfoSelect, money: Money },
  data() {
    return {
			money: {
				decimal: ",",
				thousands: ".",
				precision: 2,
				masked: false
			},
      informacoesComplementares: InformacoesComplementaresDTO,
      modalidade: ModalidadeResource,
      infoRules: INFORMACOES_RULES,
			cadastrar_info_prefix: INFORMACOES_PREFIX,
			valid: false
    };
  },

  computed: {
    ...mapGetters(["informacoesComplementaresResource"])
  },

  methods: {
    instantiate() {
      Vue.prototype.$cadastroInfo = this;
    },
		validate() {
			this.valid = false;
      this.$refs["informacoesComplementares"].validate((valid) => {
				console.log('valide', valid);
        this.valid = valid;
      });
		},
		getValid() {
			return this.valid;
		},
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

    changeToSelect(field) {
      return field.length > 4;
    }
  },

  created() {
    this.instantiate();
  },

  beforeDestroy() {
    this.$store.dispatch(
      SEND_INFORMACOES_COMPLEMENTARES,
      this.informacoesComplementares
    );
  }
};
</script>

<style lang="sass">
	@import "../../../../theme/tools/variables"

	#cadastro-info-complementares

		.label-notes
			margin: -25px 0 10px 0px
			padding: 0
			height: 20px
			color: $--cor-texto-secundario

		.money-input
			width: 250px

</style>
