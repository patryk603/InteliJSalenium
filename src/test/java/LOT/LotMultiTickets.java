package LOT;

import DDT.ExcelDataConfig;
import Main.GetScreenshot;
import Main.MainTest;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pageObjects.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LotMultiTickets extends MainTest{
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


    @BeforeMethod(alwaysRun = true)
    public void setUp1() throws Exception {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        baseUrl = "http://www.lot.com/";
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        PageFactory.initElements(driver, HomePage.class);
        PageFactory.initElements(driver, FlightsPage.class);
        PageFactory.initElements(driver, PassengersPage.class);
        PageFactory.initElements(driver, ExtrasPage.class);
        PageFactory.initElements(driver, PaymentPage.class);
    }



    @Test(dataProvider = "dataEU",groups=("BuyTickets"))
    public void Test_BuyTickets2adults(String localization, String from, String to, XSSFCell departuredata, XSSFCell returndata) throws Exception {

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

        //Given Date in String format
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());

        //Specifying date format that matches the given date
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
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


        //TEST START
        String start = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        System.out.println("Lokalizacja: "+localization+" | Lot z: " +from+ " | Lot do: "+to+" | Data wylotu: "+newDate+" | Data powrotu: "+newDate2+"  Start testu: "+start);
        //Take screenshot
        try {
            GetScreenshot.capture("HomePagePRE2 " + localization + from + to + departuredata + returndata);
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

        //Selecting NUMBER OF PASSENGERS

        HomePage.Passengers.click();

        //Number of Adults
        Select numOfAdults = new Select(HomePage.NumberOfAdults);
        //numOfAdults = (3)-1 = 2  NUMBER OF ADULTS =2
        numOfAdults.selectByIndex(3);

        //Submit Button go from Home Page to Flight Page
        HomePage.Submit.submit();

        //FlightPage
        try {
            wait.until(ExpectedConditions.visibilityOf(FlightsPage.Cart));
        } catch (Exception e) {
            System.out.println("Zbyt długi czas oczekiwania przejścia z bookera na step 2- flights : "+ e.getMessage());
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
        //PERSON1
        //Select all titles



        //Selecting title
        wait.until(ExpectedConditions.elementToBeClickable(PassengersPage.Title));
        PassengersPage.TitleAdult1.click();
        Select title1 = new Select(PassengersPage.TitleAdult1);
        title1.selectByIndex(1);
        PassengersPage.TitleAdult1.click();
        Select title2 = new Select(PassengersPage.TitleAdult2);
        title2.selectByIndex(1);

        //Selecting title

        //Enter Name and Surname
        PassengersPage.FirstName.sendKeys(name);
        PassengersPage.FirstName2.sendKeys(name);
        PassengersPage.Surname.sendKeys(surname);
        PassengersPage.Surname2.sendKeys(surname);


        //DATE OF BIRTH

        //Passanger1
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
            System.out.println("Short haul : "+ e.getMessage());
        }
        //Passanger2
        try {
            PassengersPage.DayOfBirth2.click();
            Select day = new Select(PassengersPage.DayOfBirth2);
            day.selectByVisibleText(DayOfBirth);

            PassengersPage.MonthOfBirth2.click();
            Select mouth = new Select(PassengersPage.MonthOfBirth2);
            mouth.selectByVisibleText(MonthOfBirth);

            PassengersPage.YearOfBirth2.click();
            Select year = new Select(PassengersPage.YearOfBirth2);
            year.selectByVisibleText(YearOfBirth);
        } catch (Exception e) {
            System.out.println("Short haul : "+ e.getMessage());
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
        }catch (Exception e){
            System.out.println("No Popup : "+ e.getMessage());
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
            System.out.println("No Element Continue : "+ e.getMessage());
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
            System.out.println("Problem with Continue button : "+ e.getMessage());
        }

        //END OF TEST
    }

    @Test(dataProvider = "dataEU",groups=("BuyTickets"))
    public void Test_BuyTickets3adults(String localization, String from, String to, XSSFCell departuredata, XSSFCell returndata) throws Exception {

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

        //Given Date in String format
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());

        //Specifying date format that matches the given date
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
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


        //TEST START
        String start = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        System.out.println("Lokalizacja: "+localization+" | Lot z: " +from+ " | Lot do: "+to+" | Data wylotu: "+newDate+" | Data powrotu: "+newDate2+"  Start testu: "+start);
        //Take screenshot
        try {
            GetScreenshot.capture("HomePagePRE2 " + localization + from + to + departuredata + returndata);
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

        //Selecting NUMBER OF PASSENGERS

        HomePage.Passengers.click();

        //Number of Adults
        Select numOfAdults = new Select(HomePage.NumberOfAdults);
        //numOfAdults = (4)-1 = 3  NUMBER OF ADULTS =3
        numOfAdults.selectByIndex(4);


        //Submit Button go from Home Page to Flight Page
        HomePage.Submit.submit();

        //FlightPage
        try {
            wait.until(ExpectedConditions.visibilityOf(FlightsPage.Cart));
        } catch (Exception e) {
            System.out.println("Zbyt długi czas oczekiwania przejścia z bookera na step 2- flights : "+ e.getMessage());
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
        //PERSON1
        //Select all titles



        //Selecting title
        wait.until(ExpectedConditions.elementToBeClickable(PassengersPage.Title));
        PassengersPage.TitleAdult1.click();
        Select title1 = new Select(PassengersPage.TitleAdult1);
        title1.selectByIndex(1);
        PassengersPage.TitleAdult1.click();
        Select title2 = new Select(PassengersPage.TitleAdult2);
        title2.selectByIndex(1);
        PassengersPage.TitleAdult1.click();
        Select title3 = new Select(PassengersPage.TitleAdult3);
        title3.selectByIndex(1);

        //Selecting title

        //Enter Name and Surname
        PassengersPage.FirstName.sendKeys(name);
        PassengersPage.FirstName2.sendKeys(name);
        PassengersPage.FirstName3.sendKeys(name);
        PassengersPage.Surname.sendKeys(surname);
        PassengersPage.Surname2.sendKeys(surname);
        PassengersPage.Surname3.sendKeys(surname);

        //DATE OF BIRTH

        //Passanger1
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
            System.out.println("Short haul : "+ e.getMessage());
        }
        //Passanger2
        try {
            PassengersPage.DayOfBirth2.click();
            Select day = new Select(PassengersPage.DayOfBirth2);
            day.selectByVisibleText(DayOfBirth);

            PassengersPage.MonthOfBirth2.click();
            Select mouth = new Select(PassengersPage.MonthOfBirth2);
            mouth.selectByVisibleText(MonthOfBirth);

            PassengersPage.YearOfBirth2.click();
            Select year = new Select(PassengersPage.YearOfBirth2);
            year.selectByVisibleText(YearOfBirth);
        } catch (Exception e) {
            System.out.println("Short haul : "+ e.getMessage());
        }
        //Passanger3
        try {
            PassengersPage.DayOfBirth3.click();
            Select day = new Select(PassengersPage.DayOfBirth3);
            day.selectByVisibleText(DayOfBirth);

            PassengersPage.MonthOfBirth3.click();
            Select mouth = new Select(PassengersPage.MonthOfBirth3);
            mouth.selectByVisibleText(MonthOfBirth);

            PassengersPage.YearOfBirth3.click();
            Select year = new Select(PassengersPage.YearOfBirth3);
            year.selectByVisibleText(YearOfBirth);
        } catch (Exception e) {
            System.out.println("Short haul : "+ e.getMessage());
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
        }catch (Exception e){
            System.out.println("No Popup : "+ e.getMessage());
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
            System.out.println("No Element Continue : "+ e.getMessage());
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
            System.out.println("Problem with Continue button : "+ e.getMessage());
        }
        //END OF TEST
    }

    @Test(dataProvider = "dataEU",groups=("BuyTickets"))
    public void Test_BuyTickets1adult1youth(String localization, String from, String to, XSSFCell departuredata, XSSFCell returndata) throws Exception {

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

        //Given Date in String format
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());

        //Specifying date format that matches the given date
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
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


        //TEST START
        String start = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        System.out.println("Lokalizacja: "+localization+" | Lot z: " +from+ " | Lot do: "+to+" | Data wylotu: "+newDate+" | Data powrotu: "+newDate2+"  Start testu: "+start);
        //Take screenshot
        try {
            GetScreenshot.capture("HomePagePRE2 " + localization + from + to + departuredata + returndata);
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

        //Selecting NUMBER OF PASSENGERS

        HomePage.Passengers.click();

        //Number of Youths
        Select numOfAdults = new Select(HomePage.NumberOfYouths);
        //numOfAdults = (2)-1 = 1  NUMBER OF Youths =1
        numOfAdults.selectByIndex(2);

        //Submit Button go from Home Page to Flight Page
        HomePage.Submit.submit();

        //FlightPage
        try {
            wait.until(ExpectedConditions.visibilityOf(FlightsPage.Cart));
        } catch (Exception e) {
            System.out.println("Zbyt długi czas oczekiwania przejścia z bookera na step 2- flights : "+ e.getMessage());
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
        //PERSON1
        //Select all titles



        //Selecting title
        wait.until(ExpectedConditions.elementToBeClickable(PassengersPage.Title));
        PassengersPage.TitleAdult1.click();
        Select title1 = new Select(PassengersPage.TitleAdult1);
        title1.selectByIndex(1);
        //Selecting sex
        PassengersPage.Sex1.click();
        Select sex1 = new Select(PassengersPage.Sex1);
        sex1.selectByIndex(1);

        //Selecting title

        //Enter Name and Surname
        PassengersPage.FirstName.sendKeys(name);
        PassengersPage.FirstNameYoung1.sendKeys(name);
        PassengersPage.Surname.sendKeys(surname);
        PassengersPage.SurnameYoung1.sendKeys(surname);


        //DATE OF BIRTH

        //Passanger1
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
            System.out.println("Short haul : "+ e.getMessage());
        }
        //Passanger2
        try {
            PassengersPage.DayOfBirthYoung1.click();
            Select day = new Select(PassengersPage.DayOfBirthYoung1);
            day.selectByVisibleText(DayOfBirth);

            PassengersPage.MonthOfBirthYoung1.click();
            Select mouth = new Select(PassengersPage.MonthOfBirthYoung1);
            mouth.selectByVisibleText(MonthOfBirth);

            PassengersPage.YearOfBirthYoung1.click();
            Select year = new Select(PassengersPage.YearOfBirthYoung1);
            year.selectByIndex(2);
        } catch (Exception e) {
            System.out.println("Short haul : "+ e.getMessage());
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
        }catch (Exception e){
            System.out.println("No Popup : "+ e.getMessage());
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
            System.out.println("No Element Continue : "+ e.getMessage());
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
            System.out.println("Problem with Continue button : "+ e.getMessage());
        }
        //END OF TEST
    }

    @Test(dataProvider = "dataEU",groups=("BuyTickets"))
    public void Test_BuyTickets2adults1youth(String localization, String from, String to, XSSFCell departuredata, XSSFCell returndata) throws Exception {

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

        //Given Date in String format
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());

        //Specifying date format that matches the given date
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
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


        //TEST START
        String start = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        System.out.println("Lokalizacja: "+localization+" | Lot z: " +from+ " | Lot do: "+to+" | Data wylotu: "+newDate+" | Data powrotu: "+newDate2+"  Start testu: "+start);
        //Take screenshot
        try {
            GetScreenshot.capture("HomePagePRE2 " + localization + from + to + departuredata + returndata);
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

        //Selecting NUMBER OF PASSENGERS

        HomePage.Passengers.click();

        //Number of Adults
        Select numOfAdults = new Select(HomePage.NumberOfAdults);
        //numOfAdults = (3)-1 = 2  NUMBER OF ADULTS =2
        numOfAdults.selectByIndex(3);

        //Number of Youths
        Select numOfYouths = new Select(HomePage.NumberOfYouths);
        //numOfAdults = (2)-1 = 1  NUMBER OF Youths =1
        numOfYouths.selectByIndex(2);

        //Submit Button go from Home Page to Flight Page
        HomePage.Submit.submit();

        //FlightPage
        try {
            wait.until(ExpectedConditions.visibilityOf(FlightsPage.Cart));
        } catch (Exception e) {
            System.out.println("Zbyt długi czas oczekiwania przejścia z bookera na step 2- flights : "+ e.getMessage());
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
        //PERSON1
        //Select all titles



        //Selecting title
        wait.until(ExpectedConditions.elementToBeClickable(PassengersPage.Title));
        PassengersPage.TitleAdult1.click();
        Select title1 = new Select(PassengersPage.TitleAdult1);
        title1.selectByIndex(1);
        PassengersPage.TitleAdult1.click();
        Select title2 = new Select(PassengersPage.TitleAdult2);
        title2.selectByIndex(1);

        //Selecting sex
        PassengersPage.Sex1.click();
        Select sex1 = new Select(PassengersPage.Sex1);
        sex1.selectByIndex(1);

        //Enter Name and Surname
        PassengersPage.FirstName.sendKeys(name);
        PassengersPage.FirstName2.sendKeys(name);
        PassengersPage.FirstNameYoung1.sendKeys(name);
        PassengersPage.Surname.sendKeys(surname);
        PassengersPage.Surname2.sendKeys(surname);
        PassengersPage.SurnameYoung1.sendKeys(surname);


        //DATE OF BIRTH

        //Passanger1
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
            System.out.println("Short haul : "+ e.getMessage());
        }
        //Passanger2
        try {
            PassengersPage.DayOfBirth2.click();
            Select day = new Select(PassengersPage.DayOfBirth2);
            day.selectByVisibleText(DayOfBirth);

            PassengersPage.MonthOfBirth2.click();
            Select mouth = new Select(PassengersPage.MonthOfBirth2);
            mouth.selectByVisibleText(MonthOfBirth);

            PassengersPage.YearOfBirth2.click();
            Select year = new Select(PassengersPage.YearOfBirth2);
            year.selectByVisibleText(YearOfBirth);
        } catch (Exception e) {
            System.out.println("Short haul : "+ e.getMessage());
        }

        //Young
        try {
            PassengersPage.DayOfBirthYoung1.click();
            Select day = new Select(PassengersPage.DayOfBirthYoung1);
            day.selectByVisibleText(DayOfBirth);

            PassengersPage.MonthOfBirthYoung1.click();
            Select mouth = new Select(PassengersPage.MonthOfBirthYoung1);
            mouth.selectByVisibleText(MonthOfBirth);

            PassengersPage.YearOfBirthYoung1.click();
            Select year = new Select(PassengersPage.YearOfBirthYoung1);
            year.selectByIndex(2);
        } catch (Exception e) {
            System.out.println("Short haul : "+ e.getMessage());
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
        }catch (Exception e){
            System.out.println("No Popup : "+ e.getMessage());
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
            System.out.println("No Element Continue : "+ e.getMessage());
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
            System.out.println("Problem with Continue button : "+ e.getMessage());
        }
        //END OF TEST
    }

    @Test(dataProvider = "dataEU",groups=("BuyTickets"))
    public void Test_BuyTickets4adults(String localization, String from, String to, XSSFCell departuredata, XSSFCell returndata) throws Exception {

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

        //Given Date in String format
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());

        //Specifying date format that matches the given date
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
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


        //TEST START
        String start = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        System.out.println("Lokalizacja: "+localization+" | Lot z: " +from+ " | Lot do: "+to+" | Data wylotu: "+newDate+" | Data powrotu: "+newDate2+"  Start testu: "+start);
        //Take screenshot
        try {
            GetScreenshot.capture("HomePagePRE2 " + localization + from + to + departuredata + returndata);
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

        //Selecting NUMBER OF PASSENGERS

        HomePage.Passengers.click();

        //Number of Adults
        Select numOfAdults = new Select(HomePage.NumberOfAdults);
        //numOfAdults = (5)-1 = 4  NUMBER OF ADULTS =4
        numOfAdults.selectByIndex(5);


        //Submit Button go from Home Page to Flight Page
        HomePage.Submit.submit();

        //FlightPage
        try {
            wait.until(ExpectedConditions.visibilityOf(FlightsPage.Cart));
        } catch (Exception e) {
            System.out.println("Zbyt długi czas oczekiwania przejścia z bookera na step 2- flights : "+ e.getMessage());
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
        //PERSON1
        //Select all titles



        //Selecting title
        wait.until(ExpectedConditions.elementToBeClickable(PassengersPage.Title));
        PassengersPage.TitleAdult1.click();
        Select title1 = new Select(PassengersPage.TitleAdult1);
        title1.selectByIndex(1);
        PassengersPage.TitleAdult1.click();
        Select title2 = new Select(PassengersPage.TitleAdult2);
        title2.selectByIndex(1);
        PassengersPage.TitleAdult1.click();
        Select title3 = new Select(PassengersPage.TitleAdult3);
        title3.selectByIndex(1);
        PassengersPage.TitleAdult1.click();
        Select title4 = new Select(PassengersPage.TitleAdult4);
        title4.selectByIndex(1);

        //Selecting title

        //Enter Name and Surname
        PassengersPage.FirstName.sendKeys(name);
        PassengersPage.FirstName2.sendKeys(name);
        PassengersPage.FirstName3.sendKeys(name);
        PassengersPage.FirstName4.sendKeys(name);
        PassengersPage.Surname.sendKeys(surname);
        PassengersPage.Surname2.sendKeys(surname);
        PassengersPage.Surname3.sendKeys(surname);
        PassengersPage.Surname4.sendKeys(surname);

        //DATE OF BIRTH

        //Passanger1
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
            System.out.println("Short haul : "+ e.getMessage());
        }
        //Passanger2
        try {
            PassengersPage.DayOfBirth2.click();
            Select day = new Select(PassengersPage.DayOfBirth2);
            day.selectByVisibleText(DayOfBirth);

            PassengersPage.MonthOfBirth2.click();
            Select mouth = new Select(PassengersPage.MonthOfBirth2);
            mouth.selectByVisibleText(MonthOfBirth);

            PassengersPage.YearOfBirth2.click();
            Select year = new Select(PassengersPage.YearOfBirth2);
            year.selectByVisibleText(YearOfBirth);
        } catch (Exception e) {
            System.out.println("Short haul : "+ e.getMessage());
        }
        //Passanger3
        try {
            PassengersPage.DayOfBirth3.click();
            Select day = new Select(PassengersPage.DayOfBirth3);
            day.selectByVisibleText(DayOfBirth);

            PassengersPage.MonthOfBirth3.click();
            Select mouth = new Select(PassengersPage.MonthOfBirth3);
            mouth.selectByVisibleText(MonthOfBirth);

            PassengersPage.YearOfBirth3.click();
            Select year = new Select(PassengersPage.YearOfBirth3);
            year.selectByVisibleText(YearOfBirth);
        } catch (Exception e) {
            System.out.println("Short haul : "+ e.getMessage());
        }
        //Passanger4
        try {
            PassengersPage.DayOfBirth4.click();
            Select day = new Select(PassengersPage.DayOfBirth4);
            day.selectByVisibleText(DayOfBirth);

            PassengersPage.MonthOfBirth4.click();
            Select mouth = new Select(PassengersPage.MonthOfBirth4);
            mouth.selectByVisibleText(MonthOfBirth);

            PassengersPage.YearOfBirth4.click();
            Select year = new Select(PassengersPage.YearOfBirth4);
            year.selectByVisibleText(YearOfBirth);
        } catch (Exception e) {
            System.out.println("Short haul : "+ e.getMessage());
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
        }catch (Exception e){
            System.out.println("No Popup : "+ e.getMessage());
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
            System.out.println("No Element Continue : "+ e.getMessage());
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
            System.out.println("Problem with Continue button : "+ e.getMessage());
        }
        //END OF TEST
    }

    @Test(dataProvider = "dataEU",groups=("BuyTickets"))
    public void Test_BuyTickets2adults2youth(String localization, String from, String to, XSSFCell departuredata, XSSFCell returndata) throws Exception {

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

        //Given Date in String format
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());

        //Specifying date format that matches the given date
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
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


        //TEST START
        String start = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        System.out.println("Lokalizacja: "+localization+" | Lot z: " +from+ " | Lot do: "+to+" | Data wylotu: "+newDate+" | Data powrotu: "+newDate2+"  Start testu: "+start);
        //Take screenshot
        try {
            GetScreenshot.capture("HomePagePRE2 " + localization + from + to + departuredata + returndata);
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

        //Selecting NUMBER OF PASSENGERS

        HomePage.Passengers.click();

        //Number of Adults
        Select numOfAdults = new Select(HomePage.NumberOfAdults);
        //numOfAdults = (3)-1 = 2  NUMBER OF ADULTS =2
        numOfAdults.selectByIndex(3);

        //Number of Youths
        Select numOfYouths = new Select(HomePage.NumberOfYouths);
        //numOfAdults = (3)-1 = 2  NUMBER OF Youths =2
        numOfYouths.selectByIndex(3);

        //Submit Button go from Home Page to Flight Page
        HomePage.Submit.submit();

        //FlightPage
        try {
            wait.until(ExpectedConditions.visibilityOf(FlightsPage.Cart));
        } catch (Exception e) {
            System.out.println("Zbyt długi czas oczekiwania przejścia z bookera na step 2- flights : "+ e.getMessage());
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
        //PERSON1
        //Select all titles



        //Selecting title
        wait.until(ExpectedConditions.elementToBeClickable(PassengersPage.Title));
        PassengersPage.TitleAdult1.click();
        Select title1 = new Select(PassengersPage.TitleAdult1);
        title1.selectByIndex(1);
        PassengersPage.TitleAdult1.click();
        Select title2 = new Select(PassengersPage.TitleAdult2);
        title2.selectByIndex(1);

        //Selecting sex
        PassengersPage.Sex1.click();
        Select sex1 = new Select(PassengersPage.Sex1);
        sex1.selectByIndex(1);
        PassengersPage.Sex2.click();
        Select sex2 = new Select(PassengersPage.Sex2);
        sex2.selectByIndex(1);

        //Enter Name and Surname
        PassengersPage.FirstName.sendKeys(name);
        PassengersPage.FirstName2.sendKeys(name);
        PassengersPage.FirstNameYoung1.sendKeys(name);
        PassengersPage.FirstNameYoung2.sendKeys(name);
        PassengersPage.Surname.sendKeys(surname);
        PassengersPage.Surname2.sendKeys(surname);
        PassengersPage.SurnameYoung1.sendKeys(surname);
        PassengersPage.SurnameYoung2.sendKeys(surname);


        //DATE OF BIRTH

        //Passanger1
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
            System.out.println("Short haul : "+ e.getMessage());
        }
        //Passanger2
        try {
            PassengersPage.DayOfBirth2.click();
            Select day = new Select(PassengersPage.DayOfBirth2);
            day.selectByVisibleText(DayOfBirth);

            PassengersPage.MonthOfBirth2.click();
            Select mouth = new Select(PassengersPage.MonthOfBirth2);
            mouth.selectByVisibleText(MonthOfBirth);

            PassengersPage.YearOfBirth2.click();
            Select year = new Select(PassengersPage.YearOfBirth2);
            year.selectByVisibleText(YearOfBirth);
        } catch (Exception e) {
            System.out.println("Short haul : "+ e.getMessage());
        }

        //Young
        try {
            PassengersPage.DayOfBirthYoung1.click();
            Select day = new Select(PassengersPage.DayOfBirthYoung1);
            day.selectByVisibleText(DayOfBirth);

            PassengersPage.MonthOfBirthYoung1.click();
            Select mouth = new Select(PassengersPage.MonthOfBirthYoung1);
            mouth.selectByVisibleText(MonthOfBirth);

            PassengersPage.YearOfBirthYoung1.click();
            Select year = new Select(PassengersPage.YearOfBirthYoung1);
            year.selectByIndex(2);
        } catch (Exception e) {
            System.out.println("Short haul : "+ e.getMessage());
        }
        //Young2
        try {
            PassengersPage.DayOfBirthYoung2.click();
            Select day = new Select(PassengersPage.DayOfBirthYoung2);
            day.selectByVisibleText(DayOfBirth);

            PassengersPage.MonthOfBirthYoung2.click();
            Select mouth = new Select(PassengersPage.MonthOfBirthYoung2);
            mouth.selectByVisibleText(MonthOfBirth);

            PassengersPage.YearOfBirthYoung2.click();
            Select year = new Select(PassengersPage.YearOfBirthYoung2);
            year.selectByIndex(2);
        } catch (Exception e) {
            System.out.println("Short haul : "+ e.getMessage());
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
        }catch (Exception e){
            System.out.println("No Popup : "+ e.getMessage());
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
            System.out.println("No Element Continue : "+ e.getMessage());
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
            System.out.println("Problem with Continue button : "+ e.getMessage());
        }
        //END OF TEST
    }


    @Test(dataProvider = "dataEU",groups=("BuyTickets"))
    public void Test_BuyTickets1adult1infant(String localization, String from, String to, XSSFCell departuredata, XSSFCell returndata) throws Exception {

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

        //Given Date in String format
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());

        //Specifying date format that matches the given date
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
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


        //TEST START
        String start = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        System.out.println("Lokalizacja: "+localization+" | Lot z: " +from+ " | Lot do: "+to+" | Data wylotu: "+newDate+" | Data powrotu: "+newDate2+"  Start testu: "+start);
        //Take screenshot
        try {
            GetScreenshot.capture("HomePagePRE2 " + localization + from + to + departuredata + returndata);
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

        //Selecting NUMBER OF PASSENGERS

        HomePage.Passengers.click();

        //Number of Youths
        Select numOfAdults = new Select(HomePage.NumberOfInfants);
        //numOfInfants = (2)-1 = 1  NUMBER OF Youths =1
        numOfAdults.selectByIndex(2);

        //Submit Button go from Home Page to Flight Page
        HomePage.Submit.submit();

        //FlightPage
        try {
            wait.until(ExpectedConditions.visibilityOf(FlightsPage.Cart));
        } catch (Exception e) {
            System.out.println("Zbyt długi czas oczekiwania przejścia z bookera na step 2- flights : "+ e.getMessage());
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
        //PERSON1
        //Select all titles



        //Selecting title
        wait.until(ExpectedConditions.elementToBeClickable(PassengersPage.Title));
        PassengersPage.TitleAdult1.click();
        Select title1 = new Select(PassengersPage.TitleAdult1);
        title1.selectByIndex(1);
        //Selecting sex
        PassengersPage.Sex1.click();
        Select sex1 = new Select(PassengersPage.Sex1);
        sex1.selectByIndex(1);

        //Selecting title

        //Enter Name and Surname
        PassengersPage.FirstName.sendKeys(name);
        PassengersPage.FirstNameInfant1.sendKeys(name);
        PassengersPage.Surname.sendKeys(surname);
        PassengersPage.SurnameInfant1.sendKeys(surname);


        //DATE OF BIRTH

        //Passanger1
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
            System.out.println("Short haul : "+ e.getMessage());
        }
        //Passanger2
        try {
            PassengersPage.DayOfBirthInfant1.click();
            Select day = new Select(PassengersPage.DayOfBirthInfant1);
            day.selectByVisibleText(DayOfBirth);

            PassengersPage.MonthOfBirthInfant1.click();
            Select mouth = new Select(PassengersPage.MonthOfBirthInfant1);
            mouth.selectByVisibleText(MonthOfBirth);

            PassengersPage.YearOfBirthInfant1.click();
            Select year = new Select(PassengersPage.YearOfBirthInfant1);
            year.selectByIndex(2);
        } catch (Exception e) {
            System.out.println("Short haul : "+ e.getMessage());
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
        }catch (Exception e){
            System.out.println("No Popup : "+ e.getMessage());
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
            System.out.println("No Element Continue : "+ e.getMessage());
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
            System.out.println("Problem with Continue button : "+ e.getMessage());
        }
        //END OF TEST
    }

    @Test(dataProvider = "dataEU",groups=("BuyTickets"))
    public void Test_BuyTickets2adults1child(String localization, String from, String to, XSSFCell departuredata, XSSFCell returndata) throws Exception {

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

        //Given Date in String format
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());

        //Specifying date format that matches the given date
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
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


        //TEST START
        String start = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        System.out.println("Lokalizacja: "+localization+" | Lot z: " +from+ " | Lot do: "+to+" | Data wylotu: "+newDate+" | Data powrotu: "+newDate2+"  Start testu: "+start);
        //Take screenshot
        try {
            GetScreenshot.capture("HomePagePRE2 " + localization + from + to + departuredata + returndata);
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

        //Selecting NUMBER OF PASSENGERS

        HomePage.Passengers.click();

        //Number of Adults
        Select numOfAdults = new Select(HomePage.NumberOfAdults);
        //numOfAdults = (3)-1 = 2  NUMBER OF ADULTS =2
        numOfAdults.selectByIndex(3);

        //Number of Child
        Select numOfChilds = new Select(HomePage.NumberOfChildrens);
        //numOfChilds = (2)-1 = 1  NUMBER OF Childrens =1
        numOfChilds.selectByIndex(2);

        //Submit Button go from Home Page to Flight Page
        HomePage.Submit.submit();

        //FlightPage
        try {
            wait.until(ExpectedConditions.visibilityOf(FlightsPage.Cart));
        } catch (Exception e) {
            System.out.println("Zbyt długi czas oczekiwania przejścia z bookera na step 2- flights : "+ e.getMessage());
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
        //PERSON1
        //Select all titles



        //Selecting title
        wait.until(ExpectedConditions.elementToBeClickable(PassengersPage.Title));
        PassengersPage.TitleAdult1.click();
        Select title1 = new Select(PassengersPage.TitleAdult1);
        title1.selectByIndex(1);
        PassengersPage.TitleAdult1.click();
        Select title2 = new Select(PassengersPage.TitleAdult2);
        title2.selectByIndex(1);

        //Selecting sex
        PassengersPage.Sex1.click();
        Select sex1 = new Select(PassengersPage.Sex1);
        sex1.selectByIndex(1);

        //Enter Name and Surname
        PassengersPage.FirstName.sendKeys(name);
        PassengersPage.FirstName2.sendKeys(name);
        PassengersPage.FirstNamechild1.sendKeys(name);
        PassengersPage.Surname.sendKeys(surname);
        PassengersPage.Surname2.sendKeys(surname);
        PassengersPage.Surnamechild1.sendKeys(surname);


        //DATE OF BIRTH

        //Passanger1
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
            System.out.println("Short haul : "+ e.getMessage());
        }
        //Passanger2
        try {
            PassengersPage.DayOfBirth2.click();
            Select day = new Select(PassengersPage.DayOfBirth2);
            day.selectByVisibleText(DayOfBirth);

            PassengersPage.MonthOfBirth2.click();
            Select mouth = new Select(PassengersPage.MonthOfBirth2);
            mouth.selectByVisibleText(MonthOfBirth);

            PassengersPage.YearOfBirth2.click();
            Select year = new Select(PassengersPage.YearOfBirth2);
            year.selectByVisibleText(YearOfBirth);
        } catch (Exception e) {
            System.out.println("Short haul : "+ e.getMessage());
        }

        //Child
        try {
            PassengersPage.DayOfBirthchild1.click();
            Select day = new Select(PassengersPage.DayOfBirthchild1);
            day.selectByVisibleText(DayOfBirth);

            PassengersPage.MonthOfBirthchild1.click();
            Select mouth = new Select(PassengersPage.MonthOfBirthchild1);
            mouth.selectByVisibleText(MonthOfBirth);

            PassengersPage.YearOfBirthchild1.click();
            Select year = new Select(PassengersPage.YearOfBirthchild1);
            year.selectByIndex(2);
        } catch (Exception e) {
            System.out.println("Short haul : "+ e.getMessage());
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
        }catch (Exception e){
            System.out.println("No Popup : "+ e.getMessage());
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
            System.out.println("No Element Continue : "+ e.getMessage());
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
            System.out.println("Problem with Continue button : "+ e.getMessage());
        }
        //END OF TEST
    }
    @Test(dataProvider = "dataEU",groups=("BuyTickets"))
    public void Test_BuyTickets1adult2youth(String localization, String from, String to, XSSFCell departuredata, XSSFCell returndata) throws Exception {

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

        //Given Date in String format
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());

        //Specifying date format that matches the given date
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
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


        //TEST START
        String start = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        System.out.println("Lokalizacja: "+localization+" | Lot z: " +from+ " | Lot do: "+to+" | Data wylotu: "+newDate+" | Data powrotu: "+newDate2+"  Start testu: "+start);
        //Take screenshot
        try {
            GetScreenshot.capture("HomePagePRE2 " + localization + from + to + departuredata + returndata);
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

        //Selecting NUMBER OF PASSENGERS

        HomePage.Passengers.click();

        //Number of Adults
        Select numOfAdults = new Select(HomePage.NumberOfAdults);
        //numOfAdults = (2)-1 = 1  NUMBER OF ADULTS =1
        numOfAdults.selectByIndex(2);

        //Number of Youths
        Select numOfYouths = new Select(HomePage.NumberOfYouths);
        //numOfAdults = (3)-1 = 2  NUMBER OF Youths =2
        numOfYouths.selectByIndex(3);

        //Submit Button go from Home Page to Flight Page
        HomePage.Submit.submit();

        //FlightPage
        try {
            wait.until(ExpectedConditions.visibilityOf(FlightsPage.Cart));
        } catch (Exception e) {
            System.out.println("Zbyt długi czas oczekiwania przejścia z bookera na step 2- flights : "+ e.getMessage());
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
        //PERSON1
        //Select all titles



        //Selecting title
        wait.until(ExpectedConditions.elementToBeClickable(PassengersPage.Title));
        PassengersPage.TitleAdult1.click();
        Select title1 = new Select(PassengersPage.TitleAdult1);
        title1.selectByIndex(1);

        //Selecting sex
        PassengersPage.Sex1.click();
        Select sex1 = new Select(PassengersPage.Sex1);
        sex1.selectByIndex(1);
        PassengersPage.Sex2.click();
        Select sex2 = new Select(PassengersPage.Sex2);
        sex2.selectByIndex(1);

        //Enter Name and Surname
        PassengersPage.FirstName.sendKeys(name);
        PassengersPage.FirstNameYoung1.sendKeys(name);
        PassengersPage.FirstNameYoung2.sendKeys(name);
        PassengersPage.Surname.sendKeys(surname);
        PassengersPage.SurnameYoung1.sendKeys(surname);
        PassengersPage.SurnameYoung2.sendKeys(surname);


        //DATE OF BIRTH

        //Passanger1
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
            System.out.println("Short haul : "+ e.getMessage());
        }

        //Young
        try {
            PassengersPage.DayOfBirthYoung1.click();
            Select day = new Select(PassengersPage.DayOfBirthYoung1);
            day.selectByVisibleText(DayOfBirth);

            PassengersPage.MonthOfBirthYoung1.click();
            Select mouth = new Select(PassengersPage.MonthOfBirthYoung1);
            mouth.selectByVisibleText(MonthOfBirth);

            PassengersPage.YearOfBirthYoung1.click();
            Select year = new Select(PassengersPage.YearOfBirthYoung1);
            year.selectByIndex(2);
        } catch (Exception e) {
            System.out.println("Short haul : "+ e.getMessage());
        }
        //Young2
        try {
            PassengersPage.DayOfBirthYoung2.click();
            Select day = new Select(PassengersPage.DayOfBirthYoung2);
            day.selectByVisibleText(DayOfBirth);

            PassengersPage.MonthOfBirthYoung2.click();
            Select mouth = new Select(PassengersPage.MonthOfBirthYoung2);
            mouth.selectByVisibleText(MonthOfBirth);

            PassengersPage.YearOfBirthYoung2.click();
            Select year = new Select(PassengersPage.YearOfBirthYoung2);
            year.selectByIndex(2);
        } catch (Exception e) {
            System.out.println("Short haul : "+ e.getMessage());
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
        }catch (Exception e){
            System.out.println("No Popup : "+ e.getMessage());
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
            System.out.println("No Element Continue : "+ e.getMessage());
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
            System.out.println("Problem with Continue button : "+ e.getMessage());
        }
        //END OF TEST
    }

    //Excel configuration

    @DataProvider(name ="dataEU")
    public Object[][] passDataEU()
    {
        ExcelDataConfig config = new ExcelDataConfig("C:\\Users\\Public\\LOT\\MultiTickets.xlsx");
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

    //Excel configuration

    //After and of Class test
    @AfterMethod(alwaysRun = true)
    public void tearDown1() throws Exception {
        driver.manage().deleteAllCookies();
        driver.quit();
    }


}

