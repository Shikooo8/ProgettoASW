#!/bin/bash

# 1. Compilazione di tutti i moduli (Java SDK e Gradle)
source build-all.sh

# 2. Avvio Infrastruttura (Consul, Kafka e i 4 Database separati) [cite: 105, 124, 129]
# È più pulito usare docker-compose per gestire i 4 database richiesti [cite: 140]
docker compose up -d consul kafka album-db recensioni-db connessioni-db seguite-db

# 3. Attesa tecnica 
# I database e Kafka devono essere pronti prima che le app si connettano [cite: 142]
echo "Attendendo che i database e Kafka siano pronti..."
sleep 30

# 4. Avvio dei Microservizi (Esecuzione dei file .jar)
source start-bettermusic.sh