<template lang="pug">
    #cadastro-info-complementares
        el-form#container(:model="informacoesComplementares" :rules="infoRules" label-position="top" ref="informacoesComplementares")

            el-row(:gutter="20")

                el-col(:span="24")

                    el-form-item(:label="$t(`${cadastrar_info_prefix}labels.modalidadePesca`)")
                        el-radio-group(v-model="informacoesComplementares.modalidadePesca")
                            el-radio-button(v-for="l in informacoesComplementaresResource.modalidadePesca" :key="l.cod" :label="l.cod" disabled) {{ localizeField(l) }}
                            
                el-col(:span="24")

                    el-form-item(:label="$t(`${cadastrar_info_prefix}labels.localizacaoPreferencialPesca`)" prop="localPesca")
                        el-radio-group(v-model="informacoesComplementares.localizacaoPreferencialPesca")
                            el-radio-button(v-for="l in informacoesComplementaresResource.localizacaoPreferencialPesca" :key="l.cod" :label="l.cod") {{ localizeField(l) }}
                
                el-col(:span="24")

                    el-form-item(:label="$t(`${cadastrar_info_prefix}labels.rendaMensal`)" prop="rendaMensal")
                        el-select(v-model="informacoesComplementares.rendaMensal")
                            el-option(v-for="l in informacoesComplementaresResource.rendaMensal" :key="l.cod" :value="l.cod" :label="localizeField(l)")
                
                el-col(:span="24")

                    el-form-item(:label="$t(`${cadastrar_info_prefix}labels.diasPescaPorAno`)" prop="diasPescaPorAno")
                        el-input-number(v-model="informacoesComplementares.diasPescaPorAno" :min="1" :max="365")

                el-col(:span="24")

                    el-form-item(:label="$t(`${cadastrar_info_prefix}labels.gastoMedioPesca`)" prop="gastoMedioPesca")
                        h5.label-notes {{ $t(`${cadastrar_info_prefix}notas.gastoMedioPesca`) }}
                        .money-input.el-input.el-input-group.el-input-group--prepend
                            .el-input-group__prepend R$
                            money.v-money.el-input__inner.money-input(v-model="informacoesComplementares.gastoMedioPesca" , :value="informacoesComplementares.gastoMedioPesca", v-bind='money')

                el-col(:span="24")

                    el-form-item(:label="$t(`${cadastrar_info_prefix}labels.faixaEtaria`)" prop="faixaEtaria")
                        el-select(v-model="informacoesComplementares.faixaEtaria")
                            el-option(v-for="l in informacoesComplementaresResource.faixaEtaria" :key="l.cod" :value="l.cod" :label="localizeField(l)")

                el-col(:span="24")

                    el-form-item(:label="$t(`${cadastrar_info_prefix}labels.localPesca`)" prop="localPesca")
                        el-radio-group(v-model="informacoesComplementares.localPesca")  
                            el-radio-button(v-for="l in informacoesComplementaresResource.localPesca" :key="l.cod" :label="l.cod") {{ localizeField(l) }}

                el-col(:span="24")

                    el-form-item(:label="$t(`${cadastrar_info_prefix}labels.materialPesca`)" prop="materialPesca")
                        el-radio-group(v-model="informacoesComplementares.materialPesca")  
                            el-radio-button(v-for="l in informacoesComplementaresResource.materialPesca" :key="l.cod" :label="l.cod") {{ localizeField(l) }}
                

                el-col(:span="24")

                    el-form-item(:label="$t(`${cadastrar_info_prefix}labels.tipoIsca`)" prop="tipoIsca")
                        el-radio-group(v-model="informacoesComplementares.tipoIsca")  
                            el-radio-button(v-for="l in informacoesComplementaresResource.tipoIsca" :key="l.cod" :label="l.cod") {{ localizeField(l) }}

                el-col(:span="24")

                    el-form-item(:label="$t(`${cadastrar_info_prefix}labels.agenciaTurismo`)" prop="agenciaTurismo")
                        el-radio-group(v-model="informacoesComplementares.agenciaTurismo")  
                            el-radio-button(v-for="l in informacoesComplementaresResource.agenciaTurismo" :key="l.cod" :label="l.cod") {{ localizeField(l) }}

                el-col(:span="24")

                    el-form-item(:label="$t(`${cadastrar_info_prefix}labels.peixeMaisPescado`)" prop="peixeMaisPescado")
                        el-select(v-model="informacoesComplementares.peixeMaisPescado")
                            el-option(v-for="l in informacoesComplementaresResource.peixeMaisPescado" :key="l.cod" :value="l.cod" :label="localizeField(l)")

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

export default {
  name: "RenovarInfoComplementares",
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
                faixaEtaria: null,
                localPesca: null,
                materialPesca: null,
                tipoIsca: null,
                modalidadeMaisPraticada: null,
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

        };
  },

  computed: {
    ...mapGetters(["informacoesComplementaresResource", "licenca"])
    },
    
    watch: {
        licenca (novaLicenca) {
            this.informacoesComplementares = JSON.parse(JSON.stringify(novaLicenca.informacaoComplementar));
            this.informacoesComplementares.modalidadePesca = this.informacoesComplementares.modalidadePesca.id;
            this.informacoesComplementares.localizacaoPreferencialPesca = this.informacoesComplementares.localizacaoPreferencialPesca.id;
            this.informacoesComplementares.localPesca = this.informacoesComplementares.localPesca.id;
            this.informacoesComplementares.rendaMensal = this.informacoesComplementares.rendaMensal.id;
            this.informacoesComplementares.faixaEtaria = this.informacoesComplementares.faixaEtaria.id;
            this.informacoesComplementares.materialPesca = this.informacoesComplementares.materialPesca.id;
            this.informacoesComplementares.tipoIsca = this.informacoesComplementares.tipoIsca.id;
            this.informacoesComplementares.peixeMaisPescado = this.informacoesComplementares.peixeMaisPescado.id;
            if(this.informacoesComplementares.agenciaTurismo){
                this.informacoesComplementares.agenciaTurismo = 0;
            } else {
                this.informacoesComplementares.agenciaTurismo = 1;
            }
        }
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
        // this.enviarParaStore();
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
        
        .el-radio-button__orig-radio:disabled:checked + .el-radio-button__inner
            background-color: #409EFF
            color: white
</style>
