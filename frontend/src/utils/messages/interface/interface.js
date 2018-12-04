import { EN_GERAL, PT_GERAL } from "./geral";
import { EN_REGISTRAR, PT_REGISTRAR } from "./registrar/registrar";

const merge = (geral, registrar) => Object.assign({}, geral, registrar);

export const EN_INTERFACE = {
  interface: merge(EN_GERAL, EN_REGISTRAR)
};

export const PT_INTERFACE = {
  interface: merge(PT_GERAL, PT_REGISTRAR)
};
