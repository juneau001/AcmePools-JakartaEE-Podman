FROM payara/micro:6.2025.1-jdk21

USER root
RUN apk update
RUN apk add wget
RUN apk add vim
RUN apk add fontconfig ttf-dejavu
# Obtain Postgres jar and copy into place
RUN wget -O /opt/payara/deployments/database-connector.jar https://search.maven.org/remotecontent?filepath=org/postgresql/postgresql/42.7.5/postgresql-42.7.5.jar
# Copy an application to be loaded
COPY ./target/AcmePools-AcmePools-1.0-SNAPSHOT.war /opt/payara/deployments

ENTRYPOINT ["java", "-jar", "-XX:+UseZGC", "-XX:+ZGenerational", "/opt/payara/payara-micro.jar", "--addLibs", "/opt/payara/deployments/database-connector.jar", "--deploy", "/opt/payara/deployments/AcmePools-AcmePools-1.0-SNAPSHOT.war"]
CMD ["--nocluster"]
