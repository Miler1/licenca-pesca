<template lang="pug">
	#cadastrar-dados-pessoa
		el-form(:model="pessoa" ref="pessoa" :rules="pessoaRules" label-position="top")
			h3 {{ $t("interface.registrar.identificacao.cadastro.titulos.dadosPessoais") }}

			el-row.section(:gutter="20")

				el-col(:span="6")
					el-checkbox(v-model="pessoa.estrangeiro") {{ $t("interface.registrar.identificacao.cadastro.labels.estrangeiro") }}

			el-row.section(:gutter="20")

				el-col(:span="6")
					el-form-item(:label="$t('interface.registrar.identificacao.cadastro.labels.nome')")
						el-input(v-model="pessoa.nome")

</template>

<script>
import { Pessoa } from "../../model/Pessoa";
import { PESSOA_RULES } from "../../utils/validations/pessoaValidation";
import ElInput from "../elements/InputElement";

export default {
  name: "CadastrarDadosPessoa",
  components: { ElInput },
  data() {
    return {
      pessoa: Pessoa,
      pessoaRules: PESSOA_RULES
    };
  }
};
</script>

<style lang="sass">
	@import "../../theme/tools/variables"

	#cadastrar-dados-pessoa
		margin-top: 30px

		.section
			margin-top: 20px

		.el-form-item__label
			padding: 0 !important
</style>
