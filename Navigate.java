import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Navigate extends PageObject {
    @FindBy(xpath = "//div[contains(text(), 'In Stock')]")
    public WebElement inStock;

    @FindBy(xpath = "//div[contains(text(), 'Upcoming')]")
    public WebElement upcoming;

    @FindBy(xpath = "//span[contains(text(), '1')]")
    public WebElement cart;

    public Navigate(WebDriver driver) {
        super(driver);
    }

    public void sendKeys(String[] keys) {
        for (String key : keys) {
            Utils.actions.sendKeys(Keys.valueOf(key)).build().perform();
        }
    }

    public void toInStock() {
        Utils.wait.until(
                (ExpectedCondition<Boolean>)
                        driver -> this.inStock.isDisplayed()
        );
        this.inStock.click();
    }

    public void toUpcoming() {
        Utils.wait.until(
                ExpectedConditions.elementToBeClickable(
                        this.upcoming
                )
        );
        this.upcoming.click();
    }

    public void toCart() {
        Utils.wait.until(
                (ExpectedCondition<Boolean>)
                        driver -> this.cart.isDisplayed()
        );
        this.cart.click();
    }
}
