FROM maven:3.9.2-eclipse-temurin-17

RUN apt-get update && apt-get install -y \
    chromium-browser \
    chromium-chromedriver \
    unzip \
    git \
    && rm -rf /var/lib/apt/lists/*

ENV CHROME_BIN=/usr/bin/chromium-browser
ENV CHROME_DRIVER=/usr/bin/chromedriver

WORKDIR /app
COPY . /app

CMD ["mvn", "clean", "test"]
