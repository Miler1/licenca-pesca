import RegistrarLicenca from "../components/views/RegistrarLicenca";

import CadastrarDadosPessoa from "../components/business/identificacao/dadosPessoa/CadastrarDadosPessoa";

export const routes = [
  {
    path: "/",
    name: "home",
    component: RegistrarLicenca
  },
  {
    path: "/consultar/:protocolo",
    name: "consultar",
    // Lazy loading
    component: () => import("../components/views/ConsultarLicenca")
  }
];
