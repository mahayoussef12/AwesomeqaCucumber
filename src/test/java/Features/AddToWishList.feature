Feature: Tester la fonctionnalité d'ajout à la liste de souhaits

  Background:
    Given Je suis à home page
    When Je clique sur l’onglet Mon compte dans la barre de navigation
    And Je sélectionne l’option Se connecter dans la liste déroulante
    And Je saisis l’adresse email "youssefmaha@gmail.com"
    And Je saisis mot de passe "123456789"
    And Je clique sur le bouton Connexion

  @WishlistValide
  Scenario: Ajouter un produit à la wishlist avec des données valides
    When Je clique sur le bouton Add to Wish List du produit nommé "MacBook"
    Then msg "Success: You have added MacBook to your wish list!" s’affiche
    And Le compteur de la wishlist augmente
    And L'icône du cœur devient active


  @WishlistInvalide
  Scenario Outline: Essayer d'ajouter un produit déjà présent dans la liste de souhaits
    When Je clique sur le bouton Add to Wish List du produit nommé "<produit>"
    Then msg "<msg>" s’affiche
    And Le compteur ne doit pas augmenter

    Examples:
      | produit | msg                                                       |
      | MacBook | Warning: You have already added MacBook to your wish list! |
      | iPhone  | Warning: You have already added iPhone to your wish list!  |
