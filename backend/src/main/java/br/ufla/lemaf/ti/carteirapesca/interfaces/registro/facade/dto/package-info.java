/**
 * Os DTOs são projetados de maneira orientada a casos de uso,
 * intimamente relacionados à interface da camada de serviço.
 * Eles representam uma seção específica do modelo de domínio,
 * que pode cruzar limites agregados.
 * Eles não têm comportamento e são imutáveis.
 * <p>
 * A razão para usar DTOs é principalmente para soltar o acoplamento
 * do aplicativo e a interface do usuário.
 * Ele nos permite construir o modelo de domínio completamente sem
 * ter que estar em conformidade com quaisquer convenções relacionadas
 * à interface do usuário ou requisitos, como métodos getter JavaBean
 * ou construtores no-args. Também evitamos a exposição de funcionalidades
 * internas para o modelo de domínio para a camada de interface do usuário.
 * <p>
 * Além disso, temos um contrato claro para qual parte do gráfico
 * de objeto é carregada do banco de dados quando a chamada da camada
 * de serviço retorna. Se os objetos do modelo de domínio forem passados
 * para a camada da interface do usuário, ser necessário manter a janela
 * de acesso ao banco de dados (a sessão do Hibernate, neste caso)
 * aberta durante todo o processamento de a exibição (o padrão Open
 * Session In View), caso contrário, uma parte não inicializada do modelo
 * acidentalmente acessado, causando um erro.
 * Além de vincular efetivamente a camada da interface do usuário
 * e o restante do aplicativo à mesma JVM, deixar a camada da interface
 * do usuário decidir quando inicializar os dados pode ser muito ineficiente.
 * DTOs são um caminho natural de carregar somente os dados relevantes
 * para um caso de uso específico e bloquear o acesso ao restante.
 * <p>
 * O uso de DTOs também torna a camada de serviço reutilizável em diferentes
 * tipos de frontends: web, rich clients, outros sistemas de backend etc.
 * Lembre-se que as classes que são persistentes com o Hibernate ou
 * tecnologias similares são modificadas em tempo de execução,
 * Assim, por exemplo, os campos com valor de coleção são trocados por
 * implementações customizadas que permitem carregamento lento.
 * Se essas classes de modelo de domínio persistentes forem desserializadas
 * em outra JVM, essas classes específicas de mapeamento de O / R
 * precisam estar disponíveis lá também, o que não é muito elegante.
 * <p>
 * <b>Uma advertência:</b> a utilidade dos DTOs pode variar com o grau de
 * separação vertical na aplicação.
 */
package br.ufla.lemaf.ti.carteirapesca.interfaces.registro.facade.dto;
