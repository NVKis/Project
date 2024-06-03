package OtusPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class HomePage extends BasePage{
    private static final Logger logger = Logger.getLogger(HomePage.class.getName());

    public HomePage(WebDriver driver) {
        super(driver);
        open(baseUrl);
    }

    public void selectCourse(String courseName){
        utils.navigateToEl(By.xpath("(//span[@class = 'sc-1youhxc-1 cMNIlZ'])[1]"));
        utils.click(By.xpath(String.format("//div[@class = 'sc-v5wu4-2 sc-ig0m9y-0 gXAYGA fRJDe']//a[contains(text(), '%s')]", courseName)));
        logger.info("Selected course : " + courseName);
    }

    public void selectEvent(String eventName){
        utils.navigateToEl(By.xpath("(//span[@class = 'sc-1youhxc-1 cMNIlZ'])[1]"));
        utils.click(By.xpath(String.format("//div[@class = 'sc-v5wu4-2 hKGuLv']//a[contains(text(), '%s')]", eventName)));
        logger.info("Selected event : " + eventName);
    }
}
