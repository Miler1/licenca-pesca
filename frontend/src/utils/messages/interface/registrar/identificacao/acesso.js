import Properties from "../../../../../properties";
import { handleMessage } from "../../../utils";

export const ACESSO_MESSAGES_PREFIX =
  "interface.registrar.identificacao.acesso.";

  // interface.registrar.identificacao.tabela.label.tabela

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
  },
  tabela: {
    label: {
      modalidade: ["Modalidade", "Modality"],
      cadastro: ["Cadastro", "Register"],
      ativacao: ["Ativação", "Activation"],
      vencimento: ["Vencimento", "Maturity"],
      situacao: ["Situação", "Situation"],
      acoes: ["Ações", "Actions"]
    }
  }
};

export const EN_ACESSO = handleMessage(ACESSO, Properties.LANGUAGE_INDEX.EN);
export const PT_ACESSO = handleMessage(ACESSO, Properties.LANGUAGE_INDEX.PT_BR);