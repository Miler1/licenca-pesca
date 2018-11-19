import { Notificator } from "./Notificator";

/**
 * Modelo base de Exceções
 *
 * Ex.: options = {
 *      title: "Erro padrão",
 *      message: "Erro não identificado. Contate o adiministrador do sistema.",
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

  const MENSAGEM_PADRAO = "Erro não identificado. Contate o adiministrador do sistema.";
  const TITULO_PADRAO = "Erro do sistema";

  /*
  * Caso exista no argumento de opções
  * a variavel booleana show, e a mesma seja positiva,
  * exibirá na tela a exceção
  */
  if (options && options.show)
    Notificator.show({
      title: options.title ? options.title : TITULO_PADRAO,
      massage: options.message ? options.message : MENSAGEM_PADRAO,
      type: options.type ? options.type : "error"
    });

  return {
    message: options && options.message ? options.message : MENSAGEM_PADRAO
  };

}