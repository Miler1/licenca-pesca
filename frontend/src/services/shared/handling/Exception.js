import { Notificator } from "./Notificator";

/**
 * Modelo base de Exceções
 *
 * Ex.: options = {
 *      title: "Erro padrão",
 *      message: "Erro não identificado. Contate o administrador do sistema.",
 *      type: "error",
 *      duration: 1 (opcional),
 *      show: false (opcional)
 * }
 *
 * @param options - Argumentos da exceção
 * @return {{message: (*|string)}}
 * @constructor
 * @author Highlander Paiva
 * @since 1.0
 */
export function Exception(options = null) {
  const MENSAGEM_PADRAO =
    "Erro não identificado. Contate o administrador do sistema.";
  const TITULO_PADRAO = "Erro do sistema";
  
  const MENSAGEM_FALHA_CONEXAO = "Não foi possível conectar-se ao servidor. Por favor, verifique sua conexão com a internet e tente novamente."

  /*
  * Caso exista no argumento de opções
  * a variavel booleana show, e a mesma seja positiva,
  * exibirá na tela a exceção
  */
  if (options && options.show)
    Notificator.show({
      title: options.title ? options.title : TITULO_PADRAO,
      message: options.message ? options.message : MENSAGEM_PADRAO,
      type: options.type ? options.type : "error"
    });

  return {
    message: options && options.message ? options.message : MENSAGEM_PADRAO
  };
}
