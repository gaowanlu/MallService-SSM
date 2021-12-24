FROM maven as builder
RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build
RUN mvn -B dependency:resolve dependency:resolve-plugins
COPY src /build/src
RUN mvn package

FROM tomcat:9-jdk17

COPY --from=builder /build/target/MallService-SSM-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
