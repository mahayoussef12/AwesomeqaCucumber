FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

COPY . .

# Build du projet + téléchargement des dépendances
RUN mvn -B clean install

# Commande par défaut = exécuter les tests
CMD ["mvn", "test"]
