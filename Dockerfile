FROM maven as build

COPY src src
COPY pom.xml pom.xml

RUN mvn clean package -Dmaven.test.skip

FROM amazoncorretto:21-alpine as app

RUN adduser --system shortli_admin &&  \
    addgroup --system shortli &&  \
    adduser shortli_admin shortli
USER shortli_admin

WORKDIR /app

COPY --from=build target/Shortli.jar ./app.jar

ENTRYPOINT ["java", "-jar", "./app.jar"]