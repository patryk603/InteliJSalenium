package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * Created by PTeledzinski on 04.04.2018.
 */
public class HomePage {

    final WebDriver driver;

    @FindBy(xpath = "//*[@id='menu-settings']/a/span")
    public static WebElement login;

    @FindBy(id = "logo_home_link")
    public static WebElement lotlogo;

    @FindBy(css = "#mainlink-1 > a > h3")
    public static WebElement Book;

    @FindBy(css = "#mainlink-2 > a > h3")
    public static WebElement DealsAndOffers;

    @FindBy(css = "#mainlink-3 > a > h3")
    public static WebElement PrepareForTravel;

    @FindBy(css = "#mainlink-4 > a > h3")
    public static WebElement DuringForFlight;

    @FindBy(css = "#mainlink-5 > a > h3")
    public static WebElement CustomerService;

    @FindBy(css = "#menu-main > ul > li:nth-child(6) > a > h3 > span")
    public static WebElement MilesAndMore;

    @FindBy(css = "li.item.active")
    public static WebElement ZapraszamyNaPoklad;

    @FindBy(css = ".tab-title[href*=\"reservation\"]")
    public static WebElement ManageBooking;

    @FindBy(id = "lastName")
    public static WebElement LastName;

    @FindBy(id = "bookingNumber")
    public static WebElement BookingNumber;

    @FindBy(css = "#myBookingsForm > div > div.booker-column-3 > button")
    public static WebElement NextBtn;

    @FindBy(id = "select2-departureAirport-container")
    public static WebElement FromListButton;

    @FindBy(id = "select2-departureAirport-results")
    public static WebElement FromList;

    @FindBy(id = "select2-destinationAirport-results")
    public static WebElement ToListButton;

    @FindBy(id = "select2-destinationAirport-container")
    public static WebElement ToList;

    @FindBy(css = ".select2-search__field")
    public static WebElement FromToText;

    @FindBy(id = "lot")
    public static WebElement Lot;

    @FindBy(xpath = "/html//body[@id='lot']//input[@role='textbox']")
    public static WebElement ToToText;

    @FindBy(id = "departureDate")
    public static WebElement DepartureDate;

    @FindBy(id = "returnDate")
    public static WebElement ReturnDate;

    @FindBy(id = "submit-booker")
    public static WebElement Submit;

    @FindBy(id = "numOfAdults-listener")
    public static WebElement Adults;

    @FindBy(css = "div.booker-column-2 > div:nth-child(2)")
    public static WebElement TicketClass;

    @FindBy(css = ".select2-results>ul>li[id*=\"ECONO\"]")
    public static WebElement Economy;

    @FindBy(css = ".select2-results>ul>li[id*=\"PREMIUM\"]")
    public static WebElement Premium;

    @FindBy(css = ".select2-results>ul>li[id*=\"BUSINESS\"]")
    public static WebElement Business;

    @FindBy(css = "div.booker-column-2 > div:nth-child(1)")
    public static WebElement Passengers;

    @FindBy(id = "numOfAdults")
    public static WebElement NumberOfAdults;

    @FindBy(id = "numOfYouths")
    public static WebElement NumberOfYouths;

    @FindBy(id = "numOfInfants")
    public static WebElement NumberOfInfants;

    @FindBy(id = "numOfChildren")
    public static WebElement NumberOfChildrens;

    @FindBy(css = ".flight-type>.booker-label[class*=\"margin\"]")
    public static WebElement OneWayFlight;





    public HomePage(WebDriver driver){

        this.driver = driver;
    }

}
