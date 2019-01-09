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
    path: "/buscar/:protocolo",
    name: "buscar",
    component: () => import("../components/views/AutenticidadeQr")
  },

  {
    path: "/consultar/:protocolo",
    name: "consultar",
    // Lazy loading
    component: () => import("../components/views/ConsultarLicenca")
  }
];
