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

import java.time.Duration;

public class DeleteAddressStep {
    WebDriver driver;
    DeleteAddressPage addressBookPage;
    LoginPage loginPage;


    @Given("je suis connecté avec l'email {string} et le mot de passe {string}")
    public void jeSuisConnectéAvecLEmailEtLeMotDePasse(String email, String password) {
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
