FROM openjdk:17
ARG JAVA_JAR=target/*.jar
COPY ${JAVA_JAR} apigateway.jar

ENTRYPOINT ["java", "-jar", "/apigateway.jar"]