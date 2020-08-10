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
COPY ./sormas-cargo/pom.xml /usr/src/app/sormas-cargo/pom.xml
RUN mvn -f /usr/src/app/sormas-base/pom.xml verify clean --fail-never -B
RUN mvn -f /usr/src/app/sormas-cargo/pom.xml verify --fail-never -B
RUN mvn -f /usr/src/app/sormas-serverlibs/pom.xml install --fail-never -B

# copy sources
COPY ./sormas-api /usr/src/app/sormas-api
COPY ./sormas-backend /usr/src/app/sormas-backend
COPY ./sormas-base /usr/src/app/sormas-base
COPY ./sormas-ear /usr/src/app/sormas-ear
COPY ./sormas-rest /usr/src/app/sormas-rest
COPY ./sormas-ui /usr/src/app/sormas-ui
COPY ./sormas-widgetset /usr/src/app/sormas-widgetset
COPY ./sormas-cargo /usr/src/app/sormas-cargo

# build
RUN cd /usr/src/app/sormas-base && mvn install -DskipTests=true

EXPOSE 8080
WORKDIR /usr/src/app/sormas-cargo

ENTRYPOINT ["mvn", "cargo:run"]