#!/bin/bash

# DESCRICAO
# Realiza o deploy da aplicação no ambiente desejado.

case $1 in

test)

    echo "Preparando os arquivos do frontend..."
    npm --prefix ./frontend run build

    echo "Preparando os arquivos do backend..."
    mvn backend/ clean install

    echo "Executando operações no servidor..."
    scp backend/target/central-colosso-2.0.4.RELEASE.jar deploy@java3-5.ti.lemaf.ufla.br:/var/application/pa/dev/backups

    ssh -t deploy@java3-5.ti.lemaf.ufla.br 'sudo service central-colosso stop | sudo cp -rf /var/application/pa/dev/backups/central-colosso-2.0.4.RELEASE.jar /var/application/pa/dev/central-colosso/central-colosso-2.0.4.RELEASE.jar'

    ssh -t deploy@java3-5.ti.lemaf.ufla.br 'sudo service central-colosso start'

    echo "Deploy realizado com sucesso no ambiente de teste!" ;;

homolog)

    echo "Preparando os arquivos do frontend..."
    npm --prefix ./frontend run build

    echo "Preparando os arquivos do backend..."
    mvn -f backend/ clean install

    echo "Executando operações no servidor..."
    scp backend/target/central-colosso-2.0.4.RELEASE.jar lemaf@177.105.35.45:/home/lemaf/releases_homolog

    echo "Arquivo enviado com sucesso para o servidor!" ;;

*)
    echo "Opcao Invalida!" 
    echo "Escreva './deploy test' para realizar o deploy em ambiente de teste."
    echo "Escreva './deploy homolog' para realizar o deploy em ambiente de homologação.";;
esac
