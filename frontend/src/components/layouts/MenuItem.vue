<template lang="pug">
#menu-item(:class="{ active: active }" @click="click" @mouseover="hover(true)" @mouseleave="hover(false)")
  .menu-title
    i.lnr.icone(:class="icone")
    span.margem {{ titulo }}
  .indicador2
</template>
<script>
export default {
  name: 'MenuItem',
  props: ['titulo', 'icone', 'active'],
  methods: {
    /**
     * Retorna o evento de click
     */
    click () {
      this.$emit('click')
    },
    /**
     * Retorna para o componente que chamou o estado do hover
     *
     * @param value
     */
    hover (value) {
      this.$emit('hover', value)
    }
  }
}
</script>

<style lang="sass" scoped>
#menu-item
  padding: 0 30px
  justify-content: center
  cursor: pointer
  user-select: none

  .menu-title
    display: flex
    align-items: baseline
    margin-top: 6px

    .margem
      font-size: 16px
      color: #424242
      font-weight: bold !important
      margin: 20px 0
      white-space: nowrap

  &:hover
    background-color: rgba(255, 255, 255, 0.1)

    .indicador2
      background-color: #409EFF

  .icone
    margin-right: 10px
    color: #FF8C00
    font-size: 16px

  .indicador2
    width: 100%
    height: 4px
    border-radius: 2px 2px 0 0

#menu-item.active
  .indicador2
    background-color: #409EFF

</style>
