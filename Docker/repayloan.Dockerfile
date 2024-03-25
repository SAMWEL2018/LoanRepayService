FROM eclipse-temurin:17-jdk-alpine

ENV VERSION 1.0.0

WORKDIR /app/RepayLoan/

ADD RepayLoan-$VERSION.jar $VERSION.jar

EXPOSE 8014

ENTRYPOINT ["java","-jar","1.0.0.jar", "--server"]