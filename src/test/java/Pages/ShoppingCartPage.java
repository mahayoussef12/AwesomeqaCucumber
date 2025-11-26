package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ShoppingCartPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "alert-success")
    WebElement alertMessage;

    @FindBy(className = "product-thumb")
    List<WebElement> productList;

    @FindBy(css = "ul.list-inline a[title='Shopping Cart']")
    WebElement shoppingCartList;

    @FindBy(className = "table-responsive")
    WebElement cartTable;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }



    public void clickAddToCartButton(String productName) {
        driver.get("https://awesomeqa.com/ui/index.php?route=common/home");

        for (WebElement product : productList) {
            if (product.getText().contains(productName)) {
                WebElement addButton = product.findElement( By.xpath("//button[.//span[text()='Add to Cart']]"));
                wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
                return;
            }
        }

        throw new RuntimeException("Produit non trouvé : " + productName);
    }


    public String getAlertMessage() {
        return wait.until(ExpectedConditions.visibilityOf(alertMessage)).getText().trim();
    }




    public int getQuantityForProduct(String productName) {
            shoppingCartList.click();
            wait.until(ExpectedConditions.visibilityOf(cartTable));

            for (WebElement row : cartTable.findElements(By.tagName("tr"))) {
                if (row.getText().contains(productName)) {
                    WebElement quantityInput = row.findElement(By.tagName("input"));
                    return Integer.parseInt(quantityInput.getAttribute("value"));
                }
            }
            throw new RuntimeException("Produit non trouvé");

    }



}
