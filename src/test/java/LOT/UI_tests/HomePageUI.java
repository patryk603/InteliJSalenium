package LOT.UI_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import java.util.concurrent.TimeUnit;

public class HomePageUI {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "http://www.lot.com/";
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        HomePage HomePage =PageFactory.initElements(driver, HomePage.class);
    }

    @Test(groups=("ui"))
    public void Test_HomePageUI_PL() throws Exception {
        driver.get(baseUrl + "pl/pl/");
        HomePage.lotlogo.isDisplayed();
        HomePage.login.isDisplayed();
        HomePage.MilesAndMore.isDisplayed();
        HomePage.CustomerService.isDisplayed();
        HomePage.DuringForFlight.isDisplayed();
        HomePage.PrepareForTravel.isDisplayed();
        HomePage.ZapraszamyNaPoklad.isDisplayed();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();

    }

}
