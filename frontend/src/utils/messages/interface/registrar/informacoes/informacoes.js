import Properties from "../../../../../properties";
import { handleMessage } from "../../../utils";

export const INFORMACOES_PREFIX = "interface.registrar.informacoes.";

const INFORMACOES = {
  informacoes: ["", ""]
};

export const EN_INFORMACOES = handleMessage(
  INFORMACOES,
  Properties.LANGUAGE_INDEX.EN
);

export const PT_INFORMACOES = handleMessage(
  INFORMACOES,
  Properties.LANGUAGE_INDEX.PT_BR
);
