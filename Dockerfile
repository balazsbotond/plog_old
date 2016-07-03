FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/plog.jar /plog/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/plog/app.jar"]
