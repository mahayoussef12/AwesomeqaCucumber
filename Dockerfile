FROM maven:3.9.1-jdk-17

WORKDIR /app
COPY . /app

RUN mvn clean install

CMD ["mvn", "test"]
