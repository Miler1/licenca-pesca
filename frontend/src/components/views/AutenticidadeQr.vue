<template lang="pug">
  #autenticidadeQr
    h2.title-autenticidadeQr {{ $t(`${autenticidadeQr_prefix}titulo.tituloInicial`) }}
    card.dadosPessoais

        h3.title {{ $t(`${autenticidadeQr_prefix}titulo.dadosPessoais`) }}

        el-row.section(:gutter="20")
            el-col(:span="10")
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.nome`) }}
                h4.informacoes(:class="{'not-informed': exist(licencaPesca.pessoa.nome)}") {{ licencaPesca.pessoa.nome | placeholder($t(`${autenticidadeQr_prefix}naoInformado`)) }}

            el-col(:span="10")
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.cpf`) }}
                h4.informacoes(:class="{'not-informed': exist(licencaPesca.pessoa.cpf)}") {{ licencaPesca.pessoa.cpf | placeholder($t(`${autenticidadeQr_prefix}naoInformado`)) }}

    card.dadosLicenca
        h3.title {{ $t(`${autenticidadeQr_prefix}titulo.dadosLicenca`) }}
        
        el-row.section(:gutter="20")
            el-col(:span="10")
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.numeroLicenca`) }}
                h4.informacoes(:class="{'not-informed': exist(licencaPesca.licenca.codigo)}") {{ licencaPesca.protocolo.codigoFormatado | placeholder($t(`${autenticidadeQr_prefix}naoInformado`)) }}

            el-col(:span="7")
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.modalidade`) }}
                h4.informacoes(:class="{'not-informed': exist(licencaPesca.licenca.modalidade)}") {{ licencaPesca.licenca.modalidade | placeholder($t(`${autenticidadeQr_prefix}naoInformado`)) }}

            el-col(:span="6")
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.emissao`) }}
                h4.informacoes(:class="{'not-informed': exist(licencaPesca.licenca.modalidade)}") {{ licencaPesca.licenca.dataCriacao | placeholder($t(`${autenticidadeQr_prefix}naoInformado`)) }}

            el-col(:span="10")
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.validade`) }}
                h4.informacoes(:class="{'not-informed': exist(licencaPesca.licenca.modalidade)}") {{ licencaPesca.licenca.dataVencimento | placeholder($t(`${autenticidadeQr_prefix}naoInformado`)) }}

    card.dadosEndereco
        
        h3.title {{ $t(`${autenticidadeQr_prefix}titulo.enderecoPrincipal`) }}
        
        el-row.section(:gutter="20")
            el-col(:span="20")
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.endereco`) }}
                h4.informacoes(:class="{'not-informed': exist(licencaPesca.pessoa.enderecos[1])}") {{ licencaPesca.pessoa.enderecos[1].logradouro}}, Nº {{ licencaPesca.pessoa.enderecos[1].numero}}, {{ licencaPesca.pessoa.enderecos[1].complemento}}, {{ licencaPesca.pessoa.enderecos[1].bairro}} 
                
            el-col(:span="10")
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.municipio`) }}
                 h4.informacoes(:class="{'not-informed': exist(licencaPesca.pessoa.enderecos[1])}") {{ licencaPesca.pessoa.enderecos[1].municipio.nome }}/{{licencaPesca.pessoa.enderecos[1].municipio.estado.sigla | placeholder($t(`${autenticidadeQr_prefix}naoInformado`)) }}

            el-col(:span="7")
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.cep`) }}
                h4.informacoes(:class="{'not-informed': exist(licencaPesca.pessoa.enderecos[1])}") {{ licencaPesca.pessoa.enderecos[1].cep | placeholder($t(`${autenticidadeQr_prefix}naoInformado`)) }}

            el-col(:span="6")
                h4.align {{ $t(`${autenticidadeQr_prefix}titulo.label.pais`) }}
                h4.informacoes(:class="{'not-informed': exist(licencaPesca.pessoa.enderecos[1])}") {{ licencaPesca.pessoa.enderecos[1].pais.nome | placeholder($t(`${autenticidadeQr_prefix}naoInformado`)) }}

</template>

<script>
import { mapGetters } from "vuex";
import Card from "../layouts/Card";
import { INTERFACE_AUTENTICIDADE_PREFIX } from "../../utils/messages/interface/registrar/autenticidadeQr/autenticidadeQr";
import { translate } from "../../utils/helpers/internationalization";
import Properties from "../../properties";
import { FETCH_DADOS_CARTEIRA } from '../../store/actions.type';
import { PessoaDTO, ZonaLocalizacaoDTO } from "../../model/PessoaDTO";
import { LicencaPesca } from "../../model/LicencaPesca";
import { numero } from '../../utils/validations/pessoa/pessoa_validations';
import { numericLiteral, nullLiteral } from 'babel-types';

export default {
  name: "buscar",

    components: {
        Card
    },
    
    data() {
        return {
            autenticidadeQr_prefix: INTERFACE_AUTENTICIDADE_PREFIX,
        };
    },
    
    watch: {
        "$route": "fetchData"
    },
    
    computed: {
        ...mapGetters(["licencaPesca"])
       
    },
    
    methods: {
        fetchData() {
            this.$store.dispatch(FETCH_DADOS_CARTEIRA, this.$route.params.protocolo);
        },
        
        exist(attr) {
        return attr === null || _.isNil(attr);
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
