package LOT;

import Main.MainTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LuggageComplaintPage;

public class LotLuggageComplaintFF extends MainTest {
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private String Test = "Test";

    @BeforeTest(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        baseUrl = "http://www.lot.com/";
        PageFactory.initElements(driver, LuggageComplaintPage.class);

    }


    @Test(groups=("Complaint"))
    public void LuggageComplaintPLPL() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.get(baseUrl + "/pl/pl/reklamacje-bagazowe");
        ImplicitWait(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //TestStart
        //Step 1 from 2
        LuggageComplaintPage.Gender.click();
        LuggageComplaintPage.GenderL.click();
        LuggageComplaintPage.Name.sendKeys(Test);
        LuggageComplaintPage.Surname.sendKeys(Test);
        LuggageComplaintPage.City.sendKeys(Test);
        LuggageComplaintPage.ZipCode.sendKeys("00000");
        LuggageComplaintPage.Street.sendKeys(Test);
        LuggageComplaintPage.Number.sendKeys("0");
        LuggageComplaintPage.PhoneNumber.sendKeys("000000000");
        LuggageComplaintPage.Email.sendKeys("lotest787@gmail.com");
        LuggageComplaintPage.Route.sendKeys("Warszawa-Budapeszt");
        LuggageComplaintPage.FlightNumber.sendKeys("WAW LO 34567");
        LuggageComplaintPage.BookingNumber.sendKeys("XYZ111");
        LuggageComplaintPage.FlightDate.click();
        LuggageComplaintPage.CalendarData.click();
        LuggageComplaintPage.MilesAndMore.click();
        LuggageComplaintPage.MilesAndMoreNo.click();

        // This  will scroll down the page by  1000 pixel vertical
        js.executeScript("window.scrollBy(0,1000)");
        //LuggageComplaintPage.FlightClass.click();
        Select flightClass = new Select(LuggageComplaintPage.FlightClass);
        flightClass.selectByIndex(1);
        LuggageComplaintPage.NextStep1.click();

        //Step 2 from 2
        LuggageComplaintPage.LuggageQuantityPosted.sendKeys("1");
        LuggageComplaintPage.LuggageQuantityComplaint.sendKeys("1");
        Thread.sleep(1000);
        LuggageComplaintPage.LuggagePenalty.click();
        wait.until(ExpectedConditions.elementToBeClickable(LuggageComplaintPage.LuggagePenaltyNo));
        LuggageComplaintPage.LuggagePenaltyNo.click();
        LuggageComplaintPage.LuggageDelay.click();
        LuggageComplaintPage.DamageDetails.sendKeys(Test);
        LuggageComplaintPage.LuggageWeight.sendKeys("23");
        LuggageComplaintPage.PIRNumber1.sendKeys("WAW");
        LuggageComplaintPage.PIRNumber2.sendKeys("34567");

        // This  will scroll down the page by  1000 pixel vertical
        js.executeScript("window.scrollBy(0,1000)");
        LuggageComplaintPage.NextStep2.click();
    }

    @Test(groups=("Complaint"))
    public void LuggageComplaintPLEN() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.get(baseUrl + "/pl/en/luggage-complaint");
        ImplicitWait(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //TestStart
        //Step 1 from 2
        LuggageComplaintPage.Gender.click();
        LuggageComplaintPage.GenderL.click();
        LuggageComplaintPage.Name.sendKeys(Test);
        LuggageComplaintPage.Surname.sendKeys(Test);
        LuggageComplaintPage.City.sendKeys(Test);
        LuggageComplaintPage.ZipCode.sendKeys("00000");
        LuggageComplaintPage.Street.sendKeys(Test);
        LuggageComplaintPage.Number.sendKeys("0");
        LuggageComplaintPage.PhoneNumber.sendKeys("000000000");
        LuggageComplaintPage.Email.sendKeys("lotest787@gmail.com");
        LuggageComplaintPage.Route.sendKeys("Warszawa-Budapeszt");
        LuggageComplaintPage.FlightNumber.sendKeys("WAW LO 34567");
        LuggageComplaintPage.BookingNumber.sendKeys("XYZ111");
        LuggageComplaintPage.FlightDate.click();
        LuggageComplaintPage.CalendarData.click();
        LuggageComplaintPage.MilesAndMore.click();
        LuggageComplaintPage.MilesAndMoreNo.click();

        // This  will scroll down the page by  1000 pixel vertical
        js.executeScript("window.scrollBy(0,1000)");
        //LuggageComplaintPage.FlightClass.click();
        Select flightClass = new Select(LuggageComplaintPage.FlightClass);
        flightClass.selectByIndex(1);
        LuggageComplaintPage.NextStep1.click();

        //Step 2 from 2
        LuggageComplaintPage.LuggageQuantityPosted.sendKeys("1");
        LuggageComplaintPage.LuggageQuantityComplaint.sendKeys("1");
        Thread.sleep(1000);
        LuggageComplaintPage.LuggagePenalty.click();
        wait.until(ExpectedConditions.elementToBeClickable(LuggageComplaintPage.LuggagePenaltyNo));
        LuggageComplaintPage.LuggagePenaltyNo.click();
        LuggageComplaintPage.LuggageDelay.click();
        LuggageComplaintPage.DamageDetails.sendKeys(Test);
        LuggageComplaintPage.LuggageWeight.sendKeys("23");
        LuggageComplaintPage.PIRNumber1.sendKeys("WAW");
        LuggageComplaintPage.PIRNumber2.sendKeys("34567");

        // This  will scroll down the page by  1000 pixel vertical
        js.executeScript("window.scrollBy(0,1000)");
        LuggageComplaintPage.NextStep2.click();
    }

