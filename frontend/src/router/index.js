import RegistrarLicenca from "../components/views/RegistrarLicenca";
import BuscarLicenca from "../components/views/BuscarLicenca";
import ListagemLicencasRelatorios from "../components/business/remessa/ListagemLicencasRelatorios";

export const routes = [
  {
    path: "/",
    name: "home",
    component: BuscarLicenca
  },
  {
    path: "/registrar",
    name: "registrar",
    component: RegistrarLicenca
  },

  {
    path: "/listagemRemessa",
    name: "listagemLicencasRelatorios",
    component: ListagemLicencasRelatorios
  },

  {
    path: "/informacao-carteira/:protocolo",
    name: "informacao-carteira",
    component: () => import("../components/views/AutenticidadeQr")
  },

  {
    path: "/consultar/:protocolo",
    name: "consultar",
    // Lazy loading
    component: () => import("../components/views/ConsultarLicenca")
  },

  {
    path: "/renovar/:protocolo",
    name: "renovar",
    component: () => import("../components/views/RenovarLicenca")
  }
];
