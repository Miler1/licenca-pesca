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
    scp backend/target/backend-1.0.0-SNAPSHOT.jar  deploy@runners.ti.lemaf.ufla.br:/var/spring/deploy

    ssh -t deploy@runners.ti.lemaf.ufla.br 'sudo systemctl stop carteira-pesca.service | sudo mv /var/spring/deploy/backend-1.0.0-SNAPSHOT.jar  /var/spring/carteira-pesca/carteira-pesca-1.0.0-SNAPSHOT.jar'

    ssh -t deploy@runners.ti.lemaf.ufla.br 'sudo systemctl start carteira-pesca.service'

    echo "Deploy realizado com sucesso no ambiente de teste!" ;;

homolog)



    echo "Preparando os arquivos do frontend..."
    npm --prefix ./frontend run build


    echo "Preparando os arquivos do backend..."
    cd backend
    mvn clean
    mvn compile
    mvn package -DskipTests
    cd ..


    echo "Executando operações no servidor..."

    scp backend/target/backend-1.0.0-SNAPSHOT.jar lemaf@177.105.35.45:/home/lemaf/releases_homolog/AM/carteira-pesca/

    echo "Arquivo enviado com sucesso para o servidor!" ;;

*)
    echo "Opcao Invalida!"
    echo "Escreva './deploy test' para realizar o deploy em ambiente de teste."
    echo "Escreva './deploy homolog' para realizar o deploy em ambiente de homologação.";;
esac


# Manual
# mvn clean; mvn compile; mvn package -DskipTests; scp target/backend-1.0.0-SNAPSHOT.jar USUARIO@177.93.109.141:/tmp/

# # Conectanco no 1o servidor
# ssh USUARIO@177.93.109.141

# # Copiando para o 2o servidor
# scp /tmp/backend-1.0.0-SNAPSHOT.jar 192.168.100.6:/tmp/

# # Conectanco no 2o servidor
# ssh USUARIO@192.168.100.6

# # Copiando para o diretório da aplicação
# sudo cp /tmp/backend-1.0.0-SNAPSHOT.jar /dados/var/spring/carteira-pesca/

# # Reiniciando a aplicação
# sudo systemctl restart carteira-pesca.service
