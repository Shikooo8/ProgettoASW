#!/bin/bash

#Compilazione di tutti i moduli (Java SDK e Gradle)
source build-all.sh

# Avvio Infrastruttura (Consul, Kafka e i 4 Database separati)

source start-consul.sh
cd kafka/docker
source start-kafka.sh
cd ../..
# È più pulito usare docker-compose per gestire i 4 database richiesti
docker compose up -d album-db recensioni-db connessioni-db recensioniseguite-db

#  Attesa tecnica 
# I database e Kafka devono essere pronti prima che le app si connettano
echo "Attendendo che i database e Kafka siano pronti..."
sleep 30

# Avvio dei Microservizi (Esecuzione dei file .jar)
source start-bettermusic.sh