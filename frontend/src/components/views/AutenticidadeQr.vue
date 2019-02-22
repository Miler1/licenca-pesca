<template lang="pug">
  #autenticidadeQr
    h2.title-autenticidadeQr {{ $t(`${autenticidadeQr_prefix}titulo.tituloInicial`) }}
    card.dadosPessoais
        h3.title {{ $t(`${autenticidadeQr_prefix}titulo.dadosPessoais`) }}
            .flex
                .flex-item
                    h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.nome`) }}
                    h4.informacoes(:class="{'not-informed': exist(licencaPesca.pessoa.nome)}") {{ licencaPesca.pessoa.nome | placeholder($t(`${autenticidadeQr_prefix}naoInformado`)) }}
                .flex-item
                    h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.cpf`) }}
                    h4.informacoes(:class="{'not-informed': exist(licencaPesca.pessoa.cpf)}") {{ cpfFormatado() | placeholder($t(`${autenticidadeQr_prefix}naoInformado`)) }}

    card.dadosLicenca

        h3.title {{ $t(`${autenticidadeQr_prefix}titulo.dadosLicenca`) }}

        .flex
            .flex-item
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.numeroLicenca`) }}
                h4.informacoes(:class="{'not-informed': exist(licencaPesca.licenca.codigo)}") {{ licencaPesca.protocolo.codigoFormatado | placeholder($t(`${autenticidadeQr_prefix}naoInformado`)) }}
            .flex-item
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.modalidade`) }}
                h4.informacoes(:class="{'not-informed': exist(licencaPesca.licenca.modalidade)}") {{ modalidade() | placeholder($t(`${autenticidadeQr_prefix}naoInformado`)) }}
        .flex
            .flex-item
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.emissao`) }}
                h4.informacoes(v-if="licencaPesca.licenca.emissao" :class="{'not-informed': exist(licencaPesca.licenca.emissao)}") {{ licencaPesca.licenca.dataAtivacao | placeholder($t(`${autenticidadeQr_prefix}hifem`)) }}
                h4.informacoes(v-else :class="{'not-informed': exist(licencaPesca.licenca.emissao)}") {{ licencaPesca.licenca.dataCriacao | placeholder($t(`${autenticidadeQr_prefix}hifem`)) }}

            .flex-item
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.validade`) }}
                h4.informacoes(:class="{'not-informed': exist(licencaPesca.licenca.dataVencimento)}") {{ setDataVencimento() | moment('DD/MM/YYYY') }}
        .flex
            .flex-item
                h4.status {{ $t(`${autenticidadeQr_prefix}titulo.label.situacao`) }}
                status-card(:situacao="licencaPesca.licenca.status.codigo")
                
    card.dadosEndereco
        
        h3.title {{ $t(`${autenticidadeQr_prefix}titulo.enderecoPrincipal`) }}

        .flex
            .flex-item
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.endereco`) }}
                h4.informacoes(:class="{'not-informed': exist(licencaPesca.pessoa.enderecos[1])}") {{ enderecoFormatado() | placeholder($t(`${autenticidadeQr_prefix}naoInformado`)) }}

            .flex-item
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.municipio`) }}
                h4.informacoes(:class="{'not-informed': exist(licencaPesca.pessoa.enderecos[1])}") {{ municipioFormatado() | placeholder($t(`${autenticidadeQr_prefix}naoInformado`)) }}
        .flex

            .flex-item
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.cep`) }}
                h4.informacoes(:class="{'not-informed': exist(licencaPesca.pessoa.enderecos[1])}") {{ licencaPesca.pessoa.enderecos[1].cep | placeholder($t(`${autenticidadeQr_prefix}naoInformado`)) }}

            .flex-item
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.pais`) }}
                h4.informacoes(:class="{'not-informed': exist(licencaPesca.pessoa.enderecos[1])}") {{ $t(`${autenticidadeQr_prefix}titulo.pais`) }}

</template>

<script>
import { mapGetters } from "vuex";
import Card from "../layouts/Card";
import { INTERFACE_AUTENTICIDADE_PREFIX } from "../../utils/messages/interface/registrar/autenticidadeQr/autenticidadeQr";
import { translate } from "../../utils/helpers/internationalization";
import Properties from "../../properties";
import { FETCH_DADOS_CARTEIRA } from '../../store/actions.type';
import { PessoaDTO, ZonaLocalizacaoDTO } from "../../model/PessoaDTO";
import StatusCard from "../layouts/StatusCard";
import { LicencaPesca, licencaPesca } from "../../model/LicencaPesca";
import { numero } from '../../utils/validations/pessoa/pessoa_validations';
import { numericLiteral, nullLiteral, thisExpression } from 'babel-types';
import moment from "moment";

export default {
  name: "buscar",

    components: {
        Card,
        StatusCard
    },
    
    data() {
        return {
            autenticidadeQr_prefix: INTERFACE_AUTENTICIDADE_PREFIX,
        };
    },

    computed: {
        ...mapGetters(["licencaPesca", "listaLicencas"])
       
    },
    
    methods: {
        fetchData() {
            this.$store.dispatch(FETCH_DADOS_CARTEIRA, this.$route.params.protocolo);
        },
        exist(attr) {
        return attr === null || _.isNil(attr);
        },

        enderecoFormatado(){
           if(this.licencaPesca){
               if(this.licencaPesca.pessoa.enderecos[1]) {
                    var enderecoCompleto = this.licencaPesca.pessoa.enderecos[1].logradouro + ", Nº " + this.licencaPesca.pessoa.enderecos[1].numero;
                       
               }if(this.licencaPesca.pessoa.enderecos[1]){
                   var enderecoSemComplemento = (this.licencaPesca.pessoa.enderecos[1].complemento ? ", " + this.licencaPesca.pessoa.enderecos[1].complemento : '') + ", " + this.licencaPesca.pessoa.enderecos[1].bairro
               }
               return enderecoCompleto + enderecoSemComplemento;
           }
            return '-';
        },
        municipioFormatado(){
            if(this.licencaPesca){
                return this.licencaPesca.pessoa.enderecos[1].municipio.nome + "/" + this.licencaPesca.pessoa.enderecos[1].municipio.estado.sigla;
            }
        },
        cpfFormatado() {
            if(this.licencaPesca){
                return this.licencaPesca.pessoa.cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g,"\$1.\$2.\$3\-\$4")
            }
        },
        modalidade(){
            if(this.licencaPesca.licenca.modalidade === "RECREATIVA"){
                return translate(
                    `${this.autenticidadeQr_prefix}titulo.modalidades.recreativa`
                );
            }else {
                return translate(
                    `${this.autenticidadeQr_prefix}titulo.modalidades.esportiva`
                );
            }
        },
        setDataVencimento(){
            if(this.licencaPesca.licenca.dataVencimento == null && this.licencaPesca.licenca.dataVencimentoProvisoria == null){
                return "-";
            } if(this.licencaPesca.licenca.dataVencimento == null && this.licencaPesca.licenca.dataVencimentoProvisoria != null){
                return this.licencaPesca.licenca.dataVencimentoProvisoria;
            }else {
                return this.licencaPesca.licenca.dataVencimento;
            }  
        }
     },

    created() {
        this.fetchData();
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
    
    .status
        padding-bottom: 4px;
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
    
    .flex
        display: flex

        .flex-item
            flex: 1
            display: grid

    @media screen and (max-width: 600px) 
        .flex
            display: flex
            flex-flow:  column-reverse

            .flex-item
                flex: 1
                display: grid

</style>
