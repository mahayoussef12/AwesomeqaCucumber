package StepDefinitions;

import Pages.InscriptionPage;
import io.cucumber.java.en.*;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class InscriptionStep {

    WebDriver driver= null;
    InscriptionPage inscriptionPage;
    String username = System.getenv("BROWSERSTACK_USERNAME");
    String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    String URL = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";
    @Given("Je suis sur la page d'accueil")
    public void je_suis_sur_la_page_d_accueil() throws MalformedURLException {
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
        inscriptionPage = new InscriptionPage(driver);
    }

    @When("Je clique sur mon compte dans la barre de navigation")
    public void je_clique_sur_dans_la_barre_de_navigation() {
        inscriptionPage.cliquerMonCompte();
    }

    @And("Je sélectionne s’inscrire dans la liste déroulante")
    public void je_selectionne_dans_la_liste_deroulante() {
        inscriptionPage.selectionnerRegister();
    }

    @And("Je saisis le prénom {string}")
    public void je_saisis_le_prenom(String prenom) {
        inscriptionPage.saisirPrenom(prenom);
    }

    @And("Je saisis le nom {string}")
    public void je_saisis_le_nom(String nom) {
        inscriptionPage.saisirNom(nom);
    }

    @And("Je saisis l'email {string}")
    public void je_saisis_l_email(String email) {
        inscriptionPage.saisirEmail(email);
    }

    @And("Je saisis le numéro de téléphone {string}")
    public void je_saisis_le_numero_de_telephone(String tel) {
        inscriptionPage.saisirTelephone(tel);
    }

    @And("Je saisis le mot de passe {string}")
    public void je_saisis_le_mot_de_passe(String mdp) {
        inscriptionPage.saisirMotDePasse(mdp);
    }

    @And("Je saisis la confirmation du mot de passe {string}")
    public void je_saisis_la_confirmation_du_mot_de_passe(String cmdp) {
        inscriptionPage.saisirConfirmationMotDePasse(cmdp);
    }

    @And("Je coche la case de la politique de confidentialité")
    public void je_coche_la_case_de_la_politique_de_confidentialite() {
        inscriptionPage.cocherConfidentialite();
    }

    @And("Je clique sur le bouton continuer")
    public void je_clique_sur_le_bouton() {
        inscriptionPage.cliquerContinue();
    }

    @Then("Le message {string} s’affiche")
    public void le_message_saffiche(String msgAttendu) {

        WebElement msgElement = inscriptionPage.getInputConfirmationMotDePasse();

        if (msgElement.isDisplayed()) {
            String msgReel = inscriptionPage.getMessageConfirmation().trim();
            System.out.println("Inscription réussie : " + msgReel);

            Assert.assertEquals(
                    msgAttendu,
                    msgReel,
                    "Le message de succès ne correspond pas!"
            );
        } else {

            System.out.println("Inscription échouée : message non visible");

            Assert.assertNotEquals(
                    msgAttendu,
                    "Your Account Has Been Created!",
                    "Ce test attendait un échec mais le message correspond !"
            );
        }

        driver.quit();
    }




}

