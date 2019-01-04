<template lang="pug">
    #tabela-consulta
      .tabela
        el-table(:data='tableData', style='width: 100%')
          el-table-column(prop='modalidade', :label="$t('interface.registrar.identificacao.tabela.label.modalidade')")
          el-table-column(prop='cadastro', :label="$t('interface.registrar.identificacao.tabela.label.cadastro')")
          el-table-column(prop='ativacao',  :label="$t('interface.registrar.identificacao.tabela.label.ativacao')")
          el-table-column(prop='vencimento', :label="$t('interface.registrar.identificacao.tabela.label.vencimento')")
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
    .tabela
      padding-top: 50px
</style>
