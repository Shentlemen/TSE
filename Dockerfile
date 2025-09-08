FROM openjdk:11-jdk-slim

RUN apt-get update && apt-get install -y maven wget && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

RUN mkdir -p /opt/wildfly

RUN wget https://download.jboss.org/wildfly/37.0.1.Final/wildfly-37.0.1.Final.tar.gz && \
    tar -xzf wildfly-37.0.1.Final.tar.gz -C /opt/ && \
    mv /opt/wildfly-37.0.1.Final/* /opt/wildfly/ && \
    rm wildfly-37.0.1.Final.tar.gz

# Copiar el WAR que se generó en el paso anterior
COPY target/tse-wildfly-app.war /opt/wildfly/standalone/deployments/

EXPOSE 8080

CMD ["/opt/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