    @Test(groups=("Complaint"))
    public void LuggageComplaintDEDE() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.get(baseUrl + "/de/de/gepackreklamationen");
        ImplicitWait(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //TestStart
        LuggageComplaintPage.CloseCoockieSpam.click();
        //Step 1 from 2
        LuggageComplaintPage.Gender.click();
        LuggageComplaintPage.GenderL.click();
        LuggageComplaintPage.Name.sendKeys(Test);
        LuggageComplaintPage.Surname.sendKeys(Test);
        LuggageComplaintPage.City.sendKeys(Test);
        LuggageComplaintPage.ZipCode.sendKeys("00000");
        LuggageComplaintPage.Street.sendKeys(Test);
        LuggageComplaintPage.Number.sendKeys("0");
        LuggageComplaintPage.PhoneNumber.sendKeys("000000000");
        LuggageComplaintPage.Email.sendKeys("lotest787@gmail.com");
        LuggageComplaintPage.Route.sendKeys("Warszawa-Budapeszt");
        LuggageComplaintPage.FlightNumber.sendKeys("WAW LO 34567");
        LuggageComplaintPage.BookingNumber.sendKeys("XYZ111");
        LuggageComplaintPage.FlightDate.click();
        LuggageComplaintPage.CalendarData.click();
        LuggageComplaintPage.MilesAndMore.click();
        LuggageComplaintPage.MilesAndMoreNo.click();

        // This  will scroll down the page by  1000 pixel vertical
        js.executeScript("window.scrollBy(0,1000)");
        //LuggageComplaintPage.FlightClass.click();
        Select flightClass = new Select(LuggageComplaintPage.FlightClass);
        flightClass.selectByIndex(1);
        LuggageComplaintPage.NextStep1.click();

        //Step 2 from 2
        LuggageComplaintPage.LuggageQuantityPosted.sendKeys("1");
        LuggageComplaintPage.LuggageQuantityComplaint.sendKeys("1");
        Thread.sleep(1000);
        LuggageComplaintPage.LuggagePenalty.click();
        wait.until(ExpectedConditions.elementToBeClickable(LuggageComplaintPage.LuggagePenaltyNo));
        LuggageComplaintPage.LuggagePenaltyNo.click();
        LuggageComplaintPage.LuggageDelay.click();
        LuggageComplaintPage.DamageDetails.sendKeys(Test);
        LuggageComplaintPage.LuggageWeight.sendKeys("23");
        LuggageComplaintPage.PIRNumber1.sendKeys("WAW");
        LuggageComplaintPage.PIRNumber2.sendKeys("34567");

        // This  will scroll down the page by  1000 pixel vertical
        js.executeScript("window.scrollBy(0,1000)");
        LuggageComplaintPage.NextStep2.click();
    }

