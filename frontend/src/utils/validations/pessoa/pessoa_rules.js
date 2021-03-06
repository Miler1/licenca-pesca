import { required, validate } from "../utils";
import Properties from "../../../properties";

import {
  checkCPF,
  checkDate,
  checkEmail,
  checkNumero,
  checkSemNumero,
  checkConfirmEmail
} from "./pessoa_checks";

const en = Properties.LANGUAGE_INDEX.EN;

export const PESSOA_RULES = {
  nome: [required("nome")],

  cpf: [required("cpf"), validate(checkCPF, "cpf.invalid")],

  passaporte: [required("passaporte")],

  dataNascimento: [
    required("dataNascimento", "change"),
    validate(checkDate, "dataNascimento.past", "change")
  ],

  sexo: [required("sexo", "change")],

  nomeMae: [required("nomeMae")],

  email: [required("email"), validate(checkEmail, "email.invalid")],

  confirmarEmail: [
    required("confirmarEmail"),
    validate(checkConfirmEmail, "confirmarEmail.check")
  ],

  enderecoPrincipal: {
    zonaLocalizacao: [required("zonaLocalizacao")],

    logradouro: [required("logradouro")],

    numero: [validate(checkNumero, "numero.vazio")],

    // semNumero: [validate(checkSemNumero, "numero.vazio", "change")],

    bairro: [required("bairro")],

    cep: [required("cep")],

    municipio: [required("municipio", "change")],

    uf: [required("uf", "change")],

    descricaoAcesso: [required("descricaoAcesso")]
  },

  enderecoEstrangeiro: {

    nacionalidade: [required("nacionalidade", "change")],

    descricaoEndereco: [required("descricaoEndereco")],

    cidade: [required("cidade")],

    estado: [required("estado")],

    pais: [required("pais", "change")],
  },

  enderecoCorrespondencia: {
    logradouro: [required("logradouro")],

    numero: [validate(checkNumero, "numero.vazio")],

    // semNumero: [validate(checkSemNumero, "numero.vazio", "change")],

    bairro: [required("bairro")],

    cep: [required("cep")],

    municipio: [required("municipio", "change")],

    uf: [required("uf", "change")]
  }
};
