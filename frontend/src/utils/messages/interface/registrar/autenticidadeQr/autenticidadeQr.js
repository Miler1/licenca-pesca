import { handleMessage } from "../../../utils";
import Properties from "../../../../../properties";

export const INTERFACE_AUTENTICIDADE_PREFIX = "interface.registrar.autenticidade.";

const AUTENTICIDADE = {
  autenticidade: {
    titulo: {
      dadosPessoais: ["Dados pessoais", "Personal data"],
      dadosLicenca: ["Dados da licença", "License information"],
      tituloInicial: ["Licença de Pesca", "Fishing License"],
      enderecoPrincipal: ["Endereço Principal", "Main Address"],
      enderecoCorrespondencia: ["Endereço de Correspondência", "Mailing Address"],
      label: {
        nome: ["Nome ", "Name: "],
        cpf: ["CPF/CNPJ ", "CPF/CNPJ: "],
        numeroLicenca: ["Número da licença ", ""],
        modalidade: ["Modalidade ", ""],
        emissao: ["Emissão ", ""],
        validade: ["Validade ", ""],
        endereco: ["Endereço ", ""],
        municipio: ["Município/UF ", ""],
        cep: ["CEP ", ""],
        pais: ["País ", ""],
        teste: ["Joyce de melo rodrigues"]
      }
    }
  }
};

export const PT_AUTENTICIDADE = handleMessage(
  AUTENTICIDADE,
  Properties.LANGUAGE_INDEX.PT_BR
);
export const EN_AUTENTICIDADE = handleMessage(
  AUTENTICIDADE,
  Properties.LANGUAGE_INDEX.EN
);