    @Test(groups=("Complaint"))
    public void LuggageComplaintFRFR() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.get(baseUrl + "/fr/fr/reclamations-bagages ");
        ImplicitWait(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //TestStart
        //Step 1 from 2
        LuggageComplaintPage.Gender.click();
        LuggageComplaintPage.GenderL.click();
        LuggageComplaintPage.Name.sendKeys(Test);
        LuggageComplaintPage.Surname.sendKeys(Test);
        LuggageComplaintPage.City.sendKeys(Test);
        LuggageComplaintPage.ZipCode.sendKeys("00000");
        LuggageComplaintPage.Street.sendKeys(Test);
        LuggageComplaintPage.Number.sendKeys("0");
        LuggageComplaintPage.PhoneNumber.sendKeys("000000000");
        LuggageComplaintPage.Email.sendKeys("lotest787@gmail.com");
        LuggageComplaintPage.Route.sendKeys("Warszawa-Budapeszt");
        LuggageComplaintPage.FlightNumber.sendKeys("WAW LO 34567");
        LuggageComplaintPage.BookingNumber.sendKeys("XYZ111");
        LuggageComplaintPage.FlightDate.click();
        LuggageComplaintPage.CalendarData.click();
        LuggageComplaintPage.MilesAndMore.click();
        LuggageComplaintPage.MilesAndMoreNo.click();

        // This  will scroll down the page by  1000 pixel vertical
        js.executeScript("window.scrollBy(0,1000)");
        //LuggageComplaintPage.FlightClass.click();
        Select flightClass = new Select(LuggageComplaintPage.FlightClass);
        flightClass.selectByIndex(1);
        LuggageComplaintPage.NextStep1.click();

        //Step 2 from 2
        LuggageComplaintPage.LuggageQuantityPosted.sendKeys("1");
        LuggageComplaintPage.LuggageQuantityComplaint.sendKeys("1");
        Thread.sleep(1000);
        LuggageComplaintPage.LuggagePenalty.click();
        wait.until(ExpectedConditions.elementToBeClickable(LuggageComplaintPage.LuggagePenaltyNo));
        LuggageComplaintPage.LuggagePenaltyNo.click();
        LuggageComplaintPage.LuggageDelay.click();
        LuggageComplaintPage.DamageDetails.sendKeys(Test);
        LuggageComplaintPage.LuggageWeight.sendKeys("23");
        LuggageComplaintPage.PIRNumber1.sendKeys("WAW");
        LuggageComplaintPage.PIRNumber2.sendKeys("34567");

        // This  will scroll down the page by  1000 pixel vertical
        js.executeScript("window.scrollBy(0,1000)");
        LuggageComplaintPage.NextStep2.click();
    }
    @Test(groups=("Complaint"))
    public void LuggageComplaintESES() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.get(baseUrl + "/es/es/reclamaciones-equipaje");
        ImplicitWait(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //TestStart
        //Step 1 from 2
        LuggageComplaintPage.Gender.click();
        LuggageComplaintPage.GenderL.click();
        LuggageComplaintPage.Name.sendKeys(Test);
        LuggageComplaintPage.Surname.sendKeys(Test);
        LuggageComplaintPage.City.sendKeys(Test);
        LuggageComplaintPage.ZipCode.sendKeys("00000");
        LuggageComplaintPage.Street.sendKeys(Test);
        LuggageComplaintPage.Number.sendKeys("0");
        LuggageComplaintPage.PhoneNumber.sendKeys("000000000");
        LuggageComplaintPage.Email.sendKeys("lotest787@gmail.com");
        LuggageComplaintPage.Route.sendKeys("Warszawa-Budapeszt");
        LuggageComplaintPage.FlightNumber.sendKeys("WAW LO 34567");
        LuggageComplaintPage.BookingNumber.sendKeys("XYZ111");
        LuggageComplaintPage.FlightDate.click();
        LuggageComplaintPage.CalendarData.click();
        LuggageComplaintPage.MilesAndMore.click();
        LuggageComplaintPage.MilesAndMoreNo.click();

        // This  will scroll down the page by  1000 pixel vertical
        js.executeScript("window.scrollBy(0,1000)");
        //LuggageComplaintPage.FlightClass.click();
        Select flightClass = new Select(LuggageComplaintPage.FlightClass);
        flightClass.selectByIndex(1);
        LuggageComplaintPage.NextStep1.click();

        //Step 2 from 2
        LuggageComplaintPage.LuggageQuantityPosted.sendKeys("1");
        LuggageComplaintPage.LuggageQuantityComplaint.sendKeys("1");
        Thread.sleep(1000);
        LuggageComplaintPage.LuggagePenalty.click();
        wait.until(ExpectedConditions.elementToBeClickable(LuggageComplaintPage.LuggagePenaltyNo));
        LuggageComplaintPage.LuggagePenaltyNo.click();
        LuggageComplaintPage.LuggageDelay.click();
        LuggageComplaintPage.DamageDetails.sendKeys(Test);
        LuggageComplaintPage.LuggageWeight.sendKeys("23");
        LuggageComplaintPage.PIRNumber1.sendKeys("WAW");
        LuggageComplaintPage.PIRNumber2.sendKeys("34567");

        // This  will scroll down the page by  1000 pixel vertical
        js.executeScript("window.scrollBy(0,1000)");
        LuggageComplaintPage.NextStep2.click();
    }
    @Test(groups=("Complaint"))
    public void LuggageComplaintITIT() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.get(baseUrl + "/it/it/reclami-bagaglio ");
        ImplicitWait(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //TestStart
        //Step 1 from 2
        LuggageComplaintPage.Gender.click();
        LuggageComplaintPage.GenderL.click();
        LuggageComplaintPage.Name.sendKeys(Test);
        LuggageComplaintPage.Surname.sendKeys(Test);
        LuggageComplaintPage.City.sendKeys(Test);
        LuggageComplaintPage.ZipCode.sendKeys("00000");
        LuggageComplaintPage.Street.sendKeys(Test);
        LuggageComplaintPage.Number.sendKeys("0");
        LuggageComplaintPage.PhoneNumber.sendKeys("000000000");
        LuggageComplaintPage.Email.sendKeys("lotest787@gmail.com");
        LuggageComplaintPage.Route.sendKeys("Warszawa-Budapeszt");
        LuggageComplaintPage.FlightNumber.sendKeys("WAW LO 34567");
        LuggageComplaintPage.BookingNumber.sendKeys("XYZ111");
        LuggageComplaintPage.FlightDate.click();
        LuggageComplaintPage.CalendarData.click();
        LuggageComplaintPage.MilesAndMore.click();
        LuggageComplaintPage.MilesAndMoreNo.click();

        // This  will scroll down the page by  1000 pixel vertical
        js.executeScript("window.scrollBy(0,1000)");
        //LuggageComplaintPage.FlightClass.click();
        Select flightClass = new Select(LuggageComplaintPage.FlightClass);
        flightClass.selectByIndex(1);
        LuggageComplaintPage.NextStep1.click();

        //Step 2 from 2
        LuggageComplaintPage.LuggageQuantityPosted.sendKeys("1");
        LuggageComplaintPage.LuggageQuantityComplaint.sendKeys("1");
        Thread.sleep(1000);
        LuggageComplaintPage.LuggagePenalty.click();
        wait.until(ExpectedConditions.elementToBeClickable(LuggageComplaintPage.LuggagePenaltyNo));
        LuggageComplaintPage.LuggagePenaltyNo.click();
        LuggageComplaintPage.LuggageDelay.click();
        LuggageComplaintPage.DamageDetails.sendKeys(Test);
        LuggageComplaintPage.LuggageWeight.sendKeys("23");
        LuggageComplaintPage.PIRNumber1.sendKeys("WAW");
        LuggageComplaintPage.PIRNumber2.sendKeys("34567");

        // This  will scroll down the page by  1000 pixel vertical
        js.executeScript("window.scrollBy(0,1000)");
        LuggageComplaintPage.NextStep2.click();
    }
    @Test(groups=("Complaint"))
    public void LuggageComplaintRURU() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.get(baseUrl + "/ru/ru/luggage-complaint ");
        ImplicitWait(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //TestStart
        //Step 1 from 2
        LuggageComplaintPage.Gender.click();
        LuggageComplaintPage.GenderL.click();
        LuggageComplaintPage.Name.sendKeys(Test);
        LuggageComplaintPage.Surname.sendKeys(Test);
        LuggageComplaintPage.City.sendKeys(Test);
        LuggageComplaintPage.ZipCode.sendKeys("00000");
        LuggageComplaintPage.Street.sendKeys(Test);
        LuggageComplaintPage.Number.sendKeys("0");
        LuggageComplaintPage.PhoneNumber.sendKeys("000000000");
        LuggageComplaintPage.Email.sendKeys("lotest787@gmail.com");
        LuggageComplaintPage.Route.sendKeys("Warszawa-Budapeszt");
        LuggageComplaintPage.FlightNumber.sendKeys("WAW LO 34567");
        LuggageComplaintPage.BookingNumber.sendKeys("XYZ111");
        LuggageComplaintPage.FlightDate.click();
        LuggageComplaintPage.CalendarData.click();
        LuggageComplaintPage.MilesAndMore.click();
        LuggageComplaintPage.MilesAndMoreNo.click();

        // This  will scroll down the page by  1000 pixel vertical
        js.executeScript("window.scrollBy(0,1000)");
        //LuggageComplaintPage.FlightClass.click();
        Select flightClass = new Select(LuggageComplaintPage.FlightClass);
        flightClass.selectByIndex(1);
        LuggageComplaintPage.NextStep1.click();

        //Step 2 from 2
        LuggageComplaintPage.LuggageQuantityPosted.sendKeys("1");
        LuggageComplaintPage.LuggageQuantityComplaint.sendKeys("1");
        Thread.sleep(1000);
        LuggageComplaintPage.LuggagePenalty.click();
        wait.until(ExpectedConditions.elementToBeClickable(LuggageComplaintPage.LuggagePenaltyNo));
        LuggageComplaintPage.LuggagePenaltyNo.click();
        LuggageComplaintPage.LuggageDelay.click();
        LuggageComplaintPage.DamageDetails.sendKeys(Test);
        LuggageComplaintPage.LuggageWeight.sendKeys("23");
        LuggageComplaintPage.PIRNumber1.sendKeys("WAW");
        LuggageComplaintPage.PIRNumber2.sendKeys("34567");

        // This  will scroll down the page by  1000 pixel vertical
        js.executeScript("window.scrollBy(0,1000)");
        LuggageComplaintPage.NextStep2.click();
    }

