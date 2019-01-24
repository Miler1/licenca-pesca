import { handleMessage } from "../../../utils";
import Properties from "../../../../../properties";

export const INTERFACE_VALIDACAO_PREFIX = "interface.registrar.validacao.";

const VALIDACAO = {
  validacao: {
    naoInformado: ["Não informado", "Uninformed"],
    format: {
      data: ["dd/MM/yyyy", "yyyy-MM-dd"]
    },
    botoes: {
      validar: ["Validar dados", " "],
      cancelar: ["Cancelar", " "]
    },
    titulo: {
      tituloInicial: ["Confirmação de dados", "Fishing License"],
      subtitulo: ["Para sua segurança, antes de acessar as licenças é necessário confirmar algumas informações. Por favor responda as questões abaixo: ", ""],
      nomeMae: ["Nome da mãe", ""],
      dataNascimento: ["Data de Nascimento", ""],
      municipio: ["Município", ""]
    },
    cancelamento: {
      titulo: [
        "Confirmar cancelamento",
        "Confirm cancellation",
        "Confirmar cancelación"
      ],
      mensagem: [
        "Deseja realmente cancelar a validação dos dados?",
        "Do you really want to cancel data validation?"
      ],
      botoes: {
        confirmar: ["Confirmar", "Confirm", "Confirmar"],
        cancelamento: ["Cancelar", "Cancel", "Cancelar"]
      }
    }
  }
};

export const PT_VALIDACAO = handleMessage(
  VALIDACAO,
  Properties.LANGUAGE_INDEX.PT_BR
);
export const EN_VALIDACAO = handleMessage(
  VALIDACAO,
  Properties.LANGUAGE_INDEX.EN
);
