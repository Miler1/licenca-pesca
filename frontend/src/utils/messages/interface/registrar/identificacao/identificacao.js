import { EN_ACESSO, PT_ACESSO } from "./acesso";
import { EN_VISUALIZAR, PT_VISUALIZAR } from "./visualizar";
import { EN_CADASTRO, PT_CADASTRO } from "./cadastro";

import { merge } from "../../../utils";

export const IDENTIFICADAO_MESSAGES_PREFIX =
  "interface.registrar.identificacao.";

export const EN_IDENTIFICACAO = {
  identificacao: merge(EN_ACESSO, EN_VISUALIZAR, EN_CADASTRO)
};

export const PT_IDENTIFICACAO = {
  identificacao: merge(PT_ACESSO, PT_VISUALIZAR, PT_CADASTRO)
};
