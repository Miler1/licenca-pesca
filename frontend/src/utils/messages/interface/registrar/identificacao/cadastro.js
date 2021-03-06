import Properties from "../../../../../properties";
import { handleMessage } from "../../../utils";

export const CADASTRO_MESSAGES_PREFIX =
  "interface.registrar.identificacao.cadastro.";

const CADASTRO = {
  cadastro: {
    labels: {
      estrangeiro: ["Estrangeiro", "Foreigh"],
      cpf: ["CPF:", "CPF:"],
      nome: ["Nome:", "Name:"],
      passaporte: ["Passaporte:", "Passport:"],
      sexo: ["Sexo:", "Sex:"],
      dataNascimento: ["Data de nascimento:", "Date of birth:"],
      nomeMae: ["Nome da mãe:", "Mother's name:"],
      zonaLocalizacao: ["Zona de localização:", "Location zone:"],
      logradouro: ["Logradouro:", "Public place:"],
      bairro: ["Bairro:", "Neighborhood:"],
      numero: ["Número:", "Number:"],
      complemento: ["Complemento:", "Complement:"],
      cep: ["CEP:", "Postal code:"],
      municipio: ["Município:", "County:"],
      uf: ["UF:", "UF:"],
      email: ["E-mail:", "Email:"],
      confirmarEmail: ["Confirmar e-mail:", "Confirm email:"],
      descricaoAcesso: ["Descrição de acesso:", "Access description:"],
      semNumero: ["Sem número", "No number"],
      nacionalidade: ["Nacionalidade:", "Nationality:"],
      descricaoEndereco: ["Descrição do endereço:", "Adress description:"],
      cidade: ["Cidade:", "City:"],
      estado: ["Estado/Província:", "State/Province:"],
      pais: ["País:", "Country:"]
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
    },
    titulos: {
      dadosPessoais: ["Dados pessoais", "Personal data"],
      dadosContato: ["Dados de contato", "Contact data"],
      enderecoEstrangeiro: ["Endereço de origem", "Origin Address"],
      enderecoPrincipal: ["Dados de endereço", "Address data"],
      enderecoCorrespondencia: [
        "Endereço de correspondência",
        "Mailing address"
      ]
    },
    format: {
      data: ["dd/MM/yyyy", "yyyy-MM-dd"]
    },
    placeholders: {
      select: {
        geral: ["Selecione", "Select"],
        sexo: ["Selecione o sexo", "Select the sex"]
      }
    }
  }
};

export const EN_CADASTRO = handleMessage(CADASTRO, Properties.LANGUAGE_INDEX.EN);
export const PT_CADASTRO = handleMessage(CADASTRO, Properties.LANGUAGE_INDEX.PT_BR);
