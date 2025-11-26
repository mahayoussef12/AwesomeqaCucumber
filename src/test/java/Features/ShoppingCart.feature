Feature: Ajouter un produit au panier

  Background:
    Given Dans Home Page
    When je suis connecté à la session avec email "youssefmaha@gmail.com" et mot de passe "123456789"



  @PanierValide
  Scenario: Ajouter un nouveau produit au panier
    When Je clique sur le bouton Add to Cart de produit nommé "MacBook"
    Then le message "Success: You have added MacBook to your shopping cart!" s’affiche
    And La quantité du produit dans le panier augmente de "1"

