Feature: Supprimer une adresse

  Background:
    Given je suis connecté avec l'email "youssefmaha@gmail.com" et le mot de passe "123456789"

  Scenario: Supprimer une adresse non par defaut
    When je clique sur Address Book
    And je supprime l'adresse de "Maha Yo" en choisissant "confirmer" l'alerte
    Then un message "Success: Your address has been successfully deleted"
    And l'adresse de "Maha Yo" ne devrait plus être visible

  Scenario: Supprimer une adresse par defaut avec remplacent
    When je clique sur Address Book
    And je supprime l'adresse de "Maha You55" en choisissant "confirmer" l'alerte

    Then un message "Success: Your address has been successfully deleted"


  Scenario: Supprimer une adresse par defaut sans remplacent
    When je clique sur Address Book
    And je supprime l'adresse de "Maha You55" en choisissant "confirmer" l'alerte
    Then un message "Warning: You can not delete your default address!"


  Scenario: Annuler la suppression d'une adresse
    When je clique sur Address Book
    And je supprime l'adresse de "Maha Youssef" en choisissant "annuler" l'alerte
    Then  l'adresse de "Maha Youssef" devrait être visible
