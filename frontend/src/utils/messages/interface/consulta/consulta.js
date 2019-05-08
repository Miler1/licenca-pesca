import { handleMessage } from "../../utils";
import Properties from "../../../../properties";

export const INTERFACE_CONSULTA_PREFIX = "interface.consulta.";

const CONSULTA = {
  consulta: {
    titulos: {
      licenca: ["Licença de pesca", "Fishing license", "Licencia de pesca"]
    },
    dados: {
      semLicenca: [
        "Esta licença não existe!",
        "This license does not exist!",
        "¡Esta licencia no existe!"
      ]
    },
    botoes: {
      downloadLicenca: [
        "Baixar a carteira de pesca",
        "Download the fishing license",
        "Descargar la cartera de pesca"
      ],
      downloadBoleto: [
        "Baixar o documento de arrecadação",
        "Download the collection document",
        "Descargar el documento de recaudación"
      ]
    }
  }
};

export const PT_CONSULTA = handleMessage(
  CONSULTA,
  Properties.LANGUAGE_INDEX.PT_BR
);
export const EN_CONSULTA = handleMessage(
  CONSULTA,
  Properties.LANGUAGE_INDEX.EN
);
