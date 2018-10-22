/**
 * Camada de Aplicação.
 * <p>
 * A camada de aplicação é responsável por "orquestrar" o
 * fluxo da aplicação, alinhando com os casos de uso.
 * Essas operações são interface-independent e podem ser
 * synchronous ou message-driven.
 * Essa camada é bem adequada para transações abrangentes,
 * registros em log de alto nível e segurança.
 * <p>
 * A camada de aplicação é fina em termos de lógica de
 * domínio - ela simplesmente coordena os objetos
 * da camada de domínio para executar o trabalho real.
 */
package br.ufla.lemaf.ti.carteirapesca.application;
