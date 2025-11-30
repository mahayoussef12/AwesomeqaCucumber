package StepDefinitions;

import Pages.LoginPage;
import Pages.LogoutPage;
import dev.failsafe.internal.util.Assert;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

public class LoginStep {
    WebDriver driver;
    LoginPage loginPage;
    public static final String USERNAME = System.getenv("youssefmaha_1CLco9");
    public static final String ACCESS_KEY = System.getenv("ExKy6xekvkN9SCpFLrpe");
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";


    @Given("Je suis sur la page d'accueil du site")
    public void je_suis_sur_la_page_d_accueil() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "80");
        caps.setCapability("build", "1.0");
        caps.setCapability("browserstack.debug", "true");
        caps.setCapability("project", "Browserstack Demo");
        caps.setCapability("name", "BS Test");
        driver = new RemoteWebDriver(new URL(URL), caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://awesomeqa.com/ui/index.php?route=common/home");
        loginPage = new LoginPage(driver);

    }

    @When("Je clique sur compte dans la barre de navigation")
    public void jeCliqueSurCompteDansLaBarreDeNavigation() {
        loginPage.cliquerMonCompte();

    }

    @And("Je sélectionne se connecter dans la liste déroulante")
    public void jeSélectionneSeConnecterDansLaListeDéroulante() {
        loginPage.selectionnerLogin();
    }

    @And("Je saisi l'email {string}")
    public void jeSaisiLEmail(String email) {
        loginPage.ajouteremail(email);
    }

    @And("Je saisi le mot de passe {string}")
    public void jeSaisiLeMotDePasse(String mdp) {
        loginPage.ajouterpassword(mdp);
    }
    @And("Je saisis un mot de passe incorrect {int} fois")
    public void je_saisis_un_mot_de_passe_incorrect_fois(Integer tentatives) {
        for (int i = 1; i <= tentatives; i++) {
            try {
                loginPage.ajouteremail("mayouss30@gmail.com");
                loginPage.ajouterpassword("Mauvais" + i);
                loginPage.clickloginbutton();
                String msg = loginPage.getmessageerror();
                System.out.println("Tentative " + i + " : " + msg);
            } catch (Exception e) {
                System.err.println(" Erreur pendant la tentative " + i + " : " + e.getMessage());
            }
        }
    }

    @And("Je clique sur le bouton de connexion")
    public void jeCliqueSurLeBoutonDeConnexion() {
        loginPage.clickloginbutton();
    }

    @Then("message {string} s’affiche")
    public void messageSAffiche(String msgAttendu) {

        if (msgAttendu.equals("My Account")) {
            Assertions.assertTrue(
                    driver.getPageSource().contains("My Account")

            );
        }
        else {
            String message = loginPage.getmessageerror();
            Assertions.assertEquals(
                    msgAttendu,message);
        }
        driver.quit();
    }

    }

