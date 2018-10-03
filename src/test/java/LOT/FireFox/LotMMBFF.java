package LOT.FireFox;

import DDT.ExcelDataConfig;
import Main.MainTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.MMBPage;

import java.util.Objects;

import static org.testng.Assert.assertEquals;

public class LotMMBFF extends MainTest {
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeTest(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        baseUrl = "http://www.lot.com/";
        PageFactory.initElements(driver, MMBPage.class);

    }


    @Test(dataProvider = "data",groups=("MMB"))
    public void MMB(String lastname, String bookinrexcel) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.get(baseUrl + "/pl/pl/moja-rezerwacja");
        ImplicitWait(driver);


        //TestStart
        //Enter Surname
        WebElement surname = MMBPage.MMBLastName;
        surname.sendKeys(lastname);
        WebElement book = MMBPage.MMBBookingNumber;
        book.sendKeys(bookinrexcel);
        MMBPage.MMBSubmit.click();
        //Waiting and Clicking on Big Continue Button.
        try {
            String bookingnr = driver.findElement(By.cssSelector(".confirmation-your-ticket-code strong")).getText();
            String bookinrexcel2 = bookinrexcel;
            System.out.println("PNR from Excel: " + bookinrexcel);
            System.out.println("PNR from MMB page: " +bookingnr);
            //Booking Number check
            assertEquals(bookinrexcel2, bookingnr);
            if (Objects.equals(bookinrexcel2, bookingnr)) {
                System.out.println("Booking Number is ok: " + bookinrexcel);
            } else {
                System.out.println("Booking Number not found: ");
            }
        } catch (Exception e) {
            //System.out.println("Nie znaleziono rezerwacji : " + bookinrexcel + e.getMessage());
            Assert.fail("Nie znaleziono rezerwacji : " + bookinrexcel);

        }
    }



    @DataProvider(name ="data")
    public Object[][] passData()
    {
        ExcelDataConfig config = new ExcelDataConfig("C:\\Users\\Public\\LOT\\MMB.xlsx");
        int rows = config.getRowCount(0);
        Object[][] data=new Object[rows][2];

        for(int i=0;i<rows;i++){
            data[i][0]=config.getData(0,i,0);
            data[i][1]=config.getData(0,i,1);
        }
        return data;
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
