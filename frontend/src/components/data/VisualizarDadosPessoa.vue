<template lang="pug">
	#visualizar-dados-pessoa
		h3.title {{ $t("message.register.data.titles.person") }}

		el-row.section(:gutter="20")

			el-col(:span="6")
				.align
					h4.label {{ $t("message.register.data.labels.name") }}
					h4(:class="{'not-informed': exist(pessoa.nome)}") {{ pessoa.nome | placeholder($t("message.notInformed")) }}

			el-col(:span="6")
				.align
					h4.label {{ $t("message.register.data.labels.cpf") }}
					h4(:class="{'not-informed': exist(pessoa.cpf)}") {{ pessoa.cpf | placeholder($t("message.notInformed")) }}

			el-col(:span="6")
				.align
					h4.label {{ $t("message.register.data.labels.passport") }}
					h4(:class="{'not-informed': exist(pessoa.passaporte)}") {{ pessoa.passaporte | placeholder($t("message.notInformed")) }}

			el-col(:span="6")
				.align
					h4.label {{ $t("message.register.data.labels.sex") }}
					h4(
						:class="{'not-informed': exist(pessoa.sexo)}"
					) {{ getSexo(pessoa.sexo) !== null ? $t(`message.register.data.labels.gender.${getSexo(pessoa.sexo)}`) : null | placeholder($t("message.notInformed")) }}

		el-row.section(:gutter="20")

			el-col(:span="6")
				.align
					h4.label {{ $t("message.register.data.labels.birthday") }}
					h4(:class="{'not-informed': exist(pessoa.dataNascimento)}") {{ localizeDate(pessoa.dataNascimento) !== null ? $t("date", localizeDate(pessoa.dataNascimento)) : null | placeholder($t("message.notInformed")) }}

			el-col(:span="6")
				.align
					h4.label {{ $t("message.register.data.labels.mother") }}
					h4(:class="{'not-informed': exist(pessoa.nomeMae)}") {{ pessoa.nomeMae | placeholder($t("message.notInformed")) }}

		h3.title {{ $t("message.register.data.titles.mainAddress") }}

		el-row.section(:gutter="20")

			el-col(:span="6")
				.align
					h4.label {{ $t("message.register.data.labels.location") }}
					h4(
						:class="{'not-informed': exist(pessoa.enderecoPrincipal.zonaLocalizacao)}"
					) {{ getZonaLocalizacao(pessoa.enderecoPrincipal.zonaLocalizacao) !== null ? $t(`message.register.data.labels.${getZonaLocalizacao(pessoa.enderecoPrincipal.zonaLocalizacao)}`) : null | placeholder($t("message.notInformed")) }}

			el-col(:span="6")
				.align
					h4.label {{ $t("message.register.data.labels.street") }}
					h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.logradouro)}") {{ pessoa.enderecoPrincipal.logradouro | placeholder($t("message.notInformed")) }}

			el-col(:span="6")
				.align
					h4.label {{ $t("message.register.data.labels.number") }}
					h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.numero)}") {{ pessoa.enderecoPrincipal.numero | placeholder($t("message.notInformed")) }}

			el-col(:span="6")
				.align
					h4.label {{ $t("message.register.data.labels.neighborhood") }}
					h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.bairro)}") {{ pessoa.enderecoPrincipal.bairro | placeholder($t("message.notInformed")) }}

		el-row.section(:gutter="20")

			el-col(:span="6")
				.align
					h4.label {{ $t("message.register.data.labels.complement") }}
					h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.complemento)}") {{ pessoa.enderecoPrincipal.complemento | placeholder($t("message.notInformed")) }}

			el-col(:span="6")
				.align
					h4.label {{ $t("message.register.data.labels.cep") }}
					h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.cep)}") {{ pessoa.enderecoPrincipal.cep | placeholder($t("message.notInformed")) }}

			el-col(:span="6")
				.align
					h4.label {{ $t("message.register.data.labels.city") }}
					h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.municipio)}") {{ pessoa.enderecoPrincipal.municipio | placeholder($t("message.notInformed")) }}

			el-col(:span="6")
				.align
					h4.label {{ $t("message.register.data.labels.uf") }}
					h4(:class="{'not-informed': exist(pessoa.enderecoPrincipal.uf)}") {{ pessoa.enderecoPrincipal.uf | placeholder($t("message.notInformed")) }}

		.enderecoCorrespondencia(v-if="getZonaLocalizacao(pessoa.enderecoPrincipal.zonaLocalizacao) === 'rural'")
			h3.title {{ $t("message.register.data.titles.mailingAddress") }}

			el-row.section(:gutter="20")

				el-col(:span="6")
					.align
						h4.label {{ $t("message.register.data.labels.location") }}
						h4(
						:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.zonaLocalizacao)}"
						) {{ getZonaLocalizacao(pessoa.enderecoCorrespondencia.zonaLocalizacao) !== null ? $t(`message.register.data.labels.${getZonaLocalizacao(pessoa.enderecoCorrespondencia.zonaLocalizacao)}`) : null | placeholder($t("message.notInformed")) }}

				el-col(:span="6")
					.align
						h4.label {{ $t("message.register.data.labels.street") }}
						h4(:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.logradouro)}") {{ pessoa.enderecoCorrespondencia.logradouro | placeholder($t("message.notInformed")) }}

				el-col(:span="6")
					.align
						h4.label {{ $t("message.register.data.labels.number") }}
						h4(:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.numero)}") {{ pessoa.enderecoCorrespondencia.numero | placeholder($t("message.notInformed")) }}

				el-col(:span="6")
					.align
						h4.label {{ $t("message.register.data.labels.neighborhood") }}
						h4(:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.bairro)}") {{ pessoa.enderecoCorrespondencia.bairro | placeholder($t("message.notInformed")) }}

			el-row.section(:gutter="20")

				el-col(:span="6")
					.align
						h4.label {{ $t("message.register.data.labels.complement") }}
						h4(:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.complemento)}") {{ pessoa.enderecoCorrespondencia.complemento | placeholder($t("message.notInformed")) }}

				el-col(:span="6")
					.align
						h4.label {{ $t("message.register.data.labels.cep") }}
						h4(:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.cep)}") {{ pessoa.enderecoCorrespondencia.cep | placeholder($t("message.notInformed")) }}

				el-col(:span="6")
					.align
						h4.label {{ $t("message.register.data.labels.city") }}
						h4(:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.municipio)}") {{ pessoa.enderecoCorrespondencia.municipio | placeholder($t("message.notInformed")) }}

				el-col(:span="6")
					.align
						h4.label {{ $t("message.register.data.labels.uf") }}
						h4(:class="{'not-informed': exist(pessoa.enderecoCorrespondencia.uf)}") {{ pessoa.enderecoCorrespondencia.uf | placeholder($t("message.notInformed")) }}

</template>

<script>
import * as _ from "lodash";
import { ZONA_LOCALIZACAO, SEXO } from "../../model/constantes";

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

      return [
        strDate.substring(0, 2), // dia
        strDate.substring(3, 5), // mes
        strDate.substring(6) // ano
      ];
    },

    getZonaLocalizacao(localizacao) {
      if (localizacao === ZONA_LOCALIZACAO.URBANA) {
        return "urban";
      } else if (localizacao === ZONA_LOCALIZACAO.RURAL) {
        return "rural";
      } else {
        return null;
      }
    },

    getSexo(sexo) {
      switch (sexo) {
        case SEXO.MASCULINO:
          return "male";
        case SEXO.FEMININO:
          return "fem";
        case SEXO.OUTROS:
          return "other";
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
