package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    @FindBy(xpath = "//span[text()='My Account']")
    WebElement monCompteBtn;

    @FindBy(xpath = "//a[text()='Login']")
    WebElement LoginOption;

    @FindBy(name = "email")
    WebElement email;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(xpath = "//*[@id='content']/div/div[2]/div/form/input")
    WebElement loginbutton;


    @FindBy(css = ".alert.alert-danger.alert-dismissible")
    WebElement alert;

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void cliquerMonCompte() {
        wait.until(ExpectedConditions.elementToBeClickable(monCompteBtn)).click();
    }

    public void selectionnerLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(LoginOption)).click();
    }

    public void ajouteremail(String mail) {
        wait.until(ExpectedConditions.visibilityOf(email)).clear();
        email.sendKeys(mail);
    }

    public void ajouterpassword(String mdp) {
        wait.until(ExpectedConditions.visibilityOf(password)).clear();
        password.sendKeys(mdp);
    }

    public void clickloginbutton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginbutton)).click();
    }

    public String getmessageerror() {
        wait.until(ExpectedConditions.visibilityOf(alert));
        return alert.getText();
    }

}
