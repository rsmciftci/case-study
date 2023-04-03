FROM openjdk:19
WORKDIR /case-study
COPY . .
RUN ./mvnw clean install
COPY target/*.jar case-study.jar
ENTRYPOINT ["java", "-jar", "case-study.jar"]