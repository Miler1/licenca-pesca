<template lang="pug">
	#visualizar-dados-pessoa
		h3.title {{ $t("interface.registrar.identificacao.visualizar.titulo.dadosPessoais") }}

		el-row.section(:gutter="20")

			el-col(:span="6")
				.align
					h4.label {{ $t("interface.registrar.identificacao.visualizar.label.nome") }}
					h4(:class="{'not-informed': exist(pessoa.nome)}") {{ pessoa.nome | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

			el-col(:span="6")
				.align
					h4.label {{ $t("interface.registrar.identificacao.visualizar.label.cpf") }}
					h4(:class="{'not-informed': exist(pessoa.cpf)}") {{ pessoa.cpf | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

			el-col(:span="6")
				.align
					h4.label {{ $t("interface.registrar.identificacao.visualizar.label.passaporte") }}
					h4(:class="{'not-informed': exist(pessoa.passaporte)}") {{ pessoa.passaporte | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

			el-col(:span="6")
				.align
					h4.label {{ $t("interface.registrar.identificacao.visualizar.label.sexo") }}
					h4(
						:class="{'not-informed': exist(pessoa.sexo)}"
					) {{ getSexo(pessoa.sexo) | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

		el-row.section(:gutter="20")

			el-col(:span="6")
				.align
					h4.label {{ $t("interface.registrar.identificacao.visualizar.label.dataNascimento") }}
					h4(:class="{'not-informed': exist(pessoa.dataNascimento)}") {{ localizeDate(pessoa.dataNascimento) | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

			el-col(:span="6")
				.align
					h4.label {{ $t("interface.registrar.identificacao.visualizar.label.nomeMae") }}
					h4(:class="{'not-informed': exist(pessoa.nomeMae)}") {{ pessoa.nomeMae | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

		h3.title {{ $t("interface.registrar.identificacao.visualizar.titulo.enderecoPrincipal") }}

		el-row.section(:gutter="20")

			el-col(:span="6")
				.align
					h4.label {{ $t("interface.registrar.identificacao.visualizar.label.localizacao") }}
					h4(
						:class="{'not-informed': exist(pessoa.enderecoPrincipal.zonaLocalizacao)}"
					) {{ getZonaLocalizacao(pessoa.enderecoPrincipal.zonaLocalizacao) | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

			el-col(:span="6")
				.align
					h4.label {{ $t("interface.registrar.identificacao.visualizar.label.logradouro") }}
					h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.logradouro)}") {{ pessoa.enderecoPrincipal.logradouro | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

			el-col(:span="6")
				.align
					h4.label {{ $t("interface.registrar.identificacao.visualizar.label.numero") }}
					h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.numero)}") {{ pessoa.enderecoPrincipal.numero | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

			el-col(:span="6")
				.align
					h4.label {{ $t("interface.registrar.identificacao.visualizar.label.bairro") }}
					h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.bairro)}") {{ pessoa.enderecoPrincipal.bairro | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

		el-row.section(:gutter="20")

			el-col(:span="6")
				.align
					h4.label {{ $t("interface.registrar.identificacao.visualizar.label.complemento") }}
					h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.complemento)}") {{ pessoa.enderecoPrincipal.complemento | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

			el-col(:span="6")
				.align
					h4.label {{ $t("interface.registrar.identificacao.visualizar.label.cep") }}
					h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.cep)}") {{ pessoa.enderecoPrincipal.cep | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

			el-col(:span="6")
				.align
					h4.label {{ $t("interface.registrar.identificacao.visualizar.label.municipio") }}
					h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.municipio)}") {{ pessoa.enderecoPrincipal.municipio | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

			el-col(:span="6")
				.align
					h4.label {{ $t("interface.registrar.identificacao.visualizar.label.uf") }}
					h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.uf)}") {{ pessoa.enderecoPrincipal.uf | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

		.enderecoCorrespondencia(v-if="pessoa.enderecoPrincipal.zonaLocalizacao === 1")
			h3.title {{ $t("interface.registrar.identificacao.visualizar.titulo.enderecoCorrespondencia") }}

			el-row.section(:gutter="20")

				el-col(:span="6")
					.align
						h4.label {{ $t("interface.registrar.identificacao.visualizar.label.localizacao") }}
						h4(
						:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.zonaLocalizacao)}"
						) {{ getZonaLocalizacao(pessoa.enderecoCorrespondencia.zonaLocalizacao) | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

				el-col(:span="6")
					.align
						h4.label {{ $t("interface.registrar.identificacao.visualizar.label.logradouro") }}
						h4(:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.logradouro)}") {{ pessoa.enderecoCorrespondencia.logradouro | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

				el-col(:span="6")
					.align
						h4.label {{ $t("interface.registrar.identificacao.visualizar.label.numero") }}
						h4(:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.numero)}") {{ pessoa.enderecoCorrespondencia.numero | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

				el-col(:span="6")
					.align
						h4.label {{ $t("interface.registrar.identificacao.visualizar.label.bairro") }}
						h4(:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.bairro)}") {{ pessoa.enderecoCorrespondencia.bairro | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

			el-row.section(:gutter="20")

				el-col(:span="6")
					.align
						h4.label {{ $t("interface.registrar.identificacao.visualizar.label.complemento") }}
						h4(:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.complemento)}") {{ pessoa.enderecoCorrespondencia.complemento | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

				el-col(:span="6")
					.align
						h4.label {{ $t("interface.registrar.identificacao.visualizar.label.cep") }}
						h4(:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.cep)}") {{ pessoa.enderecoCorrespondencia.cep | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

				el-col(:span="6")
					.align
						h4.label {{ $t("interface.registrar.identificacao.visualizar.label.municipio") }}
						h4(:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.municipio)}") {{ pessoa.enderecoCorrespondencia.municipio | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

				el-col(:span="6")
					.align
						h4.label {{ $t("interface.registrar.identificacao.visualizar.label.uf") }}
						h4(:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.uf)}") {{ pessoa.enderecoCorrespondencia.uf | placeholder($t("interface.registrar.identificacao.visualizar.naoInformado")) }}

</template>

<script>
import * as _ from "lodash";
import { ZONA_LOCALIZACAO, SEXO } from "../../model/constantes";
import Vue from "vue";

export default {
  name: "VisualizarDadosPessoa",

  props: {
    pessoa: {
      type: Object
    }
  },

  data() {
    return {};
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
          "interface.registrar.identificacao.visualizar.dados.zonaLocalizacao.urbana"
        );
      } else if (localizacao === ZONA_LOCALIZACAO.RURAL) {
        return Vue.prototype.$translator.t(
          "interface.registrar.identificacao.visualizar.dados.zonaLocalizacao.rural"
        );
      } else {
        return null;
      }
    },

    getSexo(sexo) {
      switch (sexo) {
        case SEXO.MASCULINO:
          return Vue.prototype.$translator.t(
            "interface.registrar.identificacao.visualizar.dados.genero.masculino"
          );
        case SEXO.FEMININO:
          return Vue.prototype.$translator.t(
            "interface.registrar.identificacao.visualizar.dados.genero.feminino"
          );
        case SEXO.OUTROS:
          return Vue.prototype.$translator.t(
            "interface.registrar.identificacao.visualizar.dados.genero.outros"
          );
        default:
          return null;
      }
    }
  }
};
</script>

<style lang="sass">
	@import "../../theme/tools/variables"

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
