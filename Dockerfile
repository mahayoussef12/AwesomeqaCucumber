FROM maven:3.9.2-eclipse-temurin-17

# Installer Chrome + dépendances Selenium
RUN apt-get update && \
    apt-get install -y software-properties-common && \
    add-apt-repository universe && \
    apt-get update && \
    apt-get install -y \
        chromium \
        chromium-driver \
        curl \
        unzip \
        git \
        libxss1 \
        libnss3 \
        libgconf-2-4 \
        libxi6 \
        libgbm1 \
        libasound2 && \
    rm -rf /var/lib/apt/lists/*

# Variables Selenium + Chrome
ENV CHROME_BIN=/usr/bin/chromium
ENV CHROME_DRIVER=/usr/bin/chromium-browser

WORKDIR /app
COPY . /app

# Lancer les tests
CMD ["mvn", "clean", "test"]
