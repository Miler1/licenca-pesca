import { handleMessage } from "../utils";
import Config from "../../../config";

export const INTERFACE_GERAL_PREFIX = "interface.geral.";

const GERAL = {
  geral: {
    data: ["{0}/{1}/{2}", "{2}-{1}-{0}"],
    emConstrucao: ["Página em desenvolvimento!", "Page under construction!"],
    footer: [
      "© " + new Date().getFullYear() + " - Sistema de Licença de Pesca",
      "© " + new Date().getFullYear() + " - Fishing Licence System"
    ]
  }
};

export const PT_GERAL = handleMessage(GERAL, Config.LANGUAGE_INDEX.PT_BR);
export const EN_GERAL = handleMessage(GERAL, Config.LANGUAGE_INDEX.EN);
