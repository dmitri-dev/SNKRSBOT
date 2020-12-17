import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Selector extends PageObject {
    // Options: "Triple Black", "Cosmic Clay"
    private static final String ITEM_NAME = "Armory Blue";
    private static final String releaseDate = "2020-12-15 19:23:00";
    private static final String[] sizeChoices = {"M 11", "M 10", "M 10.5", "M 12"};
    private static final String[] mensSizeChoices = {"11", "10", "10.5", "12"};

    @FindBy(xpath = "//img[contains(@alt, '"+ ITEM_NAME +"')]")
    public WebElement item;

    @FindAll({@FindBy(xpath = "//li[@data-qa='size-available']")})
    public List<WebElement> availableSizes;

    @FindBy(xpath = "//button[contains(text(), 'Buy')]")
    public WebElement buy;

    public Selector(WebDriver driver) {
        super(driver);
    }

    public void getItem() {
        Utils.wait.until(
                ExpectedConditions.elementToBeClickable(
                        this.item
                )
        );
        this.item.click();
    }

    public void awaitRelease() {
        String dateTime = LocalDateTime.now().toString();
        int t = dateTime.indexOf("T");
        String date = dateTime.substring(0, t);
        String time = dateTime.substring(t + 1, dateTime.indexOf("."));
        String dateStart = date + " " + time;
        System.out.println(dateStart);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = format.parse(dateStart);
            Date d2 = format.parse(releaseDate);
            System.out.println(releaseDate);
            long diff = d2.getTime() - d1.getTime();
            if (diff > 0) {
                Thread.sleep(diff);
                System.out.println("Slept for: " + diff / 1000 + " seconds");
                System.out.println(LocalDateTime.now());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void awaitSizes() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(
                (ExpectedCondition<Boolean>)
                        driver -> this.availableSizes.size() > 0
        );
    }

    public void setSize() {
        if (this.availableSizes.get(0).getText().contains("M")) {
            for (String choice : sizeChoices) {
                System.out.println(choice);
                for (WebElement size : availableSizes) {
                    System.out.println(size.getText());
                    if (size.getText().contains(choice)) {
                        System.out.println("found\n");
                        size.click();
                        return;
                    }
                }
            }
        } else {
            for (String choice : mensSizeChoices) {
                System.out.println(choice);
                for (WebElement availableSize : availableSizes) {
                    System.out.println(availableSize.getText());
                    if (availableSize.getText().contains(choice)) {
                        System.out.println("found\n");
                        Utils.actions.moveToElement(availableSize);
                        Utils.actions.sendKeys(Keys.TAB).build().perform();
                        Utils.actions.sendKeys(Keys.ENTER).build().perform();
                        return;
                    }
                }
            }
        }
    }

   public void buy() {
        this.buy.click();
   }

    /*
    public void verifySelectionSuccess() {
        Utils.wait.until(
                (ExpectedCondition<Boolean>)
                        driver -> this.header.isDisplayed()
        );
//        Utils.verify(
//                this.header.isDisplayed()
//        );
        System.out.println("Selection Complete\n");
    }
    */
}