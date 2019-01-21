import RegistrarLicenca from "../components/views/RegistrarLicenca";
import BuscarLicenca from "../components/views/BuscarLicenca";

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
