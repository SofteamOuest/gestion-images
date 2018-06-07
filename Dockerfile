FROM java:8

MAINTAINER pjboceno@gmail.com

WORKDIR /apps/

COPY target/gestion-images.jar /apps/gestion-images.jar

EXPOSE 8080

CMD java -jar gestion-images.jar