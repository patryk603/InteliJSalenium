package GAM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GAM_Cygan_haslo {

    public WebDriver driver;


    @BeforeMethod

    public void beforeMethod() {

        driver = new ChromeDriver();


        //Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://tango1.ax.lot.pl:8443/LotGAM/report/sectorTimeLineGraph.jsp");
        Assert.assertEquals("Flightman - Ground Administration Tool", driver.getTitle());

    }

    @Test

    public void ResetHasla() {
        driver.findElement(By.xpath("/html/body/form/div/fieldset/table/tbody/tr/td[2]/table/tbody/tr[1]/td[2]/input")).sendKeys("30001236");
        driver.findElement(By.xpath("/html/body/form/div/fieldset/table/tbody/tr/td[2]/table/tbody/tr[2]/td[2]/input")).sendKeys("Password1");
        driver.findElement(By.xpath("/html/body/form/div/fieldset/table/tbody/tr/td[2]/table/tbody/tr[3]/td/button[1]")).click();
        driver.navigate().to("https://tango1.ax.lot.pl:8443/LotGAM/user/userMain.jsp");


        driver.findElement(By.xpath("/html/body/form/div[2]/table/tbody/tr/td[3]/input")).click();

        driver.findElement(By.xpath("/html/body/form/div[2]/table/tbody/tr/td[6]/input")).sendKeys("Cygan");

        driver.findElement(By.xpath("/html/body/form/div[2]/table/tbody/tr/td[8]/button")).click();

        driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody/tr/td[1]")).click();
        driver.findElement(By.xpath("/html/body/form/div[4]/button[3]")).click();

        driver.findElement(By.xpath("/html/body/form/table/tbody/tr[6]/td[2]/input[1]")).click();

        driver.findElement(By.xpath("//*[@id=\"passwordRow\"]/td[2]/input")).sendKeys("samolot2");

        driver.findElement(By.xpath("//*[@id=\"confirmRow\"]/td[2]/input")).sendKeys("samolot2");


       // driver.findElement(By.xpath("/html/body/form/div/button[1]")).click();


        //driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[1]/a")).click();
        //driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[1]/ul/li[7]/ul/li[2]/a")).click();


    }

    @Test

    public void ResetHasla2() {
        driver.findElement(By.xpath("/html/body/form/div/fieldset/table/tbody/tr/td[2]/table/tbody/tr[1]/td[2]/input")).sendKeys("30001236");
        driver.findElement(By.xpath("/html/body/form/div/fieldset/table/tbody/tr/td[2]/table/tbody/tr[2]/td[2]/input")).sendKeys("Password1");
        driver.findElement(By.xpath("/html/body/form/div/fieldset/table/tbody/tr/td[2]/table/tbody/tr[3]/td/button[1]")).click();
        driver.navigate().to("https://tango1.ax.lot.pl:8443/LotGAM/user/userMain.jsp");


        driver.findElement(By.xpath("/html/body/form/div[2]/table/tbody/tr/td[3]/input")).click();

        driver.findElement(By.xpath("/html/body/form/div[2]/table/tbody/tr/td[6]/input")).sendKeys("Cygan");

        driver.findElement(By.xpath("/html/body/form/div[2]/table/tbody/tr/td[8]/button")).click();

        driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody/tr/td[1]")).click();
        driver.findElement(By.xpath("/html/body/form/div[4]/button[3]")).click();

        driver.findElement(By.xpath("/html/body/form/table/tbody/tr[6]/td[2]/input[1]")).click();

        driver.findElement(By.xpath("//*[@id=\"passwordRow\"]/td[2]/input")).sendKeys("samolot2");

        driver.findElement(By.xpath("//*[@id=\"2222confirmRow\"]/td[2]/input")).sendKeys("samolot2");


        driver.findElement(By.xpath("/html/body/form/div/button[1]")).click();




    }


    @AfterMethod

    public void afterMethod() {

        // Close the driver

        //driver.quit();

    }

}