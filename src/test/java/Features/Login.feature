Feature: Tester la fonctionnalité de connexion

  Background:
    Given Je suis sur la page d'accueil du site

  @ConnexionValide
  Scenario: Se connecter avec des identifiants valides
    When Je clique sur compte dans la barre de navigation
    And Je sélectionne se connecter dans la liste déroulante
    And Je saisi l'email "mayouss30@gmail.com"
    And Je saisi le mot de passe "123456789"
    And Je clique sur le bouton de connexion
    Then message "My Account" s’affiche

  @ConnexionInvalide
  Scenario Outline: Se connecter avec des identifiants invalides
    When Je clique sur compte dans la barre de navigation
    And Je sélectionne se connecter dans la liste déroulante
    And Je saisi l'email "<email>"
    And Je saisi le mot de passe "<password>"
    And Je clique sur le bouton de connexion
    Then message "<msg>" s’affiche

    Examples:
      | email                | password | msg                                                   |
      | mayouss30@gmail.com  | Mauvais1 | Warning: No match for E-Mail Address and/or Password. |
      | faux@awesomeqa.com   | 123456789 | Warning: No match for E-Mail Address and/or Password. |
      |                      | Test@123 | Warning: E-Mail Address must be required!             |
      | mayouss30@gmail.com  |          | Warning: Password must be required!                   |
      |                      |          | Warning: No match for E-Mail Address and/or Password. |
  @Blocage
  Scenario: Bloquer le compte après 5 tentatives échouées
    When Je clique sur compte dans la barre de navigation
    And Je sélectionne se connecter dans la liste déroulante
    And Je saisi l'email "test@awesomeqa.com"
    And Je saisis un mot de passe incorrect 5 fois
    Then Le message "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour." s’affiche


