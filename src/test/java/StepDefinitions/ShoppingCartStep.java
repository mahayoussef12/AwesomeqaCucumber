package StepDefinitions;

import Pages.LoginPage;
import Pages.ShoppingCartPage;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class ShoppingCartStep {

    WebDriver driver= null;
    LoginPage loginPage;
    ShoppingCartPage shoppingCartPage;
    int compteurAvant;

    @Given("Dans Home Page")
    public void dans_home_page() {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
      //  options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://awesomeqa.com/ui/index.php?route=common/home");
        loginPage = new LoginPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
    }

    @When("je suis connecté à la session avec email {string} et mot de passe {string}")
    public void jeSuisConnecte(String email, String password) {
        loginPage.cliquerMonCompte();
        loginPage.selectionnerLogin();
        loginPage.ajouteremail(email);
        loginPage.ajouterpassword(password);
        loginPage.clickloginbutton();
    }

    @When("Je clique sur le bouton Add to Cart de produit nommé {string}")
    public void jeCliqueSurUnProduitNomme(String productName) {
        compteurAvant = shoppingCartPage.getQuantityForProduct(productName);
        shoppingCartPage.clickAddToCartButton(productName);
    }

    @Then("le message {string} s’affiche")
    public void leMessageSaffiche(String msgAttendu) {
        String message = shoppingCartPage.getAlertMessage();
        Assertions.assertTrue(message.contains(msgAttendu),
                "Message affiché incorrect. Attendu : " + msgAttendu + " | Réel : " + message);
    }

    @And("La quantité du produit dans le panier augmente de {string}")
    public void quantiteProduitAugmente(String delta) {
        int deltaInt = Integer.parseInt(delta);
        int qtApres = shoppingCartPage.getQuantityForProduct("MacBook");
        Assertions.assertEquals(compteurAvant + deltaInt, qtApres,
                "La quantité du produit n'a pas augmenté correctement !");
    }
}
