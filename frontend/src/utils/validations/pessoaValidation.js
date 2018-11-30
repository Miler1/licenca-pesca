import "../helpers/formatacao";
import Vue from "vue";

export const checkCPF = (rule, value, callback) => {
  (value
  ? !value.isCPF()
  : false)
    ? callback(new Error("CPF Inválido"))
    : callback();
};

const locale = key => Vue.prototype.$translator.t(key);

export const PESSOA_RULES = {
  cpf: [
    {
      required: true,
      message: locale("validation.cpf.required"),
      trigger: "blur"
    },
    {
      validator: checkCPF,
      trigger: "blur",
      message: locale("validation.cpf.invalid")
    }
  ]
};
