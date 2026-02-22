#!/bin/bash

source stop-bettermusic.sh
source stop-db.sh

cd kafka/docker
source stop-kafka.sh
cd ../..

source stop-consul.sh

