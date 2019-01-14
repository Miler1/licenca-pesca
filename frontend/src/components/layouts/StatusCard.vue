<template lang="pug">
span.status-card(:class="classStyle")
    | {{label}}
</template>

<script>

import { mapGetters } from "vuex";

export default {
  name: "StatusCard",

  props: {
      situacao: String
  },

  mounted() {
      this.getConfig();
  },

  data() {
    return {
        label: '',
        classStyle: ''
    };
  },

  methods: {
    getConfig() {
        switch(this.situacao) {
            case 'AGUARDANDO_PAGAMENTO_BOLETO':
                this.label = 'Aguardando pagamento do boleto';
                this.classStyle = 'aguardando-pagamento';
                break;
            case 'ATIVO':
                this.label = 'Ativo';
                this.classStyle = 'ativo';
                break;
            case 'INVALIDADO':
                this.label = 'Invalidado';
                this.classStyle = 'invalidado';
                break;
            case 'VENCIDO':
                this.label = 'Vencido';
                this.classStyle = 'vencido';
                break;
        }
    }
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

</style>
