<template lang="pug">
  #cadastrar-dados-pessoa
    el-form(:model="pessoa" ref="pessoa" :rules="pessoaRules" label-position="top")
      h3 {{ $t(`${cadastrar_prefix}titulos.dadosPessoais`) }}

      el-row.section(:gutter="20")

        el-col(:span="6")
          el-checkbox(v-model="pessoa.estrangeiro", disabled) {{ $t(`${cadastrar_prefix}labels.estrangeiro`) }}

      el-row.section(:gutter="20")

        el-col(:span="6")
          el-form-item(:label="$t(`${cadastrar_prefix}labels.nome`)" prop="nome")
              el-input(v-model="pessoa.nome", ref="nome")

        el-col(:span="6" v-show="pessoa.estrangeiro === false")
          el-form-item(:label="$t(`${cadastrar_prefix}labels.cpf`)")
            el-input(v-model="pessoa.cpf" v-mask="['###.###.###-##']" disabled)

        el-col(:span="6" v-show="pessoa.estrangeiro === true")
          el-form-item(:label="$t(`${cadastrar_prefix}labels.passaporte`)" )
            el-input(v-model="pessoa.passaporte" v-mask="['XXXXXXXXXXXXXXXXXXXXXXXXXXXX']" disabled)

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
            el-radio-group(v-model="pessoa.enderecoPrincipal.zonaLocalizacao" @change="changeZonaLocalizacao()")
              el-radio(:label="zonaLocalizacao.urbana") {{ $t(`${cadastrar_prefix}dados.zonaLocalizacao.urbana`) }}
              el-radio(:label="zonaLocalizacao.rural") {{ $t(`${cadastrar_prefix}dados.zonaLocalizacao.rural`) }}

      el-row(:gutter="20" v-if="isEPUrbano()")

        el-col(:span="12")
          el-form-item(:label="$t(`${cadastrar_prefix}labels.logradouro`)" prop="enderecoPrincipal.logradouro")
            el-input(v-model="pessoa.enderecoPrincipal.logradouro")

        el-col(:span="6")
          el-form-item.is-required(:label="$t(`${cadastrar_prefix}labels.numero`)" prop="enderecoPrincipal.numero" ref="numeroPrincipal")
            el-input(v-model="pessoa.enderecoPrincipal.numero" v-mask="'#########'" :disabled="pessoa.enderecoPrincipal.semNumero" )

        el-col(:span="6")
          el-form-item(label="_" prop="enderecoPrincipal.semNumero")
            el-checkbox(v-model="pessoa.enderecoPrincipal.semNumero" @change="changeSemNumeroEndPrincipal()" :label="$t(`${cadastrar_prefix}labels.semNumero`)")

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
            el-select(v-model="pessoa.enderecoPrincipal.municipio" :loading="municipioSelectLoader" ref="enderecoPrincipal" :placeholder="$t(`${cadastrar_prefix}placeholders.select.geral`)" )
              el-option(v-for="municipio in municipios" :key="municipio.id" :value="municipio.id" :label="municipio.nome")

      .enderecoCorrespondencia(v-if="!isEPUrbano()")
        el-row(:gutter="20")
          el-col(:span="24")
            el-form-item(:label="$t(`${cadastrar_prefix}labels.descricaoAcesso`)" prop="enderecoPrincipal.descricaoAcesso")
              el-input(v-model="pessoa.enderecoPrincipal.descricaoAcesso")

        el-row(:gutter="20")

          el-col(:span="24")
            h4.subtitulo {{ $t(`${cadastrar_prefix}titulos.enderecoCorrespondencia`) + ":" }}

        el-row(:gutter="20")

          el-col(:span="12")
            el-form-item(:label="$t(`${cadastrar_prefix}labels.logradouro`)" prop="enderecoCorrespondencia.logradouro")
              el-input(v-model="pessoa.enderecoCorrespondencia.logradouro")

          el-col(:span="6")
            el-form-item.is-required(:label="$t(`${cadastrar_prefix}labels.numero`)" prop="enderecoCorrespondencia.numero"  ref="numeroCorrespondencia")
              el-input(v-model="pessoa.enderecoCorrespondencia.numero" v-mask="'#########'"  :disabled="pessoa.enderecoCorrespondencia.semNumero")

          el-col(:span="6")
            el-form-item(label="_" prop="enderecoCorrespondencia.semNumero")
              el-checkbox(v-model="pessoa.enderecoCorrespondencia.semNumero" @change="changeSemNumeroEndCorrespondencia()" :label="$t(`${cadastrar_prefix}labels.semNumero`)")

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
              el-select(v-model="pessoa.enderecoCorrespondencia.municipio" ref="enderecoCorrespondencia" :loading="municipioSelectLoader" :placeholder="$t(`${cadastrar_prefix}placeholders.select.geral`)")
                el-option(v-for="municipio in municipiosCorrespondencia" :key="municipio.id" :value="municipio.id" :label="municipio.nome")
      
      div(v-if="pessoa.passaporte", ref="enderecoEstrangeiro")
        el-row(:gutter="20")

          el-col(:span="24")
            h3.titulo {{ $t(`${cadastrar_prefix}titulos.enderecoEstrangeiro`) }}
          
        el-row(:gutter="20")

          el-col(:span="9")
            el-form-item(:label="$t(`${cadastrar_prefix}labels.nacionalidade`)" prop="enderecoEstrangeiro.nacionalidade")
              el-select.full-width(v-model="pessoa.enderecoEstrangeiro.nacionalidade" :loading="nacionalidadeSelectLoader" ref="nacionalidade" :placeholder="$t(`${cadastrar_prefix}placeholders.select.geral`)" )
                el-option(v-for="nacionalidade in nacionalidades" :key="nacionalidade.id" :value="nacionalidade.id" :label="nacionalidade.nome")

          el-col(:span="15")
            el-form-item(:label="$t(`${cadastrar_prefix}labels.descricaoEndereco`)" prop="enderecoEstrangeiro.descricaoEndereco")
              el-input(v-model="pessoa.enderecoEstrangeiro.descricaoEndereco")
        
        el-row(:gutter="20")

          el-col(:span="12")
            el-form-item(:label="$t(`${cadastrar_prefix}labels.cidade`)" prop="enderecoEstrangeiro.cidade")
              el-input(v-model="pessoa.enderecoEstrangeiro.cidade")

          el-col(:span="12")
            el-form-item(:label="$t(`${cadastrar_prefix}labels.estado`)" prop="enderecoEstrangeiro.estado")
              el-input(v-model="pessoa.enderecoEstrangeiro.estado")
        
        el-row(:gutter="20")
          el-col(:span="9")
            el-form-item(:label="$t(`${cadastrar_prefix}labels.pais`)" prop="enderecoEstrangeiro.pais")
              el-select.full-width(v-model="pessoa.enderecoEstrangeiro.pais" :loading="paisesSelectLoader" ref="pais" :placeholder="$t(`${cadastrar_prefix}placeholders.select.geral`)" )
                el-option(v-for="pais in paises" :key="pais.id" :value="pais.id" :label="pais.nome")

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
  FETCH_NACIONALIDADES,
  FETCH_PAISES,
  SEND_SOLICITANTE,