    @Test(groups=("Complaint"))
    public void LuggageComplaintKRKO() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.get(baseUrl + "/kr/ko/luggage-complaint ");
        ImplicitWait(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //TestStart
        //Step 1 from 2
        LuggageComplaintPage.Gender.click();
        LuggageComplaintPage.GenderL.click();
        LuggageComplaintPage.Name.sendKeys(Test);
        LuggageComplaintPage.Surname.sendKeys(Test);
        LuggageComplaintPage.City.sendKeys(Test);
        LuggageComplaintPage.ZipCode.sendKeys("00000");
        LuggageComplaintPage.Street.sendKeys(Test);
        LuggageComplaintPage.Number.sendKeys("0");
        LuggageComplaintPage.PhoneNumber.sendKeys("000000000");
        LuggageComplaintPage.Email.sendKeys("lotest787@gmail.com");
        LuggageComplaintPage.Route.sendKeys("Warszawa-Budapeszt");
        LuggageComplaintPage.FlightNumber.sendKeys("WAW LO 34567");
        LuggageComplaintPage.BookingNumber.sendKeys("XYZ111");
        LuggageComplaintPage.FlightDate.click();
        LuggageComplaintPage.CalendarData.click();
        LuggageComplaintPage.MilesAndMore.click();
        LuggageComplaintPage.MilesAndMoreNo.click();

        // This  will scroll down the page by  1000 pixel vertical
        js.executeScript("window.scrollBy(0,1000)");
        //LuggageComplaintPage.FlightClass.click();
        Select flightClass = new Select(LuggageComplaintPage.FlightClass);
        flightClass.selectByIndex(1);
        LuggageComplaintPage.NextStep1.click();

        //Step 2 from 2
        LuggageComplaintPage.LuggageQuantityPosted.sendKeys("1");
        LuggageComplaintPage.LuggageQuantityComplaint.sendKeys("1");
        Thread.sleep(1000);
        LuggageComplaintPage.LuggagePenalty.click();
        wait.until(ExpectedConditions.elementToBeClickable(LuggageComplaintPage.LuggagePenaltyNo));
        LuggageComplaintPage.LuggagePenaltyNo.click();
        LuggageComplaintPage.LuggageDelay.click();
        LuggageComplaintPage.DamageDetails.sendKeys(Test);
        LuggageComplaintPage.LuggageWeight.sendKeys("23");
        LuggageComplaintPage.PIRNumber1.sendKeys("WAW");
        LuggageComplaintPage.PIRNumber2.sendKeys("34567");

        // This  will scroll down the page by  1000 pixel vertical
        js.executeScript("window.scrollBy(0,1000)");
        LuggageComplaintPage.NextStep2.click();
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
