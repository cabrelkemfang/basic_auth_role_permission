FROM java:8
LABEL maintainer=“chathuranga.t@gmail.com”
EXPOSE 8080
ADD target/basic_poc-0.0.1-SNAPSHOT.jar basic_poc-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","basic_poc-0.0.1-SNAPSHOT.jar"]