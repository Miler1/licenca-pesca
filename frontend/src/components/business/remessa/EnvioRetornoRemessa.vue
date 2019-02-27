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
                span {{ $t(`${remessa_prefix}dataArquivoRemessa`) }}
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



    //ARQUIVOS DE RETORNO
  div(v-if="listaArquivosRetornoPaginado")
    h2.title-principal {{ $t(`${remessa_prefix}tituloRetorno`) }}
    card
      .flex
        .flex-item
          el-row
            el-col(:span='24' :class="{'enabled': !desativar }")
              el-upload.upload-demo(
                  drag='', 
                  ref='upload',
                  :action='url' 
                  :on-remove='handleRemove' 
                  :on-success='success'
                  :auto-upload='false'
                  :before-remove="beforeRemove"
                  :limit='quantidadeUploadPorVez'
                  accept=".ret") 
                el-tooltip(placement="top" content="Selecione um tipo de documento para de anexar arquivos!")
                  span.wrapper.el-button
                    el-button.btn.lnr.lnr-upload
                .texto-interno {{ $t(`${remessa_prefix}uploadArquivo`) }}
          .retorno
            el-button(slot="append" icon="el-icon-upload" @click="submitUpload" type="primary") {{ $t(`${remessa_prefix}enviarArquivoRetorno`) }}
            

      div.tabela-retornos(v-if="listaArquivosRetornoPaginado.content != 0") 
        el-row
          el-col.tabela(:span="24")
            table#tabela-paginada
              tr
                th
                  span {{ $t(`${remessa_prefix}nomeArquivo`) }} 
                th
                  span {{ $t(`${remessa_prefix}dataArquivoRemessa`) }}
                th.centralizar
                  span {{ $t(`${remessa_prefix}acao`) }}
              
              tr(v-for="arquivoRetorno in listaArquivosRetornoPaginado.content")
                td {{arquivoRetorno.nome}}
                td {{arquivoRetorno.dataCadastro | moment('DD/MM/YYYY')}}
                td.centralizar 
                  el-button.download-button(slot="append" icon="el-icon-download" @click="downloadArquivoRetorno(arquivoRetorno.id)" type="primary")
      .flex
        .flex-item
          .paginacao
            el-pagination(@current-change="inicializaListagem(pagina.atual)" 
                    :page-size='paginacaoDados.pageSize', 
                    :total='listaArquivosRetornoPaginado.totalPages' 
                    :layout="paginacaoDados.layout"
                    :current-page.sync="pagina.atual")
            .infoPagina {{ $t(`${remessa_prefix}paginacao.exibir`) }} {{pagina.atual}} - {{listaArquivosRetornoPaginado.numberOfElements}} {{ $t(`${remessa_prefix}paginacao.de`) }}  {{listaArquivosRetornoPaginado.totalElements}} {{ $t(`${remessa_prefix}paginacao.qtdRegistros`) }} 
    card(v-if="listaArquivosRetornoPaginado.content == 0")
      div.sem-arquivo-retorno
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

  props: ['desativar', 'configuracao'],


  computed: {
    ...mapGetters(["listaRemessasPaginada", "listaArquivosRetornoPaginado"])
  },

  components: {
    Card
  },

  data() {
    return {
      remessa_prefix: ENVIAR_RECEBER_REMESSA_MESSAGES_PREFIX,
      url: `${Properties.BASE_URL}/api/upload-retorno/`,
      fileList: [],
      paginacaoDados: {
          pageSize: 1,
          layout: 'prev, pager, next'
        },
      pagina: { 
        atual: 1,
        total: 5 
      },
      quantidadeUploadPorVez: 1
    };
  },

  methods: {
    handleRemove (file, fileList) {
      console.log(file, fileList);
    },

    beforeRemove(file, fileList){
      return this.$confirm('Realmente deseja excluir o arquivo ' + file.name + '?', 'Atenção');
      // return this.$confirm(
        // translate(`${this.remessa_prefix}cancelamento.mensagemCancelamento`),
        // file.name,
        // translate(`${this.remessa_prefix}cancelamento.mensagemCancelamentoAviso`));
    },

    adicionadoAnexo (response, file, fileList) {
      this.padronizarRetorno(fileList)
    },

    inicializaListagem(pagina){
      this.$store.dispatch(LISTAR_REMESSAS, pagina);
      this.$store.dispatch(LISTAR_RETORNOS, pagina);
    },

    submitUpload() {
      this.$refs.upload.submit();
    },

    uploadArquivo(){
      this.$store.dispatch(UPLOAD_ARQUIVO_RETORNO);
    },

    downloadArquivoRemessa(idRemessa){

       const href =
        `${Properties.BASE_URL}/api/download-remessa/` + idRemessa;

        window.open(href, "_blank");
    },

    downloadArquivoRetorno(idRetorno){

    const href =
    `${Properties.BASE_URL}/api/download-retorno/` + idRetorno;

    window.open(href, "_blank");
    },

    gerarArquivoRemessa(){
      this.$store.dispatch(GERAR_REMESSAS);
    },

    padronizarRetorno (keys) {
      let arquivos = []

      keys.forEach(key => {
        let arquivo = {}

        arquivo.id = key.id
        arquivo.chave = key.response
        arquivo.caminho = key.name
        arquivo.nome = key.name

        arquivos.push(arquivo)
      })
      this.documento.arquivos = arquivos
    },


    adicionadoAnexo (response, file, fileList) {
      this.padronizarRetorno(fileList)
    },

    success (response, file, fileList) {
      this.adicionadoAnexo(response, file, fileList)
    },
  },
  
  created() {
    this.inicializaListagem();
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

    .tamanho-icone-download
      font-size: 20px
      
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

    .title-principal
        font-weight: 500
        font-size: 23px
        padding-top: 20px
    
    .right
      text-align: right

    .retorno 
      text-align: right
      padding-top: 15px

    .title-principal-remessa
        font-weight: 500
        font-size: 23px
        flex: 1
        margin-top: 6px
       
    .title-principal-licencas
        font-weight: 500
        font-size: 25px
        padding-bottom: 15px
        padding-top: 15px

    .wrapper.el-button
      display: inline-block
      padding: 0
      margin: 25px 0 5px 0
      border: none

    .upl
      margin-bottom: 25px
      margin-top: 25px

    .el-upload
      display: inherit 
      padding: 0px 0px 
      margin: 0 25px
      border-radius: 5px
      &:focus
        border-color: $--cor-borda

    .enabled
      .borda-upload
        border-radius: 5px
        padding: 5px
        margin: 0 0 15px
        display: block
        align-items: center
        border: 1px solid $--cor-borda

      .lnr-upload
        display: inline-block
        width: 90px !important
        height: 90px !important
        fill: currentColor
        color: #409eff
        font-size: 40px
        padding: 0 16px
        &:hover
          background-color: #fff
          border-color: $--cor-borda
        &:active
          opacity: .5

      .el-upload-dragger
        background-color: #fff
        width: 100% !important
        height: 189px !important
        &:hover
          border-color: $--cor-borda

    .sem-remessa
      text-align: center
      font-size: 15px

    .sem-arquivo-retorno
      text-align: center
      font-size: 15px
    .tabela-retornos
      padding-top: 30px
    .paginacao    
      width: 100%
      font-family: 'Open Sans', sans-serif
      font-size: 12px
      display: flex
</style>
