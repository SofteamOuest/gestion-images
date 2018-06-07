FROM java:8

MAINTAINER pjboceno@gmail.com

WORKDIR /apps/gestion-images

COPY build/libs/gestion-images.jar /apps/api-gateway/gestion-images.jar

EXPOSE 8080

CMD java -jar gestion-images.jar