import Properties from "../../../../../properties";
import { handleMessage } from "../../../utils";

export const RESUMO_PREFIX = "interface.registrar.resumo.";

const RESUMO = {
  resumo: ["", ""]
};

export const EN_RESUMO = handleMessage(RESUMO, Properties.LANGUAGE_INDEX.EN);

export const PT_RESUMO = handleMessage(RESUMO, Properties.LANGUAGE_INDEX.PT_BR);
