const handleEnvironment = (devProperty, prodProperty, homologProperty) => {
  if (process.env.NODE_ENV === "production") {
    return prodProperty;
  } else if (process.env.NODE_ENV === "homologation") {
    return homologProperty;
  } else {
    return devProperty;
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
  BASE_URL: handleEnvironment(
    "http://localhost:9666",
    "http://sistemas.ipaam.am.gov.br/carteira-pesca",
    "http://sistemas.ipaam.am.gov.br/carteira-pesca",
    "http://sistemas.ipaam.am.gov.br/carteira-pesca"
  ),
  IBGE_API: "https://servicodados.ibge.gov.br/api/v1/localidades"
};

export default Properties;
