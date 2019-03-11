<template lang="pug">
#enviar-retornar-listagem-retorno
  div(v-if="listaArquivosRetornoPaginado")
    div.buscar
      h2.title-principal-remessa {{ $t(`${remessa_prefix}tituloRetorno`) }}
      .right
        el-button(slot="append" icon="el-icon-upload" @click='modalEnvioArquivoRetorno = true' type="primary") {{ $t(`${remessa_prefix}enviarArquivoRetorno`) }}
    card(v-if="listaArquivosRetornoPaginado.content != 0")
      div 
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
              
              tr(v-for="arquivoRetorno in listaArquivosRetornoPaginado.content")
                td {{arquivoRetorno.nome}}
                td {{arquivoRetorno.dataCadastro | moment('DD/MM/YYYY')}}
                td.centralizar 
                  el-button(slot="append" icon="el-icon-download" @click="downloadArquivoRetorno(arquivoRetorno.id)" type="primary")
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
        | {{ $t(`${remessa_prefix}semRetorno`) }}
  
    el-dialog(:visible.sync='modalEnvioArquivoRetorno')
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
                  :fileList="fileList"
                  :on-error='erro'
                  :auto-upload='false'
                  :before-remove="beforeRemove"
                  :limit='quantidadeUploadPorVez'
                  accept=".ret") 
                el-tooltip(placement="top" content="Selecione um documento de retorno!") 
                  span.wrapper.el-button
                    el-button.btn.lnr.lnr-upload
                .texto-interno {{ $t(`${remessa_prefix}uploadArquivo`) }}

      span.dialog-footer(slot='footer')
        el-button(@click='modalEnvioArquivoRetornoLimpar()') {{ $t(`${remessa_prefix}cancelar`) }}
        el-button(slot="append" icon="el-icon-upload" @click="submitUpload" type="primary") {{ $t(`${remessa_prefix}enviarArquivo`) }}

</template>

<script>
import Vue from "vue";
import { mapGetters } from "vuex";
import Card from "../../layouts/Card";
import Properties from "../../../properties";
import moment from "moment";
import { translate } from "../../../utils/helpers/internationalization";
import { ENVIAR_RECEBER_REMESSA_MESSAGES_PREFIX } from "../../../utils/messages/interface/registrar/geral";
import {
  BUSCAR_REMESSAS,
  GERAR_REMESSAS,
  LISTAR_REMESSAS,
  DOWNLOAD_REMESSA,
  UPLOAD_ARQUIVO_RETORNO,
  LISTAR_RETORNOS
} from "../../../store/actions.type";
import { debuggerStatement } from "babel-types";
import { createECDH } from "crypto";

export default {
  name: "EnviarRetornarRemessa",

  props: ["desativar", "configuracao"],

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
      modalEnvioArquivoRetorno: false,
      formLabelWidth: "100px",
      paginacaoDados: {
        pageSize: 1,
        layout: "prev, pager, next"
      },
      pagina: {
        atual: 1,
        total: 5
      },
      quantidadeUploadPorVez: 1
    };
  },

  methods: {
    handleRemove(file, fileList) {},

    beforeRemove(file, fileList) {
      return this.$confirm(
        "Realmente deseja excluir o arquivo " + file.name + "?",
        "Atenção"
      );
    },

    inicializaListagem(pagina) {
      this.$store.dispatch(LISTAR_RETORNOS, pagina);
    },

    submitUpload() {
      this.$refs.upload.submit();
     
    },

    downloadArquivoRetorno(idRetorno) {
      const href = `${Properties.BASE_URL}/api/download-retorno/` + idRetorno;

      window.open(href, "_blank");
    },
    

    modalEnvioArquivoRetornoLimpar() {
      this.modalEnvioArquivoRetorno = false;
      this.fileList = [];
    },

    padronizarRetorno(keys) {
      var arquivos = [];

      keys.forEach(key => {
        let arquivo = {};
        arquivo.id = key.id;
        arquivo.chave = key.response;
        arquivo.caminho = key.name;
        arquivo.nome = key.name;
        arquivos.push(arquivo);
      });
    },
    adicionadoAnexo(response, file, fileList) {
      this.padronizarRetorno(fileList);
    },
    success(response, file, fileList) {
      this.adicionadoAnexo(response, file, fileList);
      if (response == "") {
        Vue.prototype.$notify.success({
          title: "Sucesso",
          message: "Arquivo de retorno enviado com sucesso"
        });
      }
      this.modalEnvioArquivoRetorno = false;
      this.$store.dispatch(LISTAR_RETORNOS, 1);
      this.fileList = [];
    },
    erro(err, file, fileList) {
      if (err) {
        Vue.prototype.$notify.error({
          title: "Erro do sistema",
          message: err.message
        });
      }
    }
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

  #enviar-retornar-listagem-retorno
    h1
      font-weight: 500

    .flex
        display: flex
        .flex-item
            flex: 1
            display: grid
  
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

    .sem-arquivo-retorno
      text-align: center
      font-size: 15px

    .tabela-retornos
      padding-top: 30px
      
    .el-upload-list__item .el-icon-upload-success
      font-size: 21px
      padding-right: 17px
    
    .el-icon-close
      font-size: 17px
      padding-right: 17px
    
    .el-upload-list__item-name
      font-size: 15px
      padding-left: 22px

    #enviar-retornar-listagem-retorno .el-icon-close 
      font-size: 0px !important

    .paginacao    
      width: 100%
      font-family: 'Open Sans', sans-serif
      font-size: 12px
      display: flex
    .el-dialog__body
      padding: 18px 5px
</style>
