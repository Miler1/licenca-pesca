<template lang="pug">
#enviar-retornar-remessa
  div(v-if="listaRemessasPaginada")
    div.buscar
      h2.title-principal-remessa {{ $t(`${remessa_prefix}tituloRemessa`) }}
      .right
        el-button(slot="append" icon="el-icon-refresh" @click="gerarArquivoRemessa" type="primary") {{ $t(`${remessa_prefix}gerarRemssa`) }}
    card(v-if="listaRemessasPaginada.content != 0") 
      el-row
        el-col.tabela(:span="24")
          table#tabela-paginada
            tr
              th
                span {{ $t(`${remessa_prefix}nomeArquivo`) }} 
              th
                span {{ $t(`${remessa_prefix}dataArquivo`) }}
              th.centralizar
                span {{ $t(`${remessa_prefix}acao`) }}
            
            tr(v-for="remessas in listaRemessasPaginada.content")
              td {{remessas.arquivo.nome}}
              td {{remessas.arquivo.dataCadastro | moment('DD/MM/YYYY')}}
              td.centralizar 
                el-button.download-button(slot="append" icon="el-icon-download" @click="downloadArquivoRemessa(remessas.id)" type="primary")
      .flex
        .flex-item
          .paginacao
            el-pagination(@current-change="inicializaListagem(pagina.atual)" 
                    :page-size='paginacaoDados.pageSize', 
                    :total='listaRemessasPaginada.totalPages' 
                    :layout="paginacaoDados.layout"
                    :current-page.sync="pagina.atual")
            .infoPagina {{ $t(`${remessa_prefix}paginacao.exibir`) }} {{pagina.atual}} - {{listaRemessasPaginada.numberOfElements}} {{ $t(`${remessa_prefix}paginacao.de`) }}  {{listaRemessasPaginada.totalElements}} {{ $t(`${remessa_prefix}paginacao.qtdRegistros`) }} 
    card(v-if="listaRemessasPaginada.content == 0")
      div.sem-remessa
        | {{ $t(`${remessa_prefix}semRemessa`) }}  

</template>

<script>
import { mapGetters } from "vuex";
import Card from "../../layouts/Card";
import Properties from "../../../properties";
import moment from "moment";
import { translate } from "../../../utils/helpers/internationalization";
import { ENVIAR_RECEBER_REMESSA_MESSAGES_PREFIX } from '../../../utils/messages/interface/registrar/geral';
import { BUSCAR_REMESSAS, GERAR_REMESSAS, LISTAR_REMESSAS, DOWNLOAD_REMESSA, UPLOAD_ARQUIVO_RETORNO, LISTAR_RETORNOS } from '../../../store/actions.type';
import { debuggerStatement } from 'babel-types';
import { createECDH } from 'crypto';

export default {
  name: "EnviarRetornarRemessa",

  // props: ['desativar', 'configuracao'],


  computed: {
    ...mapGetters(["listaRemessasPaginada"])
  },

  components: {
    Card
  },

  data() {
    return {
      remessa_prefix: ENVIAR_RECEBER_REMESSA_MESSAGES_PREFIX,
      paginacaoDados: {
          pageSize: 1,
          layout: 'prev, pager, next'
        },
      pagina: { 
        atual: 1,
        total: 5 
      },
      quantidadeUploadPorVez: 1,
      paginaPrincipal: 1,
    };
  },

  methods: {
   
    inicializaListagem(paginaPrincipal){
      this.$store.dispatch(LISTAR_REMESSAS, paginaPrincipal);
    },

    downloadArquivoRemessa(idRemessa){

       const href =
        `${Properties.BASE_URL}/banco/download-remessa/` + idRemessa;

        window.open(href, "_blank");
    },

    gerarArquivoRemessa(){
      this.$store.dispatch(GERAR_REMESSAS);
    },
  },
  
  created() {
    this.inicializaListagem(this.paginaPrincipal);
  }

};
</script>

<style lang="sass">
  @import "../../../theme/tools/variables"

  #tabela-paginada
    width: 100%
    padding: 5px
    border-collapse: collapse
    font-family: 'Open Sans', sans-serif
    color: #424242
    font-size: 16px
  
  th
    border-bottom: 1px solid #ddd
    padding: 15px 8px
    word-break: break-word

  tr
    border-bottom: 1px solid #ddd

  td
    padding: 15px 8px
    word-break: break-word

  .centralizar
    text-align: center

  #enviar-retornar-remessa
    h1
      font-weight: 500

    .flex
        display: flex
        .flex-item
            flex: 1
            display: grid
  
    .download-button
      color: #409EFF
      border-color: #fff !important
      background-color: #fff !important
      font-size: 18px
      &:hover
        border-color: #409EFF !important

    .infoPagina    
      text-align: right
      margin-top: 10px
      color: #424242
      font-size: 12px

    .buscar
      display: flex

    .texto-interno
      font-family: 'Open Sans', sans-serif
      font-size: 14px
      padding: 15px
      text-align: center
      color: #9E9E9E
    
    .right
      text-align: right

    .title-principal-remessa
        font-weight: 500
        font-size: 23px
        flex: 1
        margin-top: 6px

    .sem-remessa
      text-align: center
      font-size: 15px

    .paginacao    
      width: 100%
      font-family: 'Open Sans', sans-serif
      font-size: 12px
      display: flex
</style>
