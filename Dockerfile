FROM java:8

ADD build/libs/oAuthAuthServer*.jar /app.jar

EXPOSE 8080

CMD ["java", "-Xmx256m", "-jar", "/app.jar"]
