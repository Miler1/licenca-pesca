import Vue from "vue";
import * as _ from "lodash";

const EMAIL_PATTERN = new RegExp(
  "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:" +
    "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"
);

export const isInPast = date => new Date(Date.parse(date)) < new Date();

export const isEmail = email => EMAIL_PATTERN.test(email);

export const isSameEmail = email =>
  email === Vue.prototype.$cadastro.pessoa.email;

export const semNumero = numero =>
  (!_.isNil(numero) ^
    Vue.prototype.$cadastro.pessoa.enderecoPrincipal.semNumero) ===
  1;

export const numero = semNumero =>
  (!_.isNil(Vue.prototype.$cadastro.pessoa.enderecoPrincipal.numero) ^
    semNumero) ===
  1;
