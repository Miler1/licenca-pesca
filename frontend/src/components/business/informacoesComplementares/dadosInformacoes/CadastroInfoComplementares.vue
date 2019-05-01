<template lang="pug">
	#cadastro-info-complementares
		el-form#container(:model="informacoesComplementares"
				:rules="infoRules"
				label-position="top"
				ref="informacoesComplementares")

			el-row(:gutter="24")

				el-col(:span="24")

					el-form-item(:label="$t(`${cadastrar_info_prefix}labels.modalidadePesca`)" prop="modalidadePesca")
						h5.label-notes {{ $t(`${cadastrar_info_prefix}notas.modalidadePescaEsportiva`) }}
						h5.label-notes {{ $t(`${cadastrar_info_prefix}notas.modalidadePescaRecreativa`) }}
						info-select(@value="informacoesComplementares.modalidadePesca = $event"
							:model="informacoesComplementares.modalidadePesca"
							:list="informacoesComplementaresResource.modalidadePesca")
						h5.label-notes-valor {{ valorCarteira() }}
				el-col(:span="24")
					el-form-item(:label="$t(`${cadastrar_info_prefix}labels.localizacaoPreferencialPesca`)" prop="localizacaoPreferencialPesca")
						info-select(@value="informacoesComplementares.localizacaoPreferencialPesca = $event"
							:model="informacoesComplementares.localizacaoPreferencialPesca"
							:list="informacoesComplementaresResource.localizacaoPreferencialPesca")

				el-col(:span="24")

					el-form-item(:label="$t(`${cadastrar_info_prefix}labels.rendaMensal`)" prop="rendaMensal")
						info-select.cor-placeholder(@value="informacoesComplementares.rendaMensal = $event"
							:model="informacoesComplementares.rendaMensal"
							:list="informacoesComplementaresResource.rendaMensal")

				el-col(:span="24")

					el-form-item(:label="$t(`${cadastrar_info_prefix}labels.diasPescaPorAno`)" prop="diasPescaPorAno")
						el-input-number(v-model="informacoesComplementares.diasPescaPorAno"
							:min="1"
							:max="365")

				el-col(:span="24")

					el-form-item(:label="$t(`${cadastrar_info_prefix}labels.gastoMedioPesca`)" prop="gastoMedioPesca")
						h5.label-notes {{ $t(`${cadastrar_info_prefix}notas.gastoMedioPesca`) }}
						.money-input.el-input.el-input-group.el-input-group--prepend
							.el-input-group__prepend R$
							money.v-money.el-input__inner.money-input(v-model="informacoesComplementares.gastoMedioPesca",
								:value="informacoesComplementares.gastoMedioPesca",
								v-bind='money')

				el-col(:span="24")

					el-form-item(:label="$t(`${cadastrar_info_prefix}labels.localPesca`)" prop="localPesca")
						info-select(@value="informacoesComplementares.localPesca = $event"
							:model="informacoesComplementares.localPesca"
							:list="informacoesComplementaresResource.localPesca")

				el-col(:span="24")
					el-form-item(:label="$t(`${cadastrar_info_prefix}labels.peixeMaisPescado`)" prop="peixeMaisPescado")
							info-select.cor-placeholder(@value="informacoesComplementares.peixeMaisPescado = $event"
								:model="informacoesComplementares.peixeMaisPescado"
								:list="informacoesComplementaresResource.peixeMaisPescado")

				el-col(:span="24")
					el-form-item(:label="$t(`${cadastrar_info_prefix}labels.materialPesca`)" prop="materialPesca")
						info-select(@value="informacoesComplementares.materialPesca = $event"
							:model="informacoesComplementares.materialPesca"
							:list="informacoesComplementaresResource.materialPesca")

				el-col(:span="24")
					el-form-item(:label="$t(`${cadastrar_info_prefix}labels.tipoIsca`)" prop="tipoIsca")
						info-select(@value="informacoesComplementares.tipoIsca = $event"
							:model="informacoesComplementares.tipoIsca"
							:list="informacoesComplementaresResource.tipoIsca"
							:tipoIscaDisabled="tipoIscaDisabled" )
						h5(v-if="informacoesComplementares.tipoIsca == 0" class="label-tipo-isca") {{ msgInformativaTipoIsca() }}

				el-col(:span="24")
					el-form-item(:label="$t(`${cadastrar_info_prefix}labels.agenciaTurismo`)" prop="agenciaTurismo")
							info-select(@value="informacoesComplementares.agenciaTurismo = $event"
								:model="informacoesComplementares.agenciaTurismo"
								:list="informacoesComplementaresResource.agenciaTurismo")
