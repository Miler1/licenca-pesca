import RegistrarLicenca from "../components/views/RegistrarLicenca";
import BuscarLicenca from "../components/views/BuscarLicenca";
import ListagemRemessa from "../components/business/remessa/ListagemRemessa";
import EnvioListagemRetorno from "../components/business/remessa/EnvioListagemRetorno";

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
    path: "/listagem-remessas",
    name: "listagemRemessa",
    component: ListagemRemessa
  },
  {
    path: "/envio-retorno",
    name: "envioListagemRetorno",
    component: EnvioListagemRetorno
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
