import Properties from "../../../properties";
import { handleMessage } from "../utils";

const required = (campo, language = Properties.LANGUAGE_INDEX.PT_BR) => {
  switch (language) {
    case Properties.LANGUAGE_INDEX.PT_BR:
      return `'${campo}' é um campo obrigatório.`;
    case Properties.LANGUAGE_INDEX.EN:
      return `'${campo}' is a required field.`;
  }
};

const invalid = (campo, language = Properties.LANGUAGE_INDEX.PT_BR) => {
  switch (language) {
    case Properties.LANGUAGE_INDEX.PT_BR:
      return `Você deve informar um '${campo}' válido.`;
    case Properties.LANGUAGE_INDEX.EN:
      return `You must inform a valid '${campo}'.`;
  }
};

const en = Properties.LANGUAGE_INDEX.EN;

const VALIDATION = {
  validation: {
    cpf: {
      invalid: [invalid("CPF"), invalid("CPF", en)],
      required: [required("CPF"), required("CPF", en)]
    },
    nome: {
      required: [required("Nome"), required("Name", en)]
    },
    passaporte: {
      required: [required("Passaporte"), required("Passport", en)]
    },
    dataNascimento: {
      required: [required("Data de nascimento"), required("Date of birth", en)],
      past: [
        "Data de nascimento não pode estar no futuro.",
        "Date of birth cannot be in future."
      ]
    },
    sexo: {
      required: [required("Sexo"), required("Sex", en)]
    },
    nomeMae: {
      required: [required("Nome da mãe"), required("Mother's name", en)]
    },
    email: {
      required: [required("E-mail"), required("Email", en)],
      invalid: [invalid("E-mail"), invalid("Email", en)]
    },
    confirmarEmail: {
      required: [required("Confirmar e-mail"), required("Confirm email", en)],
      check: ["Campos de email não batem.", "Email fields don't match."]
    },
    zonaLocalizacao: {
      required: [required("Zona de localização"), required("Location zone", en)]
    },
    logradouro: {
      required: [required("Logradouro"), required("Public place", en)]
    },
    numero: {
      vazio: [
        "Os campos 'Sem número' e 'Número' não podem ambos estar preenchidos ou vazios.",
        "The 'No number' and 'Number' fields can not be both filled or empty."
      ]
    },
    bairro: {
      required: [required("Bairro"), required("Neighborhood", en)]
    },
    cep: {
      required: [required("CEP"), required("Postal code", en)]
    },
    uf: {
      required: [required("UF"), required("UF", en)]
    },
    municipio: {
      required: [required("Município"), required("County", en)]
    },
    descricaoAcesso: {
      required: [
        required("Descrição de acesso"),
        required("Access description", en)
      ]
    },

    modalidadePesca: {
      required: [required("Este"), required("This", en)]
    },

    localizacaoPreferencialPesca: {
      required: [required("Este"), required("This", en)]
    },

    rendaMensal: { required: [required("Este"), required("This", en)] },

    diasPescaPorAno: {
      required: [required("Em média quantos dias por ano costuma pescar?"), required("On average how many days per year do you usually fish?", en)]
    },

    gastoMedioPesca: {
      required: [required("Qual o gasto médio com pesca?"), required("What is the average fishing spend?", en)]
    },

    faixaEtaria: { required: [required("Qual a sua faixa de idade?"), required("What is your age range?", en)] },

    localPesca: { required: [required("Onde você prefere pescar?"), required("Where do you prefer to fish?", en)] },

    materialPesca: { required: [required("Qual o tipo de material / equipamento você utiliza?"), required("What kind of material / equipment do you use?", en)] },

    tipoIsca: { required: [required("Qual tipo de isca você prefere?"), required("What kind of bait do you prefer?", en)] },

    modalidadeMaisPraticada: {
      required: [required("Qual modalidade de pesca você mais pratica?"), required("Which type of fishing do you practice most?", en)]
    },

    agenciaTurismo: { required: [required("Costuma programar suas pescarias com agências de turismo?"), required("Do you usually plan your fisheries with tourism agencies?", en)] }
  }
};

export const EN_VALIDATION = handleMessage(
  VALIDATION,
  Properties.LANGUAGE_INDEX.EN
);

export const PT_VALIDATION = handleMessage(
  VALIDATION,
  Properties.LANGUAGE_INDEX.PT_BR
);
