package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class InscriptionPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//span[text()='My Account']")
    WebElement monCompteBtn;

    @FindBy(xpath = "//a[text()='Register']")
    WebElement registerOption;

    @FindBy(id = "input-firstname")
    WebElement inputPrenom;

    @FindBy(id = "input-lastname")
    WebElement inputNom;

    @FindBy(id = "input-email")
    WebElement inputEmail;

    @FindBy(id = "input-telephone")
    WebElement inputTelephone;

    @FindBy(id = "input-password")
    WebElement inputMotDePasse;

    @FindBy(id = "input-confirm")
    WebElement inputConfirmationMotDePasse;

    @FindBy(name = "agree")
    WebElement checkboxConfidentialite;

    @FindBy(css = "input[value='Continue']")
    WebElement boutonContinue;

    @FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
    WebElement messageConfirmation;

    public InscriptionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void cliquerMonCompte() {
        wait.until(ExpectedConditions.elementToBeClickable(monCompteBtn)).click();
    }

    public void selectionnerRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(registerOption)).click();
    }

    public void saisirPrenom(String prenom) {
        wait.until(ExpectedConditions.visibilityOf(inputPrenom)).sendKeys(prenom);
    }

    public void saisirNom(String nom) {
        wait.until(ExpectedConditions.visibilityOf(inputNom)).sendKeys(nom);
    }

    public void saisirEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(inputEmail)).sendKeys(email);
    }

    public void saisirTelephone(String tel) {
        wait.until(ExpectedConditions.visibilityOf(inputTelephone)).sendKeys(tel);
    }

    public void saisirMotDePasse(String mdp) {
        wait.until(ExpectedConditions.visibilityOf(inputMotDePasse)).sendKeys(mdp);
    }

    public void saisirConfirmationMotDePasse(String cmdp) {
        wait.until(ExpectedConditions.visibilityOf(inputConfirmationMotDePasse)).sendKeys(cmdp);
    }

    public void cocherConfidentialite() {
        wait.until(ExpectedConditions.elementToBeClickable(checkboxConfidentialite)).click();
    }

    public void cliquerContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(boutonContinue)).click();
    }

    public String getMessageConfirmation() {
        wait.until(ExpectedConditions.visibilityOf(messageConfirmation));
        return messageConfirmation.getText();
    }
    public WebElement getInputConfirmationMotDePasse(){
        return  messageConfirmation;
    }
}
