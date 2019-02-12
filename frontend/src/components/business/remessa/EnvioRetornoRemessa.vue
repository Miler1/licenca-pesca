<template lang="pug">
  #enviar-retornar-remessa
    div.buscar
      h2.title-principal-remessa {{ $t(`${remessa_prefix}tituloRemessa`) }}
      .right
        el-button(slot="append" icon="el-icon-refresh" @click="downloadArquivoRemessa" type="primary") {{ $t(`${remessa_prefix}gerarRemssa`) }}
    card
      //- .remessas(v-if="listaRemessas && listaRemessas.length > 0" v-for="remessa in listaRemessas")
      h2.titulo-remessa {{ $t(`${remessa_prefix}listagemRemessa`) }}
      .withDivisor.listMargin
        .flex
            .flex-item
              span.item-title {{ $t(`${remessa_prefix}nomeArquivoRemessa`) }}
              //- span.item-content {{remessa.nome}}
            .flex-item
              span.item-title {{ $t(`${remessa_prefix}dataArquivoRemessa`) }}
              //- span.item-content {{remessa.dataCadastro}}
            .flex-item
              span.item-title {{ $t(`${remessa_prefix}acao`) }}
              span.item-content-acoes
                el-button(slot="append" icon="el-icon-download" @click="downloadArquivoRemessa" type="primary") {{ $t(`${remessa_prefix}botaoAcao`) }}
            
    h2.title-principal {{ $t(`${remessa_prefix}tituloRetorno`) }}
    card
      .flex
        .flex-item
          el-row
            el-col(:span='24' :class="{'enabled': !desativar }")
              el-upload(:action='url' :on-preview='handlePreview' :on-remove='handleRemove' :on-success='success'
                        accept=" .jpg, .jpeg, .pdf" :file-list='fileList' drag multiple)
                el-tooltip(placement="top" content="Selecione um tipo de documento para de anexar arquivos!")
                  span.wrapper.el-button
                    el-button.btn.lnr.lnr-upload
                .texto-interno {{ $t(`${remessa_prefix}uploadArquivo`) }}

</template>

<script>
import { mapGetters } from "vuex";
import Card from "../../layouts/Card";
import { translate } from "../../../utils/helpers/internationalization";
import { ENVIAR_RECEBER_REMESSA_MESSAGES_PREFIX } from '../../../utils/messages/interface/registrar/geral';

export default {
  name: "EnviarRetornarRemessa",

  props: ['desativar', 'baixarArquivo', 'files',  'excluirAnexoDaSelecao'],

  watch: {
    files () {
      this.fileList = this.files
    }
  },

  components: {
    Card
  },

  data() {
    return {
     remessa_prefix: ENVIAR_RECEBER_REMESSA_MESSAGES_PREFIX,
     url: `http://${process.env.NODE_ENV}/upload-retorno'`,
     fileList: [],
    };
  },

  methods: {

    handlePreview (file) {
      this.baixarArquivo(file.response, file.name)
    },

    handleRemove (file, fileList) {
      this.excluirAnexoDaSelecao(file, fileList)
    },

    downloadArquivoRemessa(protocolo){

      // if(protocolo.codigoFormatado) {
      //   protocolo = protocolo.codigoFormatado;
      // }

      // let protocoloDesformatado = protocolo.replace("/", "").replace("-", "").replace("-","");

      const href =
        `${Properties.BASE_URL}/api/download-remessa?idRemessa`;

      window.open(href, "_blank");
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
  
  }
};
</script>

<style lang="sass">
  @import "../../../theme/tools/variables"

  #enviar-retornar-remessa
    h1
      font-weight: 500

    .flex
        display: flex
        .flex-item
            flex: 1
            display: grid

    .item-title
      font-weight: 500
      margin-bottom: 10px
    
    .item-content
      font-size: 15px

    .download-button
      color: #409EFF
      border-color: #c6e2ff
      background-color: #ecf5ff
      &:hover
        background-color: white

    .withDivisor
      margin-top: 15px
      border-top: 1px solid #ddd
      padding-top: 20px
    
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

    .titulo-remessa
      font-size: 16px
    
    .right
      text-align: right

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

</style>
