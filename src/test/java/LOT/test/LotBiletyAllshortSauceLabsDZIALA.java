package LOT.test;

import DDT.ExcelDataConfig;
import Main.MainTest;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.*;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LotBiletyAllshortSauceLabsDZIALA extends MainTest{
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

    //--------SauceLabs
    public static final String USERNAME = "lotek603";
    public static final String ACCESS_KEY = "32d9b4c8-3977-4781-aa35-91098dfcf36a";
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
    //--------SauceLabs


    @BeforeTest(alwaysRun = true)
    public void setUp() throws Exception {
    }


    @Test(dataProvider = "dataEU",groups=("BuyTickets"))
    public void Test_BuyTicketsEU(String localization, String from, String to, XSSFCell departuredata, XSSFCell returndata) throws Exception {

        DesiredCapabilities caps = DesiredCapabilities.edge();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("version", "16.16299");
        caps.setCapability("screenResolution", "1920x1080");

        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);

        PageFactory.initElements(driver, HomePage.class);
        PageFactory.initElements(driver, FlightsPage.class);
        PageFactory.initElements(driver, PassengersPage.class);
        PageFactory.initElements(driver, ExtrasPage.class);
        PageFactory.initElements(driver, PaymentPage.class);


        driver.get(baseUrl + localization);

        //TIME Configuration
        String dat1 = String.valueOf(departuredata);
        if (dat1.length() > 0) {
            dat1 = dat1.substring(0, (dat1.length() - 2));
        }

        String dat2 = String.valueOf(returndata);
        if (dat2.length() > 0) {
            dat2 = dat2.substring(0, (dat2.length() - 2));
        }

        /* Printing Data
        System.out.println(departuredata);
        System.out.println(returndata);
        System.out.println(dat1);
        System.out.println(dat2);
        */

        //Given Date in String format
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());

        // Printing Data
        //System.out.println(timeStamp);

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

        //Displaying the new Date after addition of Days
        //System.out.println("Date after Addition/ Departure: " + newDate);
        //System.out.println("Date after Addition/ Return: " + newDate2);
        //TIME

        //TEST START
        long start = System.currentTimeMillis();
        System.out.println("Lokalizacja: "+localization+"Lot z: " +from+ "/Lot do: "+to+"Data wylotu: "+departuredata+"/Data powrotu: "+returndata+"  Start testu: "+start);

        //Selecting From Flight

        HomePage.FromListButton.click();

        HomePage.FromToText.sendKeys(from);

        driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*="+from+"]")).click();
        //Click on home page

        Thread.sleep(1000);

        //Selecting To Flight
        try {

            HomePage.ToToText.sendKeys(to);
            driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*="+to+"]")).click();
        } catch (Exception e) {
            System.out.println("Need additional click : " + e.getMessage());
            HomePage.Lot.click();

            HomePage.ToList.click();

            HomePage.ToToText.sendKeys(to);
            driver.findElement(By.cssSelector(".select2-results__options > li > ul > li[id*="+to+"]")).click();
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

        //Submit Button go from Home Page to Flight Page
        HomePage.Submit.submit();

        //FlightPage
        try {

        } catch (Exception e) {
            System.out.println("Zbyt długi czas oczekiwania przejścia z bookera na step 2- flights : "+ e.getMessage());
        }

        //Popup handle
        try {
            FlightsPage.OK.click();
        } catch (Exception e) {
            System.out.println("Flight are available in that date : " + e.getMessage());
        }


        //Selecting First ACTIVE Ticket TO

        try {
            FlightsPage.FirstTO.click();
        } catch (Exception e) {
            System.out.println("Other tickets : "+ e.getMessage());
            FlightsPage.FirstTO1.click();
        }

        //Excel configuration
    }

    @DataProvider(name ="dataEU")
    public Object[][] passDataEU()
    {
        ExcelDataConfig config = new ExcelDataConfig("C:\\Users\\Public\\LOT\\Short.xlsx");
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



}
