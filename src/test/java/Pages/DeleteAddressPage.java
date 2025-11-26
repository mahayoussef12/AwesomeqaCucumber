package Pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DeleteAddressPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "a.list-group-item[href*='route=account/address']")
    WebElement addressBookLink;

    @FindBy(css = "table.table.table-bordered.table-hover tbody")
    WebElement addressTable;

    @FindBy(css = ".alert.alert-warning")
    WebElement alertMessage;

    public DeleteAddressPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickAddressBook() {
        wait.until(ExpectedConditions.elementToBeClickable(addressBookLink)).click();
    }

    public void deleteAddressByName(String fullName, boolean confirmAlert) {
        wait.until(ExpectedConditions.visibilityOf(addressTable));
        List<WebElement> rows = addressTable.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            if (row.getText().contains(fullName)) {
                WebElement deleteButton = row.findElement(By.cssSelector("a.btn.btn-danger"));
                deleteButton.click();

                // Gestion de l'alerte
                try {
                    Alert alert = driver.switchTo().alert();
                    if (confirmAlert) {
                        alert.accept(); // Confirme la suppression
                    } else {
                        alert.dismiss(); // Annule la suppression
                    }
                } catch (NoAlertPresentException e) {
                    // Aucune alerte affichée
                }

                break; // On sort après avoir trouvé l'adresse
            }
        }
    }

    public String getAlertMessage() {
        wait.until(ExpectedConditions.visibilityOf(alertMessage));
        return alertMessage.getText().trim();
    }

    public boolean isAddressPresent(String fullName) {
        wait.until(ExpectedConditions.visibilityOf(addressTable));
        List<WebElement> rows = addressTable.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            if (row.getText().contains(fullName)) {
                return true;
            }
        }
        return false;
    }




}
