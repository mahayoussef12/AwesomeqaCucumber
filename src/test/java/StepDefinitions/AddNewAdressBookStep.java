package StepDefinitions;
import Pages.AddNewAdressBookPage;
import Pages.LoginPage;
import Pages.ShoppingCartPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class AddNewAdressBookStep {
    WebDriver driver;
    AddNewAdressBookPage addressPage = new AddNewAdressBookPage(driver);
    LoginPage loginPage;
    @Given("je suis sur la Home Page")
    public void jeSuisSurLaHomePage() {
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
        addressPage = new AddNewAdressBookPage(driver);
    }

    @When("je me connecte à la session avec l'email {string} et le mot de passe {string}")
    public void jeMeConnecteALaSession(String email, String password) {
        loginPage.cliquerMonCompte();
        loginPage.selectionnerLogin();
        loginPage.ajouteremail(email);
        loginPage.ajouterpassword(password);
        loginPage.clickloginbutton();
    }

    @When("je clique sur le bouton {string}")
    public void jeCliqueSurLeBouton(String buttonName) {
        switch (buttonName) {
            case "Address Book":
                addressPage.clickAddressBook();
                break;
            case "New Address":
                addressPage.clickNewAddress();
                break;
            case "Continue":
                addressPage.clickContinue();
                break;
            default:
                throw new IllegalArgumentException("Bouton inconnu : " + buttonName);
        }
    }

    @And("je saisis le prénom {string}")
    public void jeSaisisLePrenom(String firstname) {
        addressPage.enterFirstName(firstname);
    }

    @And("je saisis le nom {string}")
    public void jeSaisisLeNom(String lastname) {
        addressPage.enterLastName(lastname);
    }

    @And("je saisis l'adresse 1 {string}")
    public void jeSaisisLAdresse1(String address1) {
        addressPage.enterAddress1(address1);
    }

    @And("je saisis la ville {string}")
    public void jeSaisisLaVille(String city) {
        addressPage.enterCity(city);
    }

    @And("je saisis le code postal {string}")
    public void jeSaisisLeCodePostal(String postcode) {
        addressPage.enterPostcode(postcode);
    }

    @And("je choisis le pays {string}")
    public void jeChoisisLePays(String country) {
        addressPage.selectCountry(country);
    }

    @And("je choisis la région {string}")
    public void jeChoisisLaRegion(String region) {
        addressPage.selectRegion(region);
    }

    @And("je définis cette adresse comme adresse par défaut {string}")
    public void jeDefinisDefaultAddress(String choice) {
        addressPage.setDefaultAddress(choice);
    }

    @Then("je devrais voir {string}")
    public void jeDevraisVoir(String expectedMessage) {

        // On vérifie success ou error
        String successMsg = addressPage.getSuccessMessage();
        String errorMsg = addressPage.getErrorMessage();

        if (!successMsg.isEmpty()) {
            Assert.assertTrue("Message attendu non trouvé",
                    successMsg.contains(expectedMessage));
            driver.quit();
        } else if (!errorMsg.isEmpty()) {
            Assert.assertTrue("Message attendu non trouvé",
                    errorMsg.contains(expectedMessage));
            driver.quit();

        } else {
            Assert.fail("Aucun message trouvé sur la page");
            driver.quit();

        }
    }
}
