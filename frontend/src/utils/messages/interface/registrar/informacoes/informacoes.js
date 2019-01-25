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
      faixaEtaria: [
        "Qual a sua faixa de idade?",
        "What is your age range?",
        "¿Cuál es su rango de edad?"
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
      modalidadeMaisPraticada: [
        "Qual modalidade de pesca você mais pratica?",
        "Which type of fishing do you practice most?",
        "¿Cuál es la modalidad de pesca que más practica?"
      ],
      agenciaTurismo: [
        "Costuma programar suas pescarias com agências de turismo?",
        "Do you usually plan your fisheries with tourism agencies?",
        "¿Usted suele programar sus pesquerías con agencias de turismo?"
      ]
    },
    notas: {
      modalidadePesca: [
        "Modalidade esportiva: Pesca e solta o peixe; Modalidade recreativa: Leva o peixe",
        "Sport mode: Fishing and loosening the fish; Recreational mode: It takes the fish",
        "Modalidad deportiva: Pesca y suelta el pescado; Modalidad recreativa: Lleva el pescado"
      ],
      gastoMedioPesca: [
        "Incluindo meios de transporte, alimentação, hospedagem, equipamentos e outros. Em Reais.",
        "Including means of transport, food, lodging, equipment and others. In Brazilian real.",
        "Incluyendo medios de transporte, alimentación, hospedaje, equipos y otros. En el Real brasileño."
      ]
    },
    titulos: {
      informacoesComplementares: [
        "Informações complementares",
        "Additional information",
        "Informaciones complementarias"
      ]
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
