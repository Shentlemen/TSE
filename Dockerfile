FROM openjdk:11-jdk-slim

RUN apt-get update && apt-get install -y maven wget && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copiar archivos de configuración
COPY pom.xml .

# Descargar dependencias
RUN mvn dependency:go-offline

# Copiar código fuente
COPY src ./src

# Compilar la aplicación
RUN mvn clean package -DskipTests

# Instalar WildFly
RUN mkdir -p /opt/wildfly
RUN wget https://download.jboss.org/wildfly/37.0.1.Final/wildfly-37.0.1.Final.tar.gz && \
    tar -xzf wildfly-37.0.1.Final.tar.gz -C /opt/ && \
    mv /opt/wildfly-37.0.1.Final/* /opt/wildfly/ && \
    rm wildfly-37.0.1.Final.tar.gz

# Mover el WAR generado a WildFly
RUN cp target/tse-wildfly-app.war /opt/wildfly/standalone/deployments/

EXPOSE 8080

CMD ["/opt/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
