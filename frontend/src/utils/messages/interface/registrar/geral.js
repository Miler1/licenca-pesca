import Properties from "../../../../properties";
import { handleMessage } from "../../utils";

export const REGISTRAR_GERAL_MESSAGES_PREFIX = "interface.registrar.geral.";

const GERAL = {
  geral: {
    titulo: ["Registrar Licença de Pesca", "Register Fishing License"],
    steps: {
      indices: {
        identificacao: ["Identificação", "Identification"],
        informacoes: ["Informações complementares", "Additional information"],
        resumo: ["Resumo", "Summary"]
      },
      botoes: {
        cancelar: ["Cancelar", "Cancel"],
        voltar: ["Voltar", "Back"],
        proxima: ["Próxima", "Next"],
        concluir: ["Concluir", "Done"]
      },
      label: ["Etapa {0} de {1}", "Step {0} of {1}"]
    }
  }
};

export const EN_GERAL = handleMessage(GERAL, Properties.LANGUAGE_INDEX.EN);
export const PT_GERAL = handleMessage(GERAL, Properties.LANGUAGE_INDEX.PT_BR);
