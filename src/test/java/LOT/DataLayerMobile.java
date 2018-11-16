package LOT;

import DDT.ExcelDataConfig;
import Main.GetScreenshot;
import Main.MainTest;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class DataLayerMobile extends MainTest{
    private String baseUrl;

    @BeforeTest(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        baseUrl = "https://m.lot.com/";
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        PageFactory.initElements(driver, HomePage.class);
        PageFactory.initElements(driver, FlightsPage.class);
        PageFactory.initElements(driver, PassengersPage.class);
        PageFactory.initElements(driver, ExtrasPage.class);
        PageFactory.initElements(driver, PaymentPage.class);
    }


    @Test(dataProvider = "data",groups=("BuyTickets"))
    public void DataLayerTest(String market, String language, String departureport, String arrivalport, XSSFCell bookingwindow, XSSFCell staylength, XSSFCell ADT, XSSFCell YTH, XSSFCell CHD, XSSFCell INF, String cabinclass, String page_version) throws Exception {

        //Data From Excel configuration
        market = market.toLowerCase();
        language = language.toLowerCase();


        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.get(baseUrl + market + "/" + language + "/?site_preference=mobile");
        ImplicitWait(driver);


        //TEST START
        ZonedDateTime start = ZonedDateTime.now();

        //JSESSION ID
        Cookie cookie = driver.manage().getCookieNamed("JSESSIONID");
        System.out.println(market + "/" + language + "/" + departureport + "/" + arrivalport + "/" + bookingwindow + "/" + staylength + "/" + ADT + "/" + YTH + "/" + CHD + "/" + INF + "/" + cabinclass + "/" + page_version + "JSESSIONID: " + cookie.getValue());


        //Take screenshot
        try {
            GetScreenshot.capture("HomePage " + market + "/" + language + departureport + arrivalport + bookingwindow + staylength);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread.sleep(3000);


        //Data Layer
        //market
        market = market.toUpperCase();
        Object DLmarket = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"market\"]");
        System.out.println("Data Layer |market| From Excel: " + market);
        System.out.println("Data Layer |market| From LOT.COM: " + DLmarket);
        Assert.assertEquals(market, DLmarket, "Market does not match");

        //language
        language = language.toUpperCase();
        Object DLlanguage = ((JavascriptExecutor) driver).executeScript("return dataLayer[0][\"language\"]");
        System.out.println("Data Layer |language| From Excel: " + language);
        System.out.println("Data Layer |language| From LOT.COM: " + DLlanguage);
        Assert.assertEquals(language, DLlanguage, "Language does not match");

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
