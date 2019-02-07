package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by PTeledzinski on 18.05.2018.
 */
public class PaymentPage {

    final WebDriver driver;

    @FindBy(css = ".nr-cart--buttons>button")
    public static WebElement BigContinue;

    @FindBy(css = ".summary-detail--textPNR")
    public static WebElement BookNr;

    @FindBy(id = "adyen-encrypted-form-number")
    public static WebElement CardNr;

    @FindBy(id = "cvc")
    public static WebElement Cvc;

    @FindBy(id = "adyen-encrypted-form-holder-name")
    public static WebElement Name;

    @FindBy(id = "city")
    public static WebElement City;

    @FindBy(id = "postal-code")
    public static WebElement PostalCode;

    @FindBy(id = "street")
    public static WebElement Street;

    @FindBy(id = "adyen-encrypted-form-expiry-month")
    public static WebElement Month;

    @FindBy(id = "adyen-encrypted-form-expiry-year")
    public static WebElement Year;

    @FindBy(id = "country")
    public static WebElement Country;

    @FindBy(id = "lotBody")
    public static WebElement Lot;

    //2019.01.30
    @FindBy(css = "#_shoppingcartportlet_WAR_lotairwaysportlet_cart-total-price")
    public static WebElement FinalPriceAfterDiscount;

    public PaymentPage(WebDriver driver){

        this.driver = driver;
    }

}
