import { handleMessage } from "../../../utils";
import Properties from "../../../../../properties";

export const INTERFACE_AUTENTICIDADE_PREFIX = "interface.registrar.autenticidade.";

const AUTENTICIDADE = {
  autenticidade: {
    naoInformado: ["Não informado", "Uninformed"],
    hifem: [" - ", " - "],
    titulo: {
      dadosPessoais: ["Dados pessoais", "Personal data"],
      dadosLicenca: ["Dados da licença", "License information"],
      tituloInicial: ["Licença de Pesca", "Fishing License"],
      enderecoPrincipal: ["Endereço Principal", "Main Address"],
      enderecoCorrespondencia: ["Endereço de Correspondência", "Mailing Address"],
      label: {
        nome: ["Nome ", "Name: "],
        cpf: ["CPF/CNPJ ", "CPF/CNPJ: "],
        numeroLicenca: ["Número da licença ", "License number"],
        modalidade: ["Modalidade ", "Modality"],
        emissao: ["Emissão ", "Emission"],
        validade: ["Validade ", "Validity"],
        situacao: ["Situação", "Situation"],
        endereco: ["Endereço ", "Address"],
        municipio: ["Município/UF ", "City / UF"],
        cep: ["CEP ", "Zip code"],
        pais: ["País ", "Parents"]
      },
      pais: ["Brasil", "Country"]
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
