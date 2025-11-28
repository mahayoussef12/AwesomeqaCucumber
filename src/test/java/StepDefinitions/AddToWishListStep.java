package StepDefinitions;

import Pages.AddToWishListPage;
import Pages.LoginPage;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class AddToWishListStep {

    WebDriver driver;
    LoginPage loginPage;
    AddToWishListPage addToWishListPage;
    int compteurAvant;


    @Given("Je suis à home page")
    public void jeSuisSurLaPage() {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://awesomeqa.com/ui/index.php?route=common/home");
        loginPage = new LoginPage(driver);
        addToWishListPage = new AddToWishListPage(driver);
    }

    @When("Je clique sur l’onglet Mon compte dans la barre de navigation")
    public void jeCliqueSurLOngletMonCompteDansLaBarreDeNavigation() {
        loginPage.cliquerMonCompte();
    }

    @And("Je sélectionne l’option Se connecter dans la liste déroulante")
    public void jeSelectionneLOptionSeConnecter() {
        loginPage.selectionnerLogin();
    }

    @And("Je saisis l’adresse email {string}")
    public void jeSaisisLAdresseEmail(String email) {
        loginPage.ajouteremail(email);
    }

    @And("Je saisis mot de passe {string}")
    public void jeSaisisMotDePasse(String password) {
        loginPage.ajouterpassword(password);
    }

    @And("Je clique sur le bouton Connexion")
    public void jeCliqueSurLeBoutonConnexion() {
        loginPage.clickloginbutton();
    }


    @When("Je clique sur le bouton Add to Wish List du produit nommé {string}")
    public void jeCliqueSurLeBoutonAddToWishListDuProduitNommé(String produit) {
        driver.get("https://awesomeqa.com/ui/index.php?route=common/home");
        compteurAvant = addToWishListPage.getWishlistCounter();
        addToWishListPage.clickAddToWishListButton(produit);
    }

    @Then("msg {string} s’affiche")
    public void msgSAffiche(String msgAttendu) {
        String message = addToWishListPage.getSuccessMessage(); // ou warning
        Assertions.assertTrue(
                message.contains(msgAttendu),
                "Le message affiché ne correspond pas.\nAttendu : '" + msgAttendu + "'\nRéel : '" + message + "'"
        );
    }

    @And("Le compteur de la wishlist augmente")
    public void leCompteurDeLaWishlistAugmente() {
        int apres = addToWishListPage.getWishlistCounter();
        Assertions.assertEquals(compteurAvant + 1, apres, "Le compteur de la wishlist n'a pas augmenté !");
    }

    @And("L'icône du cœur devient active")
    public void lIcôneDuCœurDevientActive() {
        // exemple pour MacBook, tu peux le rendre dynamique si besoin
        Assertions.assertTrue(addToWishListPage.iconHeartActive("MacBook"), "L'icône du cœur n'est pas active !");
    }
    @And("Le compteur ne doit pas augmenter")
    public void leCompteurNeDoitPasAugmenter() {
        int apres = addToWishListPage.getWishlistCounter();
        Assertions.assertEquals(compteurAvant, apres, "Le compteur a augmenté alors qu'il ne devait pas !");
    }



}
