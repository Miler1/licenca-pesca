import { EN_INTERFACE, PT_INTERFACE } from "./interface/interface";
import { EN_VALIDATION, PT_VALIDATION } from "./validation/validation";
import PT_ELEMENT from "element-ui/lib/locale/lang/pt-br";
import EN_ELEMENT from "element-ui/lib/locale/lang/en";
import { merge } from "./utils";

const messages = {
  EN: merge(EN_INTERFACE, EN_VALIDATION, EN_ELEMENT),

  "PT-BR": merge(PT_INTERFACE, PT_VALIDATION, PT_ELEMENT)
};

console.log(messages);
export default messages;
