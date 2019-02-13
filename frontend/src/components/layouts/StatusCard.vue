<template lang="pug">
div
    span.status-card.aguardando-pagamento(v-if="situacao === 'AGUARDANDO_PAGAMENTO'")
        | {{ $t(`${consultar_prefix}listaLicenca.situacao.aguardandoBoleto`) }}
    span.status-card.ativo(v-if="situacao === 'ATIVO'")
        | {{ $t(`${consultar_prefix}listaLicenca.situacao.ativo`) }}
    span.status-card.ativo_aguardando_pagamento(v-if="situacao === 'ATIVO_AGUARDANDO_PAGAMENTO'")
        | {{ $t(`${consultar_prefix}listaLicenca.situacao.ativoAguardandoPagamento`) }}
    span.status-card.invalidado(v-if="situacao === 'INVALIDADO'")
        | {{ $t(`${consultar_prefix}listaLicenca.situacao.invalido`) }}
    span.status-card.vencido(v-if="situacao === 'VENCIDO'")
        | {{ $t(`${consultar_prefix}listaLicenca.situacao.vencido`) }}
    span.status-card.renovado(v-if="situacao === 'RENOVADO'")
        | {{ $t(`${consultar_prefix}listaLicenca.situacao.renovado`) }}
</template>

<script>

import { mapGetters } from "vuex";  
import { REGISTRAR_GERAL_MESSAGES_PREFIX } from "../../utils/messages/interface/registrar/geral";

export default {
  name: "StatusCard",

  props: {
      situacao: String
  },

  mounted() {
  },

  data() {
    return {    
        label: '',
        consultar_prefix: REGISTRAR_GERAL_MESSAGES_PREFIX,
        classStyle: ''
    };
  },

  methods: {
  }

};
</script>

<style lang="sass" >
.status-card
    font-size: 11px
    padding: 5px
    border-radius: 10px
    font-weight: bold

.aguardando-pagamento
    background-color: #e6ac00
    

.vencido
    background-color: #cc0000
    color: white
    font-size: 13px

.invalidado
    background-color: #b3b3b3
    color: white
    font-size: 13px

.ativo
    background-color: #00b33c
    color: white
    font-size: 13px

.ativo_aguardando_pagamento
    background-color: #C5E1A5

.renovado
    background-color: #3f51b5
    color: white
    font-size: 13px

</style>
