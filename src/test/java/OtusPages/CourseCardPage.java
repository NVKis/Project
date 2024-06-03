package OtusPages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DateHelper;

public class CourseCardPage extends BasePage{
    public CourseCardPage(WebDriver driver) {
        super(driver);
    }

    public String getCourseName(){
        return driver.findElement(By.xpath("//h1[@class = 'sc-1og4wiw-0 sc-s2pydo-1 iLVLDh diGrSa']")).getText();
    }

    public String getCourseDescription(){
        return driver.findElement(By.xpath("(//div[@class = 'sc-1og4wiw-0 sc-pyhrzd-0 gcChXs jpjhPZ']//h2)[1]")).getText();
    }

    public void checkCourseDetails(){
        var details = driver.findElements(By.xpath("//p[@class = 'sc-1og4wiw-0 sc-3cb1l3-0 gcChXs dgWykw']"));
        for (WebElement detail : details){
            String text = detail.getText();
            var sd = DateHelper.getDate(text);
            Assertions.assertFalse(text.isEmpty(), "Text should not be empty");
        }
    }
}
