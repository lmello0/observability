FROM maven:3-amazoncorretto-17 AS build

ADD . /build
RUN cd /build && mvn package --quiet -DskipTests

FROM amazoncorretto:17-alpine-full

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

WORKDIR /app

COPY --from=build /build/target/*.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]
