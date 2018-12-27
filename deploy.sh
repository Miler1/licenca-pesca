#!/bin/bash

# DESCRICAO
# Realiza o deploy da aplicação no ambiente desejado.

case $1 in

test)

    echo "Preparando os arquivos do frontend..."
    npm --prefix ./frontend run build


    echo "Preparando os arquivos do backend..."
    cd backend
    mvn clean install
    cd ..

    echo "Executando operações no servidor..."
    scp backend/target/backend-1.0.0-SNAPSHOT.jar  deploy@java3-5.ti.lemaf.ufla.br:/var/application/am/dev/backups

    ssh -t deploy@java3-5.ti.lemaf.ufla.br 'sudo service carteira-pesca stop | sudo cp -rf /var/application/am/dev/backups/backend-1.0.0-SNAPSHOT.jar  /var/application/am/dev/carteira-pesca/carteira-pesca-1.0.0-SNAPSHOT.jar'

    ssh -t deploy@java3-5.ti.lemaf.ufla.br 'sudo service carteira-pesca start'

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
