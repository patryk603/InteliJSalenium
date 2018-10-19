package PRE2_pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by PTeledzinski on 04.04.2018.
 */
public class HomePagePRE2 {

    final WebDriver driver;

    @FindBy(id = "_bookingportlet_WAR_lotairwaysportlet_INSTANCE_wl9jlWyYLKBg_origin_1")
    public static WebElement From;

    @FindBy(id = "_bookingportlet_WAR_lotairwaysportlet_INSTANCE_wl9jlWyYLKBg_destination_1")
    public static WebElement To;

    @FindBy(css = ".user")
    public static WebElement ListFrom;

    @FindBy(css = "#_bookingportlet_WAR_lotairwaysportlet_INSTANCE_wl9jlWyYLKBg_departingPicker > div > div > button > img")
    public static WebElement Departure;

    @FindBy(css = "a.ui-state-default")
    public static WebElement DepartureData;

    @FindBy(css = "#_bookingportlet_WAR_lotairwaysportlet_INSTANCE_wl9jlWyYLKBg_returningPicker > div > div > button")
    public static WebElement Return;

    @FindBy(css = "#ui-datepicker-div > div.ui-datepicker-group.ui-datepicker-group-first > table > tbody > tr > td > a.ui-state-default.ui-state-highlight.ui-state-active.ui-state-hover")
    public static WebElement Today;

    @FindBy(css = "#ui-datepicker-div > div.ui-datepicker-group.ui-datepicker-group-last > table > tbody > tr:nth-child(1) > td:nth-child(3) > a")
    public static WebElement FirsOfNextMounth;

    @FindBy(id = "_availabilityportlet_WAR_lotairwaysportlet_precioFormatAdult-1-EUPINKTAN-3")
    public static WebElement LotEconomySaver;

    @FindBy(css = "body > header > div > div > div.b-column.four.g-text-right.fontresizer-eight.o-header-controls.right > ul:nth-child(4) > li > a > span")
    public static WebElement Login;

    @FindBy(id = "buttonSearchFlights")
    public static WebElement FindFlights;



    public HomePagePRE2(WebDriver driver){

        this.driver = driver;
    }

}
