Feature: Ajouter une nouvelle adresse

  Background:
    Given je suis sur la Home Page
    When je me connecte à la session avec l'email "youssefmaha@gmail.com" et le mot de passe "123456789"

  @AdresseValide
  Scenario Outline: Ajouter une nouvelle adresse valide
    When je clique sur le bouton "Address Book"
    And je clique sur le bouton "New Address"
    And je saisis le prénom "<FirstName>"
    And je saisis le nom "<LastName>"
    And je saisis l'adresse 1 "<Address1>"
    And je saisis la ville "<City>"
    And je saisis le code postal "<Postcode>"
    And je choisis le pays "<Country>"
    And je choisis la région "<Region>"
    And je définis cette adresse comme adresse par défaut "<DefaultAddress>"
    And je clique sur le bouton "Continue"
    Then je devrais voir "<ResultMessage>"
    Examples:
      | FirstName | LastName | Address1 | City            | Postcode | Country | Region    | DefaultAddress |ResultMessage|
      | John        | Doe  | Khniss   | Monastir-Khniss | 5011     | Tunisia | Monastir | Yes            | Your address has been successfully added|
      | John      | Doe      | Street1  | Tunis           | 1000     | Tunisia | Tunis    | No             |Your address has been successfully added |

  @AdresseInvalide
  Scenario Outline: Ajouter une nouvelle adresse invalide
    When je clique sur le bouton "Address Book"
    And je clique sur le bouton "New Address"
    And je saisis le prénom "<FirstName>"
    And je saisis le nom "<LastName>"
    And je saisis l'adresse 1 "<Address1>"
    And je saisis la ville "<City>"
    And je saisis le code postal "<Postcode>"
    And je choisis le pays "<Country>"
    And je choisis la région "<Region>"
    And je définis cette adresse comme adresse par défaut "<DefaultAddress>"
    And je clique sur le bouton "Continue"
    Then je devrais voir "<ResultMessage>"

    Examples:
      | FirstName | LastName | Address1 | City     | Postcode | Country | Region   | DefaultAddress | ResultMessage                              |
      |           | Youssef  | Khniss   | Monastir | 5011     | Tunisia | Monastir | Yes            | First name is required                      |
      | Maha      |          | Khniss   | Monastir | 5011     | Tunisia | Monastir | Yes            | Last name is required                       |
      | Ma        | Youssef  | Khniss   | Monastir | 5011     | Tunisia | Monastir | Yes            | First name must be alphabetic and > 3 chars |
      | Maha123   | Youssef  | Khniss   | Monastir | 5011     | Tunisia | Monastir | Yes            | First name must contain only letters        |
      | Maha      | Yo       | Khniss   | Monastir | 5011     | Tunisia | Monastir | Yes            | Last name must be alphabetic and > 3 chars  |
      | Maha      | You55    | Khniss   | Monastir | 5011     | Tunisia | Monastir | Yes            | Last name must contain only letters         |
      | Maha      | Youssef  |          | Monastir | 5011     | Tunisia | Monastir | Yes            | Address is required                         |
      | Maha      | Youssef  | Khniss   |          | 5011     | Tunisia | Monastir | Yes            | City is required                            |
      | Maha      | Youssef  | Khniss   | Monastir |          | Tunisia | Monastir | Yes            | Postcode is required                        |
      | Maha      | Youssef  | Khniss   | Monastir | 50A1     | Tunisia | Monastir | No            | Postcode must contain only numbers          |
