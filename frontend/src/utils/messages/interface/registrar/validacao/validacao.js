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
      tituloInicial: [" Validação de dados", "Fishing License"],
      subtitulo: ["Para acessar as suas licenças é necessário que selecione as respostas corretas correspondente aos seus dados: ", ""],
      nomeMae: ["Nome da mãe", ""],
      dataNascimento: ["Data de Nascimento", ""],
      municipio: ["Município", ""]
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
