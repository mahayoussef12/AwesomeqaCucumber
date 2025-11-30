package StepDefinitions;

import Pages.LoginPage;
import Pages.ShoppingCartPage;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCartStep {

    WebDriver driver;
    LoginPage loginPage;
    ShoppingCartPage shoppingCartPage;
    int compteurAvant;
    String username = System.getenv("BROWSERSTACK_USERNAME");
    String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    String URL = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";
    @Given("Dans Home Page")
    public void dans_home_page() throws MalformedURLException {
        // options spécifiques BrowserStack
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
        Assert.assertTrue(message.contains(msgAttendu),
                "Message affiché incorrect. Attendu : " + msgAttendu + " | Réel : " + message);
    }

    @And("La quantité du produit dans le panier augmente de {string}")
    public void quantiteProduitAugmente(String delta) {
        int deltaInt = Integer.parseInt(delta);
        int qtApres = shoppingCartPage.getQuantityForProduct("MacBook");
        Assert.assertEquals(compteurAvant + deltaInt, qtApres,
                "La quantité du produit n'a pas augmenté correctement !");
    }
}
