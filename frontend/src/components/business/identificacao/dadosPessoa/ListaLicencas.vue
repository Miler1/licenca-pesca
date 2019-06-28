<template lang="pug">
#lista-licenca
    h3.title.withDivisor(v-if="listaLicencas && listaLicencas.length > 0") {{ $t(`${consultar_prefix}listaLicenca.titulo`) }}
    .licencas(v-if="listaLicencas && listaLicencas.length > 0" v-for="lista in listaLicencas")
        .protocolo {{ lista.protocolo.codigoFormatado }}
        .withDivisor.listMargin
            .flex
                .flex-item
                    span.item-title  {{$t(`${cadastrar_info_prefix}labels.modalidadePesca`)}}
                    span.item-content(v-if="lista.modalidade.id === 1")
                        | {{ $t(`${consultar_prefix}listaLicenca.modalidade.recreativa`) }}
                    span.item-content(v-if="lista.modalidade.id === 0")
                        | {{ $t(`${consultar_prefix}listaLicenca.modalidade.esportiva`) }}

                .flex-item
                    span.item-title {{ $t(`${consultar_prefix}listaLicenca.cadastro`) }}
                    span.item-content {{formatData(lista.dataCriacao)}}
                .flex-item
                    span.item-title {{ $t(`${consultar_prefix}listaLicenca.ativacao`) }}
                    span.item-content {{(lista.dataAtivacao) ? formatData(lista.dataAtivacao) : '-'}}
                .flex-item
                    span.item-title {{ $t(`${consultar_prefix}listaLicenca.vencimento`)}}
                    span.item-content {{setDataVencimento(lista) | moment('DD/MM/YYYY') }}

                .flex-item
                    span.item-title {{ $t(`${consultar_prefix}listaLicenca.situacao.titulo`) }}
                    span.item-content
                        status-card(:situacao="lista.status.codigo")
                .flex-item
                    span.item-title-acoes {{ $t(`${consultar_prefix}listaLicenca.acoes`) }}
                    span.item-content-acoes
                        el-dropdown(trigger="click", v-if="verificaCondicoesParaBotaoDeAcoes(lista)")
                            span.el-dropdown-link.el-button.el-button--primary {{ $t(`${consultar_prefix}listaLicenca.acoes`) }}
                                i.el-icon-arrow-down.el-icon--right
                            el-dropdown-menu(slot="dropdown")
                                el-dropdown-item(type="primary",
                                    v-if="lista.status.codigo === 'AGUARDANDO_PAGAMENTO' || lista.status.codigo === 'INVALIDADO'",
                                    @click.native="gerarBoleto(lista)") {{ $t(`${consultar_prefix}listaLicenca.acoesOpcoes.gerarDocumentoPagamento`) }}

                                el-dropdown-item(type="primary",
                                    v-if="lista.status.codigo === 'ATIVO_AGUARDANDO_PAGAMENTO'",
                                    @click.native="gerarBoleto(lista)") {{ $t(`${consultar_prefix}listaLicenca.acoesOpcoes.gerarDocumentoPagamento`) }}

                                el-dropdown-item(type="primary",
                                    v-if="lista.status.codigo === 'ATIVO'",
                                    @click.native="gerarCarteira(lista)") {{ $t(`${consultar_prefix}listaLicenca.acoesOpcoes.baixarCarteira`) }}

                                el-dropdown-item(type="primary",
                                    v-if="verificarRenovacao(lista)",
                                    @click.native="renovar(lista)") {{ $t(`${consultar_prefix}listaLicenca.acoesOpcoes.renovarLicenca`) }}

                        span(v-if="lista.status.codigo === 'INVALIDADO' || lista.status.codigo === 'RENOVADO'") -
                        span(v-if="!verificaCondicoesParaBotaoDeAcoes(lista)") -

    .sem-licenca.withDivisor(v-if="!listaLicencas || listaLicencas.length <= 0")
        | {{ $t(`${consultar_prefix}listaLicenca.semLicenca`) }}
</template>

<script>

import { mapGetters } from "vuex";
import StatusCard from "../../../layouts/StatusCard";
import Properties from "../../../../properties";
import { translate } from "../../../../utils/helpers/internationalization";
import { REGISTRAR_GERAL_MESSAGES_PREFIX } from "../../../../utils/messages/interface/registrar/geral";
import { INFORMACOES_PREFIX } from "../../../../utils/messages/interface/registrar/informacoes/informacoes";
import moment from "moment";

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
      consultar_prefix: REGISTRAR_GERAL_MESSAGES_PREFIX,
      cadastrar_info_prefix: INFORMACOES_PREFIX
    };
  },

  methods: {

    formatData(strDate) {
        if (strDate === null) return null;
        const DATE_PATTERN = new RegExp("([\\d]{2})\\/([\\d]{2})\\/([\\d]{4})");
        if (DATE_PATTERN.test(strDate)) {
            return translate("interface.geral.data", [
            strDate.substring(0, 2),
            strDate.substring(3, 5),
            strDate.substring(6)
            ]);
        } else {
            let date;
            if (strDate instanceof Date) {
            date = strDate;
            } else {
            date = new Date(strDate);
            }
            return translate("interface.geral.data", [
            date.getDate(),
            date.getMonth() + 1,
            date.getFullYear()
            ]);
        }
    },
    gerarBoleto(lista) {
        let protocolo = lista.protocolo.codigoFormatado;
        let protocoloDesformatado = protocolo.replace("/", "").replace("-", "").replace("-", "");

        const href =
            `${Properties.BASE_URL}/api/boleto?protocolo=` + protocoloDesformatado;

        window.open(href, "_blank");
    },
    gerarCarteira(lista) {
        let protocolo = lista.protocolo.codigoFormatado;
        let protocoloDesformatado = protocolo.replace("/", "").replace("-", "").replace("-", "");

        const href =
        `${Properties.BASE_URL}/api/carteira?protocolo=` +
        protocoloDesformatado;

        window.open(href, "_blank");
    },
    renovar(lista) {
        let protocoloDesformatado = lista.protocolo.codigoFormatado.replace("/", "").replace("-", "").replace("-", "");
        this.$router.push({ name: 'renovar', params: { protocolo: protocoloDesformatado }})
    },
    verificarRenovacao(lista) {
        return lista.status.codigo === 'VENCIDO' || lista.podeRenovar;
    },
    setDataVencimento(lista){
        if(lista.status.codigo !== 'AGUARDANDO_PAGAMENTO'){
            if(lista.dataVencimento == null && lista.dataVencimentoProvisoria != null){
                return lista.dataVencimentoProvisoria;
            }else {
                return (lista.dataVencimento ? lista.dataVencimento : '-');
            }
        }else {
            return "-";
        }
    },
    verificaCondicoesParaBotaoDeAcoes(lista){
        if(lista.status.codigo === 'VENCIDO'){
            return lista.podeRenovar;
        }
        return (lista.status.codigo !== 'RENOVADO')
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

    .sem-licenca
        text-align: center
        font-size: 15px

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
    .item-content-acoes
        font-size: 13px
        text-align: center

    .flex
        display: flex

        .flex-item
            flex: 1
            display: grid

            .item-title
                font-weight: 500
                margin-bottom: 10px

            .item-title-acoes
                font-weight: 500
                margin-bottom: 10px
                text-align: center
</style>
