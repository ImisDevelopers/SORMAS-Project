FROM maven:3-openjdk-11 as build
ENV MAVEN_OPTS="-Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn"
# cache dependencies
COPY ./sormas-api/pom.xml /usr/src/app/sormas-api/pom.xml
COPY ./sormas-backend/pom.xml /usr/src/app/sormas-backend/pom.xml
COPY ./sormas-base/pom.xml /usr/src/app/sormas-base/pom.xml
COPY ./sormas-ear/pom.xml /usr/src/app/sormas-ear/pom.xml
COPY ./sormas-rest/pom.xml  /usr/src/app/sormas-rest/pom.xml
COPY ./sormas-serverlibs/ /usr/src/app/sormas-serverlibs/
COPY ./sormas-ui/pom.xml /usr/src/app/sormas-ui/pom.xml
COPY ./sormas-widgetset/pom.xml /usr/src/app/sormas-widgetset/pom.xml
RUN mvn -f /usr/src/app/sormas-base/pom.xml verify clean --fail-never -B
RUN mvn -f /usr/src/app/sormas-serverlibs/pom.xml package --fail-never -B

# copy sources
COPY ./sormas-api /usr/src/app/sormas-api
COPY ./sormas-backend /usr/src/app/sormas-backend
COPY ./sormas-base /usr/src/app/sormas-base
COPY ./sormas-ear /usr/src/app/sormas-ear
COPY ./sormas-rest /usr/src/app/sormas-rest
COPY ./sormas-ui /usr/src/app/sormas-ui
COPY ./sormas-widgetset /usr/src/app/sormas-widgetset

# build
RUN cd /usr/src/app/sormas-base && mvn install -DskipTests=true

FROM payara/server-full:5.2020.3-jdk11
ENV DOMAIN_NAME=production DOMAIN_DIR=${PAYARA_DIR}/glassfish/domains/${DOMAIN_NAME}
RUN mkdir -p $DOMAIN_DIR
COPY sormas-base/setup/sormas.properties ${DOMAIN_DIR}
COPY sormas-base/setup/logback.xml ${DOMAIN_DIR}/config
COPY sormas-cargo/init-domain.sh ${SCRIPT_DIR}/init_0_domain.sh
COPY --from=build /usr/src/app/sormas-serverlibs/target/sormas-serverlibs-libjar.jar ${DOMAIN_DIR}/lib/sormas-serverlibs-libjar.jar
COPY --from=build /usr/src/app/sormas-ear/target/sormas-ear.ear $DEPLOY_DIR
COPY --from=build /usr/src/app/sormas-ui/target/sormas-ui.war $DEPLOY_DIR
COPY --from=build /usr/src/app/sormas-rest/target/sormas-rest.war $DEPLOY_DIR

EXPOSE 8080