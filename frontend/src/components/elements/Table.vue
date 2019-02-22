<template lang="pug">
    #tabela-consulta
      .tabela
        el-table(:data='tableData', style='width: 100%')
          el-table-column(prop='modalidade', :label="$t('interface.registrar.identificacao.tabela.label.modalidade')")
          el-table-column(prop='situacao', :label="$t('interface.registrar.identificacao.tabela.label.situacao')")
          el-table-column(prop='acoes', :label="$t('interface.registrar.identificacao.tabela.label.acoes')")

</template>

<script>
export default {
  name: "TabelaConsulta",

  data() {
    return {
      
    };
  }
};
</script>

<style lang="sass">

</style>
