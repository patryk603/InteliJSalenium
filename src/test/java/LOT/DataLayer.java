package LOT;

import DDT.ExcelDataConfig;
import Main.GetScreenshot;
import Main.MainTest;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pageObjects.*;
import org.testng.asserts.IAssert;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class DataLayer extends MainTest{
    private String baseUrl;

    @BeforeTest(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        baseUrl = "http://www.lot.com/";
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        PageFactory.initElements(driver, HomePage.class);
        PageFactory.initElements(driver, FlightsPage.class);
        PageFactory.initElements(driver, PassengersPage.class);
        PageFactory.initElements(driver, ExtrasPage.class);
        PageFactory.initElements(driver, PaymentPage.class);
    }


    @Test(dataProvider = "data",groups=("DataLayer"))
    public void DataLayerTest(String market, String language, String departureport, String arrivalport, XSSFCell bookingwindow, XSSFCell staylength, XSSFCell ADT, XSSFCell YTH, XSSFCell CHD, XSSFCell INF, String cabinclass, String page_version) throws Exception {

        //Data Config
        long SL = (long) staylength.getNumericCellValue();
        long SL1 = (long) staylength.getNumericCellValue()+1;
        long ADT1 = (long) ADT.getNumericCellValue();
        long YTH1 = (long) YTH.getNumericCellValue();
        long CHD1 = (long) CHD.getNumericCellValue();
        long INF1 = (long) INF.getNumericCellValue();


        //Data From Excel configuration
        market = market.toLowerCase();
        language = language.toLowerCase();


        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.get(baseUrl + market + "/" + language);
        ImplicitWait(driver);
        //TIME Configuration
        String dat1 = String.valueOf(bookingwindow);
        if (dat1.length() > 0) {
            dat1 = dat1.substring(0, (dat1.length() - 2));
        }

        String dat2 = String.valueOf(staylength);
        if (dat2.length() > 0) {
            dat2 = dat2.substring(0, (dat2.length() - 2));
        }

        //Data Formats
        String eutime = "dd.MM.yyyy";
        String hutime = "yy.MM.dd";
        String ustime = "MM.dd.yyyy";
        String unify = "d.M.yy";

        String actualtime;
        if (market.contains("us")) {
            actualtime = ustime;
        } else if (market.startsWith("hu/hu")) {
            actualtime = hutime;
        } else {
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
        b.add(Calendar.DAY_OF_MONTH, Integer.parseInt(String.valueOf(dat1))+Integer.parseInt(String.valueOf(dat2)));

        //Date after adding the days to the given date
        String newDate = sdf.format(c.getTime()); //departure
        String newDate2 = sdf.format(b.getTime()); //return

        //Displaying the new Date after addition of Days

        //Add Date for check in DataLayer
        //Given Date in String format
        String unitime = new SimpleDateFormat(unify).format(Calendar.getInstance().getTime());

        //Specifying date format that matches the given date
        SimpleDateFormat sdf2 = new SimpleDateFormat(unify);


        Calendar v = Calendar.getInstance();
        Calendar n = Calendar.getInstance();
        try {
            //Setting the date to the given date
            v.setTime(sdf2.parse(unitime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            //Setting the date to the given date
            n.setTime(sdf2.parse(unitime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Number of Days to add
        v.add(Calendar.DAY_OF_MONTH, Integer.parseInt(String.valueOf(dat1)));
        n.add(Calendar.DAY_OF_MONTH, Integer.parseInt(String.valueOf(dat2)));

        //Date after adding the days to the given date
        String DepDate = sdf2.format(c.getTime()); //departure
        String RetDate = sdf2.format(b.getTime()); //return

        //Format time
        System.out.println("DepDate1: " + DepDate);
        System.out.println("RetDate1: " + RetDate);
        String DDate=DepDate.replaceAll("\\.","/");
        String RDate=RetDate.replaceAll("\\.","/");
        //TIME

        //TEST START
        ZonedDateTime start = ZonedDateTime.now();
        System.out.println("Test started for: "+market + "/" + language + "/" + departureport + "/" + arrivalport + "/" + bookingwindow + "/" + staylength + "/" + ADT + "/" + YTH + "/" + CHD + "/" + INF + "/" + cabinclass + "/" + page_version);

        //Take screenshot
        try {
            GetScreenshot.capture("HomePage " + market + "/" + language + departureport + arrivalport + bookingwindow + staylength);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Selecting From Flight
        wait.until(ExpectedConditions.elementToBeClickable(HomePage.FromListButton));
        HomePage.FromListButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(HomePage.FromToText));
        HomePage.FromToText.sendKeys(departureport);

        driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*=" + departureport + "]")).click();
        //Click on home page

        Thread.sleep(1000);

        //Selecting To Flight

        HomePage.Lot.click();
        wait.until(ExpectedConditions.elementToBeClickable(HomePage.ToList));
        HomePage.ToList.click();
        wait.until(ExpectedConditions.elementToBeClickable(HomePage.ToToText));
        HomePage.ToToText.sendKeys(arrivalport);
        driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*=" + arrivalport + "]")).click();


        //Click on home page
        HomePage.Lot.click();

        //Selecting Departure Data
        HomePage.DepartureDate.clear();
        HomePage.DepartureDate.sendKeys(newDate);


        //OW vs RT
        System.out.println("SL: " + SL);
        if (SL == -1) {
            wait.until(ExpectedConditions.elementToBeClickable(HomePage.OneWayFlight));
            HomePage.OneWayFlight.click();
            System.out.println("One Way Flight ");

        } else {
            //Selecting Return Date
            wait.until(ExpectedConditions.elementToBeClickable(HomePage.RTFlight));
            HomePage.RTFlight.click();
            wait.until(ExpectedConditions.elementToBeClickable(HomePage.ReturnDate));
            HomePage.ReturnDate.clear();
            HomePage.ReturnDate.sendKeys(newDate2);
            System.out.println(" RT selected ");
        }

        HomePage.Lot.click();

        //Data Config
        int adt = (int) ADT.getNumericCellValue() + 1;
        int yth = (int) YTH.getNumericCellValue() + 1;
        int chd = (int) CHD.getNumericCellValue() + 1;
        int inf = (int) INF.getNumericCellValue() + 1;

        //Selecting NUMBER OF PASSENGERS

        if (adt > 0) {
            wait.until(ExpectedConditions.elementToBeClickable(HomePage.Passengers));
            HomePage.Passengers.click();
            wait.until(ExpectedConditions.elementToBeClickable(HomePage.NumberOfAdults));
            Select numOfAdults = new Select(HomePage.NumberOfAdults);
            System.out.println("Adults: " + adt);
            numOfAdults.selectByIndex(adt);
        }

        if (yth > 0) {
            wait.until(ExpectedConditions.elementToBeClickable(HomePage.NumberOfYouths));
            Select numOfYouths = new Select(HomePage.NumberOfYouths);
            System.out.println("Youths: " + yth);
            numOfYouths.selectByIndex(yth);
        }

        if (chd > 0) {
            wait.until(ExpectedConditions.elementToBeClickable(HomePage.NumberOfChildrens));
            Select numOfAdults = new Select(HomePage.NumberOfChildrens);
            System.out.println("Children: " + chd);
            numOfAdults.selectByIndex(chd);
        }

        if (inf > 0) {
            wait.until(ExpectedConditions.elementToBeClickable(HomePage.NumberOfInfants));
            Select numOfAdults = new Select(HomePage.NumberOfInfants);
            System.out.println("Infants: " + inf);
            numOfAdults.selectByIndex(inf);
        }

        //Cabin Class select
        if (cabinclass.contains("E")) {
            wait.until(ExpectedConditions.elementToBeClickable(HomePage.TicketClass));
            HomePage.TicketClass.click();
            HomePage.Economy.click();
            System.out.println(" Economy class selected ");

        } else if (cabinclass.contains("B")) {
            wait.until(ExpectedConditions.elementToBeClickable(HomePage.TicketClass));
            HomePage.TicketClass.click();
            HomePage.Business.click();
            System.out.println(" Business class selected ");

        } else if (cabinclass.contains("P")) {
            wait.until(ExpectedConditions.elementToBeClickable(HomePage.TicketClass));
            HomePage.TicketClass.click();
            HomePage.Premium.click();
            System.out.println(" Premium class selected ");

        } else {
            System.out.println(" Please check |cabinclass| value in excel = " + cabinclass);
        }
        Thread.sleep(11000);
        //Submit Button go from Home Page to Flight Page
        HomePage.Submit.submit();

        //FlightPage

        try {
            wait.until(ExpectedConditions.visibilityOf(FlightsPage.Cart));
        } catch (Exception e) {
            System.out.println("To long loading time of booker page | step 2- flights : " + e.getMessage());
        }

        //JSESSION ID
        Cookie cookie = driver.manage().getCookieNamed("JSESSIONID");
        System.out.println("JSESSIONID: " + cookie.getValue());

        //Popup handle
        try {
            FlightsPage.OK.click();
        } catch (Exception e) {
            //System.out.println("Flight are available in that date : " + e.getMessage());
        }

        //JSESSION ID
        Cookie jcookie = driver.manage().getCookieNamed("JSESSIONID");
        System.out.println("JSESSIONID: " + jcookie.getValue());

        //Take screenshot
        try {
            GetScreenshot.capture("FlightPage " + market + "/" + language + departureport + arrivalport + bookingwindow + staylength);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread.sleep(6000);


        //Data Layer

        SoftAssert assertion=new SoftAssert();

        //market
        market = market.toUpperCase();
        Object DLmarket = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"market\"]");
        System.out.println("Data Layer |market| From LOT.COM: " + DLmarket);
        assertion.assertEquals(market, DLmarket, "Market does not match");

        //language
        language = language.toUpperCase();
        Object DLlanguage = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"language\"]");
        System.out.println("Data Layer |language| From LOT.COM: " + DLlanguage);
        assertion.assertEquals(language, DLlanguage, "Language does not match");

        //pageid
        Object pageid = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"pageid\"]");
        System.out.println("Data Layer |pageid| From LOT.COM: " + pageid);
        assertion.assertEquals(pageid, "FLIGHTS", "Pageid should be: FLIGHTS, is: " + pageid);


        if (SL == -1) {
            //triptype
            Object triptype = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"triptype\"]");
            System.out.println("Data Layer |triptype| From LOT.COM: " + triptype);
            assertion.assertEquals(triptype, "O", "Triptype should be: One Way, is: " + triptype);

        } else {
            //triptype
            Object triptype = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"triptype\"]");
            System.out.println("Data Layer |triptype| From LOT.COM: " + triptype);
            assertion.assertEquals(triptype, "R", "Triptype should be: Run Trip, is: " + triptype);
        }

        //departureloc
        Object departureloc = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"departureloc\"]");
        System.out.println("Data Layer |departureloc| From LOT.COM: " + departureloc);
        assertion.assertEquals(departureloc, departureport, "Departureloc should be: "+departureport+" , is: " + departureloc);

        //arrivalloc
        Object arrivalloc = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"arrivalloc\"]");
        System.out.println("Data Layer |arrivalloc| From LOT.COM: " + arrivalloc);
        assertion.assertEquals(arrivalloc, arrivalport, "Arrivalloc should be: "+arrivalport+" , is: " + arrivalloc);

        //depdate
        Object depdate = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"depdate\"]");
        System.out.println("Data Layer |depdate| From LOT.COM: " + depdate);
        assertion.assertEquals(depdate, DDate, "Depdate should be: "+DDate+" , is: " + depdate);

        if (SL == -1) {
            //triptype
            Object triptype = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"triptype\"]");
            System.out.println("Data Layer |triptype| From LOT.COM: " + triptype);
            assertion.assertEquals(triptype, "O", "Triptype should be: One Way, is: " + triptype);

        } else {
            //triptype
            Object triptype = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"triptype\"]");
            System.out.println("Data Layer |triptype| From LOT.COM: " + triptype);
            assertion.assertEquals(triptype, "R", "Triptype should be: Run Trip, is: " + triptype);
        }
        if (SL == -1) {
            //retdate
            Object retdate = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"retdate\"]");
            System.out.println("Data Layer |retdate| From LOT.COM: " + retdate);
            assertion.assertEquals(retdate, null, "Retdate should be: "+null+" , is: " + retdate);

        } else {
            //retdate
            Object retdate = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"retdate\"]");
            System.out.println("Data Layer |retdate| From LOT.COM: " + retdate);
            assertion.assertEquals(retdate, RDate, "Retdate should be: "+RDate+" , is: " + retdate);
        }

        if (SL == -1) {
            //staylength
            Object slength = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"staylength\"]");
            System.out.println("Data Layer |staylength| From LOT.COM: " + slength);
            assertion.assertEquals(slength, null, "Staylength should be: "+null+" , is: " + slength);

        } else {
            //staylength
            Object slength = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"staylength\"]");
            System.out.println("Data Layer |staylength| From LOT.COM: " + slength);
            assertion.assertEquals(slength, SL1, "Staylength should be: "+SL1+" , is: " + slength);
        }

        //numofadults
        Object numofadults = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"numofadults\"]");
        System.out.println("Data Layer |numofadults| From LOT.COM: " + numofadults);
        assertion.assertEquals(numofadults, ADT1, "Numofadults should be: "+ADT1+" , is: " + numofadults);

        //numofteenagers
        Object numofteenagers = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"numofteenagers\"]");
        System.out.println("Data Layer |numofteenagers| From LOT.COM: " + numofteenagers);
        assertion.assertEquals(numofteenagers, YTH1, "Numofteenagers should be: "+YTH1+" , is: " + numofteenagers);

        //numofchildren
        Object numofchildren = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"numofchildren\"]");
        System.out.println("Data Layer |numofchildren| From LOT.COM: " + numofchildren);
        assertion.assertEquals(numofchildren, CHD1, "Numofchildren should be: "+CHD1+" , is: " + numofchildren);

        //numofinfants
        Object numofinfants = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"numofinfants\"]");
        System.out.println("Data Layer |numofinfants| From LOT.COM: " + numofinfants);
        assertion.assertEquals(numofinfants, INF1, "Numofinfants should be: "+INF1+" , is: " + numofinfants);

        //numoftravellers
        Long Travellers = ADT1+YTH1+CHD1+INF1;
        Object numoftravellers = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"numoftravellers\"]");
        System.out.println("Data Layer |numoftravellers| From LOT.COM: " + numoftravellers);
        assertion.assertEquals(numoftravellers, Travellers, "Numoftravellers should be: "+Travellers+" , is: " + numoftravellers);

        //cabinclass
        Object cablass = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"cabinclass\"]");
        System.out.println("Data Layer |cabinclass| From LOT.COM: " + cablass);
        assertion.assertEquals(cablass, cabinclass, "Cabinclass should be: "+cabinclass+" , is: " + cablass);

        //platform
        Object platform = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"platform\"]");
        System.out.println("Data Layer |platform| From LOT.COM: " + platform);
        assertion.assertEquals(platform, "PORTAL", "Platform should be: PORTAL , is: " + platform);

        assertion.assertAll();

        System.out.println("Test ended for: "+market + "/" + language + "/" + departureport + "/" + arrivalport + "/" + bookingwindow + "/" + staylength + "/" + ADT + "/" + YTH + "/" + CHD + "/" + INF + "/" + cabinclass + "/" + page_version);

    }

    @DataProvider(name ="data")
    public Object[][] passData()
    {
        ExcelDataConfig config = new ExcelDataConfig("C:\\Users\\Public\\LOT\\kasia.xlsx");
        int rows = config.getRowCount(0);
        rows = rows-1;
        Object[][] data=new Object[rows][12];

        for(int i=0;i<rows;i++){
            data[i][0]=config.getData(0,i+1,0);
            data[i][1]=config.getData(0,i+1,1);
            data[i][2]=config.getData(0,i+1,2);
            data[i][3]=config.getData(0,i+1,3);
            data[i][4]=config.getNumber(0,i+1,4);
            data[i][5]=config.getNumber(0,i+1,5);
            data[i][6]=config.getNumber(0,i+1,6);
            data[i][7]=config.getNumber(0,i+1,7);
            data[i][8]=config.getNumber(0,i+1,8);
            data[i][9]=config.getNumber(0,i+1,9);
            data[i][10]=config.getData(0,i+1,10);
            data[i][11]=config.getData(0,i+1,11);
        }
        return data;
    }

    @AfterTest(alwaysRun = true)
    public void tearDown1() throws Exception {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
