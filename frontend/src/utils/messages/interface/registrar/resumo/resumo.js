import Config from "../../../../../config";
import { handleMessage } from "../../../utils";

export const RESUMO_PREFIX = "interface.registrar.resumo.";

const RESUMO = {
  resumo: ["", ""]
};

export const EN_RESUMO = handleMessage(RESUMO, Config.LANGUAGE_INDEX.EN);

export const PT_RESUMO = handleMessage(RESUMO, Config.LANGUAGE_INDEX.PT_BR);
