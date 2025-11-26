package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddNewAdressBookPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//a[contains(text(),'Address Book')]")
    WebElement addressBookButton;

    @FindBy(xpath = "//a[contains(text(),'New Address')]")
    WebElement newAddressButton;

    @FindBy(id = "input-firstname")
    WebElement firstNameInput;

    @FindBy(id = "input-lastname")
    WebElement lastNameInput;

    @FindBy(id = "input-address-1")
    WebElement address1Input;

    @FindBy(id = "input-city")
    WebElement cityInput;

    @FindBy(id = "input-postcode")
    WebElement postcodeInput;

    @FindBy(id = "input-country")
    WebElement countryDropdown;

    @FindBy(id = "input-zone")
    WebElement regionDropdown;

    @FindBy(css = "input[name='default'][value='1']")
    WebElement defaultAddressYes;

    @FindBy(css = "input[name='default'][value='0']")
    WebElement defaultAddressNo;

    @FindBy(css = ".btn.btn-primary")
    WebElement continueButton;

    // Message de succès (si disponible sur votre application)
    @FindBy(css = ".alert-success")
    WebElement successMessage;

    // Message d'erreur général
    @FindBy(css = ".alert-danger")
    WebElement errorMessage;

    public AddNewAdressBookPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickAddressBook() {
        wait.until(ExpectedConditions.elementToBeClickable(addressBookButton)).click();
    }

    public void clickNewAddress() {
        wait.until(ExpectedConditions.elementToBeClickable(newAddressButton)).click();
    }

    public void enterFirstName(String firstname) {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput)).clear();
        firstNameInput.sendKeys(firstname);
    }

    public void enterLastName(String lastname) {
        wait.until(ExpectedConditions.visibilityOf(lastNameInput)).clear();
        lastNameInput.sendKeys(lastname);
    }

    public void enterAddress1(String address) {
        wait.until(ExpectedConditions.visibilityOf(address1Input)).clear();
        address1Input.sendKeys(address);
    }

    public void enterCity(String city) {
        wait.until(ExpectedConditions.visibilityOf(cityInput)).clear();
        cityInput.sendKeys(city);
    }

    public void enterPostcode(String postcode) {
        wait.until(ExpectedConditions.visibilityOf(postcodeInput)).clear();
        postcodeInput.sendKeys(postcode);
    }

    public void selectCountry(String country) {
        wait.until(ExpectedConditions.visibilityOf(countryDropdown));
        new Select(countryDropdown).selectByVisibleText(country);
    }

    public void selectRegion(String region) {
        wait.until(ExpectedConditions.visibilityOf(regionDropdown));
        new Select(regionDropdown).selectByVisibleText(region);
    }

    public void setDefaultAddress(String choice) {
        if (choice.equalsIgnoreCase("Yes")) {
            wait.until(ExpectedConditions.elementToBeClickable(defaultAddressYes)).click();
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(defaultAddressNo)).click();
        }
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }


    // Récupérer message de succès
    public String getSuccessMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(successMessage)).getText();
        } catch (Exception e) {
            return "";
        }
    }

    // Récupérer message d’erreur
    public String getErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
