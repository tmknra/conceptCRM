##!/bin/bash
## Make sure it will accept connections
#until nc --send-only db 5432 < /dev/null
#do
#    echo "waiting for postgres container..."
#    sleep 2
#done
#
## Make sure the DB is ready to accept commands
#until psql -w -p 5432 -h db -c "select 1"
#do
#    echo "waiting for postgres to accept connections..."
#    sleep 2
#done
#
#./flyway migrate -user=$PGUSER -password=$PGPASSWORD -url="jdbc:postgresql://db:5432/${PGDATABASE}" -locations='filesystem:/opt/migrations'
