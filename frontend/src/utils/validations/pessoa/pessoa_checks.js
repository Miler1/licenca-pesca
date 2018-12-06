import "../../helpers/formatacao";
import {
  numero,
  isEmail,
  isInPast,
  semNumero,
  isSameEmail
} from "./pessoa_validations";

const check = (value, expression, callback, messageError = "Erro") => {
  if (value) {
    expression ? callback() : callback(new Error(messageError));
  }
};

export const checkCPF = (rule, value, callback) => {
  check(value, value.isCPF(), callback, "CPF Inválido");
};

export const checkDate = (rule, value, callback) => {
  check(value, isInPast(value), callback, "Data Inválida");
};

export const checkEmail = (rule, value, callback) => {
  check(value, isEmail(value), callback, "Email inválido");
};

export const checkConfirmEmail = (rule, value, callback) => {
  check(value, isSameEmail(value), callback, "Email não confere");
};

export const checkNumero = (rule, value, callback) => {
  check(value, semNumero(value), callback, "Numero / Sem número inválidos");
};

export const checkSemNumero = (rule, value, callback) => {
  check(value, numero(value), callback, "Numero / Sem número inválidos");
};
