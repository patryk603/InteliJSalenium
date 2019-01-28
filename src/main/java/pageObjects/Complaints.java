package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * Created by PTeledzinski on 25.01.2019.
 */
public class Complaints {

    final WebDriver driver;

    @FindBy(id = "name")
    public static WebElement Name;

    @FindBy(id = "surname")
    public static WebElement Surname;

    @FindBy(css = "#country--span-select-one-1")
    public static WebElement Country;

    @FindBy(css = "country--ul-select-one-2")
    public static WebElement CountryList;

    @FindBy(id = "city")
    public static WebElement City;

    @FindBy(id = "zipCode")
    public static WebElement ZipCode;

    @FindBy(id = "phoneNumber")
    public static WebElement PhoneNumber;

    @FindBy(id = "street")
    public static WebElement Street;

    @FindBy(id = "number")
    public static WebElement StreetNumber;

    @FindBy(id = "email")
    public static WebElement Email;

    @FindBy(id = "bookingNumber")
    public static WebElement BookingNumber;

    @FindBy(id = "ticketNumber")
    public static WebElement TicketNumber;

    @FindBy(id = "flightNumber")
    public static WebElement FlightNumber;

    @FindBy(id = "route")
    public static WebElement Route;

    @FindBy(id = "flightDate--input-datepicker-1")
    public static WebElement FlightDate;

    @FindBy(css = "#ui-datepicker-div > table > tbody > tr > td:not([class*=\"disabled\"])")
    public static WebElement FlightDatePicker;

    @FindBy(id = "opinionComplaintConcern--span-select-one-1")
    public static WebElement Opinion;

    @FindBy(css = "#opinionComplaintConcern--ul-select-one-2 > li")
    public static WebElement OpinionList;

    @FindBy(id = "message")
    public static WebElement Message;

    @FindBy(id = "sendCopyToCustomer--span-checkbox-1")
    public static WebElement SendCopyToCustomer;

    @FindBy(id = "consent--span-checkbox-1")
    public static WebElement Rodo;













    public Complaints(WebDriver driver){

        this.driver = driver;
    }

}
