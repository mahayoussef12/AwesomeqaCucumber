# Dockerfile
FROM maven:3.9.2-eclipse-temurin-17

# Installer Chromium (navigateur) et outils utiles
RUN apt-get update && apt-get install -y \
    chromium-browser \
    xvfb \
    unzip \
    git \
    && rm -rf /var/lib/apt/lists/*

ENV CHROME_BIN=/usr/bin/chromium-browser

WORKDIR /app
COPY . /app

# Pré-télécharger les dépendances pour accélérer les builds (optionnel)
RUN mvn -Dmaven.test.skip=true -q clean package

# Lorsqu'on lance l'image, exécute les tests (xvfb-run pour sécurité UI headless)
CMD ["xvfb-run", "-a", "mvn", "clean", "test", "-DskipTests=false"]
