package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by PTeledzinski on 23.04.2018.
 */
public class FakeMails {

    final WebDriver driver;

    @FindBy(id = "pole_login")
    public static WebElement Nemail;

    @FindBy(css = "#login_form > input.submit")
    public static WebElement Npass;

    @FindBy(id = "login")
    public static WebElement Yemail;

    @FindBy(css = ".sbut")
    public static WebElement Ypass;

    @FindBy(css = "a[href*=\"confirm\"]")
    public static WebElement Yhrefconfirm;



    public FakeMails(WebDriver driver){

        this.driver = driver;
    }

}
