FROM postgres

ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD loopis
ENV POSTGRES_DB loopisdb

#copy scripts.sql /docker-entrypoint-initdb.d/

