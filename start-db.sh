#!/bin/bash

echo Starting Database

docker compose -f docker-compose-db.yml up -d 
