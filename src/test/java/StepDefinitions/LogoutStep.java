package StepDefinitions;

import Pages.InscriptionPage;
import Pages.LoginPage;
import Pages.LogoutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LogoutStep {
    WebDriver driver;
    LogoutPage logoutPage ;
    LoginPage loginPage;
    @Given("Je suis sur la page")
    public void jeSuisSurLaPage() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.get("https://awesomeqa.com/ui/index.php?route=common/home");

        loginPage = new LoginPage(driver);
        logoutPage= new LogoutPage(driver);
    }


    @And("Je clique sur compte dans barre de navigation")
    public void jeCliqueSurCompteDansBarreDeNavigation() {
        loginPage.cliquerMonCompte();
    }

    @And("Je sélectionne connecter dans la liste déroulante")
    public void jeSélectionneConnecterDansLaListeDéroulante() {
       loginPage.selectionnerLogin();
    }

    @And("Je saisi email {string}")
    public void jeSaisiEmail(String mail) {
        loginPage.ajouteremail(mail);

    }

    @And("Je saisi mot de passe {string}")
    public void jeSaisiMotDePasse(String mdp) {
        loginPage.ajouterpassword(mdp);

    }

    @And("Je clique sur bouton de connexion")
    public void jeCliqueSurBoutonDeConnexion() {
      loginPage.clickloginbutton();
    }
    @When("Je clique sur la barre de navigation a mon compte")
    public void jeCliqueSurLaBarreDeNavigationAMonCompte() {

       logoutPage.cliquerMonCompte();
    }

    @When("Je sélectionne logout dans la liste")
    public void je_sélectionne_logout_dans_la_liste() {
        logoutPage.logout();
    }

    @And("je clique sur le boutton continuer")
    public void je_clique_sur_le_boutton_continuer() {
        logoutPage.contine();
    }

    @Then("message succes {string} s’affiche")
    public void message_succes_s_affiche(String expectedLinkText) {
        String dropdownText = logoutPage.getResult();
         expectedLinkText = "Register";
        Assertions.assertEquals(expectedLinkText,dropdownText,
                "Logout échoué : 'Register' non trouvé dans le dropdown");
        driver.quit();
    }



}
