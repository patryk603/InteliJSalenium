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

    @FindBy(css = ".row-group.jsOfferSection[id*=\"Ida\"][class*=\"row-group jsOfferSection\"]:not([id*=\"0\"]):not([style*=\"display: none\"])>div>div>div>label>span[class*=\"price\"]")
    public static WebElement FirstTOCost;

    @FindBy(id = "upsellPrice")
    public static WebElement UpsellPrice;

    @FindBy(css = "#flights-table__table_1 > tbody > tr.flights-table__row.active > td.flights-radios.flights-radios--active.flights-radios--col-select>div>div>div>div>div>label>input[name=\"precio\"]")
    public static WebElement UpsellTOCost;

    @FindBy(css = ".row-group.jsOfferSection[id*=\"Vuelta\"][class*=\"row-group jsOfferSection\"]:not([id*=\"0\"]):not([style*=\"display: none\"])")
    public static WebElement FirstBack;

    @FindBy(css = ".row-group.jsOfferSection[id*=\"Vuelta\"][class*=\"row-group jsOfferSection\"]:not([id*=\"0\"]):not([style*=\"display: none\"])>div>div>div>label>input[name=\"precio\"]")
    public static WebElement FirstBackCost;

    @FindBy(css = "#flights-table__table_2 > tbody > tr.flights-table__row.active > td.flights-radios.flights-radios--active.flights-radios--col-select>div>div>div>div>div>label>input[name=\"precio\"]")
    public static WebElement UpsellBackCost;

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

    @FindBy(css = "#loadingDetailsUpsell > div.modal-upsell-footer > div.modal-upsell-footer-buttons > button.btn.nr-btn__next-step")
    public static WebElement YesThanks;



    public FlightsPage(WebDriver driver){

        this.driver = driver;
    }

}
