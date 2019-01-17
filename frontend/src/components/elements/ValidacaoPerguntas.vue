<template lang="pug">
  #validacao-perguntas
    h3.title-validacao {{ $t(`${validacao_prefix}titulo.tituloInicial`) }}
    p.espaco-cards.subtitulo {{ $t(`${validacao_prefix}titulo.subtitulo`) }}
    .flex
        .flex-item
            .espaco-cards
                el-card.box-card.column
                    h3.title.display {{ $t(`${validacao_prefix}titulo.nomeMae`) }}
                        div(style='margin-top: 20px')
                            el-radio-group(v-for="nomesMae in buscaMaes", :key="nomesMae" v-model="pessoa.mae" )
                                el-radio.custom(:label='nomesMae' border='' required) {{nomesMae}} 
                            | {{pessoa.mae}}          
        .flex-item   
            .espaco-cards   
                el-card.box-card.column
                    h3.title {{ $t(`${validacao_prefix}titulo.dataNascimento`) }}
                        div(style='margin-top: 20px')
                        el-form(v-model="pessoa" ref="pessoa" label-position="top")
                            el-form-item(prop="dataNascimento")                           
                                el-date-picker(v-model="pessoa.dataNascimento" :format="$t(`${validacao_prefix}format.data`)")
                        | {{pessoa.dataNascimento}}
        .flex-item
            .espaco-cards
                el-card.box-card.column
                    h3.title {{ $t(`${validacao_prefix}titulo.municipio`) }}
                        div(style='margin-top: 20px')
                            el-radio-group(v-for="nomesMunicipios in buscaMunicipios", :key="nomesMunicipios" v-model="pessoa.municipio" )
                                el-radio(:label='nomesMunicipios', border='' required) {{nomesMunicipios}}
                            | {{pessoa.municipio}} 
    .buttons
        .right
          el-button(icon="el-icon-check" type="default" @click="validarDados" ) {{ $t(`${validacao_prefix}botoes.validar`) }}
                                
</template>

<script>
import { mapGetters } from "vuex";
import Card from "../layouts/Card";
import { INTERFACE_VALIDACAO_PREFIX } from "../../utils/messages/interface/registrar/validacao/validacao";
import { translate } from "../../utils/helpers/internationalization";
import Properties from "../../properties";
import { FETCH_DADOS_CARTEIRA, VALIDA_DADOS } from '../../store/actions.type';
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
            pessoa: {
                dataNascimento: null,
            },
           
        };
    },

    computed: {
        ...mapGetters(["solicitante", "buscaMaes", "buscaMunicipios"])
       
    },
    
    methods: {
        instantiate() {
            this.validaDados = this;
        },
        fetchData() {
            debugger
        },

        validarDados(){
            this.$store.dispatch(VALIDA_DADOS, this.pessoa);
            let date = this.pessoa.dataNascimento;
            if(typeof(date) !== "string"){
                this.pessoa.dataNascimento =  date.getDate() + '/' + (date.getMonth() + 1)+ '/' + date.getFullYear();
          }
        },

        created() {
            this.instantiate();
            this.fetchData();
            this.validacaoDados();
        },

    }
};
</script>

<style lang="sass">
  @import "../../theme/tools/variables"

  #validacao-perguntas
    h1
      font-weight: 500
    
    .subtitulo    
        font-size: 15px;
    
    .title
      font-weight: bold
      font-size: 17px

    .custom
        padding-bottom: 10px

    .display
        display: flex
        flex-direction: column

    .title-validacao
        text-align: center
        font-weight: bold
        padding-top: 50px
        padding-bottom: 10px
        font-size: 21px
    
    .el-radio--mini.is-bordered .el-radio__label
        font-size: 14px
    
    .el-radio-group
        display: grid;
        margin-bottom: 8px;
        // line-height: 1;
        // width: 100%;
        // vertical-align: middle;
        // font-size: 0;
    
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

    .flex
        display: flex

        .flex-item
            flex: 1
            display: grid
</style>
