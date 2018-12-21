import RegistrarLicenca from "../components/views/RegistrarLicenca";

export const routes = [
  {
    path: "/",
    name: "home",
    component: RegistrarLicenca
  },
  // {
  //   path: "/home/cadastrar",
  //   name: "cadastrar",
  //   component: () => import("../components/business/identificacao/dadosPessoa/CadastrarDadosPessoa")
  // },

  {
    path: "/consultar/:protocolo",
    name: "consultar",
    // Lazy loading
    component: () => import("../components/views/ConsultarLicenca")
  }
];
