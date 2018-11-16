package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by PTeledzinski on 16.05.2018.
 */
public class FlightsPage {

    final WebDriver driver;

    @FindBy(css = ".row-group.jsOfferSection[id*=\"Ida\"][class*=\"row-group jsOfferSection\"]:not([id*=\"0\"]):not([style*=\"display: none\"])")
    public static WebElement FirstTO;

    @FindBy(css = ".row-group.jsOfferSection[id*=\"Vuelta\"][class*=\"row-group jsOfferSection\"]:not([id*=\"0\"]):not([style*=\"display: none\"])")
    public static WebElement FirstBack;

    @FindBy(css = ".VAB__flights__list[id*=\"1\"] > li > div > ul > li > div > a")
    public static WebElement FirstTO1;

    @FindBy(css = ".VAB__flights__list[id*=\"2\"] > li > div > ul > li > div > a")
    public static WebElement FirstBack2;

    @FindBy(css = ".nr-cart--buttons--tablet>button")
    public static WebElement SmallContinue;

    @FindBy(css = ".nr-cart--buttons>button")
    public static WebElement BigContinue;

    @FindBy(id = "nr-cart")
    public static WebElement Cart;

    @FindBy(css = "#modal-warning-description > div > button")
    public static WebElement OK;

    @FindBy(css = "#modal-price-change-description > div > button")
    public static WebElement Popup;

    @FindBy(css = ".timeToThink__link")
    public static WebElement TTT;

    @FindBy(css = "#loadingDetailsUpsell > div.modal-upsell-footer > div.modal-upsell-footer-buttons > button.btn.btn-type6")
    public static WebElement NoThanks;



    public FlightsPage(WebDriver driver){

        this.driver = driver;
    }

}
