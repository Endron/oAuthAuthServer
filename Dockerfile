FROM java:8

ADD build/libs/oAuthAuthServer*.jar /app.jar

RUN groupadd -r java && useradd -r -g java java

EXPOSE 8080

USER java

CMD ["java", "-Xmx256m", "-Djava.security.egd=file:/dev/urandom", "-jar", "/app.jar"]
