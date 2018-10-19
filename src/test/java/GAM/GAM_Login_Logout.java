package GAM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GAM_Login_Logout {

    public WebDriver driver;


    @BeforeMethod

    public void beforeMethod() {

        driver = new ChromeDriver();

        //Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://tango1.ax.lot.pl:8443/LotGAM/report/sectorTimeLineGraph.jsp");
        Assert.assertEquals("Flightman1 - Ground Administration Tool", driver.getTitle());

    }

    @Test

    public void main() {
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/form/div/fieldset/table/tbody/tr/td[2]/table/tbody/tr[1]/td[2]/input")),"Wrong username or password!");
        driver.findElement(By.xpath("/html/body/form/div/fieldset/table/tbody/tr/td[2]/table/tbody/tr[1]/td[2]/input")).sendKeys("30001236");
        driver.findElement(By.xpath("/html/body/form/div/fieldset/table/tbody/tr/td[2]/table/tbody/tr[2]/td[2]/input")).sendKeys("Password1");
        driver.findElement(By.xpath("/html/body/form/div/fieldset/table/tbody/tr/td[2]/table/tbody/tr[3]/td/button[1]")).click();

        System.out.println(" Login Successfully, now it is the time to Log Off buddy.");


    }


    @AfterMethod

    public void afterMethod() {

        // Close the driver

        //driver.quit();

    }

}