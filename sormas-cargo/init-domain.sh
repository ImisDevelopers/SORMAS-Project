#!/usr/bin/env bash

# set some defaults
DB_PORT=${DB_PORT:-5432}
DB_NAME=${DB_NAME:-sormas_db}
DB_NAME_AUDIT=${DB_NAME_AUDIT:-sormas_audit_db}
SORMAS_POSTGRES_PASSWORD=${SORMAS_POSTGRES_PASSWORD:-sormasdev}
SORMAS_POSTGRES_USER=${SORMAS_POSTGRES_USER:-sormas_user}
MAIL_FROM=${MAIL_FROM:-admin@example.com}

if [ -z "${DB_HOST}" ]; then
  echo "DB_HOST not set"
  exit 1
fi

# disable unnecessary stuff
echo "set-hazelcast-configuration --enabled=false"
echo "set-monitoring-service-configuration --enabled=false"
echo "set-healthcheck-configuration --enabled=true"
echo "disable-phone-home"

# General domain settings
#echo "delete-jvm-options -Xmx512m" >> $POSTBOOT_COMMANDS
#echo "create-jvm-options -Xmx4096m" >> $POSTBOOT_COMMANDS

# JDBC pool
echo "create-jdbc-connection-pool --restype javax.sql.ConnectionPoolDataSource \
  --datasourceclassname org.postgresql.ds.PGConnectionPoolDataSource --isconnectvalidatereq true \
  --validationmethod custom-validation  \
  --validationclassname org.glassfish.api.jdbc.validation.PostgresConnectionValidation \
  --property 'portNumber=${DB_PORT}:databaseName=${DB_NAME}:serverName=${DB_HOST}:user=${SORMAS_POSTGRES_USER}:password=${SORMAS_POSTGRES_PASSWORD}' sormasDataPool" >> $POSTBOOT_COMMANDS
echo "create-jdbc-resource --connectionpoolid sormasDataPool jdbc/sormasDataPool" >> $POSTBOOT_COMMANDS

# Pool for audit log
echo "create-jdbc-connection-pool --restype javax.sql.XADataSource \
  --datasourceclassname org.postgresql.xa.PGXADataSource --isconnectvalidatereq true \
  --validationmethod custom-validation \
  --validationclassname org.glassfish.api.jdbc.validation.PostgresConnectionValidation \
  --property 'portNumber=${DB_PORT}:databaseName=${DB_NAME_AUDIT}:serverName=${DB_HOST}:user=${SORMAS_POSTGRES_USER}:password=${SORMAS_POSTGRES_PASSWORD}' sormasAuditlogPool" >> $POSTBOOT_COMMANDS
echo "create-jdbc-resource --connectionpoolid sormasAuditlogPool jdbc/AuditlogPool" >> $POSTBOOT_COMMANDS

echo "create-javamail-resource --mailhost localhost --mailuser user --fromaddress ${MAIL_FROM} mail/MailSession" >> $POSTBOOT_COMMANDS

echo "create-custom-resource --restype java.util.Properties --factoryclass org.glassfish.resources.custom.factory.PropertiesFactory --property "org.glassfish.resources.custom.factory.PropertiesFactory.fileName=\${com.sun.aas.instanceRoot}/sormas.properties" sormas/Properties" >> $POSTBOOT_COMMANDS

echo "create-jvm-options -Dlogback.configurationFile=\${com.sun.aas.instanceRoot}/config/logback.xml" >> $POSTBOOT_COMMANDS
echo "set-log-attributes com.sun.enterprise.server.logging.GFFileHandler.maxHistoryFiles=14" >> $POSTBOOT_COMMANDS
echo "set-log-attributes com.sun.enterprise.server.logging.GFFileHandler.rotationLimitInBytes=0" >> $POSTBOOT_COMMANDS
echo "set-log-attributes com.sun.enterprise.server.logging.GFFileHandler.rotationOnDateChange=true" >> $POSTBOOT_COMMANDS

echo "deploy /opt/payara/deployments/sormas-ear.ear" >> $POSTBOOT_COMMANDS
echo "deploy /opt/payara/deployments/sormas-rest.war" >> $POSTBOOT_COMMANDS
echo "deploy /opt/payara/deployments/sormas-ui.war" >> $POSTBOOT_COMMANDS