import { EN_INTERFACE, PT_INTERFACE } from "./interface/interface";
import { EN_VALIDATION, PT_VALIDATION } from "./validation/validation";
import ptLocale from "element-ui/lib/locale/lang/pt-br";
import enLocale from "element-ui/lib/locale/lang/en";

const merge = (interfaces, validation, element) =>
  Object.assign({}, interfaces, validation, element);

const messages = {
  EN: merge(EN_INTERFACE, EN_VALIDATION, enLocale),

  "PT-BR": merge(PT_INTERFACE, PT_VALIDATION, ptLocale)
};

export default messages;
