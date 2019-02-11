import RegistrarLicenca from "../components/views/RegistrarLicenca";
import BuscarLicenca from "../components/views/BuscarLicenca";
import EnvioRetornoRemessa from "../components/business/remessa/EnvioRetornoRemessa";

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
    path: "/envio-retorno-remessa",
    name: "envioRetornoRemessa",
    component: EnvioRetornoRemessa
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
