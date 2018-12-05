import { Validator } from "vee-validate";
import i18n from "../configs/i18n";
import pt_BR from "vee-validate/dist/locale/pt_BR";
import en from "vee-validate/dist/locale/en";

const langs = {
  PT_BR: pt_BR,
  EN: en
};

export const localizeValidation = () => {
  Validator.localize(i18n.locale, langs[i18n.locale]);
};

Validator.localize("PT-BR", pt_BR);

export default new Validator({}, {});
