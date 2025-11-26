Feature: Tester la fonctionnalité de deconnexion

  Background:
    Given Je suis sur la page
    And Je clique sur compte dans barre de navigation
    And Je sélectionne connecter dans la liste déroulante
    And Je saisi email "youssefmaha@gmail.com"
    And Je saisi mot de passe "123456789"
    And Je clique sur bouton de connexion

  Scenario: Se deconnecter correctement
    When Je clique sur la barre de navigation a mon compte
    And Je sélectionne logout dans la liste
    And je clique sur le boutton continuer
    Then message succes "Register" s’affiche