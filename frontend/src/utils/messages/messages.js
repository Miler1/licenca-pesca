import { EN_INTERFACE, PT_INTERFACE } from "./interface/interface";
import { EN_VALIDATION, PT_VALIDATION } from "./validation/validation";

const merge = (interfaces, validation) =>
  Object.assign({}, interfaces, validation);

const messages = {
  EN: merge(EN_INTERFACE, EN_VALIDATION),

  "PT-BR": merge(PT_INTERFACE, PT_VALIDATION)
};

export default messages;
