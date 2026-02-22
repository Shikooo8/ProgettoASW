#!/bin/bash

echo Halting BETTERMUSIC

docker compose -f docker-compose-services.yml down 
