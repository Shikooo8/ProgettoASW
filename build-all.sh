#!/bin/bash

# Script per la compilazione e costruzione dell'intera applicazione 

echo "Compilazione di tutti i progetti in corso..."

# Esegue la pulizia delle build precedenti per evitare conflitti
./gradlew clean 

# Compila e assembla tutti i moduli saltando i test per velocizzare il processo
./gradlew build -x test 

echo "Build completata con successo."