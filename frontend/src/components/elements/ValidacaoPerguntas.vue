<template lang="pug">
  #validacao-perguntas
    h2.title-autenticidadeQr {{ $t(`${validacao_prefix}titulo.tituloInicial`) }}
    card.dadosPessoais

        h3.title {{ $t(`${validacao_prefix}titulo.dadosPessoais`) }}

        el-row.section(:gutter="20")
            el-col(:span="10")
                h4.align {{ $t(`${validacao_prefix}titulo.label.nome`) }}
               

            el-col(:span="10")
                h4.align {{ $t(`${validacao_prefix}titulo.label.cpf`) }}
               

    card.dadosLicenca
        h3.title {{ $t(`${validacao_prefix}titulo.dadosLicenca`) }}
        
        el-row.section(:gutter="20")
            el-col(:span="10")
                h4.align {{ $t(`${validacao_prefix}titulo.label.numeroLicenca`) }}
                

            el-col(:span="7")
                h4.align {{ $t(`${validacao_prefix}titulo.label.modalidade`) }}
               

            el-col(:span="6")
                h4.align {{ $t(`${validacao_prefix}titulo.label.emissao`) }}
                

            el-col(:span="10")
                h4.align {{ $t(`${validacao_prefix}titulo.label.validade`) }}
               

            el-col(:span="10")
                h4.status {{ $t(`${validacao_prefix}titulo.label.validade`) }}
               

    card.dadosEndereco
        
        h3.title {{ $t(`${validacao_prefix}titulo.enderecoPrincipal`) }}
        
        el-row.section(:gutter="20")
            el-col(:span="20")
                h4.align {{ $t(`${validacao_prefix}titulo.label.endereco`) }}
                
            el-col(:span="10")
                h4.align {{ $t(`${validacao_prefix}titulo.label.municipio`) }}
                
            el-col(:span="7")
                h4.align {{ $t(`${validacao_prefix}titulo.label.cep`) }}
                
            el-col(:span="6")
                h4.align {{ $t(`${validacao_prefix}titulo.label.pais`) }}

</template>

<script>
import { mapGetters } from "vuex";
import Card from "../layouts/Card";
import { INTERFACE_VALIDACAO_PREFIX } from "../../utils/messages/interface/registrar/validacao/validacao";
import { translate } from "../../utils/helpers/internationalization";
import Properties from "../../properties";
import { FETCH_DADOS_CARTEIRA } from '../../store/actions.type';
import { PessoaDTO, ZonaLocalizacaoDTO } from "../../model/PessoaDTO";
import StatusCard from "../layouts/StatusCard";
import { LicencaPesca, licencaPesca } from "../../model/LicencaPesca";
import { numero } from '../../utils/validations/pessoa/pessoa_validations';
import { numericLiteral, nullLiteral } from 'babel-types';

export default {
  name: "ValidacaoPerguntas",

    components: {
        Card,
        StatusCard
    },
    
    data() {
        return {
            validacao_prefix: INTERFACE_VALIDACAO_PREFIX,
        };
    },

    computed: {
        ...mapGetters(["licencaPesca", "listaLicencas"])
       
    },
    
    methods: {
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
