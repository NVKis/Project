package Driver;

import lombok.Getter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static utils.PropertyLoader.loadSystemProperty;

public abstract class WebDriverSetup {
    private static final String BROWSER_NAME = loadSystemProperty("browser", "chrome");
    private static final String SELENOID_URL = "http://193.104.57.173:4444/wd/hub";

    @Getter
    protected static RemoteWebDriver driver;

    @BeforeAll
    public static void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("enableVideo", false);

        capabilities.setCapability("selenoid:options", selenoidOptions);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

       // driver = new ChromeDriver();

        URL selenoidUrl;
        try {
            selenoidUrl = new URL(SELENOID_URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenoid URL", e);
        }

        driver = new RemoteWebDriver(selenoidUrl, capabilities);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
