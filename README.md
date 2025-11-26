# 🚀 AwesomeQA – Projet d’Automatisation QA

Automatisation complète des scénarios fonctionnels du site **AwesomeQA** à l’aide de **Selenium WebDriver, Cucumber (BDD) et JUnit**.  
Le projet valide le **parcours utilisateur complet** et assure la **fiabilité** de l’application via un workflow QA structuré et maintenable.

---

## 🎯 Objectif du projet

Simuler et valider le **parcours utilisateur complet** sur l’application web, selon les bonnes pratiques **ISTQB** :

✔ Analyse des besoins  
✔ Rédaction des scénarios  
✔ Automatisation  
✔ Exécution des tests  
✔ Reporting & analyse des résultats  

---

## 🧰 Technologies & Outils

| Catégorie           | Outils |
|---------------------|-----------------------------|
| **Langage**         | Java 17 |
| **Automatisation Web** | Selenium WebDriver 4.25 |
| **Framework BDD**   | Cucumber (Gherkin) |
| **Test Runner**     | JUnit |
| **IDE**             | IntelliJ IDEA |
| **Build Tool**      | Maven |
| **Reporting**       | Cucumber Report |

---

## 🧪 Scénarios Automatisés

| Scénario | Description |
|----------|-------------|
| 🔐 Login | Connexion avec identifiants valides ou invalides |
| 🚪 Logout | Déconnexion de l’utilisateur |
| 📝 Register | Inscription d’un nouvel utilisateur |
| 💖 Wishlist | Ajouter des produits à la liste de souhaits |
| 🛒 Shopping Cart | Ajouter / supprimer des produits du panier |
| 🏠 Gestion d’adresses | Ajouter une nouvelle adresse, supprimer une adresse, vérifier messages de succès et d’erreur |

👉 Tous les scénarios sont écrits en **Gherkin (Given / When / Then)** pour une meilleure lisibilité et collaboration.

---

## 📂 Architecture du projet

```plaintext
src/test/java
  ├── features/          # Scénarios Cucumber (.feature)
  ├── stepDefinitions/   # Étapes Gherkin
  ├── pages/             # Page Object Model (POM)
  ├── runners/           # Fichiers JUnit Runner
```
📌 Architecture POM → facilite la maintenance, la réutilisabilité et l’évolution du projet.

----

## 📈 Résultats & Valeur Ajoutée

✔ Tests automatisés reproductibles

✔ Architecture POM professionnelle

✔ Scénarios BDD clairs et structurés

✔ Gestion complète des parcours utilisateur (login, register, wishlist, panier, adresses)

✔ Génération automatique de rapports

✔ Approche QA alignée avec ISTQB

✔ Développement des compétences en Selenium, Cucumber et Java

------

## 🧠 Bonnes Pratiques Utilisées

✔ Architecture POM (Page Object Model)

✔ Scénarios Given / When / Then (BDD)

✔ Nommage clair des méthodes et variables

✔ Tests indépendants & maintenables

✔ Respect des conventions Maven

✔ Reporting HTML automatisé

✔ Gestion des alertes (confirmation / annulation)

------

## 📊 Reporting

À la fin de l’exécution des tests, les éléments suivants sont générés :

📄 Rapport HTML – Cucumber

🧾 Logs d’exécution

📈 Statistiques de réussite / échec

----

##📌 Instructions pour exécuter les tests

1- Cloner le dépôt :
```plaintext
git clone https://github.com/ton-utilisateur/AwesomeQA.git
cd AwesomeQA
```
2- Installer les dépendances Maven :
```plaintext
mvn clean install
```
3- Exécuter les tests via Maven :
```plaintext
mvn test
```
4 - Ou via le **Cucumber Runner** dans l’IDE (ex. RunnerTest.java).
