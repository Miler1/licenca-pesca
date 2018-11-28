import { Exception } from "./Exception";
import { HttpError } from "./HttpError";
import * as _ from "lodash";

/**
 * Excessão de erros HTTP.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
export const HttpException = {
  /**
   * Retorna um objeto erro dada a resposta.
   *
   * @param response A resposta HTTP
   * @param show A flag que dita se o erro será ou não exportado ao
   *             usuário
   * @return {*|void} O erro
   */
  init(response, show = false) {
    return this.handleError(response, show);
  },

  /**
   * Retorna um objeto erro com o devido status e
   * mensagem.
   *
   * @param response A resposta HTTP
   * @param show A flag que dita se o erro será ou não exportado ao usuário
   * @return {{message: (*|string)}}
   */
  handleError(response, show) {
    if (this.valid(response)) {
      return this.generateMessage(response) !== null
        ? Exception({ message: this.generateMessage(response), show })
        : Exception({ show });
    } else {
      return new Error("Argumentos inválidos de Exceção.");
    }
  },

  /**
   * Confere se o status é previsto no helper de erros
   * comparando se a mensagem entregue por parâmetro
   * é null ou undefined e retornando falso pra caso seja.
   *
   * @param statusMsg O status da mensagem
   * @return {Boolean} Verdadeiro se existir a mensagem tratada
   * no {HttpError}.
   */
  handledHttpError(statusMsg) {
    return !_.isNil(HttpError[statusMsg]);
  },

  /**
   * Dado o erro HTTP, retorna a mensagem correta
   * de acordo com seu status, guardado em HttpError ou
   * disponibilizado pelo backend.
   *
   * @param error
   * @return {String | null}
   */
  generateMessage(error) {
    let status = error.response.status;

    if (this.handledHttpError(status)) {
      return HttpError[status];
    } else if (!_.isNil(error.message)) {
      return error.message;
    } else {
      return null;
    }
  },

  /**
   * Checa se os argumentos do HttpException são
   * válidos.
   *
   * @param response A response
   * @return verdadeiro se a response estiver como esperada
   */
  valid(response) {
    return response && response.response && response.response.status;
  }
};
