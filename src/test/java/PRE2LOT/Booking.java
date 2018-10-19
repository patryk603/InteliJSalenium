package PRE2LOT;

import PRE2_pageObjects.HomePagePRE2;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Booking {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeTest(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.get("https://prodcopy-pre2:MHXozAuh4eeCgK4x6ww6@pre2.book.lot.com/");
        driver.manage().timeouts().implicitlyWait(220, TimeUnit.SECONDS);
        HomePagePRE2 HomePage =PageFactory.initElements(driver, HomePagePRE2.class);
    }


    @Test()
    public void Test_Pr2ShortEU() throws Exception {

        //driver.get("https://pre2.book.lot.com/");

        HomePagePRE2.Departure.click();
        HomePagePRE2.Today.click();
        HomePagePRE2.Return.click();
        HomePagePRE2.FirsOfNextMounth.click();
        HomePagePRE2.From.sendKeys("WAW");
        HomePagePRE2.ListFrom.click();
        HomePagePRE2.To.sendKeys("FRA",Keys.ARROW_DOWN,Keys.ENTER);


    }



}