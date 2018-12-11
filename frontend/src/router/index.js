import RegistrarLicenca from "../components/views/RegistrarLicenca";

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
