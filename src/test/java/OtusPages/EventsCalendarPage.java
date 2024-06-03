package OtusPages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DateHelper;

public class EventsCalendarPage extends BasePage{

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM", Locale.getDefault()); // Определяем формат даты

    public EventsCalendarPage(WebDriver driver) {
        super(driver);
    }

    public int getEventsCount(){
        return driver.findElements(By.xpath("//div[@class = 'dod_new-events__list js-dod_new_events']//a")).size();
    }

    public void checkEventsDate() {
        LocalDate today = LocalDate.now();

        List<WebElement> eventDates = driver.findElements(By.xpath(
                "//div[@class='dod_new-events__list js-dod_new_events']//a//span[@class='dod_new-event__icon dod_new-event__calendar-icon']/following-sibling::span[1]"));

        for (WebElement event : eventDates) {
            String dateStr = event.getText();
            LocalDate date = getEventDate(dateStr);
            Assertions.assertTrue(date.isAfter(today), "Дата должна быть больше текущей");
        }
    }

    private LocalDate getEventDate(String dateStr) {
        try {
            return DateHelper.getDate(dateStr);
        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка обработки даты: " + dateStr, e);
        }
    }

    public void chooseTypeOfEvent(String typeOfEvent){
        utils.click(By.xpath("(//span[@class = 'dod_new-events-dropdown__input-selected'])[1]"));
        utils.click(By.xpath(String.format("(//div[@class = 'dod_new-events-dropdown__list js-dod_new_events-dropdown'])[1]//a[@title = '%s']",
                typeOfEvent)));
    }

    public void checkEventsByType(String typeOfEvent){
        var events = driver.findElements(By.xpath("//div[@class = 'dod_new-events__list js-dod_new_events']//div[@class = 'dod_new-type__text']"));
        for (WebElement event : events) {
            String eventStr = event.getText();
            Assertions.assertEquals(typeOfEvent, eventStr);
        }
    }
}