</template>

<script>
import Vue from "vue";
import { Money } from "v-money";

import {
  InformacoesComplementaresDTO,
  ModalidadeResource
} from "../../../../model/InformacoesComplementaresDTO";
import { mapGetters } from "vuex";
import VueScrollTo from 'vue-scrollto';
import InfoSelect from "../../../elements/InfoSelect";
import { INFORMACOES_PREFIX } from "../../../../utils/messages/interface/registrar/informacoes/informacoes";
import { INFORMACOES_RULES } from "../../../../utils/validations/informacoes/informacoes_rules";
import { SEND_INFORMACOES_COMPLEMENTARES } from "../../../../store/actions.type";
import { returnStatement } from 'babel-types';
import { numero } from '../../../../utils/validations/pessoa/pessoa_validations';
import { translate } from '../../../../utils/helpers/internationalization';

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
	  modalidade: ModalidadeResource,
			infoRules: INFORMACOES_RULES,
			cadastrar_info_prefix: INFORMACOES_PREFIX,
			valid: false,
			informacoesComplementares: {
				modalidadePesca: null,
				localizacaoPreferencialPesca: null,
				rendaMensal: null,
				diasPescaPorAno: 1,
				gastoMedioPesca: 0,
				localPesca: null,
				materialPesca: null,
				tipoIsca: null,
				agenciaTurismo: null,
				peixeMaisPescado: null
			},
			options: {
				container: '#container',
				easing: 'ease-in',
				offset: -60,
				cancelable: true,
				onStart: function (element) {
				// scrolling started
				},
				onDone: function (element) {
				// scrolling is done
				},
				onCancel: function () {
				// scrolling has been interrupted
				},
				x: false,
				y: true
			},
			tipoIscaDisabled: false
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
		this.valid = valid;
	  });
		},
		enviarParaStore() {
			this.$store.dispatch(
				SEND_INFORMACOES_COMPLEMENTARES, this.informacoesComplementares
			);
		},
		getValid() {
			return this.valid;
		},
		valorCarteira(){
			if (this.informacoesComplementares.modalidadePesca == 0) {
				return translate(
		  `${this.cadastrar_info_prefix}valoresCarteira.modalidades.esportiva`
		);
			} else if (this.informacoesComplementares.modalidadePesca == 1) {
					return translate(
		  `${this.cadastrar_info_prefix}valoresCarteira.modalidades.recreativa`
		);
			}
				return translate(
		  `${this.cadastrar_info_prefix}valoresCarteira.modalidades.mensagemInicial`
		);

		},
		msgInformativaTipoIsca() {
			if(this.informacoesComplementares.tipoIsca == 0) {
				return translate(`${this.cadastrar_info_prefix}informacaoTipoIsca.natural`);
			}
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
		scroll () {
			VueScrollTo.scrollTo('#container', 1, this.options);
		},
	changeToSelect(field) {
	  return field.length > 4;
	}
  },
  created() {
	this.instantiate();
	},
  beforeDestroy() {
		this.enviarParaStore();
  }
};
</script>

<style lang="sass">
	@import "../../../../theme/tools/variables"

	#cadastro-info-complementares

		.label-notes
			margin: -15px 0px 10px 0 !important
			padding: 5px
			height: 20px
			color: $--cor-texto-secundario

		.label-notes-valor
			padding: 5px
			height: 20px
			font-size: 14px;

		.label-tipo-isca
			padding: 5px
			height: 20px
			font-size: 14px;
			line-height: 20px;
			margin-top: 5px;

		.el-radio-button__orig-radio:disabled:checked + .el-radio-button__inner
			background-color: #409EFF
			color: white
		.el-form--label-top .el-form-item__label
			padding: 0 0 1px 0;

		.cor-placeholder
			::-webkit-input-placeholder
				color: #606266;

		.money-input
			width: 250px
</style>
