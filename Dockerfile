FROM node:10-alpine
COPY ./client /code
WORKDIR /code
RUN npm ci && npm run build

FROM maven:3-jdk-8
COPY ./server /code
COPY --from=0 /code/build /code/src/main/resources/static
WORKDIR /code
RUN mvn clean package

FROM openjdk:8-alpine
RUN mkdir /code
COPY --from=1 /code/target/cm.jar /code/cm.jar
VOLUME [ "/code/config" ]
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.5.0/wait /wait
RUN chmod +x /wait
CMD /wait && java -jar /code/cm.jar -Dspring.config.location=/code/config/ -Djava.security.egd=file:/dev/./urandom