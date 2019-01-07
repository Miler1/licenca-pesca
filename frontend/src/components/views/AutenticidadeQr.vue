<template lang="pug">
  #autenticidadeQr
    h2.title-autenticidadeQr {{ $t(`${autenticidadeQr_prefix}titulo.tituloInicial`) }}
    card.dadosPessoais

        h3.title {{ $t(`${autenticidadeQr_prefix}titulo.dadosPessoais`) }}

        //- el-row.section(:gutter="20")
        //-     el-col(:span="6" v-if="!exist(pessoa.nome)")
        //-         .align
        //-             h4.label {{ $t(`${visualizar_prefix}label.nome`) }}
        //-             h4(:class="{'not-informed': exist(pessoa.nome)}") {{ pessoa.nome | placeholder($t(`${visualizar_prefix}naoInformado`)) }}
            
        //-     el-col(:span="6" v-if="!exist(pessoa.cpf)")
        //-         .align
        //-             h4.label {{ $t(`${visualizar_prefix}label.cpf`) }}
        //-             h4(:class="{'not-informed': exist(pessoa.cpf)}") {{ pessoa.cpf | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

        //-     el-col(:span="6" v-if="!exist(pessoa.passaporte)")
        //-         .align
        //-             h4.label {{ $t(`${visualizar_prefix}label.passaporte`) }}
        //-             h4(:class="{'not-informed': exist(pessoa.passaporte)}") {{ pessoa.passaporte | placeholder($t(`${visualizar_prefix}naoInformado`)) }}
    
    card.dadosLicenca
        h3.title {{ $t(`${autenticidadeQr_prefix}titulo.dadosLicenca`) }}

    card.dadosEndereco
        
        h3.title {{ $t(`${autenticidadeQr_prefix}titulo.enderecoPrincipal`) }}

        //- el-row.section(:gutter="20")

        //-     //- concatenar logadouro+numero+complemento+bairro
        //- el-col(:span="6" v-if="!exist(pessoa.enderecoPrincipal.logradouro)")
        //-     .align
        //-         h4.label {{ $t(`${visualizar_prefix}label.logradouro`) }}
        //-         h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.logradouro)}") {{ pessoa.enderecoPrincipal.logradouro | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

        //- el-col(:span="6" v-if="!exist(pessoa.enderecoPrincipal.numero)")
        //-     .align
        //-         h4.label {{ $t(`${visualizar_prefix}label.numero`) }}
        //-         h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.numero)}") {{ pessoa.enderecoPrincipal.numero | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

        //- el-col(:span="6" v-if="!exist(pessoa.enderecoPrincipal.bairro)")
        //-     .align
        //-         h4.label {{ $t(`${visualizar_prefix}label.bairro`) }}
        //-         h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.bairro)}") {{ pessoa.enderecoPrincipal.bairro | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

        //- el-row.section(:gutter="20")

        //- el-col(:span="6" v-if="!exist(pessoa.enderecoPrincipal.complemento)")
        //-     .align
        //-         h4.label {{ $t(`${visualizar_prefix}label.complemento`) }}
        //-         h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.complemento)}") {{ pessoa.enderecoPrincipal.complemento | placeholder($t(`${visualizar_prefix}naoInformado`)) }}
            
        //-     //- fim

        //- el-col(:span="6" v-if="!exist(pessoa.enderecoPrincipal.cep)")
        //-     .align
        //-         h4.label {{ $t(`${visualizar_prefix}label.cep`) }}
        //-         h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.cep)}") {{ pessoa.enderecoPrincipal.cep | placeholder($t(`${visualizar_prefix}naoInformado`)) }}
            
        //-     //- concatenar municipio+UF
        //- el-col(:span="6" v-if="!exist(pessoa.enderecoPrincipal.municipio)")
        //-     .align
        //-         h4.label {{ $t(`${visualizar_prefix}label.municipio`) }}
        //-         h4(v-if="pessoa.enderecoPrincipal.municipio.nome" :class="{'not-informed': exist(pessoa.enderecoPrincipal.municipio.nome)}") {{ pessoa.enderecoPrincipal.municipio.nome | placeholder($t(`${visualizar_prefix}naoInformado`)) }}
        //-         h4(v-else :class="{'not-informed': exist(pessoa.enderecoPrincipal.municipioNome)}") {{ pessoa.enderecoPrincipal.municipioNome | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

        //- el-col(:span="6" v-if="!exist(pessoa.enderecoPrincipal.uf)")
        //-     .align
        //-         h4.label {{ $t(`${visualizar_prefix}label.uf`) }}
        //-         h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.uf)}") {{ pessoa.enderecoPrincipal.uf | placeholder($t(`${visualizar_prefix}naoInformado`)) }}
            
        //-     //- add pais
        

</template>

<script>
import { mapGetters } from "vuex";
import Card from "../layouts/Card";
import { INTERFACE_AUTENTICIDADE_PREFIX } from "../../utils/messages/interface/registrar/autenticidadeQr/autenticidadeQr";
import { translate } from "../../utils/helpers/internationalization";
import Properties from "../../properties";

export default {
  name: "AutenticidadeQr",

    components: {
        Card
    },
    
    props: {
        pessoa: Object
    },

    data() {
        return {
            autenticidadeQr_prefix: INTERFACE_AUTENTICIDADE_PREFIX
        };
    },
    
    methods: {
        exist(attr) {
        return attr === null || _.isNil(attr);
        },
     }
};
</script>

<style lang="sass">
  @import "../../theme/tools/variables"

  #autenticidadeQr
    h1
      font-weight: 500

    .label-search
      margin-top: 30px

    .el-steps
      height: 30px

    .title
      font-weight: bold
      font-size: 18px

    .title-autenticidadeQr
        text-align: center
        font-weight: bold
        font-size: 30px

    
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
