import { Validator } from "vee-validate";
import { translate } from "../../helpers/internationalization";

const TODAY = new Date();

Validator.extend("past", {
  validate(data, args) {
    return data <= TODAY;
  },
  getMessage(field, params, data) {
    return translate("validation.dataNascimento.past");
  }
});

export const PESSOA_RULES = {
  nome: "required|alpha|min:10|max:50",
  cpf: "required",
  dataNascimento: "required|past",
  sexo: "required",
  nomeMae: "required|alpha|min:10|max:50",
  email: "required|email"
};