FETCH_MUNICIPIOS_CORRESPONDENCIA
} from "../../../../store/actions.type";

export default {
  name: "CadastrarDadosPessoa",
  components: { ElInput },
  data() {
    return {
      estrangeiroDisabled: false,
      pessoaCpf: "",
      pessoaPassaporte: "",
      pessoa: {
        estrangeiro: false,
        nome: null,
        cpf: null,
        passaporte: null,
        dataNascimento: null,
        sexo: null,
        nomeMae: null,
        email: null,
        confirmarEmail: null,
        enderecoPrincipal: {
          tipo: null,
          zonaLocalizacao: ZonaLocalizacaoDTO.urbana,
          semNumero: null,
          logradouro: null,
          numero: null,
          bairro: null,
          complemento: null,
          cep: null,
          uf: null,
          municipio: null,
          municipioNome: null,
          descricaoAcesso: null
        },
        enderecoCorrespondencia: {
          tipo: null,
          zonaLocalizacao: ZonaLocalizacaoDTO.urbana,
          semNumero: null,
          logradouro: null,
          numero: null,
          bairro: null,
          complemento: null,
          cep: null,
          uf: null,
          municipio: null,
          municipioNome: null,
          descricaoAcesso: null
        },
        enderecoEstrangeiro: {
          nacionalidade: null,
          descricaoEndereco: null,
          cidade: null,
          estado: null,
          pais: null
        }
      },
      zonaLocalizacao: ZonaLocalizacaoDTO,
      pessoaRules: PESSOA_RULES,
      genero_options: GENERO_OPTIONS,
      cadastrar_prefix: CADASTRO_MESSAGES_PREFIX,
      municipioSelectLoader: true,
      paisesSelectLoader: true,
      ufSelectLoader: true,
      valid: false
    };
  },
  computed: {
    ...mapGetters(["municipios", "municipiosCorrespondencia", "ufs", "cpfPesquisa", "passaportePesquisa", "nacionalidades", "paises"])
  },
 
  methods: {
    instantiate() {
      Vue.prototype.$cadastroPessoa = this;
    },

    validate() {
      this.valid = false;
      this.$refs["pessoa"].validate(valid => {
        this.valid = valid;
      });
    },
    getValidate() {
      return this.valid;
    },
    
    atualizarCpfPesquisado(resource) {
      this.pessoa.cpf = resource.cpf;
      this.pessoa.passaporte = resource.passaporte;
      if(resource.cpf === null && resource.passaporte !== null){
        this.pessoa.estrangeiro = true;
        this.estrangeiroDisabled = true;
      } else {
        this.pessoa.estrangeiro = false;
        this.estrangeiroDisabled = false;
      }
    },
    isEPUrbano() {
      return (
        this.pessoa.enderecoPrincipal.zonaLocalizacao ===
        this.zonaLocalizacao.urbana
      );
    },
    enviarParaStore() {
      if (this.$refs["enderecoPrincipal"]) {
        let municipioId = this.pessoa.enderecoPrincipal.municipio;
        this.pessoa.enderecoPrincipal.municipio = {};
        this.pessoa.enderecoPrincipal.municipio.id = municipioId;
        this.pessoa.enderecoPrincipal.municipioNome = this.$refs["enderecoPrincipal"].selectedLabel;
      }
      if (this.$refs["enderecoCorrespondencia"]) {
        let municipioId = this.pessoa.enderecoCorrespondencia.municipio;
        this.pessoa.enderecoCorrespondencia.municipio = {};
        this.pessoa.enderecoCorrespondencia.municipio.id = municipioId;
        this.pessoa.enderecoCorrespondencia.municipioNome = this.$refs["enderecoCorrespondencia"].selectedLabel;
      }
      if(this.$refs["enderecoEstrangeiro"]){
        let nacionalidadeId = this.pessoa.enderecoEstrangeiro.nacionalidade;
        let paisId = this.pessoa.enderecoEstrangeiro.pais;
        this.pessoa.enderecoEstrangeiro.pais = {};
        this.pessoa.enderecoEstrangeiro.pais.id = paisId;
        this.pessoa.enderecoEstrangeiro.paisNome = this.$refs["pais"].selectedLabel;
        this.pessoa.enderecoEstrangeiro.nacionalidade = {};
        this.pessoa.enderecoEstrangeiro.nacionalidade.id = nacionalidadeId;
        this.pessoa.enderecoEstrangeiro.nacionalidadeNome = this.$refs["nacionalidade"].selectedLabel;

      }
      // this.pessoa.cpf = this.pessoaCpf;
      // this.pessoa.passaporte = this.pessoaPassaporte;
      this.$store.dispatch(SEND_SOLICITANTE, this.pessoa);
    },
    tratarMunicipio() {
      if(this.pessoa.enderecoPrincipal.municipio){
        this.pessoa.enderecoPrincipal.municipio = this.pessoa.enderecoPrincipal.municipio.id; 
      }
      if(this.pessoa.enderecoCorrespondencia.municipio){
        this.pessoa.enderecoCorrespondencia.municipio = this.pessoa.enderecoCorrespondencia.municipio.id; 
      }
    },
    changeSemNumeroEndPrincipal() {
      this.removerMensagemErro("numeroPrincipal");
      this.pessoa.enderecoPrincipal.numero = "";
    },
    removerMensagemErro(ref) {

      this.$refs[ref].validateMessage = "";
      this.$refs[ref].validateState = undefined;
    },
    changeSemNumeroEndCorrespondencia() {
      this.removerMensagemErro("numeroCorrespondencia");
      this.pessoa.enderecoCorrespondencia.numero = "";
    },
    fetchUfs() {
      this.ufSelectLoader = true;
      this.$store
        .dispatch(FETCH_UFS)
        .finally(() => (this.ufSelectLoader = false));
    },
    changeZonaLocalizacao () {
      this.$refs["pessoa"].clearValidate();
    },
    fetchMunicipiosEnderecoPrincial(uf) {
      this.municipioSelectLoader = true;
      this.pessoa.enderecoPrincipal.municipio = null;
      if (uf !== null) {
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
        let idUf = this.ufs.find(u => u.sigla === uf).id;
        this.$store
          .dispatch(FETCH_MUNICIPIOS_CORRESPONDENCIA, idUf)
          .finally(() => (this.municipioSelectLoader = false));
      }
    },
    fetchNacionalidade() {
      this.nacionalidadeSelectLoader = true;
      this.$store
        .dispatch(FETCH_NACIONALIDADES)
        .finally(() => (this.nacionalidadeSelectLoader = false));
    },
    fetchPaises() {
      this.paisesSelectLoader = true;
      this.$store
        .dispatch(FETCH_PAISES)
        .finally(() => (this.paisesSelectLoader = false));
    }
  },
  created() {
    this.instantiate();
    this.fetchUfs();
    this.fetchNacionalidade();
    this.fetchPaises();
  },

  beforeDestroy() {
    this.enviarParaStore();
  },

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
    .full-width
      width: 100%
</style>
