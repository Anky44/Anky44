import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class SauceDemoTest {
    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.swaglabsmobileapp");
        desiredCapabilities.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() {
        MobileElement el1 = (MobileElement) driver.findElementById("com.swaglabsmobileapp:id/testUsername");
        el1.sendKeys("standard_user");
        MobileElement el2 = (MobileElement) driver.findElementById("com.swaglabsmobileapp:id/testPassword");
        el2.sendKeys("secret_sauce");
        MobileElement el3 = (MobileElement) driver.findElementById("com.swaglabsmobileapp:id/login_button");
        el3.click();

        //Assertions and further testing steps here
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}