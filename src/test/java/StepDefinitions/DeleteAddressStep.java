package StepDefinitions;

import Pages.DeleteAddressPage;
import Pages.LoginPage;
import Pages.ShoppingCartPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DeleteAddressStep {
    WebDriver driver= null;
    DeleteAddressPage addressBookPage;
    LoginPage loginPage;
    String username = System.getenv("BROWSERSTACK_USERNAME");
    String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    String URL = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";

    @Given("je suis connecté avec l'email {string} et le mot de passe {string}")
    public void jeSuisConnectéAvecLEmailEtLeMotDePasse(String email, String password) throws MalformedURLException {
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
        addressBookPage = new DeleteAddressPage(driver);
        loginPage.cliquerMonCompte();
        loginPage.selectionnerLogin();
        loginPage.ajouteremail(email);
        loginPage.ajouterpassword(password);
        loginPage.clickloginbutton();
    }


    @When("je clique sur Address Book")
    public void jeCliqueSurAddressBook() {
        addressBookPage.clickAddressBook();
    }

    @And("je supprime l'adresse de {string} en choisissant {string} l'alerte")
    public void jeSupprimeAdresseAvecConfirmation(String fullName, String action) {
        boolean confirm = action.equalsIgnoreCase("confirmer");
        addressBookPage.deleteAddressByName(fullName, confirm);
    }


    @Then("un message {string}")
    public void unMessage(String msg) {
        // On suppose que le message s'affiche dans une div.alert-success ou div.alert-danger
        String msgActuelle = addressBookPage.getAlertMessage();
        Assert.assertEquals(msg, msgActuelle);
    }

    @And("l'adresse de {string} ne devrait plus être visible")
    public void lAdresseDeNeDevraitPlusÊtreVisible(String fullName) {
        Assert.assertFalse(addressBookPage.isAddressPresent(fullName));
    }


    @Then("l'adresse de {string} devrait être visible")
    public void lAdresseDeDevraitÊtreVisible(String fullName) {
        Assert.assertTrue(addressBookPage.isAddressPresent(fullName));
    }
}
