package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * Created by PTeledzinski on 21.12.2018.
 */
public class MultiCity {

    final WebDriver driver;

    @FindBy(css = ".select2-search__field")
    public static WebElement DDL;

    @FindBy(css = "#select2-departureAirportMulti_0-container > span")
    public static WebElement From1;

    @FindBy(id = "select2-departureAirportMulti_1-container")
    public static WebElement From2;

    @FindBy(id = "select2-departureAirportMulti_2-container")
    public static WebElement From3;

    @FindBy(id = "select2-departureAirportMulti_3-container")
    public static WebElement From4;

    @FindBy(id = "select2-destinationAirportMulti_0-container")
    public static WebElement To1;

    @FindBy(id = "select2-destinationAirportMulti_1-container")
    public static WebElement To2;

    @FindBy(id = "select2-destinationAirportMulti_2-container")
    public static WebElement To3;

    @FindBy(id = "select2-destinationAirportMulti_3-container")
    public static WebElement To4;

    @FindBy(css = "#lot > span > span > span.select2-search.select2-search--dropdown > input")
    public static WebElement Text;

    @FindBy(id = "departureDateMulti_0")
    public static WebElement DepartureDate1;

    @FindBy(id = "departureDateMulti_1")
    public static WebElement DepartureDate2;

    @FindBy(id = "departureDateMulti_2")
    public static WebElement DepartureDate3;

    @FindBy(id = "departureDateMulti_3")
    public static WebElement DepartureDate4;


    @FindBy(id = "multiAdd")
    public static WebElement MultiAdd;

    public MultiCity(WebDriver driver){

        this.driver = driver;
    }

}
