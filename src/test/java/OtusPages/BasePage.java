package OtusPages;


import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.WebDriverUtils;

import static utils.PropertyLoader.loadSystemProperty;

public class BasePage {

    protected WebDriver driver;

    protected WebDriverUtils utils;
    protected final String baseUrl = loadSystemProperty("baseUrl");
    private final String login = loadSystemProperty("login");
    private final String password = loadSystemProperty("password");

    By clickLoginLocator = By.xpath("//div[./input[@name='email']]");
    By clickPassLocator = By.xpath("//div[./input[@type='password']]");
    By inputEmailLocator = By.xpath("//input[@name='email']");
    By inputPassLocator = By.xpath("//input[@type='password']");
    By portalButton= By.cssSelector("#__PORTAL__ button");
    By enterButton= By.xpath("//button[@class = 'sc-mrx253-0 enxKCy sc-945rct-0 iOoJwQ']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.utils = new WebDriverUtils(driver);
    }

    public void open(String url){
        utils.navigateToUrl(url);
        login(login, password);
    }

    @SneakyThrows
    public void login(String username, String password) {
        utils.click(enterButton);
        utils.click(clickLoginLocator);
        utils.fillInputField(inputEmailLocator, username);
        utils.click(clickPassLocator);
        utils.fillInputField(inputPassLocator, password);
        Thread.sleep(2000);
        utils.click(portalButton);
        Thread.sleep(2000);
    }

}
