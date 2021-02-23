FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} transacoes.jar
ENTRYPOINT ["java","-Xmx512m","-jar","/transacoes.jar"]