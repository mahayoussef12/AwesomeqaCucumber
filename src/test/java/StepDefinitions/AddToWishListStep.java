package StepDefinitions;

import Pages.AddToWishListPage;
import Pages.LoginPage;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class AddToWishListStep {

    WebDriver driver= null;
    LoginPage loginPage;
    AddToWishListPage addToWishListPage;
    int compteurAvant;
    String username = System.getenv("BROWSERSTACK_USERNAME");
    String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    String URL = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";

    @Given("Je suis à home page")
    public void jeSuisSurLaPage() throws MalformedURLException {        // options spécifiques BrowserStack
        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("os", "Windows");
        bstackOptions.put("osVersion", "11");
        bstackOptions.put("projectName", "Demo Project");
        bstackOptions.put("buildName", "Cucumber Build");
        bstackOptions.put("sessionName", "Login Test");
        bstackOptions.put("debug", true);

        // capabilities W3C
        Map<String, Object> caps = new HashMap<>();
        caps.put("browserName", "Chrome");
        caps.put("browserVersion", "latest");
        caps.put("bstack:options", bstackOptions);

        driver = new RemoteWebDriver(new URL(URL), new org.openqa.selenium.remote.DesiredCapabilities(caps));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
