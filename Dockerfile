FROM maven:3.9.2-eclipse-temurin-17

# Installer Chromium + outils
RUN apt-get update && apt-get install -y \
    chromium-browser \
    chromium-chromedriver \
    unzip \
    git \
    && rm -rf /var/lib/apt/lists/*

# Variables d'environnement
ENV CHROME_BIN=/usr/bin/chromium-browser
ENV CHROME_DRIVER=/usr/bin/chromedriver

# Répertoire de travail
WORKDIR /app
COPY . /app

# IMPORTANT : éviter crash Chrome dans Docker
RUN mkdir -p /usr/share/man/man1
RUN apt-get install -y --no-install-recommends libxss1 libnss3 libgconf-2-4 libxi6

# Lancer les tests
CMD ["mvn", "clean", "test"]
