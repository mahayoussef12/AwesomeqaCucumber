
FROM maven:3.9.6-openjdk-17


WORKDIR /app
COPY . /app

RUN mvn clean install

CMD ["mvn", "test"]
