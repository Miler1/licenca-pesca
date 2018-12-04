import Config from "../../../../../config";
import { handleMessage } from "../../../utils";

export const ACESSO_MESSAGES_PREFIX =
  "interface.registrar.identificacao.acesso.";

const ACESSO = {
  acesso: {
    label: {
      search: ["CPF ou número do passaporte:", "CPF or passport number:"]
    },
    select: {
      cpf: ["CPF", "CPF"],
      passaporte: ["Passaporte", "Passport"]
    },
    placeholder: {
      cpf: ["Informe o CPF", "Enter the CPF"],
      passaporte: ["Informe o passaporte", "Enter the passport number"]
    }
  }
};

export const EN_ACESSO = handleMessage(ACESSO, Config.LANGUAGE_INDEX.EN);
export const PT_ACESSO = handleMessage(ACESSO, Config.LANGUAGE_INDEX.PT_BR);
