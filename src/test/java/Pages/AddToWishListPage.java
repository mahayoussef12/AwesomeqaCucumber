package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddToWishListPage {

    WebDriver driver;
    WebDriverWait wait;


    @FindBy(css = ".alert.alert-success.alert-dismissible")
     WebElement successMessage;


    @FindBy(id = "wishlist-total")
     WebElement wishlistCounter;


    @FindBy(className = "product-thumb")
   List<WebElement> products;



    public AddToWishListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void clickAddToWishListButton(String productName) {
        for (WebElement product : products) {

            WebElement title = product.findElement(By.tagName("h4")).findElement(By.tagName("a"));
            if (title.getText().equalsIgnoreCase(productName)) {
                WebElement heartIcon = product.findElement(By.className("fa-heart"));
                wait.until(ExpectedConditions.elementToBeClickable(heartIcon)).click();
                break;
            }
        }
    }



    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successMessage.getText().trim();
    }



    public int getWishlistCounter() {
        wait.until(ExpectedConditions.visibilityOf(wishlistCounter));
        return Integer.parseInt(wishlistCounter.getText().replaceAll("\\D", ""));
    }


    public boolean iconHeartActive(String productName) {
        for (WebElement product : products) {
            WebElement title = product.findElement(By.tagName("h4")).findElement(By.tagName("a"));
            if (title.getText().equalsIgnoreCase(productName)) {
                WebElement heartIcon = product.findElement(By.className("fa-heart"));
                wait.until(ExpectedConditions.visibilityOf(heartIcon));
                heartIcon.click();
                wait.until(ExpectedConditions.visibilityOf(heartIcon));
                return heartIcon.isDisplayed();
            }
        }
        return false;
    }
}
