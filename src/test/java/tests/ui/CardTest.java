package tests.ui;

import Constants.Courses;
import Constants.Events;
import Constants.EventsType;
import Driver.WebDriverSetup;
import OtusPages.*;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest extends WebDriverSetup {

    HomePage homePage = new HomePage(driver);
    CatalogPage catalogPage = new CatalogPage(driver);
    CourseCardPage courseCardPage = new CourseCardPage(driver);
    EventsCalendarPage eventsCalendarPage = new EventsCalendarPage(driver);

    @Test
    @Description("Проверка на количество курсов в разделе 'Тестирование'")
    public void testExampleElementText() {
        homePage.selectCourse(Courses.TESTING_COURSE);
        var countOfCards = catalogPage.getCountOfCards();
        Assertions.assertEquals(countOfCards, 10);
    }

    @Test
    @Description("Проверка наполнения карточки курса")
    public void checkCourseCardFilling() {
        homePage.selectCourse(Courses.TESTING_COURSE);
        catalogPage.openCourseCard(1);
        var courseName = courseCardPage.getCourseName();
        var courseDescription = courseCardPage.getCourseDescription();
        courseCardPage.checkCourseDetails();
        Assertions.assertFalse(courseName.isEmpty());
        Assertions.assertFalse(courseDescription.isEmpty());
    }

    @Test
    @Description("Валидация дат предстоящих мероприятий")
    public void validateEvents(){
        homePage.selectEvent(Events.EVENTS_CALENDAR);
        var eventsCount = eventsCalendarPage.getEventsCount();
        Assertions.assertTrue(eventsCount>=1);
        eventsCalendarPage.checkEventsDate();
    }

    @Test
    @Description("Проверка сортировки мероприятий по типу")
    public void checkEvents(){
        homePage.selectEvent(Events.EVENTS_CALENDAR);
        eventsCalendarPage.chooseTypeOfEvent(EventsType.OPEN_WEBINAR_EVENT);
        eventsCalendarPage.checkEventsByType(EventsType.OPEN_WEBINAR_EVENT);
    }


}
