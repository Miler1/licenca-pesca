<template lang="pug">
	#visualizar-dados-pessoa
		h3.title {{ $t(`${visualizar_prefix}titulo.dadosPessoais`) }}

		el-row.section(:gutter="20")

			el-col(:span="6")
				.align
					h4.label {{ $t(`${visualizar_prefix}label.nome`) }}
					h4(:class="{'not-informed': exist(pessoa.nome)}") {{ pessoa.nome | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

			el-col(:span="6")
				.align
					h4.label {{ $t(`${visualizar_prefix}label.cpf`) }}
					h4(:class="{'not-informed': exist(pessoa.cpf)}") {{ pessoa.cpf | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

			el-col(:span="6")
				.align
					h4.label {{ $t(`${visualizar_prefix}label.passaporte`) }}
					h4(:class="{'not-informed': exist(pessoa.passaporte)}") {{ pessoa.passaporte | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

			el-col(:span="6")
				.align
					h4.label {{ $t(`${visualizar_prefix}label.sexo`) }}
					h4(
						:class="{'not-informed': exist(pessoa.sexo)}"
					) {{ getSexo(pessoa.sexo) | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

		el-row.section(:gutter="20")

			el-col(:span="6")
				.align
					h4.label {{ $t(`${visualizar_prefix}label.dataNascimento`) }}
					h4(:class="{'not-informed': exist(pessoa.dataNascimento)}") {{ localizeDate(pessoa.dataNascimento) | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

			el-col(:span="6")
				.align
					h4.label {{ $t(`${visualizar_prefix}label.nomeMae`) }}
					h4(:class="{'not-informed': exist(pessoa.nomeMae)}") {{ pessoa.nomeMae | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

		h3.title {{ $t(`${visualizar_prefix}titulo.enderecoPrincipal`) }}

		el-row.section(:gutter="20")

			el-col(:span="6")
				.align
					h4.label {{ $t(`${visualizar_prefix}label.localizacao`) }}
					h4(
						:class="{'not-informed': exist(pessoa.enderecoPrincipal.zonaLocalizacao)}"
					) {{ getZonaLocalizacao(pessoa.enderecoPrincipal.zonaLocalizacao) | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

			el-col(:span="6")
				.align
					h4.label {{ $t(`${visualizar_prefix}label.logradouro`) }}
					h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.logradouro)}") {{ pessoa.enderecoPrincipal.logradouro | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

			el-col(:span="6")
				.align
					h4.label {{ $t(`${visualizar_prefix}label.numero`) }}
					h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.numero)}") {{ pessoa.enderecoPrincipal.numero | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

			el-col(:span="6")
				.align
					h4.label {{ $t(`${visualizar_prefix}label.bairro`) }}
					h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.bairro)}") {{ pessoa.enderecoPrincipal.bairro | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

		el-row.section(:gutter="20")

			el-col(:span="6")
				.align
					h4.label {{ $t(`${visualizar_prefix}label.complemento`) }}
					h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.complemento)}") {{ pessoa.enderecoPrincipal.complemento | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

			el-col(:span="6")
				.align
					h4.label {{ $t(`${visualizar_prefix}label.cep`) }}
					h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.cep)}") {{ pessoa.enderecoPrincipal.cep | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

			el-col(:span="6")
				.align
					h4.label {{ $t(`${visualizar_prefix}label.municipio`) }}
					h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.municipio)}") {{ pessoa.enderecoPrincipal.municipio | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

			el-col(:span="6")
				.align
					h4.label {{ $t(`${visualizar_prefix}label.uf`) }}
					h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.uf)}") {{ pessoa.enderecoPrincipal.uf | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

		.enderecoCorrespondencia(v-if="pessoa.enderecoPrincipal.zonaLocalizacao === 1")
			h3.title {{ $t(`${visualizar_prefix}titulo.enderecoCorrespondencia`) }}

			el-row.section(:gutter="20")

				el-col(:span="6")
					.align
						h4.label {{ $t(`${visualizar_prefix}label.localizacao`) }}
						h4(
						:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.zonaLocalizacao)}"
						) {{ getZonaLocalizacao(pessoa.enderecoCorrespondencia.zonaLocalizacao) | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

				el-col(:span="6")
					.align
						h4.label {{ $t(`${visualizar_prefix}label.logradouro`) }}
						h4(:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.logradouro)}") {{ pessoa.enderecoCorrespondencia.logradouro | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

				el-col(:span="6")
					.align
						h4.label {{ $t(`${visualizar_prefix}label.numero`) }}
						h4(:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.numero)}") {{ pessoa.enderecoCorrespondencia.numero | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

				el-col(:span="6")
					.align
						h4.label {{ $t(`${visualizar_prefix}label.bairro`) }}
						h4(:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.bairro)}") {{ pessoa.enderecoCorrespondencia.bairro | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

			el-row.section(:gutter="20")

				el-col(:span="6")
					.align
						h4.label {{ $t(`${visualizar_prefix}label.complemento`) }}
						h4(:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.complemento)}") {{ pessoa.enderecoCorrespondencia.complemento | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

				el-col(:span="6")
					.align
						h4.label {{ $t(`${visualizar_prefix}label.cep`) }}
						h4(:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.cep)}") {{ pessoa.enderecoCorrespondencia.cep | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

				el-col(:span="6")
					.align
						h4.label {{ $t(`${visualizar_prefix}label.municipio`) }}
						h4(:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.municipio)}") {{ pessoa.enderecoCorrespondencia.municipio | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

				el-col(:span="6")
					.align
						h4.label {{ $t(`${visualizar_prefix}label.uf`) }}
						h4(:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.uf)}") {{ pessoa.enderecoCorrespondencia.uf | placeholder($t(`${visualizar_prefix}naoInformado`)) }}

