import { required } from "../utils";

export const INFORMACOES_RULES = {
  modalidadePesca: [required("modalidadePesca", "change")],

  localizacaoPreferencialPesca: [required("localizacaoPreferencialPesca", "change")],

  rendaMensal: [required("rendaMensal", "change")],

  diasPescaPorAno: [required("diasPescaPorAno", "change")],

  gastoMedioPesca: [required("gastoMedioPesca", "change")],

  faixaEtaria: [required("faixaEtaria", "change")],

  localPesca: [required("localPesca", "change")],

  materialPesca: [required("materialPesca", "change")],

  tipoIsca: [required("tipoIsca", "change")],
  
  agenciaTurismo: [required("agenciaTurismo", "change")]
};
