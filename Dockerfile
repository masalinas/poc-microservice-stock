FROM openjdk:11

WORKDIR /opt/app

ENV SPRING_PROFILES_ACTIVE=docker

ADD ./target/*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]