FROM maven:3.9.2-eclipse-temurin-11

# Installer Chromium et le ChromeDriver pour Selenium
RUN apt-get update && apt-get install -y \
    chromium-browser \
    chromium-chromedriver \
    unzip \
    git \
    && rm -rf /var/lib/apt/lists/*

# Variables d'environnement pour Selenium
ENV CHROME_BIN=/usr/bin/chromium-browser
ENV CHROME_DRIVER=/usr/bin/chromedriver

WORKDIR /app
COPY . /app

# Commande par défaut pour exécuter les tests Maven
CMD ["mvn", "clean", "test"]
