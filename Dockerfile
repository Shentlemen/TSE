FROM openjdk:24-jdk

RUN apk update && apk add --no-cache maven wget

ENV JAVA_HOME=/usr/local/openjdk-24
ENV PATH="$JAVA_HOME/bin:$PATH"

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

RUN java -version

RUN mkdir -p /opt/wildfly

RUN wget https://github.com/wildfly/wildfly/releases/download/37.0.1.Final/wildfly-37.0.1.Final.tar.gz && \
    tar -xzf wildfly-37.0.1.Final.tar.gz -C /opt/ && \
    mv /opt/wildfly-37.0.1.Final/* /opt/wildfly/ && \
    rm wildfly-37.0.1.Final.tar.gz

# Configurar WildFly para usar Java 24
RUN echo "JAVA_HOME=/usr/local/openjdk-24" >> /opt/wildfly/bin/standalone.conf

RUN cp target/tse-wildfly-app.war /opt/wildfly/standalone/deployments/

EXPOSE 8080
ENV PORT=8080

CMD ["/opt/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-Djboss.http.port=${PORT}"]
