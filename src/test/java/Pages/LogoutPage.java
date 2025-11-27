package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//span[text()='My Account']")
    WebElement monCompteBtn;

    @FindBy(xpath = "//a[text()='Logout']")
    WebElement logout;

    @FindBy(css = ".btn.btn-primary")
    WebElement contine;

    @FindBy(xpath = "//a[text()='Register']")
    WebElement dropdownMenu;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(60));
    }
    public void cliquerMonCompte() {
        wait.until(ExpectedConditions.elementToBeClickable(monCompteBtn)).click();
    }
    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
    }
    public void contine() {
        wait.until(ExpectedConditions.elementToBeClickable(contine)).click();
    }

    public String getResult() {
        wait.until(ExpectedConditions.visibilityOf(monCompteBtn)).click();
        return dropdownMenu.getText();
    }
}
