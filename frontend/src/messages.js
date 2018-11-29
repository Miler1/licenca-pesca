const messages = {
  EN: {
    message: {
      registerTitle: "Register Fishing License",
      footer: "© " + new Date().getFullYear() + " - Fishing Licence System",
      underConstruction: "Page under construction!",
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
        }
      }
    }
  },

  "PT-BR": {
    message: {
      registerTitle: "Registrar Licença de Pesca",
      footer:
        "© " + new Date().getFullYear() + " - Sistema de Licença de Pesca",
      underConstruction: "Página em desenvolvimento!",
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
        }
      }
    }
  }
};

export default messages;
