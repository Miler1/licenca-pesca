/**
 * Camada de Domínio da aplicação.
 *
 * As interfaces de <i>model</i>, <i>services</i> e <i>repository</i>. <b>Esta é a parte central da aplicação.</b>
 * A linguagem ubíqua {@see <a href="https://martinfowler.com/bliki/UbiquitousLanguage.html"></a>} é usada nas classes, interfaces, e assinaturas de métodos,
 * e todos conceitos aqui é famíliar ao expecialista pelo negócio.
 *
 * Não existe código de <b>infrastructure</b> ou <b>interface</b> aqui, exceto por coisas como
 * metadados transacional e de segurança.
 *
 */
package br.ufla.lemaf.ti.carteirapesca.domain;