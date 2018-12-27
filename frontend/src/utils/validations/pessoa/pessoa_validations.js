import Vue from "vue";
import * as _ from "lodash";

const EMAIL_PATTERN = new RegExp(
  "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:" +
    "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"
);

export const isInPast = date => new Date(Date.parse(date)) < new Date();

export const isEmail = email => EMAIL_PATTERN.test(email);

export const isSameEmail = email => {
  return Vue.prototype.$cadastroPessoa &&
  Vue.prototype.$cadastroPessoa.pessoa &&
  email === Vue.prototype.$cadastroPessoa.pessoa.email;
}

export const semNumero = numero => {
  return !_.isNil(numero) &&
    Vue.prototype.$cadastroPessoa &&
    Vue.prototype.$cadastroPessoa.pessoa &&
    Vue.prototype.$cadastroPessoa.pessoa.enderecoPrincipal &&
    !Vue.prototype.$cadastroPessoa.pessoa.enderecoPrincipal.semNumero;
}

export const numero = semNumero => {

  return _.isNil(Vue.prototype.$cadastroPessoa.pessoa.enderecoPrincipal.numero)
    && semNumero;
}