package utils;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebDriverUtils {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(WebDriverUtils.class.getName());

    public WebDriverUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Установка таймаута ожидания 10 секунд
    }

    public void navigateToUrl(String url) {
        logger.log(Level.INFO, "Navigating to URL: " + url);
        driver.get(url);
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void waitForElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void scrollIntoView(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public void navigateToEl(By mainMenuItem) {
        Actions actions = new Actions(driver);
        WebElement mainMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(mainMenuItem));
        actions.moveToElement(mainMenu).perform();
    }

    public void fillInputField(By locator, String text) {
        WebElement inputField = driver.findElement(locator);
        inputField.clear(); // Очищаем поле перед вводом нового значения
        inputField.sendKeys(text); // Заполняем поле переданным текстом
    }

    public void waitForElementPresenceAndAssert(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        Assertions.assertTrue(element.isDisplayed(), "Element with locator " + locator + " is not displayed.");
    }
}
