FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/playground-coffee-mailer.jar /playground-coffee-mailer/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/playground-coffee-mailer/app.jar"]
