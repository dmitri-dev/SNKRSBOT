import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Checkout extends PageObject {
    private static final String CVV = "878";

    private static final Navigate Navigate = Utils.Navigate;

//    @FindBy(xpath = "//input[@data-shortname='cvv']")
//    private WebElement cvv;

    @FindBy(xpath = "//button[contains(text(), 'Delete')]")
    public WebElement deleteButton;

//    @FindBy(xpath = "//button[contains(text(), 'Order')]")
//    public WebElement placeOrder;

    @FindBy(xpath = "//div[contains(text(), 'Pending')]")
    private WebElement confirmation;

    public Checkout(WebDriver driver) {
        super(driver);
    }

//    public void enterCVV() {
//        Navigate.sendKeys(new String[] {"TAB", "TAB", "ENTER", "TAB"});
//        Utils.actions.sendKeys(CVV).build().perform();
//    }

    public void enterCVV() {
        Utils.wait.until(
                ExpectedConditions.elementToBeClickable(
                        deleteButton
                )
        );
        Utils.actions.moveToElement(deleteButton).build().perform();
        Utils.actions.sendKeys(Keys.TAB).build().perform();
        Utils.actions.sendKeys(CVV).build().perform();
    }

    public void placeOrder() {
        Utils.actions.sendKeys(Keys.TAB).build().perform();
        Utils.actions.sendKeys(Keys.ENTER).build().perform();
    }

    public void verifyCheckoutSuccess() {
        Utils.wait.until(
                (ExpectedCondition<Boolean>)
                        driver -> this.confirmation.isDisplayed()
        );
        System.out.println("Checkout Complete");
    }
}