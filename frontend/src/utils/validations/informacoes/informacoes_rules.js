import { required } from "../utils";

export const INFORMACOES_RULES = {
  modalidadePesca: [required("modalidadePesca")],

  localizacaoPreferencialPesca: [required("localizacaoPreferencialPesca")],

  rendaMensal: [required("rendaMensal")],

  diasPescaPorAno: [required("diasPescaPorAno")],

  gastoMedioPesca: [required("gastoMedioPesca")],

  faixaEtaria: [required("faixaEtaria")],

  localPesca: [required("localPesca")],

  materialPesca: [required("materialPesca")],

  tipoIsca: [required("tipoIsca")],

  modalidadeMaisPraticada: [required("modalidadeMaisPraticada")],

  agenciaTurismo: [required("agenciaTurismo")]
};
