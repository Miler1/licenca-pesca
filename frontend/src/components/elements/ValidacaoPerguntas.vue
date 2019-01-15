<template lang="pug">
  #validacao-perguntas
    h3.title-validacao {{ $t(`${validacao_prefix}titulo.tituloInicial`) }}
    .flex
        .flex-item
            .espaco-cards
                el-card.box-card.column
                    h3.title {{ $t(`${validacao_prefix}titulo.nomeMae`) }}
                        div(style='margin-top: 20px')
                            el-radio-group(v-model='radio10')
                                el-radio.custom(label='1', border='') Maria de fatima de alguma coissa
                                el-radio.custom(label='2', border='') Maria de fatima de alguma coissa
                                el-radio.custom(label='3', border='') Maria de fatima de alguma coissa
                                el-radio.custom(label='4', border='') Maria de fatima de alguma coissa  
                                el-radio.custom(label='5', border='') Maria de fatima de alguma coissa           
        .flex-item   
            .espaco-cards   
                el-card.box-card.column
                    h3.title {{ $t(`${validacao_prefix}titulo.dataNascimento`) }}
                        div(style='margin-top: 20px')
                            el-radio-group(v-model='radio10')
                                el-radio(label='1', border='') 25/03/1980
                                el-radio(label='2', border='') 25/03/1980
                                el-radio(label='3', border='') 25/03/1980
                                el-radio(label='4', border='') 25/03/1980
                                el-radio(label='5', border='') 25/03/1980
        .flex-item
            .espaco-cards
                el-card.box-card.column
                    h3.title {{ $t(`${validacao_prefix}titulo.municipio`) }}
                        div(style='margin-top: 20px')
                            el-radio-group(v-model='radio10')
                                el-radio(label='1', border='') Maria de fatima de alguma coissa
                                el-radio(label='2', border='') Maria de fatima de alguma coissa
                                el-radio(label='3', border='') Maria de fatima de alguma coissa
                                el-radio(label='4', border='') Maria de fatima de alguma coissa
                                el-radio(label='5', border='') Maria de fatima de alguma coissa

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
            radio10: '1'
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

  #validacao-perguntas
    h1
      font-weight: 500

    .title
      font-weight: bold
      font-size: 17px

    .custom
        padding-bottom: 10px

    .title-validacao
        text-align: center
        font-weight: bold
        padding-top: 50px
        padding-bottom: 10px
        font-size: 18px
    
    .el-radio--mini.is-bordered .el-radio__label
        font-size: 14px
        
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
    
    .espaco-cards
        margin: 20px

    // .column 
    //     flex-direction: column;
    //     padding: 10px

    .flex
        display: flex

        .flex-item
            flex: 1
            display: grid
</style>
