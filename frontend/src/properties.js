const handleEnvironment = (devProperty, prodProperty) => {
  if (process.env.NODE_ENV !== "production") {
    return devProperty;
  } else {
    return prodProperty;
  }
};

/**
 * Configurações base da aplicação.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
const Properties = {
  STEPS: {
    IDENTIFICACAO: 0,
    INFORMACOES_COMPLEMENTARES: 1,
    RESUMO: 2
  },
  LANGUAGE_INDEX: {
    PT_BR: 0,
    EN: 1
  },
  BASE_URL: handleEnvironment("http://localhost:9666", ""),
  IBGE_API: "https://servicodados.ibge.gov.br/api/v1/localidades"
};

export default Properties;
