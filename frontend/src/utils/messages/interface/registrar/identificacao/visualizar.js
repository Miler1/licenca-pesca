import Properties from "../../../../../properties";
import { handleMessage } from "../../../utils";

export const VISUALIZAR_MESSAGES_PREFIX =
  "interface.registrar.identificacao.visualizar.";

const VISUALIZAR = {
  visualizar: {
    naoInformado: ["Não informado", "Not informed."],
    titulo: {
      dadosPessoais: ["Dados Pessoais", "Personal Data"],
      enderecoPrincipal: ["Endereço Principal", "Main Address"],
      enderecoCorrespondencia: [
        "Endereço de Correspondência",
        "Mailing Address"
      ]
    },
    label: {
      cpf: ["CPF:", "CPF:"],
      nome: ["Nome:", "Name:"],
      passaporte: ["Passaporte:", "Passport:"],
      sexo: ["Sexo:", "Sex:"],
      dataNascimento: ["Data de nascimento:", "Date of birth:"],
      nomeMae: ["Nome da mãe:", "Mother's name:"],
      localizacao: ["Zona de localização:", "Location zone:"],
      logradouro: ["Logradouro:", "Public place:"],
      bairro: ["Bairro:", "Neighborhood:"],
      numero: ["Número:", "Number:"],
      complemento: ["Complemento:", "Complement:"],
      cep: ["CEP:", "Postal code:"],
      municipio: ["Municipio:", "County:"],
      uf: ["UF:", "UF:"]
    },
    dados: {
      genero: {
        masculino: ["Masculino", "Male"],
        feminino: ["Feminino", "Female"],
        outros: ["Outros", "Others"]
      },
      zonaLocalizacao: {
        urbana: ["Urbana", "Urban"],
        rural: ["Rural", "Rural"]
      }
    }
  }
};

export const EN_VISUALIZAR = handleMessage(
  VISUALIZAR,
  Properties.LANGUAGE_INDEX.EN
);

export const PT_VISUALIZAR = handleMessage(
  VISUALIZAR,
  Properties.LANGUAGE_INDEX.PT_BR
);
