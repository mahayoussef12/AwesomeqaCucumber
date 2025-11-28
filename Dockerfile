FROM maven:3.9.2-eclipse-temurin-11

RUN apt-get update && apt-get install -y \
    chromium \
    chromium-driver \
    unzip \
    git \
    && rm -rf /var/lib/apt/lists/*

ENV CHROME_BIN=/usr/bin/chromium
ENV CHROME_DRIVER=/usr/bin/chromedriver

WORKDIR /app
COPY . /app

CMD ["mvn", "clean", "test"]
