import {
  EN_IDENTIFICACAO,
  PT_IDENTIFICACAO
} from "./identificacao/identificacao";
import { EN_INFORMACOES, PT_INFORMACOES } from "./informacoes/informacoes";
import { EN_RESUMO, PT_RESUMO } from "./resumo/resumo";
import { EN_GERAL, PT_GERAL } from "./geral";

const merge = (identificacao, informacoes, resumo, geral) =>
  Object.assign({}, identificacao, informacoes, resumo, geral);

export const REGISTRAR_MESSAGES_PREFIX = "interface.registrar.";

export const EN_REGISTRAR = {
  registrar: merge(EN_IDENTIFICACAO, EN_INFORMACOES, EN_RESUMO, EN_GERAL)
};

export const PT_REGISTRAR = {
  registrar: merge(PT_IDENTIFICACAO, PT_INFORMACOES, PT_RESUMO, PT_GERAL)
};
