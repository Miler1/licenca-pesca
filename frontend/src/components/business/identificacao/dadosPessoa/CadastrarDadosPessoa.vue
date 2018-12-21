<template lang="pug">
	#cadastrar-dados-pessoa
		el-form(:model="pessoa" ref="pessoa" :rules="pessoaRules" label-position="top")
			h3 {{ $t(`${cadastrar_prefix}titulos.dadosPessoais`) }}

			el-row.section(:gutter="20")

				el-col(:span="6")
					el-checkbox(v-model="pessoa.estrangeiro") {{ $t(`${cadastrar_prefix}labels.estrangeiro`) }}

			el-row.section(:gutter="20")

				el-col(:span="6")
					el-form-item(:label="$t(`${cadastrar_prefix}labels.nome`)" prop="nome")
						el-input(v-model="pessoa.nome")

				el-col(:span="6" v-if="pessoa.estrangeiro === false")
					el-form-item(:label="$t(`${cadastrar_prefix}labels.cpf`)" prop="cpf")
						el-input(v-model="pessoa.cpf" v-mask="['###.###.###-##']")

				el-col(:span="6" v-if="pessoa.estrangeiro === true")
					el-form-item(:label="$t(`${cadastrar_prefix}labels.passaporte`)" prop="passaporte")
						el-input(v-model="pessoa.passaporte")

				el-col(:span="6")
					el-form-item(:label="$t(`${cadastrar_prefix}labels.dataNascimento`)" prop="dataNascimento")
						el-date-picker(v-model="pessoa.dataNascimento" :format="$t(`${cadastrar_prefix}format.data`)")

				el-col(:span="6")
					el-form-item(:label="$t(`${cadastrar_prefix}labels.sexo`)" prop="sexo")
						el-select(v-model="pessoa.sexo" :placeholder="$t(`${cadastrar_prefix}placeholders.select.sexo`)")
							el-option(v-for="genero in genero_options" :key="genero.key" :value="genero.value" :label="$t(`${cadastrar_prefix}dados.genero.${genero.label}`)")

			el-row(:gutter="20")

				el-col(:span="6")
					el-form-item(:label="$t(`${cadastrar_prefix}labels.nomeMae`)" prop="nomeMae")
						el-input(v-model="pessoa.nomeMae")

			el-row(:gutter="20")

				el-col(:span="24")
					h3.titulo {{ $t(`${cadastrar_prefix}titulos.dadosContato`) }}

			el-row(:gutter="20")

				el-col(:span="6")
					el-form-item(:label="$t(`${cadastrar_prefix}labels.email`)" prop="email")
						el-input(v-model="pessoa.email")

				el-col(:span="6")
					el-form-item(:label="$t(`${cadastrar_prefix}labels.confirmarEmail`)" prop="confirmarEmail")
						el-input(v-model="pessoa.confirmarEmail")

			el-row(:gutter="20")

				el-col(:span="24")
					h3.titulo {{ $t(`${cadastrar_prefix}titulos.enderecoPrincipal`) }}

			el-row(:gutter="20")

				el-col(:span="6")
					el-form-item(:label="$t(`${cadastrar_prefix}labels.zonaLocalizacao`)" prop="enderecoPrincipal.zonaLocalizacao")
						el-radio-group(v-model="pessoa.enderecoPrincipal.zonaLocalizacao")
							el-radio(:label="zonaLocalizacao.urbana") {{ $t(`${cadastrar_prefix}dados.zonaLocalizacao.urbana`) }}
							el-radio(:label="zonaLocalizacao.rural") {{ $t(`${cadastrar_prefix}dados.zonaLocalizacao.rural`) }}

			el-row(:gutter="20" v-if="isEPUrbano()")

				el-col(:span="12")
					el-form-item(:label="$t(`${cadastrar_prefix}labels.logradouro`)" prop="enderecoPrincipal.logradouro")
						el-input(v-model="pessoa.enderecoPrincipal.logradouro")

				el-col(:span="6")
					el-form-item(:label="$t(`${cadastrar_prefix}labels.numero`)" prop="enderecoPrincipal.numero")
						el-input(v-model="pessoa.enderecoPrincipal.numero" v-mask="'#########'")

				el-col(:span="6")
					el-form-item(label="_" prop="enderecoPrincipal.semNumero")
						el-checkbox(v-model="pessoa.enderecoPrincipal.semNumero" :label="$t(`${cadastrar_prefix}labels.semNumero`)")

			el-row(:gutter="20" v-if="isEPUrbano()")

				el-col(:span="6")
					el-form-item(:label="$t(`${cadastrar_prefix}labels.complemento`)" prop="enderecoPrincipal.complemento")
						el-input(v-model="pessoa.enderecoPrincipal.complemento")

				el-col(:span="12")
					el-form-item(:label="$t(`${cadastrar_prefix}labels.bairro`)" prop="enderecoPrincipal.bairro")
						el-input(v-model="pessoa.enderecoPrincipal.bairro")

				el-col(:span="6")
					el-form-item(:label="$t(`${cadastrar_prefix}labels.cep`)" prop="enderecoPrincipal.cep")
						el-input(v-model="pessoa.enderecoPrincipal.cep" v-mask="'#####-###'")

			el-row(:gutter="20")

				el-col(:span="3")
					el-form-item(:label="$t(`${cadastrar_prefix}labels.uf`)" prop="enderecoPrincipal.uf")
						el-select(v-model="pessoa.enderecoPrincipal.uf" :loading="ufSelectLoader" @change="fetchMunicipiosEnderecoPrincial"  :placeholder="$t(`${cadastrar_prefix}placeholders.select.geral`)")
							el-option(v-for="uf in ufs" :key="uf.id" :value="uf.sigla" :label="uf.sigla")

				el-col(:span="6")
					el-form-item(:label="$t(`${cadastrar_prefix}labels.municipio`)" prop="enderecoPrincipal.municipio")
						el-select(v-model="pessoa.enderecoPrincipal.municipio" :loading="municipioSelectLoader" :placeholder="$t(`${cadastrar_prefix}placeholders.select.geral`)")
							el-option(v-for="municipios in municipios" :key="municipios.id" :value="municipios.nome" :label="municipios.nome")

			el-row(:gutter="20" v-if="!isEPUrbano()")

				el-col(:span="24")
					el-form-item(:label="$t(`${cadastrar_prefix}labels.descricaoAcesso`)" prop="enderecoPrincipal.descricaoAcesso")
						el-input(type="textarea" :autosize="{ minRows: 2, maxRows: 4 }" v-model="pessoa.enderecoPrincipal.descricaoAcesso")

			.enderecoCorrespondencia(v-if="!isEPUrbano()")
				el-row(:gutter="20")

					el-col(:span="24")
						h4.subtitulo {{ $t(`${cadastrar_prefix}titulos.enderecoCorrespondencia`) + ":" }}

				el-row(:gutter="20")

					el-col(:span="12")
						el-form-item(:label="$t(`${cadastrar_prefix}labels.logradouro`)" prop="enderecoCorrespondencia.logradouro")
							el-input(v-model="pessoa.enderecoCorrespondencia.logradouro")

					el-col(:span="6")
						el-form-item(:label="$t(`${cadastrar_prefix}labels.numero`)" prop="enderecoCorrespondencia.numero")
							el-input(v-model="pessoa.enderecoCorrespondencia.numero" v-mask="'#########'")

					el-col(:span="6")
						el-form-item(label="_" prop="enderecoCorrespondencia.semNumero")
							el-checkbox(v-model="pessoa.enderecoCorrespondencia.semNumero" :label="$t(`${cadastrar_prefix}labels.semNumero`)")

				el-row(:gutter="20")

					el-col(:span="6")
						el-form-item(:label="$t(`${cadastrar_prefix}labels.complemento`)" prop="enderecoCorrespondencia.complemento")
							el-input(v-model="pessoa.enderecoCorrespondencia.complemento")

					el-col(:span="12")
						el-form-item(:label="$t(`${cadastrar_prefix}labels.bairro`)" prop="enderecoCorrespondencia.bairro")
							el-input(v-model="pessoa.enderecoCorrespondencia.bairro")

					el-col(:span="6")
						el-form-item(:label="$t(`${cadastrar_prefix}labels.cep`)" prop="enderecoCorrespondencia.cep")
							el-input(v-model="pessoa.enderecoCorrespondencia.cep" v-mask="'#####-###'")

				el-row(:gutter="20")

					el-col(:span="3")
						el-form-item(:label="$t(`${cadastrar_prefix}labels.uf`)" prop="enderecoCorrespondencia.uf")
							el-select(v-model="pessoa.enderecoCorrespondencia.uf" :loading="ufSelectLoader" @change="fetchMunicipiosEnderecoCorrespondencia" :placeholder="$t(`${cadastrar_prefix}placeholders.select.geral`)")
								el-option(v-for="uf in ufs" :key="uf.id" :value="uf.sigla" :label="uf.sigla")

					el-col(:span="6")
						el-form-item(:label="$t(`${cadastrar_prefix}labels.municipio`)" prop="enderecoCorrespondencia.municipio")
							el-select(v-model="pessoa.enderecoCorrespondencia.municipio" :loading="municipioSelectLoader" :placeholder="$t(`${cadastrar_prefix}placeholders.select.geral`)")
								el-option(v-for="municipios in municipios" :key="municipios.id" :value="municipios.id" :label="municipios.nome")