</template>

<script>
import Vue from "vue";
import * as _ from "lodash";
import { ZONA_LOCALIZACAO, SEXO } from "../../../../model/constantes";
import { VISUALIZAR_MESSAGES_PREFIX } from "../../../../utils/messages/interface/registrar/identificacao/visualizar";

export default {
  name: "VisualizarDadosPessoa",

  props: {
    pessoa: {
      type: Object
    }
  },

  data() {
    return {
      visualizar_prefix: VISUALIZAR_MESSAGES_PREFIX
    };
  },

  methods: {
    exist(attr) {
      return attr === null || _.isEmpty(attr.toString());
    },

    localizeDate(strDate) {
      if (strDate === null) return null;

      let data = [
        strDate.substring(0, 2), // dia
        strDate.substring(3, 5), // mes
        strDate.substring(6) // ano
      ];

      return Vue.prototype.$translator.t("interface.geral.data", data);
    },

    getZonaLocalizacao(localizacao) {
      if (localizacao === ZONA_LOCALIZACAO.URBANA) {
        return Vue.prototype.$translator.t(
          `${this.visualizar_prefix}dados.zonaLocalizacao.urbana`
        );
      } else if (localizacao === ZONA_LOCALIZACAO.RURAL) {
        return Vue.prototype.$translator.t(
          `${this.visualizar_prefix}dados.zonaLocalizacao.rural`
        );
      } else {
        return null;
      }
    },

    getSexo(sexo) {
      switch (sexo) {
        case SEXO.MASCULINO:
          return Vue.prototype.$translator.t(
            `${this.visualizar_prefix}dados.genero.masculino`
          );
        case SEXO.FEMININO:
          return Vue.prototype.$translator.t(
            `${this.visualizar_prefix}dados.genero.feminino`
          );
        case SEXO.OUTROS:
          return Vue.prototype.$translator.t(
            `${this.visualizar_prefix}dados.genero.outros`
          );
        default:
          return null;
      }
    }
  }
};
</script>

<style lang="sass">
	@import "../../../../theme/tools/variables"

	#visualizar-dados-pessoa

		.title
			margin-top: 30px

		.section
			margin-top: 20px

		.align
			display: flex
			flex-direction: column

			.label
				margin-bottom: 5px
				color: $--cor-texto-secundario

		.not-informed
			font-style: italic

</style>
