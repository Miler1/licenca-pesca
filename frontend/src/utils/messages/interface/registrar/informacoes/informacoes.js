import Properties from "../../../../../properties";
import { handleMessage } from "../../../utils";

export const INFORMACOES_PREFIX = "interface.registrar.informacoes.";

const INFORMACOES = {
  informacoes: {
    labels: {
      modalidadePesca: [
        "Modalidade de Pesca",
        "Fishing mode",
        "Modalidad de pesca"
      ],
      localizacaoPreferencialPesca: [
        "Onde você costuma pescar?",
        "Where do you usually go fishing?",
        "¿Dónde usted suele pescar?"
      ],
      rendaMensal: ["Renda mensal", "Monthly income", "Ingresos mensuales"],
      diasPescaPorAno: [
        "Em média quantos dias por ano costuma pescar?",
        "On average how many days per year do you usually fish?",
        "¿En promedio cuántos días al año suele pescar?"
      ],
      gastoMedioPesca: [
        "Qual o gasto médio com pesca?",
        "What is the average fishing spend?",
        "¿Cuál es el gasto medio con la pesca?"
      ],
      localPesca: [
        "Onde você prefere pescar?",
        "Where do you prefer to fish?",
        "¿Dónde prefieres pescar?"
      ],
      materialPesca: [
        "Qual o tipo de material / equipamento você utiliza?",
        "What kind of material / equipment do you use?",
        "¿Cuál es el tipo de material / equipo que usted utiliza?"
      ],
      tipoIsca: [
        "Qual tipo de isca você utiliza?",
        "What kind of bait do you use?",
        "¿Qué tipo de cebo prefieres?"
      ],
      agenciaTurismo: [
        "Costuma programar suas pescarias com agências de turismo?",
        "Do you usually plan your fisheries with tourism agencies?",
        "¿Usted suele programar sus pesquerías con agencias de turismo?"
      ],
      peixeMaisPescado: [
        "Qual peixe você mais costuma pescar?",
        "Which fish do you most fish?",
        "¿Cuál es el pescado que más suele pescar?"
      ]
    },
    notas: {
      modalidadePescaEsportiva: [
        "Modalidade esportiva: Pesca e solta o peixe;",
        "Sport mode: Fishing and loosening the fish;",
        "Modalidad deportiva: Pesca y suelta el pescado; Modalidad recreativa: Lleva el pescado"
      ],
      modalidadePescaRecreativa: [
        "Modalidade recreativa: Leva o peixe",
        "Recreational mode: Take the fish",
        "Modalidad deportiva: Pesca y suelta el pescado; Modalidad recreativa: Lleva el pescado"
      ],
      gastoMedioPesca: [
        "Incluindo meios de transporte, alimentação, hospedagem, equipamentos e outros. Em Reais.",
        "Including means of transport, food, lodging, equipment and others. In Brazilian real.",
        "Incluyendo medios de transporte, alimentación, hospedaje, equipos y otros. En el Real brasileño."
      ]
    },
    valoresCarteira: {
      modalidades: {
        esportiva: ["Valor total a pagar: R$ 45.19", "Total amount to pay: R$ 45.19"],
        recreativa: ["Valor total a pagar: R$ 59.50", "Total amount to pay: R$ 59.50"],
        mensagemInicial: ["Selecione uma modalidade para ver o valor da respectiva carteira", "Select a mode to see the value"]
      }
    },
    titulos: {
      informacoesComplementares: [
        "Informações complementares",
        "Additional information",
        "Informaciones complementarias"
      ]
    },
    informacaoTipoIsca: {
      natural: ["Para isca viva deve haver comprovação de que ela foi adquirida em empreendimento aquícola licenciado pelo órgão ambiental competente.",
        "For live bait there must be proof that it was acquired in an agricultural enterprise licensed by the competent environmental agency."]
    },
    dia: ["dia", "day", "día"],
    naoInformado: ["Não informado", "Uninformed", "No informado"]
  }
};

export const EN_INFORMACOES = handleMessage(
  INFORMACOES,
  Properties.LANGUAGE_INDEX.EN
);

export const PT_INFORMACOES = handleMessage(
  INFORMACOES,
  Properties.LANGUAGE_INDEX.PT_BR
);
