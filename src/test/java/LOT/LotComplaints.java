package LOT;

import DDT.ExcelDataConfig;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LotComplaints {
    public static WebDriver driver;
    private String baseUrl;

    //All Static Data
    String testData = "Test";
    String surname = "Test";
    String email = "a.musial@lot.pl";
    String phone = "532752626";
    String city = "Warsaw";
    String zipcode = "02-444";
    String street = "Obrony robotników";
    String flightNr = "LO281";
    String message = "To jest wiadomość wygenerowana automatycznie przez skrypt testujący Selenium";
    String bookingNumber = "XYZ111";
    String ticketNumber = "0801234567890";


    @BeforeTest(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
        baseUrl = "https://www.lot.com/pl/pl/reklamacje-pasazerskie-po-podrozy";
        PageFactory.initElements(driver, HomePage.class);
        PageFactory.initElements(driver, Complaints.class);
    }


    @Test(groups=("BuyTickets"))
    public void Complaints() throws Exception {

        open(baseUrl);

        //TEST START

        //JSESSION ID
        Cookie cookie= driver.manage().getCookieNamed("JSESSIONID");
        System.out.println("JSESSIONID: "+cookie.getValue());

        //Close cookies
        Boolean isPresent = driver.findElements(By.cssSelector("span.g-font-1.small-hide")).size() > 0;
        if (isPresent==true){HomePage.CoockiesFooter.click();}

        $(Complaints.Name).shouldBe(visible);
        $(Complaints.Name).sendKeys(testData);
        $(Complaints.Surname).sendKeys(testData);
        $(Complaints.Country).click();

        //Select random country
        List<WebElement> countrys = driver.findElements(By.id("country--ul-select-one-2"));
        Random country = new Random();
        int randomBaggage = country.nextInt(countrys.size()); //Getting a random value that is between 0 and (list's size)-1
        countrys.get(randomBaggage).click(); //Clicking on the random item in the list.

        $(Complaints.City).sendKeys(testData);
        $(Complaints.ZipCode).sendKeys(zipcode);
        $(Complaints.Street).sendKeys(street);
        $(Complaints.StreetNumber).sendKeys("69");
        $(Complaints.PhoneNumber).sendKeys(phone);
        $(Complaints.Email).sendKeys(email);
        $(Complaints.BookingNumber).sendKeys(bookingNumber);
        $(Complaints.TicketNumber).sendKeys(ticketNumber);
        $(Complaints.FlightNumber).sendKeys(flightNr);
        $(Complaints.Route).sendKeys(testData);

        //Select random data
        $(Complaints.FlightDate).click();
        List<WebElement> dat = driver.findElements(By.cssSelector("#ui-datepicker-div > table > tbody > tr > td:not([class*=\"disabled\"])"));
        Random dat1 = new Random();
        int randomDat = dat1.nextInt(dat.size()); //Getting a random value that is between 0 and (list's size)-1
        dat.get(randomDat).click(); //Clicking on the random item in the list.

       // $(Complaints.Message).scrollTo();
       // $(Complaints.Message).shouldBe(visible);
        $(Complaints.Message).sendKeys(message);

        //Select random opinion
        $(Complaints.Opinion).click();
        List<WebElement> opinion = driver.findElements(By.cssSelector("#opinionComplaintConcern--ul-select-one-2 > li"));
        Random op1 = new Random();
        int randomOpinion = op1.nextInt(opinion.size()); //Getting a random value that is between 0 and (list's size)-1
        opinion.get(randomOpinion).click(); //Clicking on the random item in the list.

        $(Complaints.SendCopyToCustomer).click();
        $(Complaints.Rodo).click();

        //END OF TEST
    }

    @AfterTest(alwaysRun = true)
    public void tearDown1() throws Exception {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
