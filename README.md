# Projeto Licença de Pesca do Amazonas

![](https://gitlab.ti.lemaf.ufla.br/uploads/-/system/project/avatar/655/ipaam-400x400.png?width=64)

_(Work In Progress)_

## Configuração do projeto

```
 carteira-pesca
├─┬ backend     → backend module
│ ├── src
│ └── pom.xml
├─┬ frontend    → frontend module
│ ├── src
│ └── pom.xml
└── pom.xml     → pom raíz do Maven para gerenciar ambos os módulos
```

## Executando

Dentro do diretório raíz:

```
mvn clean install
```

Rodando a aplicação como um todo:

```
mvn --projects backend spring-boot:run
```
