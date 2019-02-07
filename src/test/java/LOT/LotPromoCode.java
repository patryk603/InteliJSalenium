package LOT;

import DDT.ExcelDataConfig;
import Main.BasedTimeFunctions;
import Main.Datas;
import Main.GetScreenshot;
import Main.MainTest;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LotPromoCode extends MainTest {

    private static String baseUrl = "http://www.lot.com/";
    private static String promo = "G34jfsa451Af";

    private double basketPriceBefore = 0;
    private double basketPriceAfter = 0;
    private double finalPrice = 0;

    private int time = 10000;//7000
    private int sleepTime = 2500;//2500

    Datas dat = new Datas();

    @BeforeTest(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
        PageFactory.initElements(driver, HomePage.class);
        PageFactory.initElements(driver, FlightsPage.class);
        PageFactory.initElements(driver, PassengersPage.class);
        PageFactory.initElements(driver, ExtrasPage.class);
        PageFactory.initElements(driver, PaymentPage.class);
    }

    @Test(dataProvider = "data", groups = "ButTickets")
    public void LotPromoCode(String localization, String from, String to, XSSFCell departureData, XSSFCell returnData) throws Exception {

        $("http://www.lot.com/");

        BasedTimeFunctions btf = new BasedTimeFunctions();
        btf.DataChecker(baseUrl, localization, from, to, departureData, returnData);

        /*
         * ******************************************************************************** HomePage
         * */

        Thread.sleep(sleepTime);

        //Select from flight
        $(HomePage.FromListButton).waitUntil(enabled, time).click();
        $(HomePage.FromToText).waitUntil(enabled, time).sendKeys(from);

        $(driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*=" + from + "]"))).click();

        if ($(HomePage.ToToText).isDisplayed()){
            $(HomePage.ToToText).sendKeys(to);
            $(driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*=" + to + "]"))).click();
        } else {
            System.out.println("Need additional click");
            $(HomePage.Lot).click();
            $(HomePage.ToList).waitUntil(enabled,time).click();
            $(HomePage.ToToText).waitUntil(enabled,time).sendKeys(to);
            $(driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*=" + to + "]"))).click();
        }

        $(HomePage.Lot).click();

        //Selecting Departure Data
        $(HomePage.DepartureDate).clear();
        $(HomePage.DepartureDate).sendKeys(btf.getStartDate());

        //Selecting Return Date
        $(HomePage.ReturnDate).clear();
        $(HomePage.ReturnDate).sendKeys(btf.getEndDate());

        $(HomePage.Lot).click();

        $(HomePage.PromoCodeActive).click();
        $(HomePage.PromoCode).clear();
        $(HomePage.PromoCode).sendKeys(promo);

//        if (from.equals("EWR") || from.equals("ORD") || from.equals("YYZ") || from.equals("LAX") || from.equals("NRT") || from.equals("PEK") || from.equals("SIN")){
//            longOrShort = true;
//        } else if (to.equals("EWR") || to.equals("ORD") || to.equals("YYZ") || to.equals("LAX") || to.equals("NRT") || to.equals("PEK") || to.equals("SIN")){
//            longOrShort = true;
//        } else {
//            longOrShort = false;
//        }

        $(HomePage.Submit).submit();

        /*
        * ******************************************************************************** FlightPage
        * */

        Thread.sleep(sleepTime);

        //Lack of available flights on this date
        if ($(FlightsPage.FlightNotAvailable).isDisplayed()){
            $(FlightsPage.FlightNotAvailable).click();
            Assert.fail("There is no available flights on that days");
        } else {
            System.out.println("There is no message about inaccessible flights on that days");
        }

        //Checking appearing of basket
        if ($(FlightsPage.Cart).isDisplayed()){
            System.out.println("Cart is available");
        } else {
            Assert.fail("Cart is not available");
        }

        //Popup handle
        if ($(FlightsPage.OK).isDisplayed()){
            $(FlightsPage.OK).click();
            System.out.println("Pop-Up: " + " \"" + FlightsPage.OkPopUpText.getText() + "\"");
        } else {
            System.out.println("Flights are available on that days");
        }

        //JSESSION ID
        Cookie cookie2= driver.manage().getCookieNamed("JSESSIONID");
        System.out.println("FlightPage JSESSIONID: "+cookie2.getValue());

        //Take screenshot
        try {
            GetScreenshot.capture("FlightPage " + localization + from + to + departureData + returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Thread.sleep(sleepTime);

        if ($(FlightsPage.NoLongerAvailable).isDisplayed()){
            Assert.fail("Selected flights are no longer available");
        } else {
            System.out.println("There is no message about the availability of flights");
        }

        //Selecting First ACTIVE Ticket TO
        if ($(FlightsPage.FirstTO).isDisplayed()){
            $(FlightsPage.FirstTO).click();
        } else {
            $(FlightsPage.FirstTO1).click();
        }

        Thread.sleep(1000);

        String basketAfterDiscount = FlightsPage.BasketAfterDiscount.getText();
        basketPriceAfter = Double.valueOf(basketAfterDiscount.replaceAll("\\s", "").replaceAll(",", "."));
        String basketBeforeDiscount = FlightsPage.BasketBeforeDiscount.getText();
        basketPriceBefore = Double.valueOf(basketBeforeDiscount.replaceAll("\\s", "").replaceAll(",", "."));

//        if (basketPriceAfter < basketPriceBefore){
//            System.out.println("\nCena jednego biletu niższa");
//        } else {
//            System.out.println("\nBrak obniżki");
//        }

        if (basketPriceAfter == 0 || basketPriceBefore == 0){
            Assert.fail("NO DISCOUNT!!!");
        }

        System.out.println("1 ticket price:\n before - " + basketPriceBefore + " || after - " + basketPriceAfter);
        Assert.assertTrue(basketPriceAfter < basketPriceBefore);

        //Selecting First ACTIVE Ticket BACK
        if ($(FlightsPage.FirstBack).isDisplayed()){
            $(FlightsPage.FirstBack).click();
        } else {
            $(FlightsPage.FirstBack2).click();
        }

        Thread.sleep(1000);

        basketAfterDiscount = FlightsPage.BasketAfterDiscount.getText();
        basketPriceAfter = Double.valueOf(basketAfterDiscount.replaceAll("\\s", "").replaceAll(",", "."));
        basketBeforeDiscount = FlightsPage.BasketBeforeDiscount.getText();
        basketPriceBefore = Double.valueOf(basketBeforeDiscount.replaceAll("\\s", "").replaceAll(",", "."));

//        if (basketPriceAfter < basketPriceBefore){
//            System.out.println("\nCena biletów niższa\n");
//        } else {
//            System.out.println("\nBrak obniżki\n");
//        }

        System.out.println("2 ticket price:\n before - " + basketPriceBefore + " || after - " + basketPriceAfter);
        Assert.assertTrue(basketPriceAfter < basketPriceBefore);

        //Button Continue
        if ($(FlightsPage.BigContinue).isDisplayed()){
            $(FlightsPage.BigContinue).click();
        } else {
            $(FlightsPage.Popup).click();
            Thread.sleep(1000);
            $(FlightsPage.BigContinue).click();
            System.out.println("Accepted the alert successfully.");
            System.out.println("No Element Continue");
        }

        //Upsell Popup
        GetScreenshot.capture("Upsell/" + from + "/" + to);

        if ($(FlightsPage.NoThanks).isDisplayed()){
            $(FlightsPage.NoThanks).click();
        } else {
            System.out.println("No Upsell for: " + localization +"/"+ from+"/"+ to +"/"+ departureData +"/"+ returnData);
        }

        /*
         * ******************************************************************************** PassengerPage
         * */

        Thread.sleep(sleepTime);

        //Selecting title
        $(PassengersPage.Title).waitUntil(enabled, time).click();
        Select title = new Select(PassengersPage.Title);
        title.selectByIndex(1);

        //Enter Name and Surname
        $(PassengersPage.FirstName).sendKeys(dat.getName());
        $(PassengersPage.Surname).sendKeys(dat.getSurname());

        //DATE OF BIRTH
        if ($(PassengersPage.DayOfBirth).isDisplayed()){
            $(PassengersPage.DayOfBirth).click();
            Select day = new Select(PassengersPage.DayOfBirth);
            day.selectByVisibleText(dat.getDayOfBirth());

            $(PassengersPage.MonthOfBirth).click();
            Select month = new Select(PassengersPage.MonthOfBirth);
            month.selectByVisibleText(dat.getMonthOfBirth());

            $(PassengersPage.YearOfBirth).click();
            Select year = new Select(PassengersPage.YearOfBirth);
            year.selectByVisibleText(dat.getYearOfBirth());
        } else {
            System.out.println("Short haul");
        }

        //Passengers data: Email Phone
        $(PassengersPage.Email).sendKeys(dat.getEmail());
        $(PassengersPage.Phone).sendKeys(dat.getPhone());

        //Waiting and Clicking on "I have read and I accept Terms of Use, Privacy Policy and Terms and Conditions of Transportation (Excerpt from clause) *"
        $(PassengersPage.CheckboxAccept).waitUntil(enabled,time).click();

        //Waiting and Clicking on Big Continue Button. Next try to Click Accept User Data Popup.
        $(PassengersPage.BigContinue).waitUntil(enabled,time).click();

        Thread.sleep(sleepTime);

        if ($(PassengersPage.PopupAccept).isDisplayed()){
            $(PassengersPage.PopupAccept).click();
        } else {
            System.out.println("No Pop-up");
        }

        /*
         * ******************************************************************************** ExtrasPage
         * */

        Thread.sleep(sleepTime);

        $(ExtrasPage.Column1).waitUntil(visible, time);
        try {
            GetScreenshot.capture("ExtraPage " + localization + from + to + departureData + returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        if ($(ExtrasPage.BigContinue).isEnabled()){
//            $(ExtrasPage.BigContinue).click();
//        } else {
//            System.out.println("No continue element");
//        }

        try {
            ExtrasPage.BigContinue.click();
        } catch (Exception e){
            System.out.println("Błąd " + e.getMessage());
        }

        /*
         * ******************************************************************************** PaymentPage
         * */

        Thread.sleep(sleepTime);

        $(PaymentPage.BookNr).waitUntil(visible, time);
        try {
            GetScreenshot.capture("PaymentPage " + localization + from + to + departureData + returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //BookingNumber
        String BookNumber = PaymentPage.BookNr.getText();
        System.out.println(BookNumber);
        //BookingNumber

        //Credit Card Data
        $(PaymentPage.CardNr).sendKeys(dat.getCreditCard());
        $(PaymentPage.Cvc).sendKeys(dat.getCvv());
        $(PaymentPage.Name).sendKeys(dat.getName());
        $(PaymentPage.City).sendKeys(dat.getCity());
        $(PaymentPage.PostalCode).sendKeys(dat.getZipCode());
        $(PaymentPage.Street).sendKeys(dat.getStreet());

        //DropdownLists
        $(PaymentPage.Month).click();
        Select month = new Select(PaymentPage.Month);
        month.selectByVisibleText(dat.getMonth());

        $(PaymentPage.Year).click();
        Select cardYear = new Select(PaymentPage.Year);
        cardYear.selectByVisibleText(dat.getYear());

        $(PaymentPage.Country).click();
        Select country = new Select(PaymentPage.Country);
        country.selectByIndex(167);
        $(PaymentPage.Lot).click();

        Thread.sleep(sleepTime);

        String finalPriceDiscount = PaymentPage.FinalPriceAfterDiscount.getText();
        finalPrice = Double.valueOf(finalPriceDiscount.replaceAll("\\s", "").replaceAll(",", "."));

//        if (basketPriceBefore > finalPrice){
//            System.out.println("Cena została obniżona");
//        } else {
//            System.out.println("Zniżka nie zadziała");
//        }

        System.out.println("2 ticket price at the end:\n before - " + basketPriceBefore + " || final - " + finalPrice);
        Assert.assertTrue(basketPriceBefore > finalPrice);

        //Waiting and Clicking on Big Continue Button.
        if ($(PaymentPage.BigContinue).isDisplayed()){
            $(PaymentPage.BigContinue).click();
        } else {
            System.out.println("Problem with continue button");
        }
        //END OF TEST
        driver.manage().deleteCookieNamed("JSESSIONID");

    }

    @DataProvider(name = "data")
    public Object[][] passData() {
        ExcelDataConfig config = new ExcelDataConfig("C:\\Users\\Public\\LOT\\Discount.xlsx");
        int rows = config.getRowCount(0);
        Object[][] data = new Object[rows][5];

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
    public void tearDown() throws Exception {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
