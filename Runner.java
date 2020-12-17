import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Runner {
    private static final WebDriver driver = Utils.driver;
    private static final Navigate Navigate = Utils.Navigate;

    @BeforeSuite
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "SNKRS")
    public static void run() throws InterruptedException {
        driver.get(Utils.BASE_URL);
        driver.manage().window().maximize();

//        login();

        select();

        checkout();

//        cart();

        Thread.sleep(10000);
    }

    public static void login() throws InterruptedException {
        Login login = new Login(driver);
        login.login();
        login.verifyLoginSuccess();
    }

    public static void select() throws InterruptedException {
        Navigate.toUpcoming();
        Selector selector = new Selector(driver);
        selector.getItem();
        selector.awaitRelease();
        selector.awaitSizes();
        selector.setSize();
        selector.buy();
//        selector.verifySelectionSuccess();
    }

    public static void checkout() {
        Checkout checkout = new Checkout(driver);
        checkout.enterCVV();
        checkout.placeOrder();
        checkout.verifyCheckoutSuccess();
    }

    public static void cart() {
        Cart cart = new Cart(driver);
        Navigate.toCart();
        cart.empty();
    }

    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}