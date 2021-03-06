import Properties from "../../../../properties";
import { handleMessage } from "../../utils";

export const REGISTRAR_GERAL_MESSAGES_PREFIX = "interface.registrar.geral.";
export const CONSULTAR_GERAL_MESSAGES_PREFIX = "interface.registrar.consultar.";
export const ENVIAR_RECEBER_REMESSA_MESSAGES_PREFIX = "interface.registrar.remessa.";

const GERAL = {
  geral: {
    titulo: ["Registrar Licença de Pesca Amadora (Esportiva ou Recreativa)", "Register Fishing License"],
    tituloRenovar: ["Renovar Licença de Pesca Amadora (Esportiva ou Recreativa)", "Renew Fishing License"],
    steps: {
      indices: {
        identificacao: ["Identificação", "Identification"],
        informacoes: ["Informações complementares", "Additional information"],
        resumo: ["Resumo", "Summary"]
      },
      botoes: {
        cancelar: ["Cancelar", "Cancel"],
        voltar: ["Voltar", "Back"],
        proxima: ["Próxima", "Next"],
        concluir: ["Concluir", "Done"]
      },
      label: ["Etapa {0} de {1}", "Step {0} of {1}"]
    },
    listaLicenca: {
      semLicenca: ["Não existe licença cadastrada para o CPF/Passaporte pesquisado. Para realizar o cadastramento, utilize o botão 'Cadastrar Licença'.",
      "The CPF/Passport searched doesn't have any license. For register one, please use 'Register license' buttom."],
      titulo: ["Lista de licenças", "License list"],
      ativacao: ["Ativação", "Activate date"],
      cadastro: ["Cadastro", "Register date"],
      vencimento: ["Vencimento", "Expire date"],
      acoes: ["Ações", "Actions"],
      modalidade: {
        esportiva: ["Esportiva", "Sportive"],
        recreativa: ["Recreativa", "Recreative"]
      },
      situacao: {
        titulo: ["Situação", "Situation"],
        aguardandoBoleto: ["Aguardando pagamento", "Awaiting payment"],
        invalido: ["Inválido", "Invalid"],
        ativo: ["Ativo", "Active"],
        ativoAguardandoPagamento: ["Ativo, aguardando pagamento", "Active, awaiting payment"],
        vencido: ["Vencido", "Expired"],
        renovado: ["Renovado", "Renewed"]
      },
      acoesOpcoes:{
        gerarDocumentoPagamento: ["Baixar o documento de arrecadação", "Download the collection document"],
        renovarLicenca: ["Renovar licença", "Renew license"],
        baixarCarteira: ["Baixar Carteira de Pesca", "Download fishing license"],
        baixarCarteiraProvisoria: ["Baixar Carteira de Pesca Provisória", "Download Provisional Fishing Portfolio"]
      }
    },
    confirm: {
      titulo: [
        "Confirmar conclusão",
        "Confirm completion",
        "Confirmar la conclusión"
      ],
      mensagem: [
        "Declaro, para os devidos fins e efeitos legais, serem pessoais e verdadeiras as informações inseridas na solicitação da carteira de pesca ao IPAAM, sobre as quais assumo todas as responsabilidades, sob pena de incorrer nas sanções previstas nos artigos 299 e 307 do Código Penal (falsidade ideológica e falsa identidade).",
        "I declare, for the proper purposes and legal effects, the information included in the application for the fishing license to the IPAAM, for which I assume all the responsibilities, to be personal and true, under penalty of incurring the penalties provided for in articles 299 and 307 of the Brazilian Penal Code ( false ideology and false identity).",
        "Declaro, para los debidos fines y efectos legales, ser personales y verdaderas las informaciones insertadas en la solicitud de la cartera de pesca al IPAAM, sobre las cuales asumo todas las responsabilidades, so pena de incurrir en las sanciones previstas en los artículos 299 y 307 del Código Penal brasileño (falsedad ideológica y falsa identidad)."
      ],
      botoes: {
        confirm: ["Confirmar", "Confirm", "Confirmar"],
        cancel: ["Cancelar", "Cancel", "Cancelar"]
      }
    },
    cancel: {
      titulo: [
        "Confirmar cancelamento",
        "Confirm cancellation",
        "Confirmar cancelación"
      ],
      mensagem: [
        "Deseja realmente cancelar o cadastro? Ao selecionar a opção 'Confirmar', todos os dados até o momento serão perdidos.",
        "Do you really want to cancel the registration? By selecting the 'Confirm' option, all data so far will be lost.",
        "Desea realmente cancelar el registro? Al seleccionar la opción 'Confirmar', todos los datos hasta el momento se perderán."
      ],
      mensagemRenovar: [
        "Deseja realmente cancelar a renovação da Licença de Pesca?",
        "Do you really want to cancel your Fishing License?",
        "¿Desea realmente cancelar la renovación de la licencia de pesca?"
      ],
      botoes: {
        confirm: ["Confirmar", "Confirm", "Confirmar"],
        cancel: ["Cancelar", "Cancel", "Cancelar"]
      }
    }
  },
  consultar: {
    titulo: ["Licença de Pesca Amadora (Esportiva ou Recreativa)", "Amadora Fishing License (Sportive or Recreational)"],
    botoes: {
      cadastrar: ["Cadastrar licença", "Register license"]
    },
    menuArquivos: {
      tituloGeral: ["Arquivos", "Files"],
      remessa: ["Remessa", "Shipping"],
      retorno: ["Retorno", "Return"]
    }
  },
  remessa: {
    tituloRetorno: ["Arquivos de retorno", "Return files"],
    tituloRemessa: ["Arquivos de remessa", "Shipping files"],
    nomeArquivo: ["Nome do arquivo", "File name"],
    paginacao: {
      exibir: ["Exibindo página","Viewing page"],
      de: ["de","in"],
      qtdRegistros: ["registros","records"]
    },
    listagemRemessa: ["Listagem dos arquivos de remessa", "Listing of shipping files"],
    dataArquivo: ["Data de criação", "Creation date"],
    semRemessa: ["Sem arquivos de remessas registrados", "No Registered Shipment Files"],
    semRetorno: ["Sem arquivos de retorno registrados", "No registered return files"],
    acao: ["Download", "Action"],
    botaoAcao: ["Download", "Download file"],
    uploadArquivo: ["Clique no botão de cima para adcionar um arquivo de retorno", "Click the top button to add a return file"],
    gerarRemssa: ["Gerar remessa", "Generate Shipping"],
    enviarArquivoRetorno: ["Enviar arquivo de retorno", "Send return file"],
    enviarArquivo:["Enviar arquivo", "Send file"],
    cancelar: ["Cancelar", "Cancel"],
    licenca: {
      tituloLicenca: ["Licenças", "License"],
      listagemLicenca: ["Listagem das licenças", "List of licenses"]
    },
    cancelamento: {
      mensagemCancelamento: ["Realmente deseja excluir o arquivo","Really want to delete file"],
      mensagemCancelamentoAviso: ["Atenção","Attention"]
    }
  }
};

export const EN_GERAL = handleMessage(GERAL, Properties.LANGUAGE_INDEX.EN);
export const PT_GERAL = handleMessage(GERAL, Properties.LANGUAGE_INDEX.PT_BR);
