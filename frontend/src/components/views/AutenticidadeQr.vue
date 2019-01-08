<template lang="pug">
  #autenticidadeQr
    h2.title-autenticidadeQr {{ $t(`${autenticidadeQr_prefix}titulo.tituloInicial`) }}
    card.dadosPessoais

        h3.title {{ $t(`${autenticidadeQr_prefix}titulo.dadosPessoais`) }}

        el-row.section(:gutter="20")
            el-col(:span="10")
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.nome`) }}
                h4.informacoes {{ $t(`${autenticidadeQr_prefix}titulo.label.teste`) }}

            el-col(:span="10")
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.cpf`) }}
                h4.informacoes {{ $t(`${autenticidadeQr_prefix}titulo.label.teste`) }}

    card.dadosLicenca
        h3.title {{ $t(`${autenticidadeQr_prefix}titulo.dadosLicenca`) }}
        
        el-row.section(:gutter="20")
            el-col(:span="10")
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.numeroLicenca`) }}

            el-col(:span="7")
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.modalidade`) }}

            el-col(:span="6")
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.emissao`) }}

            el-col(:span="10")
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.validade`) }}

    card.dadosEndereco
        
        h3.title {{ $t(`${autenticidadeQr_prefix}titulo.enderecoPrincipal`) }}
        
        el-row.section(:gutter="20")
            el-col(:span="20")
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.endereco`) }}

            el-col(:span="10")
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.municipio`) }}

            el-col(:span="7")
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.cep`) }}

            el-col(:span="6")
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.pais`) }}

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

    .align
      display: flex
      flex-direction: column
      padding-top: 20px
      font-weight: bold
    
    .informacoes
        padding-top: 8px

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
