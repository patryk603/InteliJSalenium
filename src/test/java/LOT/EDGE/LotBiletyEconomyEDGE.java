package LOT.EDGE;

import DDT.ExcelDataConfig;
import Main.GetScreenshot;
import Main.MainTest;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class LotBiletyEconomyEDGE extends MainTest{
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    //All Static Data
    String name = "Test";
    String surname = "Test";
    String email = "TestLot@niepodam.pl";
    String phone = "532752626";
    String DayOfBirth = String.valueOf(11);
    String MonthOfBirth = String.valueOf(05);
    String YearOfBirth = String.valueOf(1989);
    //--------CreditCard
    String creditcard = "41111111111111111"; //MasterCard
    String cvv= "737";
    String city = "Warsaw";
    String zipcode = "02-444";
    String street = "Obrony robotników 43";
    String Month = String.valueOf(05);
    String Year = String.valueOf(2020);
    //--------CreditCard


    @BeforeTest(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new EdgeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        baseUrl = "http://www.lot.com/";
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
        PageFactory.initElements(driver, HomePage.class);
        PageFactory.initElements(driver, FlightsPage.class);
        PageFactory.initElements(driver, PassengersPage.class);
        PageFactory.initElements(driver, ExtrasPage.class);
        PageFactory.initElements(driver, PaymentPage.class);
    }


    @Test(dataProvider = "data",groups=("BuyTickets"))
    public void Test_BuyTicketsEU(String localization, String from, String to, XSSFCell departuredata, XSSFCell returndata) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.get(baseUrl + localization);
        ImplicitWait(driver);

        //TIME Configuration
        String dat1 = String.valueOf(departuredata);
        if (dat1.length() > 0) {
            dat1 = dat1.substring(0, (dat1.length() - 2));
        }

        String dat2 = String.valueOf(returndata);
        if (dat2.length() > 0) {
            dat2 = dat2.substring(0, (dat2.length() - 2));
        }

        //Data Formats
        String eutime = "dd.MM.yyyy";
        String hutime = "yy.MM.dd";
        String ustime = "MM.dd.yyyy";

        String actualtime;
        if (localization.contains("us")) {
            actualtime = ustime;
        }   else if (localization.startsWith("hu/hu")) {
            actualtime = hutime;
        }   else {
            actualtime = eutime;
        }

        //Given Date in String format
        String timeStamp = new SimpleDateFormat(actualtime).format(Calendar.getInstance().getTime());

        //Specifying date format that matches the given date
        SimpleDateFormat sdf = new SimpleDateFormat(actualtime);


        Calendar c = Calendar.getInstance();
        Calendar b = Calendar.getInstance();
        try {
            //Setting the date to the given date
            c.setTime(sdf.parse(timeStamp));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            //Setting the date to the given date
            b.setTime(sdf.parse(timeStamp));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Number of Days to add
        c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(String.valueOf(dat1)));
        b.add(Calendar.DAY_OF_MONTH, Integer.parseInt(String.valueOf(dat2)));

        //Date after adding the days to the given date
        String newDate = sdf.format(c.getTime());
        String newDate2 = sdf.format(b.getTime());

        //Displaying the new Date after addition of Days
        System.out.println("Data wylotu: " + newDate);
        System.out.println("Data powrotu: " + newDate2);
        //TIME

        //TEST START

        //Take screenshot
        try {
            GetScreenshot.capture("HomePage " + localization + from + to + departuredata + returndata);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Selecting From Flight
        wait.until(ExpectedConditions.elementToBeClickable(HomePage.FromListButton));
        HomePage.FromListButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(HomePage.FromToText));
        HomePage.FromToText.sendKeys(from);

        driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*=" + from + "]")).click();
        //Click on home page

        Thread.sleep(1000);

        //Selecting To Flight
        try {
            //wait.until(ExpectedConditions.elementToBeClickable(HomePagePRE2.ToList));
            //HomePagePRE2.ToList.click();
            wait.until(ExpectedConditions.elementToBeClickable(HomePage.ToToText));
            HomePage.ToToText.sendKeys(to);
            driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*=" + to + "]")).click();
        } catch (Exception e) {
            System.out.println("Need additional click : " + e.getMessage());
            HomePage.Lot.click();
            wait.until(ExpectedConditions.elementToBeClickable(HomePage.ToList));
            HomePage.ToList.click();
            wait.until(ExpectedConditions.elementToBeClickable(HomePage.ToToText));
            HomePage.ToToText.sendKeys(to);
            driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*=" + to + "]")).click();
        }


        //Click on home page
        HomePage.Lot.click();

        //Selecting Departure Data
        HomePage.DepartureDate.clear();
        HomePage.DepartureDate.sendKeys(newDate);

        //Selecting Return Date
        HomePage.ReturnDate.clear();
        HomePage.ReturnDate.sendKeys(newDate2);
        HomePage.Lot.click();

        //Selecting Class
        Thread.sleep(1000);
        HomePage.TicketClass.click();
        HomePage.Economy.click();
        Thread.sleep(1000);

        //Submit Button go from Home Page to Flight Page
        HomePage.Submit.submit();

        //FlightPage
        try {
            wait.until(ExpectedConditions.visibilityOf(FlightsPage.Cart));
        } catch (Exception e) {
            System.out.println("Zbyt długi czas oczekiwania przejścia z bookera na step 2- flights : " + e.getMessage());
        }

        //Popup handle
        try {
            FlightsPage.OK.click();
        } catch (Exception e) {
            System.out.println("Flight are available in that date : " + e.getMessage());
        }
        //Take screenshot
        try {
            GetScreenshot.capture("FlightPage " + localization + from + to + departuredata + returndata);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Selecting First ACTIVE Ticket TO

        try {
            FlightsPage.FirstTO.click();
        } catch (Exception e) {
            System.out.println("Other tickets : " + e.getMessage());
            FlightsPage.FirstTO1.click();
        }

        //Selecting First ACTIVE Ticket BACK
        Thread.sleep(1000);
        try {
            FlightsPage.FirstBack.click();
        } catch (Exception e) {
            System.out.println("Other tickets : " + e.getMessage());
            FlightsPage.FirstBack2.click();
        }
        Thread.sleep(1000);
        //Button Continue
        try {
            wait.until(ExpectedConditions.visibilityOf(FlightsPage.BigContinue));
            FlightsPage.BigContinue.click();
        } catch (Exception e) {
            FlightsPage.Popup.click();
            Thread.sleep(1000);
            FlightsPage.BigContinue.click();
            System.out.println("Accepted the alert successfully.");
            System.out.println("No Element Continue : " + e.getMessage());
        }
        // Passengers Page
        //Take screenshot
        wait.until(ExpectedConditions.visibilityOf(PassengersPage.CheckboxAccept));
        try {
            GetScreenshot.capture("PassengersPage " + localization + from + to + departuredata + returndata);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Selecting title
        wait.until(ExpectedConditions.elementToBeClickable(PassengersPage.Title));
        PassengersPage.Title.click();
        Select title = new Select(PassengersPage.Title);
        title.selectByIndex(1);
        //Selecting title

        //Enter Name and Surname
        PassengersPage.FirstName.sendKeys(name);
        PassengersPage.Surname.sendKeys(surname);

        //DATE OF BIRTH
        try {
            PassengersPage.DayOfBirth.click();
            Select day = new Select(PassengersPage.DayOfBirth);
            day.selectByVisibleText(DayOfBirth);

            PassengersPage.MonthOfBirth.click();
            Select mouth = new Select(PassengersPage.MonthOfBirth);
            mouth.selectByVisibleText(MonthOfBirth);

            PassengersPage.YearOfBirth.click();
            Select year = new Select(PassengersPage.YearOfBirth);
            year.selectByVisibleText(YearOfBirth);
        } catch (Exception e) {
            System.out.println("Short haul : " + e.getMessage());
        }
        //DATE OF BIRTH

        //Passengers data: Email Phone
        PassengersPage.Email.sendKeys(email);
        PassengersPage.Phone.sendKeys(phone);

        //Waiting and Clicking on "I have read and I accept Terms of Use, Privacy Policy and Terms and Conditions of Transportation (Excerpt from clause) *"
        wait.until(ExpectedConditions.elementToBeClickable(PassengersPage.CheckboxAccept));
        PassengersPage.CheckboxAccept.click();

        //Waiting and Clicking on Big Continue Button. Next try to Click Accept User Data Popup.
        wait.until(ExpectedConditions.elementToBeClickable(PassengersPage.BigContinue));
        PassengersPage.BigContinue.click();
        try {
            PassengersPage.PopupAccept.click();
        } catch (Exception e) {
            System.out.println("No Popup : " + e.getMessage());
        }

        //Extra Page
        //Take screenshot
        wait.until(ExpectedConditions.visibilityOf(ExtrasPage.Column1));
        try {
            GetScreenshot.capture("ExtraPage " + localization + from + to + departuredata + returndata);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Waiting and Clicking on Big Continue Button.
        try {
            wait.until(ExpectedConditions.visibilityOf(ExtrasPage.BigContinue));
            ExtrasPage.BigContinue.click();
        } catch (Exception e) {
            System.out.println("No Element Continue : " + e.getMessage());
        }
        //Extra Page

        //Payment Page
        wait.until(ExpectedConditions.visibilityOf(PaymentPage.BookNr));
        try {
            GetScreenshot.capture("PaymentPage " + localization + from + to + departuredata + returndata);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //BookingNumber
        String BookNumber = PaymentPage.BookNr.getText();
        System.out.println(BookNumber);
        //BookingNumber

        //Credit Card Data
        PaymentPage.CardNr.sendKeys(creditcard);
        PaymentPage.Cvc.sendKeys(cvv);
        PaymentPage.Name.sendKeys(name);
        PaymentPage.City.sendKeys(city);
        PaymentPage.PostalCode.sendKeys(zipcode);
        PaymentPage.Street.sendKeys(street);

        //DropdownLists
        PaymentPage.Month.click();
        Select mounth = new Select(PaymentPage.Month);
        mounth.selectByVisibleText(Month);

        PaymentPage.Year.click();
        Select cardyear = new Select(PaymentPage.Year);
        cardyear.selectByVisibleText(Year);

        PaymentPage.Country.click();
        Select country = new Select(PaymentPage.Country);
        country.selectByIndex(167);
        PaymentPage.Lot.click();
        //DropdownLists
        //Credit Card

        //Waiting and Clicking on Big Continue Button.
        try {
            wait.until(ExpectedConditions.visibilityOf(PaymentPage.BigContinue));
            PaymentPage.BigContinue.click();
        } catch (Exception e) {
            System.out.println("Problem with Continue button : " + e.getMessage());
        }
        //END OF TEST
    }

    //Excel configuration
    @DataProvider(name ="data")
    public Object[][] passData()
    {
        ExcelDataConfig config = new ExcelDataConfig("C:\\Users\\Public\\LOT\\Economy.xlsx");
        int rows = config.getRowCount(0);
        Object[][] data=new Object[rows][5];

        for(int i=0;i<rows;i++){
            data[i][0]=config.getData(0,i,0);
            data[i][1]=config.getData(0,i,1);
            data[i][2]=config.getData(0,i,2);
            data[i][3]=config.getNumber(0,i,3);
            data[i][4]=config.getNumber(0,i,4);
        }
        return data;
    }

    @AfterTest(alwaysRun = true)
    public void tearDown1() throws Exception {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
