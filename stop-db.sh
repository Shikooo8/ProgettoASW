#!/bin/bash

echo Halting Database

docker compose -f docker-compose-db.yml down -v
