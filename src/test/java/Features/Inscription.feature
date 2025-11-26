Feature: Tester la fonctionnalité d'inscription

  Background:
    Given Je suis sur la page d'accueil

  @InscriptionValide
  Scenario: S'inscrire avec des données valides
    When Je clique sur mon compte dans la barre de navigation
    And Je sélectionne s’inscrire dans la liste déroulante
    And Je saisis le prénom "Anis"
    And Je saisis le nom "Ben Ali"
    And Je saisis l'email "anis960@gmail.com"
    And Je saisis le numéro de téléphone "42635890"
    And Je saisis le mot de passe "Maha@123"
    And Je saisis la confirmation du mot de passe "Maha@123"
    And Je coche la case de la politique de confidentialité
    And Je clique sur le bouton continuer
    Then Le message "Your Account Has Been Created!" s’affiche

  @InscriptionInvalide
  Scenario Outline: S'inscrire avec des données invalides
    When Je clique sur mon compte dans la barre de navigation
    And Je sélectionne s’inscrire dans la liste déroulante
    And Je saisis le prénom "<prenom>"
    And Je saisis le nom "<nom>"
    And Je saisis l'email "<email>"
    And Je saisis le numéro de téléphone "<tel>"
    And Je saisis le mot de passe "<mdp>"
    And Je saisis la confirmation du mot de passe "<cmdp>"
    And Je coche la case de la politique de confidentialité
    And Je clique sur le bouton continuer
    Then Le message "<msg>" s’affiche

    Examples:
      | prenom | nom     | email            | tel      | mdp       | cmdp      | msg                              |
      | Anis   |         |anis7@gmail.com   | 42635890 | Maha@123  | Maha@123  | Your Account Has Been Created!   |
      |        | BenAli  | anis3@gmail.com   | 42635890 | Maha@123  | Maha@123  | Your Account Has Been Created!   |
      | Anis   | BenAli  | anis15gmail.com    | 42635890 | Maha@123  | Maha@123  | Your Account Has Been Created!   |
      | Anis   | BenAli  | anis23@gmail.com   | abcdef   | Maha@123  | Maha@123  | Your Account Has Been Created!   |
      | Anis   | BenAli  | anis23@gmail.com   | 42635890 | Maha123   | Maha123   | Your Account Has Been Created!   |
      | Anis   | BenAli  | anis6@gmail.com   | 42635890 | Maha@123  | Maha@12   | Your Account Has Been Created!   |
