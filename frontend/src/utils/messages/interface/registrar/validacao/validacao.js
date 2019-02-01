import { handleMessage } from "../../../utils";
import Properties from "../../../../../properties";

export const INTERFACE_VALIDACAO_PREFIX = "interface.registrar.validacao.";

const VALIDACAO = {
  validacao: {
    naoInformado: ["Não informado", "Uninformed"],
    format: {
      data: ["dd/MM/yyyy", "yyyy-MM-dd"]
    },
   
    titulo: {
      tituloInicial: ["Confirmação de dados", "Confirmation of data"],
      subtitulo: ["Para sua segurança, antes de acessar as licenças é necessário confirmar algumas informações. Por favor responda as questões abaixo: ", "For your security, before accessing the licenses you need to confirm some information. Please answer the questions below:"],
      nomeMae: ["Nome da mãe", "Mother's name"],
      dataNascimento: ["Data de Nascimento", "Date of birth"],
      municipio: ["Município", "County"]
    },
    botoes: {
      validar: ["Validar dados", "Validate data "],
      cancelar: ["Cancelar", "Cancel"]
    },
    cancelamento: {
      titulo: [
        "Confirmar cancelamento",
        "Confirm cancellation",
        "Confirmar cancelación"
      ],
      mensagemValidacao: [
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
