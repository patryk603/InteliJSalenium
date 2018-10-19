package PRE2LOT;

import DDT.ExcelDataConfig;
import Main.GetScreenshot;
import Main.MainTest;
import PRE2_pageObjects.HomePagePRE2;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PRE2Short extends MainTest{
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
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        baseUrl = "https://prodcopy-pre2:MHXozAuh4eeCgK4x6ww6@pre2.book.lot.com/";
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        PageFactory.initElements(driver, HomePagePRE2.class);
        PageFactory.initElements(driver, HomePage.class);
        PageFactory.initElements(driver, FlightsPage.class);
        PageFactory.initElements(driver, PassengersPage.class);
        PageFactory.initElements(driver, ExtrasPage.class);
        PageFactory.initElements(driver, PaymentPage.class);
    }


    @Test(dataProvider = "dataEU",groups=("BuyTickets"))
    public void Test_Pr2ShortEU(String from, String to) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.get(baseUrl);
        ImplicitWait(driver);
        try {
            GetScreenshot.capture("HomePagePRE2 " + from + to );
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Selecting random Departure Data
        try {
            List<WebElement> listings = driver.findElements(By.cssSelector("a.ui-state-default"));
            Random r = new Random();
            int randomValue = r.nextInt(listings.size()); //Getting a random value that is between 0 and (list's size)-1
            listings.get(randomValue).click(); //Clicking on the random item in the list.
            //Click on add button
            wait.until(ExpectedConditions.elementToBeClickable(HomePagePRE2.DepartureData));
            HomePagePRE2.DepartureData.click();
        } catch (Exception e) {
            System.out.println("No Element additional TO : "+ e.getMessage());
        }

        HomePagePRE2.Return.click();

        //Selecting random Return Data
        try {
            List<WebElement> listings2 = driver.findElements(By.cssSelector("a.ui-state-default"));
            Random r = new Random();
            int randomValue = r.nextInt(listings2.size()); //Getting a random value that is between 0 and (list's size)-1
            listings2.get(randomValue).click(); //Clicking on the random item in the list.
            //Click on add button
            //wait.until(ExpectedConditions.elementToBeClickable(HomePagePRE2.DepartureData));
            driver.findElement(By.cssSelector("a.ui-state-default")).click();
            //HomePagePRE2.DepartureData.click();
        } catch (Exception e) {
            System.out.println("No Element additional TO : "+ e.getMessage());
        }
        Thread.sleep(2000);


        //Selecting From Flight
        wait.until(ExpectedConditions.elementToBeClickable(HomePagePRE2.From));
        HomePagePRE2.From.clear();
        HomePagePRE2.From.sendKeys(from);
        driver.findElement(By.cssSelector(".yui3-aclist.yui3-widget-positioned > div > ul > li[data-text="+from+"]")).click();
        //Click on home page

        Thread.sleep(1000);

        //Selecting To Flight

        wait.until(ExpectedConditions.elementToBeClickable(HomePagePRE2.To));
        HomePagePRE2.To.clear();
        HomePagePRE2.To.sendKeys(to);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".yui3-aclist.yui3-widget-positioned > div > ul > li[data-text="+to+"]")));
        driver.findElement(By.cssSelector(".yui3-aclist.yui3-widget-positioned > div > ul > li[data-text="+to+"]")).click();

        HomePagePRE2.FindFlights.submit();
        Thread.sleep(1000);

        //Take screenshot
        try {
            GetScreenshot.capture("FlightPagePRE2 " + from + to );
        } catch (IOException e) {
            e.printStackTrace();
        }


        //END OF TEST



    }

    //Excel configuration
    @DataProvider(name ="dataEU")
    public Object[][] passDataEU() {
        ExcelDataConfig config = new ExcelDataConfig("C:\\Users\\Public\\LOT\\PRE2Short.xlsx");
        int rows = config.getRowCount(0);
        Object[][] data = new Object[rows][2];

        for (int i = 0; i < rows; i++) {
            data[i][0] = config.getData(0, i, 0);
            data[i][1] = config.getData(0, i, 1);

        }
        return data;
    }
    //Excel configuration

    //After and of Class test

    //After and of Class test
    @AfterTest(alwaysRun = true)
    public void tearDown1() throws Exception {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
    @AfterClass(alwaysRun = true)
    public void tearDown2() throws Exception {
        //driver.manage().deleteAllCookies();
        //driver.quit();
    }

}
