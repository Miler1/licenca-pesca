/**
 * Camada de Infraestrutura da aplicação.
 *
 * Em adicional com as três camadas verticais <i>(interfaces, application e domain)</i>,
 * existe a camada de infraestrutura. Esta suporta todas as três camadas de formas diferente,
 * facilitando a comunicação entre as camadas. Em termos simples, a infraestrutura consiste em tudo que existe
 * independente da aplicação: bibliotecas externas, motored de banco, servidores de aplicação, messaging backend e etc.
 *
 * Também consideramos aruivos de configuração como parte da camada de infraestrutura. Olhando por exemplo pelo
 * aspecto de persistência, as definições de esquema de banco de dados, configurações e arquivos de mapeamento do
 * Hibernate e implementações das interfaces de repositório são partes da camada de infraestrutura.
 *
 * */
package br.ufla.lemaf.ti.carteirapesca.infrastructure;