import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;

public class SauceDemoTestSuite {
    private WebDriver driver;
    private WebDriverWait wait;

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 20);
    }

    public void loginTest() {
        try {
            driver.get("https://www.saucedemo.com/");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys("standard_user");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("secret_sauce");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button"))).click();
            
            boolean loginSuccess = wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));
            System.out.println(loginSuccess ? "Test Passed - Login successful" : "Test Failed - Login unsuccessful");
        } catch (Exception e) {
            System.out.println("Exception occurred during login test: " + e.getMessage());
        }
    }

    public void shoppingTest() {
        try {
            loginTest();
            wait.until(ExpectedConditions.elementToBeClickable(By.className("inventory_item"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack"))).click();
            driver.findElement(By.className("shopping_cart_link")).click();
            WebElement checkout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout")));
            checkout.click();
            
            // Filling in checkout information
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("John");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name"))).sendKeys("Doe");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code"))).sendKeys("12345");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("continue"))).click();
            
            // Verify total cost and finish checkout
            wait.until(ExpectedConditions.elementToBeClickable(By.id("finish"))).click();
            boolean orderSuccess = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='THANK YOU FOR YOUR ORDER']"))).isDisplayed();
            System.out.println(orderSuccess ? "Test Passed - Order successful" : "Test Failed - Order unsuccessful");
            
            if (orderSuccess) {
                takeScreenshot("order_success");
            }
        } catch (Exception e) {
            System.out.println("Exception occurred during shopping test: " + e.getMessage());
        }
    }

    private void takeScreenshot(String fileName) {
        // Code to capture screenshot
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void main(String[] args) {
        SauceDemoTestSuite testSuite = new SauceDemoTestSuite();
        testSuite.setUp();
        testSuite.loginTest();
        testSuite.shoppingTest();
        testSuite.tearDown();
    }
}