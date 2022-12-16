#!/usr/bin/env bash
set -e

# TODO для создания баз прописать свой вариант
export VARIANT="v3"
export SCRIPT_PATH=./lab_data/postgres/docker-entrypoint-initdb.d/
export PGPASSWORD=postgres
echo $SCRIPT_PATH
psql -f "$SCRIPT_PATH/scripts/db-v3.sql"
psql -f "$SCRIPT_PATH/scripts/cars.sql"
