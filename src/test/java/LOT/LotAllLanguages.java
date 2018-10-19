package LOT;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePage;

import java.util.concurrent.TimeUnit;

public class LotAllLanguages {
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

    @Test(groups=("p1"))
    public void Test_HomePageTabs_PL() throws Exception {
        driver.get(baseUrl + "pl/pl/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
        HomePage.MilesAndMore.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_AM() throws Exception {
        driver.get(baseUrl + "am/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_AT() throws Exception {
        driver.get(baseUrl + "at/de/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_BE() throws Exception {
        driver.get(baseUrl + "be/fr/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_BY() throws Exception {
        driver.get(baseUrl + "by/ru/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }


    @Test(groups=("p1"))
    public void Test_HomePageTabs_CA() throws Exception {
        driver.get(baseUrl + "ca/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_CN() throws Exception {
        driver.get(baseUrl + "cn/zh/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_HR() throws Exception {
        driver.get(baseUrl + "hr/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_CY() throws Exception {
        driver.get(baseUrl + "cy/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_CZ() throws Exception {
        driver.get(baseUrl + "cz/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_DK() throws Exception {
        driver.get(baseUrl + "dk/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_EG() throws Exception {
        driver.get(baseUrl + "eg/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_EE() throws Exception {
        driver.get(baseUrl + "ee/ru/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_IL() throws Exception {
        driver.get(baseUrl + "il/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_FI() throws Exception {
        driver.get(baseUrl + "fi/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_FR() throws Exception {
        driver.get(baseUrl + "fr/fr/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_GE() throws Exception {
        driver.get(baseUrl + "ge/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_DE() throws Exception {
        driver.get(baseUrl + "de/de/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }


    @Test(groups=("p1"))
    public void Test_HomePageTabs_GB() throws Exception {
        driver.get(baseUrl + "gb/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_GR() throws Exception {
        driver.get(baseUrl + "gr/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }



    @Test(groups=("p1"))
    public void Test_HomePageTabs_HU() throws Exception {
        driver.get(baseUrl + "hu/hu/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_IT() throws Exception {
        driver.get(baseUrl + "it/it/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_KZ() throws Exception {
        driver.get(baseUrl + "kz/ru/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }


    @Test(groups=("p1"))
    public void Test_HomePageTabs_JA() throws Exception {
        driver.get(baseUrl + "ja/ja/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }


    @Test(groups=("p1"))
    public void Test_HomePageTabs_LV() throws Exception {
        driver.get(baseUrl + "lv/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_LB() throws Exception {
        driver.get(baseUrl + "lb/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_LT() throws Exception {
        driver.get(baseUrl + "lt/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }


    @Test(groups=("p1"))
    public void Test_HomePageTabs_LU() throws Exception {
        driver.get(baseUrl + "lu/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_MD() throws Exception {
        driver.get(baseUrl + "md/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_ME() throws Exception {
        driver.get(baseUrl + "me/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_NL() throws Exception {
        driver.get(baseUrl + "nl/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_NO() throws Exception {
        driver.get(baseUrl + "no/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }


    @Test(groups=("p1"))
    public void Test_HomePageTabs_MK() throws Exception {
        driver.get(baseUrl + "mk/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_RO() throws Exception {
        driver.get(baseUrl + "ro/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }


    @Test(groups=("p1"))
    public void Test_HomePageTabs_RU() throws Exception {
        driver.get(baseUrl + "ru/ru/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_RS() throws Exception {
        driver.get(baseUrl + "rs/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_SG() throws Exception {
        driver.get(baseUrl + "sg/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_SI() throws Exception {
        driver.get(baseUrl + "si/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_SK() throws Exception {
        driver.get(baseUrl + "sk/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }


    @Test(groups=("p1"))
    public void Test_HomePageTabs_KR() throws Exception {
        driver.get(baseUrl + "kr/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_ES() throws Exception {
        driver.get(baseUrl + "es/es/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_SE() throws Exception {
        driver.get(baseUrl + "se/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_CH() throws Exception {
        driver.get(baseUrl + "ch/ch/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @Test(groups=("p1"))
    public void Test_HomePageTabs_TR() throws Exception {
        driver.get(baseUrl + "tr/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }
    @Test(groups=("p1"))
    public void Test_HomePageTabs_UA() throws Exception {
        driver.get(baseUrl + "ua/uk/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }
    @Test(groups=("p1"))
    public void Test_HomePageTabs_US() throws Exception {
        driver.get(baseUrl + "us/en/");
        HomePage.login.click();
        HomePage.lotlogo.click();
        HomePage.Book.click();
        HomePage.lotlogo.click();
        HomePage.DealsAndOffers.click();
        HomePage.lotlogo.click();
        HomePage.PrepareForTravel.click();
        HomePage.lotlogo.click();
        HomePage.DuringForFlight.click();
        HomePage.lotlogo.click();
        HomePage.CustomerService.click();
        HomePage.lotlogo.click();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();

    }

}
