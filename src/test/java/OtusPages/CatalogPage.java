package OtusPages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogPage extends BasePage {

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public int getCountOfCards(){
        return driver.findElements(By.xpath("//div[@class = 'sc-18q05a6-1 bwGwUO']//a")).size();
    }

    public void openCourseCard(int courseNumber){
        utils.click(By.xpath(String.format("(//div[@class = 'sc-18q05a6-1 bwGwUO']//a)[%s]", courseNumber)));
    }
}
