
FROM JDK

RUN mkdir /app
WORKDIR /APP
COPY *.jar /app/app.jar

