import { EN_GERAL, PT_GERAL } from "./geral";
import { EN_CONSULTA, PT_CONSULTA } from "./consulta/consulta";
import { EN_REGISTRAR, PT_REGISTRAR } from "./registrar/registrar";

import { merge } from "../utils";

export const EN_INTERFACE = {
  interface: merge(EN_GERAL, EN_REGISTRAR, EN_CONSULTA)
};

export const PT_INTERFACE = {
  interface: merge(PT_GERAL, PT_REGISTRAR, PT_CONSULTA)
};