</template>

<script>
import Vue from "vue";
import ElInput from "../../../elements/InputElement";
import { mapGetters } from "vuex";
import { PessoaDTO, ZonaLocalizacaoDTO } from "../../../../model/PessoaDTO";
import { PESSOA_RULES } from "../../../../utils/validations/pessoa/pessoa_rules";
import { GENERO_OPTIONS } from "../../../../utils/layout/selectOptions";
import { CADASTRO_MESSAGES_PREFIX } from "../../../../utils/messages/interface/registrar/identificacao/cadastro";
import {
  FETCH_MUNICIPIOS,
  FETCH_UFS,
  SEND_SOLICITANTE
} from "../../../../store/actions.type";

export default {
  name: "CadastrarDadosPessoa",
  components: { ElInput },
  data() {
    return {
      pessoa: PessoaDTO,
      zonaLocalizacao: ZonaLocalizacaoDTO,
      pessoaRules: PESSOA_RULES,
      genero_options: GENERO_OPTIONS,
      cadastrar_prefix: CADASTRO_MESSAGES_PREFIX,
      municipioSelectLoader: true,
      ufSelectLoader: true
    };
  },
  computed: {
    ...mapGetters(["municipios", "ufs"])
  },
  methods: {
    instantiate() {
      Vue.prototype.$cadastroPessoa = this;
    },
    isEPUrbano() {
      return (
        this.pessoa.enderecoPrincipal.zonaLocalizacao ===
        this.zonaLocalizacao.urbana
      );
    },
    fetchUfs() {
      this.ufSelectLoader = true;
      this.$store
        .dispatch(FETCH_UFS)
        .finally(() => (this.ufSelectLoader = false));
    },
    fetchMunicipiosEnderecoPrincial(uf) {
      this.municipioSelectLoader = true;
      this.pessoa.enderecoPrincipal.municipio = null;
      if (uf !== null) {
        debugger;
        let idUf = this.ufs.find(u => u.sigla === uf).id;
        this.$store
          .dispatch(FETCH_MUNICIPIOS, idUf)
          .finally(() => (this.municipioSelectLoader = false));
      }
    },

    fetchMunicipiosEnderecoCorrespondencia(uf) {
      this.municipioSelectLoader = true;
      this.pessoa.enderecoCorrespondencia.municipio = null;
      if (uf !== null) {
        debugger;
        let idUf = this.ufs.find(u => u.sigla === uf).id;
        this.$store
          .dispatch(FETCH_MUNICIPIOS, idUf)
          .finally(() => (this.municipioSelectLoader = false));
      }
    }
  },
  created() {
    this.instantiate();
    this.fetchUfs();
  },

  beforeDestroy() {
    this.$store.dispatch(SEND_SOLICITANTE, this.pessoa);
  }
};
</script>

<style lang="sass">
	@import "../../../../theme/tools/variables"

	#cadastrar-dados-pessoa
		margin-top: 30px

		.information
			margin-top: 20px
			display: flex
			align-items: baseline

			i
				color: $--cor-tema-primario
				margin-right: 10px

		.section
			margin-top: 20px

		.titulo
			margin-bottom: 15px
			padding-top: 20px
			margin-top: 10px
			border-top: 1px solid $--cor-borda

		.subtitulo
			margin-bottom: 15px
			margin-top: 10px
			font-weight: 700

		.el-form-item__label
			padding: 0 !important

		.el-form-item__label[for="enderecoPrincipal.semNumero"]
			color: transparent

		.el-form-item__label[for="enderecoCorrespondencia.semNumero"]
			color: transparent
</style>
