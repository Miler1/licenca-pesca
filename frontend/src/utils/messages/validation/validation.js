const required = (campo, language = "pt-BR") =>
  language === "pt-BR"
    ? `'${campo}' é um campo obrigatório.`
    : `'${campo}' is a required field.`;

const invalid = (campo, language = "pt-BR") =>
  language === "pt-BR"
    ? `Você deve informar um '${campo}' válido.`
    : `You must inform a valid '${campo}'.`;

export const EN_VALIDATION = {
  validation: {
    cpf: {
      invalid: invalid("CPF", "en"),
      required: required("CPF", "en")
    },
    nome: {
      required: required("Name", "en")
    },
    passaporte: {
      required: required("Passport", "en")
    },
    dataNascimento: {
      required: required("Date of birth", "en"),
      past: "Date of birth cannot be in future."
    },
    sexo: {
      required: required("Sex", "en")
    },
    nomeMae: {
      required: required("Mother's name", "en")
    },
    email: {
      required: required("Email", "en"),
      invalid: invalid("Email", "en")
    },
    confirmarEmail: {
      required: required("Confirm email", "en"),
      check: "Email fields don't match."
    },
    zonaLocalizacao: {
      required: required("Location zone", "en")
    },
    logradouro: {
      required: required("City place", "en")
    },
    numero: {
      vazio:
        "The 'No number' and 'Number' fields can not be both filled or empty."
    },
    bairro: {
      required: required("Neighborhood", "en")
    },
    cep: {
      required: required("Postal code", "en")
    },
    uf: {
      required: required("UF", "en")
    },
    municipio: {
      required: required("County", "en")
    },
    descricaoAcesso: {
      required: required("Access description", "en")
    }
  }
};

export const PT_VALIDATION = {
  validation: {
    cpf: {
      invalid: "Deve ser informado um CPF válido.",
      required: required("CPF")
    },
    nome: {
      required: required("Nome")
    },
    passaporte: {
      required: required("Passaporte")
    },
    dataNascimento: {
      required: required("Data de nascimento"),
      past: "Data de nascimento não pode estar no futuro."
    },
    sexo: {
      required: required("Sexo")
    },
    nomeMae: {
      required: required("Nome da mãe")
    },
    email: {
      required: required("Email"),
      invalid: invalid("Email")
    },
    confirmarEmail: {
      required: required("Confirmar email"),
      check: "Campos de email não batem."
    },
    zonaLocalizacao: {
      required: required("Zona de localização")
    },
    logradouro: {
      required: required("Logradouro")
    },
    numero: {
      vazio:
        "Os campos 'Sem número' e 'Número' não podem ambos estar preenchidos ou vazios."
    },
    bairro: {
      required: required("Bairro")
    },
    cep: {
      required: required("CEP")
    },
    uf: {
      required: required("UF")
    },
    municipio: {
      required: required("Município")
    },
    descricaoAcesso: {
      required: required("Descrição de acesso")
    }
  }
};
