import { handleMessage } from "../../../utils";
import Properties from "../../../../../properties";

export const INTERFACE_VALIDACAO_PREFIX = "interface.registrar.validacao.";

const VALIDACAO = {
  validacao: {
    naoInformado: ["Não informado", "Uninformed"],
    titulo: {
      tituloInicial: ["Validação de segurança", "Fishing License"],
      nomeMae: ["Nome da mãe", ""],
      label: {
        nome: ["Nome ", "Name "],
        cpf: ["CPF/CNPJ ", "CPF/CNPJ "],
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
      pais: ["Brasil", "Brazil"]
    }
  }
};

export const PT_VALIDACAO = handleMessage(
  VALIDACAO,
  Properties.LANGUAGE_INDEX.PT_BR
);
export const EN_VALIDACAO = handleMessage(
  VALIDACAO,
  Properties.LANGUAGE_INDEX.EN
);
