import {
  EN_IDENTIFICACAO,
  PT_IDENTIFICACAO
} from "./identificacao/identificacao";
import { EN_INFORMACOES, PT_INFORMACOES } from "./informacoes/informacoes";
import { EN_RESUMO, PT_RESUMO } from "./resumo/resumo";
import { EN_GERAL, PT_GERAL } from "./geral";
import { EN_AUTENTICIDADE, PT_AUTENTICIDADE } from "./autenticidadeQr/autenticidadeQr";

import { merge } from "../../utils";

export const REGISTRAR_MESSAGES_PREFIX = "interface.registrar.";

export const EN_REGISTRAR = {
  registrar: merge(EN_IDENTIFICACAO, EN_INFORMACOES, EN_RESUMO, EN_GERAL, EN_AUTENTICIDADE)
};

export const PT_REGISTRAR = {
  registrar: merge(PT_IDENTIFICACAO, PT_INFORMACOES, PT_RESUMO, PT_GERAL, PT_AUTENTICIDADE)
};
