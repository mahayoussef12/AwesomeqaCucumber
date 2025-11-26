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
