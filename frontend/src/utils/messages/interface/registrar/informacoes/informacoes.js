import Config from "../../../../../config";
import { handleMessage } from "../../../utils";

export const INFORMACOES_PREFIX = "interface.registrar.informacoes.";

const INFORMACOES = {
  informacoes: ["", ""]
};

export const EN_INFORMACOES = handleMessage(
  INFORMACOES,
  Config.LANGUAGE_INDEX.EN
);

export const PT_INFORMACOES = handleMessage(
  INFORMACOES,
  Config.LANGUAGE_INDEX.PT_BR
);
