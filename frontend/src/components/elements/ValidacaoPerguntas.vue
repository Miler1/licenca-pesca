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
                            el-radio-group(v-for="nomesMae in buscaMaes", :key="nomesMae" v-model="pessoa.nomeMae" )
                                el-radio.custom(:label='nomesMae' border required) {{nomesMae}}         
        .flex-item   
            .espaco-cards   
                el-card.box-card.column
                    h3.title {{ $t(`${validacao_prefix}titulo.dataNascimento`) }}
                        div(style='margin-top: 20px')
                        el-form(v-model="pessoa" ref="pessoa" label-position="top")
                            el-form-item(prop="dataNascimento")                           
                                el-date-picker(v-model="pessoa.dataNascimento" :format="$t(`${validacao_prefix}format.data`)")
    .buttons
        .left
            el-button(icon="el-icon-close" @click="cancelarValidacao") {{ $t(`${validacao_prefix}botoes.cancelar`) }}
            el-button.right(icon="el-icon-check" v-if="!visualizarBotaoValidar()" type="primary" @click="validarDadosBuscar" ) {{ $t(`${validacao_prefix}botoes.validar`) }}
            el-button.right(icon="el-icon-check" v-if="visualizarBotaoValidar()" type="primary" @click="validarDadosRegistrar" ) {{ $t(`${validacao_prefix}botoes.validar`) }}
                         
</template>

<script>
import Vue from "vue";
import { mapGetters } from "vuex";
import Card from "../layouts/Card";
import { INTERFACE_VALIDACAO_PREFIX } from "../../utils/messages/interface/registrar/validacao/validacao";
import { translate } from "../../utils/helpers/internationalization";
import Properties from "../../properties";
import { FETCH_DADOS_CARTEIRA, VALIDA_DADOS, SEND_SOLICITANTE, BUSCAR_LICENCAS, CANCELAR, ACESSAR } from '../../store/actions.type';
import { PessoaDTO, ZonaLocalizacaoDTO } from "../../model/PessoaDTO";
import StatusCard from "../layouts/StatusCard";
import { LicencaPesca, licencaPesca } from "../../model/LicencaPesca";
import { numero } from '../../utils/validations/pessoa/pessoa_validations';
import { numericLiteral, nullLiteral } from 'babel-types';
import { PESSOA_RULES } from '../../utils/validations/pessoa/pessoa_rules';

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
                cpf: null,
                nomeMae: null
            }
        };
    },

    computed: {
        ...mapGetters(["solicitante", "buscaMaes", "errorTelaInicial", "validacaoDados", "buscaMunicipios"])
    },
    
    methods: {

        instantiate() {
            Vue.prototype.$validacaoPerguntas = this;
        },

        validarDadosBuscar(){
            let dto = {
                acessoResource: {
                    cpf: this.pessoa.cpf,
                    passaporte: this.pessoa.passaporte
                },
                dataNascimento: this.pessoa.dataNascimento,
                nomeMae: this.pessoa.nomeMae
            }
            this.$store.dispatch(BUSCAR_LICENCAS, dto);
        },

        validarDadosRegistrar(){
            let dto = {
                acessoResource: {
                    cpf: this.pessoa.cpf,
                    passaporte: this.pessoa.passaporte
                },
                dataNascimento: this.pessoa.dataNascimento,
                nomeMae: this.pessoa.nomeMae
            }
            this.$store.dispatch(ACESSAR, dto).then();
        },

        atualizarCpfPesquisado(resource) {
            this.pessoa.cpf = resource.cpf;
            this.pessoa.passaporte = resource.passaporte;
                if(resource.cpf === null && resource.passaporte){
                    this.pessoa.estrangeiro = true;
                    this.estrangeiroDisabled = true;
                } else {
                    this.pessoa.estrangeiro = false;
                    this.estrangeiroDisabled = false;
                }
        },
        visualizarBotaoValidar(){
            return this.$router.history.current.name == "registrar";
        },
        cancelarValidacao() {
            this.$confirm(
                translate(`${this.validacao_prefix}cancelamento.mensagemValidacao`),
                translate(`${this.validacao_prefix}cancelamento.titulo`),
                {
                confirmButtonText: translate(
                    `${this.validacao_prefix}cancelamento.botoes.confirmar`
                ),
                cancelButtonText: translate(
                    `${this.validacao_prefix}cancelamento.botoes.cancelamento`
                )
                }
            )
                .then(() => {
                    this.$store.dispatch(CANCELAR).then(p => {
                    this.step = 0;
                    this.$router.push({name: 'home'});
                    });
                })
                .catch(() => {
                    // DO nothing!
                });
        }

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

    .right
        float: right

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
