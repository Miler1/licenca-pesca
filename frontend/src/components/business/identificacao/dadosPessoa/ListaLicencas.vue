<template lang="pug">
#lista-licenca
    h3.title.withDivisor Lista licenças
    .licencas(v-for="lista in listaLicencas")
        .protocolo {{ lista.protocolo.codigoFormatado }}
        .withDivisor.listMargin
            .flex
                .flex-item
                    span.item-title Modalidade:
                    span.item-content {{lista.modalidade}}
                .flex-item
                    span.item-title Cadastro:
                    span.item-content {{formatData(lista.dataCriacao)}}
                .flex-item
                    span.item-title Ativação:
                    span.item-content {{(lista.dataAtivacao) ? formatData(lista.dataAtivacao) : '-'}}
                .flex-item
                    span.item-title Vencimento:
                    span.item-content {{(lista.dataVencimento) ? formatData(lista.dataVencimento) : '-'}}
                .flex-item
                    span.item-title Situação:
                    span.item-content 
                        status-card(:situacao="lista.status")
                .flex-item
                    span.item-title Ações:
                    span.item-content 
                        el-dropdown(trigger="click", v-if="lista.status !== 'INVALIDADO'")
                            span.el-dropdown-link Ações
                                i.el-icon-arrow-down.el-icon--right
                            el-dropdown-menu(slot="dropdown")
                                el-dropdown-item(type="primary", v-if="lista.status === 'AGUARDANDO_PAGAMENTO_BOLETO'",  @click.native="gerarBoleto(lista)") Gerar boleto
                                el-dropdown-item(type="primary", v-if="lista.status === 'ATIVO'",  @click.native="gerarCarteira(lista)") Gerar carteira
                                el-dropdown-item(type="primary", v-if="verificarRenovacao(lista)", @click.native="renovar(lista)") Renovar
                        span(v-if="lista.status === 'INVALIDADO'") -
</template>

<script>

import { mapGetters } from "vuex";
import StatusCard from "../../../layouts/StatusCard";
import Properties from "../../../../properties";

export default {
  name: "ListaLicencas",

  components: {
    StatusCard
  },

  computed: {
    ...mapGetters(["listaLicencas"])
  },

  data() {
    return {
    };
  },

  methods: {
      formatData(data) {
          return new Date(data).convertDate();
      },
      gerarBoleto(lista) {
        let protocolo = lista.protocolo.codigoFormatado;
        let protocoloDesformatado = protocolo.replace("/", "").replace("-", "");

        const href =
            `${Properties.BASE_URL}/api/boleto?protocolo=` + protocoloDesformatado;

        window.open(href, "_blank");
      },
      gerarCarteira(lista) {
        let protocolo = lista.protocolo.codigoFormatado;
        let protocoloDesformatado = protocolo.replace("/", "").replace("-", "");

        const href =
        `${Properties.BASE_URL}/api/carteira?protocolo=` +
        protocoloDesformatado;

        window.open(href, "_blank");
      },
      renovar() {
          console.log('renovar');
      },
      verificarRenovacao(lista) {
          return lista.status === 'VENCIDO' || lista.podeRenovar;
      }
  }

};
</script>

<style lang="sass">

.el-dropdown-link
    cursor: pointer
    color: #409EFF
.el-icon-arrow-down
    font-size: 12px

.el-dropdown-menu
    // margin-left: -100px !important

#lista-licenca
    .title
        margin-bottom: 20px

    .withDivisor
        margin-top: 25px
        border-top: 1px solid #ddd
        padding-top: 20px

    .listMargin
        margin-top: 10px
    
    .licencas
        margin-bottom: 20px

    .protocolo
        font-weight: bold

    .item-content
        font-size: 13px

    .flex
        display: flex

        .flex-item
            flex: 1
            display: grid
            
            .item-title
                font-weight: 500
                margin-bottom: 10px
</style>
