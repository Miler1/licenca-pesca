const messages = {
  EN: {
    date: "{2}-{1}-{0}",
    message: {
      registerTitle: "Register Fishing License",
      footer: "© " + new Date().getFullYear() + " - Fishing Licence System",
      underConstruction: "Page under construction!",
      notInformed: "Not informed.",
      register: {
        steps: {
          identification: "Identification",
          info: "Additional information",
          summary: "Summary"
        },
        access: {
          cpf: "CPF",
          passport: "Passaport",
          search: "CPF or passport number:",
          placeholder: {
            cpf: "Enter the CPF",
            passport: "Enter the passport number"
          }
        },
        data: {
          titles: {
            person: "Personal data",
            mainAddress: "Main address",
            mailingAddress: "Mailing address"
          },
          labels: {
            cpf: "CPF:",
            name: "Name:",
            passport: "Passport:",
            sex: "Sex:",
            birthday: "Birthday:",
            mother: "Mother's name:",
            gender: {
              male: "Male",
              fem: "Female",
              other: "Other"
            },
            location: "Location zone:",
            urban: "Urban",
            rural: "Rural",
            street: "Public place:",
            number: "Number:",
            neighborhood: "Neighborhood:",
            complement: "Complement:",
            cep: "Postal Code:",
            city: "County:",
            uf: "UF:"
          }
        }
      }
    }
  },

  "PT-BR": {
    date: "{0}/{1}/{2}",
    message: {
      registerTitle: "Registrar Licença de Pesca",
      footer:
        "© " + new Date().getFullYear() + " - Sistema de Licença de Pesca",
      underConstruction: "Página em desenvolvimento!",
      notInformed: "Não informado.",
      register: {
        steps: {
          identification: "Identificação",
          info: "Informações complementares",
          summary: "Resumo"
        },
        access: {
          cpf: "CPF",
          passport: "Passaporte",
          search: "CPF ou número do passaporte:",
          placeholder: {
            cpf: "Informe o CPF",
            passport: "Informe o passaporte"
          }
        },
        data: {
          titles: {
            person: "Dados Pessoais",
            mainAddress: "Endereço principal",
            mailingAddress: "Endereço de correspondência"
          },
          labels: {
            cpf: "CPF:",
            name: "Nome:",
            passport: "Passaporte:",
            sex: "Sexo:",
            birthday: "Data de nascimento:",
            mother: "Nome da mãe:",
            gender: {
              male: "Masculino",
              fem: "Feminino",
              other: "Outro"
            },
            location: "Zona de localização:",
            urban: "Urbana",
            rural: "Rural",
            street: "Logradouro:",
            number: "Número:",
            neighborhood: "Bairro:",
            complement: "Complemento:",
            cep: "CEP:",
            city: "Município:",
            uf: "UF:"
          }
        }
      }
    }
  }
};

export default messages;
