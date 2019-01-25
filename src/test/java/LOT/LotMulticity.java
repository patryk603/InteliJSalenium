package LOT;

import DDT.ExcelDataConfig;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LotMulticity {
    public static WebDriver driver;
    private String baseUrl;

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
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
        baseUrl = "http://www.lot.com/";
        PageFactory.initElements(driver, HomePage.class);
        PageFactory.initElements(driver, MultiCity.class);
        PageFactory.initElements(driver, FlightsPage.class);
        PageFactory.initElements(driver, PassengersPage.class);
        PageFactory.initElements(driver, ExtrasPage.class);
        PageFactory.initElements(driver, PaymentPage.class);
    }


    @Test(dataProvider = "data",groups=("BuyTickets"))
    public void Multicity(String localization, String from1, String to1, String from2, String to2, String from3, String to3,String from4, String to4, XSSFCell departuredata1, XSSFCell returndata1,XSSFCell departuredata2, XSSFCell returndata2) throws Exception {

        open(baseUrl + localization);

        //TIME Configuration
        String dat1 = String.valueOf(departuredata1);
        if (dat1.length() > 0) {
            dat1 = dat1.substring(0, (dat1.length() - 2));
        }

        String dat2 = String.valueOf(returndata1);
        if (dat2.length() > 0) {
            dat2 = dat2.substring(0, (dat2.length() - 2));
        }
        String dat3 = String.valueOf(departuredata2);
        if (dat3.length() > 0) {
            dat3 = dat3.substring(0, (dat3.length() - 2));
        }

        String dat4 = String.valueOf(returndata2);
        if (dat4.length() > 0) {
            dat4 = dat4.substring(0, (dat4.length() - 2));
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
        Calendar d = Calendar.getInstance();
        Calendar f = Calendar.getInstance();
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
        try {
            //Setting the date to the given date
            d.setTime(sdf.parse(timeStamp));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            //Setting the date to the given date
            f.setTime(sdf.parse(timeStamp));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Number of Days to add
        c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(String.valueOf(dat1)));
        b.add(Calendar.DAY_OF_MONTH, Integer.parseInt(String.valueOf(dat2)));
        d.add(Calendar.DAY_OF_MONTH, Integer.parseInt(String.valueOf(dat3)));
        f.add(Calendar.DAY_OF_MONTH, Integer.parseInt(String.valueOf(dat4)));

        //Date after adding the days to the given date
        String newDate = sdf.format(c.getTime());
        String newDate2 = sdf.format(b.getTime());
        String newDate3 = sdf.format(d.getTime());
        String newDate4 = sdf.format(f.getTime());

        //Displaying the new Date after addition of Days
        System.out.println("Data lotu1: " + newDate);
        System.out.println("Data lotu2: " + newDate2);
        System.out.println("Data lotu3: " + newDate3);
        System.out.println("Data lotu4: " + newDate4);
        //TIME

        //TEST START
        String start = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        System.out.println("Lokalizacja: "+localization+" | Lot z: " +from1+ " | Lot do: "+to1+" | Data lotu1: "+newDate+" | Data lotu2: "+newDate2+" | Data lotu3: "+newDate3+" | Data lotu4: "+newDate4+"  Start testu: "+start);

        //JSESSION ID
        Cookie cookie= driver.manage().getCookieNamed("JSESSIONID");
        System.out.println("HomePage JSESSIONID: "+cookie.getValue());

        //Take screenshot
        screenshot("HomePage " + localization + from1 + to1 + departuredata1 + returndata1);
        //Close cookies
        Boolean isPresent = driver.findElements(By.cssSelector("span.g-font-1.small-hide")).size() > 0;
        if (isPresent==true){HomePage.CoockiesFooter.click();}

        $(HomePage.NextHP).click();
        $(HomePage.MultiCity).click();
        //Selecting From1 Flight
        $(MultiCity.From1).shouldBe(visible);
        $(MultiCity.From1).click();
        $(MultiCity.DDL).shouldBe(visible);
        $(MultiCity.DDL).sendKeys(from1);
        $(MultiCity.List).shouldBe(visible);
        $(driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*="+from1+"]"))).click();

        //Selecting To1 Flight
        try {
            $(MultiCity.DestArrow1).shouldBe(visible);
            $(MultiCity.DestArrow1).click();
            $(MultiCity.DestArrow1).click();
            $(MultiCity.Text).shouldBe(visible);
            $(MultiCity.Text).sendKeys(to1);
            $(driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*="+to1+"]"))).click();
        } catch (Exception e) {
            $(MultiCity.DestArrow1).click();
            $(MultiCity.Text).sendKeys(to1);
            $(driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*="+to1+"]"))).click();
        }

        //Selecting Flight1 Data
        $(MultiCity.DepartureDate1).clear();
        $(MultiCity.DepartureDate1).sendKeys(newDate);

        //Add flight
        $(MultiCity.MultiAdd).click();

        //Selecting From2 Flight
        $(MultiCity.From2).click();
        $(MultiCity.DDL).shouldBe(visible);
        $(MultiCity.DDL).sendKeys(from2);
        $(driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*=" + from2 + "]"))).shouldBe(visible);
        $(driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*=" + from2 + "]"))).click();
        //Selecting To2 Flight
        $(MultiCity.DestArrow2).click();
        $(MultiCity.Text).sendKeys(to2);
        $(driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*=" + to2 + "]"))).click();
        //Selecting Flight2 Data
        $(MultiCity.DepartureDate2).clear();
        $(MultiCity.DepartureDate2).sendKeys(newDate2);

        //Add flight
        $(MultiCity.MultiAdd).click();

        //Selecting From3 Flight
        $(MultiCity.From3).click();
        $(MultiCity.DDL).shouldBe(visible);
        $(MultiCity.DDL).sendKeys(from3);
        $(driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*=" + from3 + "]"))).shouldBe(visible);
        $(driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*=" + from3 + "]"))).click();
        //Selecting To3 Flight
        $(MultiCity.DestArrow3).click();
        $(MultiCity.Text).sendKeys(to3);
        $(driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*=" + to3 + "]"))).shouldBe(visible);
        $(driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*=" + to3 + "]"))).click();
        //Selecting Flight3 Data
        $(MultiCity.DepartureDate3).clear();
        $(MultiCity.DepartureDate3).sendKeys(newDate3);

        //Add flight
        $(MultiCity.MultiAdd).click();

        //Selecting From4 Flight
        $(MultiCity.From4).click();
        $(MultiCity.DDL).shouldBe(visible);
        $(MultiCity.DDL).sendKeys(from4);
        $(driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*=" + from4 + "]"))).shouldBe(visible);
        $(driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*=" + from4 + "]"))).click();
        //Selecting To4 Flight
        $(MultiCity.DestArrow4).click();
        $(MultiCity.Text).sendKeys(to4);
        $(driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*=" + to4 + "]"))).shouldBe(visible);
        $(driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*=" + to4 + "]"))).click();
        //Selecting Flight4 Data
        $(MultiCity.DepartureDate4).clear();
        $(MultiCity.DepartureDate4).sendKeys(newDate4);
        $(driver.findElement(By.cssSelector("#flightBookingForm > div:nth-child(7) > div:nth-child(1)"))).click();

        //Click Search Flights
        $(MultiCity.Search).click();

        //FlightPage
        //$(FlightsPage.Cart).waitUntil(visible,20000);

        //Popup handle
        try {
            FlightsPage.OK.click();
        } catch (Exception e) {
            System.out.println("Flight are available in that date : " + e.getMessage());
        }

        //JSESSION ID
        Cookie cookie2= driver.manage().getCookieNamed("JSESSIONID");
        System.out.println("FlightPage JSESSIONID: "+cookie2.getValue());
        //Take screenshot
        screenshot("FlightPage " + localization + from1 + to1 + departuredata1 + returndata1);

        //Selecting First ACTIVE Tickets
        $(FlightsPage.Multi1).scrollTo();
        $(FlightsPage.Multi1).click();
        $(FlightsPage.BacketTicket1).shouldBe(visible);
        $(FlightsPage.Multi2).scrollTo();
        $(FlightsPage.Multi2).click();
        $(FlightsPage.Multi2).click();
        $(FlightsPage.Multi2).click();
        $(FlightsPage.BacketTicket2).shouldBe(visible);
        $(FlightsPage.Multi3).scrollTo();
        $(FlightsPage.Multi3).click();
        $(FlightsPage.BacketTicket3).shouldBe(visible);
        $(FlightsPage.Multi4).scrollTo();
        $(FlightsPage.Multi4).click();
        $(FlightsPage.BacketTicket4).shouldBe(visible);

        //Button Continue

        try {
            $(FlightsPage.BigContinue).shouldBe(visible);
            $(FlightsPage.BigContinue).click();
        } catch (Exception e) {
            $(FlightsPage.Popup).shouldBe(visible);
            $(FlightsPage.Popup).click();
            $(FlightsPage.BigContinue).click();
            System.out.println("Accepted the alert successfully.");
            System.out.println("No Element Continue : " + e.getMessage());
        }
        try {
            $(FlightsPage.BigContinue).shouldBe(visible);
            $(FlightsPage.BigContinue).click();
        } catch (Exception e) {
            $(FlightsPage.Popup).shouldBe(visible);
            $(FlightsPage.Popup).click();
            $(FlightsPage.BigContinue).click();
            System.out.println("Accepted the alert successfully.");
            System.out.println("No Element Continue : " + e.getMessage());
        }
        // Passengers Page

        //Upsell Popup
        screenshot("Upsell/"+from1+"/"+to1);
        try {
            FlightsPage.NoThanks.click();
        } catch (Exception e) {
            screenshot("No Upsell for: " + localization +"/"+ from1+"/"+ to1 +"/"+ departuredata1 +"/"+ returndata1);
            System.out.println("No Upsell for: " + localization +"/"+ from1+"/"+ to1 +"/"+ departuredata1 +"/"+ returndata1);
        }

        //Selecting title
        $(PassengersPage.Title).shouldBe(visible);
        $(PassengersPage.Title).click();
        Select title = new Select(PassengersPage.Title);
        title.selectByIndex(1);

        //Enter Name and Surname
        $(PassengersPage.FirstName).sendKeys(name);
        $(PassengersPage.Surname).sendKeys(surname);

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
        $(PassengersPage.Email).sendKeys(email);
        $(PassengersPage.Phone).sendKeys(phone);

        //Waiting and Clicking on "I have read and I accept Terms of Use, Privacy Policy and Terms and Conditions of Transportation (Excerpt from clause) *"
        $(PassengersPage.CheckboxAccept).shouldBe(visible);
        $(PassengersPage.CheckboxAccept).click();

        //Waiting and Clicking on Big Continue Button. Next try to Click Accept User Data Popup.
        $(PassengersPage.BigContinue).shouldBe(visible);
        $(PassengersPage.BigContinue).click();
        try {
            PassengersPage.PopupAccept.click();
        } catch (Exception e) {
            System.out.println("No Popup : " + e.getMessage());
        }

        //Extra Page
        //Take screenshot
        $(ExtrasPage.Column1).shouldBe(visible);
        screenshot("ExtraPage " + localization + from1 + to1 + departuredata1 + returndata1);
        //Waiting and Clicking on Big Continue Button.
        $(ExtrasPage.BigContinue).shouldBe(visible);
        $(ExtrasPage.BigContinue).click();

        //Payment Page
        $(PaymentPage.BookNr).shouldBe(visible);
        screenshot("PaymentPage " + localization + from1 + to1 + departuredata1 + returndata1);

        //BookingNumber
        String BookNumber = $(PaymentPage.BookNr).getText();
        System.out.println(BookNumber);
        //BookingNumber

        //Credit Card Data
        $(PaymentPage.CardNr).sendKeys(creditcard);
        $(PaymentPage.Cvc).sendKeys(cvv);
        $(PaymentPage.Name).sendKeys(name);
        $(PaymentPage.City).sendKeys(city);
        $(PaymentPage.PostalCode).sendKeys(zipcode);
        $(PaymentPage.Street).sendKeys(street);

        //DropdownLists
        $(PaymentPage.Month).click();
        Select mounth = new Select(PaymentPage.Month);
        mounth.selectByVisibleText(Month);

        $(PaymentPage.Year).click();
        Select cardyear = new Select(PaymentPage.Year);
        cardyear.selectByVisibleText(Year);

        $(PaymentPage.Country).click();
        Select country = new Select(PaymentPage.Country);
        country.selectByIndex(167);
        $(PaymentPage.Lot).click();
        //DropdownLists
        //Credit Card

        //Waiting and Clicking on Big Continue Button.
        $(PaymentPage.BigContinue).shouldBe(visible);
        $(PaymentPage.BigContinue).click();
        //END OF TEST
    }

    //Excel configuration
    @DataProvider(name ="data")
    public Object[][] passData()
    {
        ExcelDataConfig config = new ExcelDataConfig("C:\\Users\\Public\\LOT\\Multicity.xlsx");
        int rows = config.getRowCount(0);
        Object[][] data=new Object[rows][13];

        for(int i=0;i<rows;i++){
            data[i][0]=config.getData(0,i,0);
            data[i][1]=config.getData(0,i,1);
            data[i][2]=config.getData(0,i,2);
            data[i][3]=config.getData(0,i,3);
            data[i][4]=config.getData(0,i,4);
            data[i][5]=config.getData(0,i,5);
            data[i][6]=config.getData(0,i,6);
            data[i][7]=config.getData(0,i,7);
            data[i][8]=config.getData(0,i,8);
            data[i][9]=config.getNumber(0,i,9);
            data[i][10]=config.getNumber(0,i,10);
            data[i][11]=config.getNumber(0,i,11);
            data[i][12]=config.getNumber(0,i,12);
        }
        return data;
    }

    @AfterTest(alwaysRun = true)
    public void tearDown1() throws Exception {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
