package LOT;

import DDT.ExcelDataConfig;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.*;
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
    String street = "Obrony robotnikow";
    String flightNr = "LO281";
    String message = "To jest wiadomosc wygenerowana automatycznie przez skrypt testujacy Selenium";
    String bookingNumber = "XYZ111";
    String ticketNumber = "0801234567890";


    @BeforeTest(alwaysRun = true)
    public void setUp() throws Exception {
        baseUrl = "https://www.lot.com/pl/pl/reklamacje-pasazerskie-po-podrozy";
    }


    @Test(groups = { "Complaints" },invocationCount =25)
    public void ComplaintsChrome(ITestContext testContext) throws Exception {

        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
        PageFactory.initElements(driver, HomePage.class);
        PageFactory.initElements(driver, Complaints.class);

        int currentCount = testContext.getAllTestMethods()[0].getCurrentInvocationCount();
        System.out.println("Executing count: " + currentCount);

        open(baseUrl);

        //TEST START

        //JSESSION ID
        Cookie cookie= driver.manage().getCookieNamed("JSESSIONID");
        System.out.println("JSESSIONID: "+cookie.getValue());

        //Close cookies
        $(HomePage.CoockiesFooter).waitUntil(visible,15000);
        $(HomePage.CoockiesFooter).click();

        $(Complaints.Name).shouldBe(visible);
        $(Complaints.Name).sendKeys(testData);
        $(Complaints.Surname).sendKeys(testData);
        $(Complaints.Country).click();

        //Select random country
        List<WebElement> countrys = driver.findElements(By.cssSelector("#country--ul-select-one-2 > li"));
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

        $(Complaints.Message).sendKeys(message+"| Executing count: " + currentCount+ " |Run on Chrome");

        //Select random opinion
        $(Complaints.Opinion).click();
        List<WebElement> opinion = driver.findElements(By.cssSelector("#opinionComplaintConcern--ul-select-one-2 > li"));
        Random op1 = new Random();
        int randomOpinion = op1.nextInt(opinion.size()); //Getting a random value that is between 0 and (list's size)-1
        opinion.get(randomOpinion).click(); //Clicking on the random item in the list.

        $(Complaints.SendCopyToCustomer).click();
        $(Complaints.Rodo).click();

        screenshot("SubmitComplaints"+currentCount);

        $(Complaints.Submit).submit();

        $(Complaints.SendConfirm).waitUntil(visible,10000);
        screenshot("SendConfirmation"+currentCount);


        //END OF TEST
    }

    @AfterTest(alwaysRun = true)
    public void tearDown1() throws Exception {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
