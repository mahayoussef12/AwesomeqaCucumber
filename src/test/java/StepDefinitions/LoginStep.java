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
import java.util.HashMap;
import java.util.Map;

public class LoginStep {
    WebDriver driver;
    LoginPage loginPage;
    String username = System.getenv("BROWSERSTACK_USERNAME");
    String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    String URL = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";

    @Given("Je suis sur la page d'accueil du site")
    public void je_suis_sur_la_page_d_accueil() throws Exception {

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

