package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by PTeledzinski on 18.05.2018.
 */
public class ExtrasPage {

    final WebDriver driver;

    @FindBy(css = "#modal-emergency > div > div.modal-footer > div > a")
    public static WebElement OK;

    @FindBy(css = ".nr-cart--buttons>button")
    public static WebElement BigContinue;

    @FindBy(id = "ancillaries-content")
    public static WebElement Seats;

    @FindBy(id = "column-1")
    public static WebElement Column1;

    @FindBy(css = ".nr-anc__seats__content__right>div>table>tbody>tr>th>table>tbody>tr>td[id*=\"tdSeat_flight_1\"]:not([class*=\"disabled\"])")
    public static WebElement SeatsOnPlain;

    @FindBy(css = ".popover>div>div>a[class*=\"add\"]:not([class*=\"hidden\"]")
    public static WebElement AddSeatsButton;

    @FindBy(css = ".nr-anc__seats__content__right>div>table>tbody>tr>th>table>tbody>tr>td[id*=\"tdSeat_flight_1\"]:not([class*=\"disabled\"])")
    public static WebElement Flight1;

    @FindBy(css = ".nr-anc__seats__content__right>div>table>tbody>tr>th>table>tbody>tr>td[id*=\"tdSeat_flight_2\"]:not([class*=\"disabled\"])")
    public static WebElement Flight2;

    @FindBy(css = "#nr-mmb-ancillaries-collapse_button_special-eqp")
    public static WebElement BuyNowSpecialEquipment;

    @FindBy(css = "#nr-mmb-ancillaries-collapse_button_aditional-baggage")
    public static WebElement BuyNowSpecialBaggage;

    @FindBy(css = ".field.field.jsInputSameEachFlight[id*=SpecialEquipment]")
    public static WebElement SpecialEquipmentSameForAllButton;

    @FindBy(css = ".field.field.jsInputSameEachFlight[id*=Baggage]")
    public static WebElement SpecialBaggageSameForAllButton;

    //Baggage
    @FindBy(css = "#nr-ancillaries-collapse__content-baggages >div>div>div>div>div:nth-child(2)>div>div>div>div>div>select[id*=\"1_pass_1\"]")
    public static WebElement SpecialBaggagePassanger1;

    @FindBy(css = "#nr-ancillaries-collapse__content-baggages >div>div>div>div>div:nth-child(2)>div>div>div>div>div>select[id*=\"1_pass_2\"]")
    public static WebElement SpecialBaggagePassanger2;

    @FindBy(css = "#nr-ancillaries-collapse__content-baggages >div>div>div>div>div:nth-child(2)>div>div>div>div>div>select[id*=\"1_pass_3\"]")
    public static WebElement SpecialBaggagePassanger3;

    @FindBy(css = "#nr-ancillaries-collapse__content-baggages >div>div>div>div>div:nth-child(2)>div>div>div>div>div>select[id*=\"1_pass_1\"]")
    public static WebElement SpecialBaggagePassanger4;
    //Baggage END

    //Equipment
    @FindBy(css = ".nr-anc__passenger-selector__segments>div>div>div>div>div>div>div>div>select[id$=\"pass_1_eq_1\"]")
    public static WebElement SpecialEquipmentPassanger1;

    @FindBy(css = ".nr-anc__passenger-selector__segments>div>div>div>div>div>div>div>div>select[id$=\"pass_2_eq_1\"]")
    public static WebElement SpecialEquipmentPassanger2;

    @FindBy(css = ".nr-anc__passenger-selector__segments>div>div>div>div>div>div>div>div>select[id$=\"pass_3_eq_1\"]")
    public static WebElement SpecialEquipmentPassanger3;

    @FindBy(css = ".nr-anc__passenger-selector__segments>div>div>div>div>div>div>div>div>select[id$=\"pass_4_eq_1\"]")
    public static WebElement SpecialEquipmentPassanger4;

    @FindBy(css = "#modal-emergency > div > div.modal-footer > div > a")
    public static WebElement AdditionalPopup;


    //Equipment END
    public ExtrasPage(WebDriver driver){

        this.driver = driver;
    }

}
