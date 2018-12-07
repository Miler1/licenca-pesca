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
    },
    confirm: {
      titulo: [
        "Confirmar conclusão",
        "Confirm completion",
        "Confirmar la conclusión"
      ],
      mensagem: [
        "Declaro, para os devidos fins e efeitos legais, serem pessoais e verdadeiras as informações inseridas na solicitação da carteira de pesca ao IPAAM, sobre as quais assumo todas as responsibilidades, sob pena de incorrer nas sanções previstas nos artigos 299 e 307 do Código Penal (falsidade ideológica e falsa identidade).",
        "I declare, for the proper purposes and legal effects, the information included in the application for the fishing license to the IPAAM, for which I assume all the responsibilities, to be personal and true, under penalty of incurring the penalties provided for in articles 299 and 307 of the Brazilian Penal Code ( false ideology and false identity).",
        "Declaro, para los debidos fines y efectos legales, ser personales y verdaderas las informaciones insertadas en la solicitud de la cartera de pesca al IPAAM, sobre las cuales asumo todas las responsabilidades, so pena de incurrir en las sanciones previstas en los artículos 299 y 307 del Código Penal brasileño (falsedad ideológica y falsa identidad)."
      ],
      botoes: {
        confirm: ["Confirmar", "Confirm", "Confirmar"],
        cancel: ["Cancelar", "Cancel", "Cancelar"]
      }
    }
  }
};

export const EN_GERAL = handleMessage(GERAL, Properties.LANGUAGE_INDEX.EN);
export const PT_GERAL = handleMessage(GERAL, Properties.LANGUAGE_INDEX.PT_BR);
