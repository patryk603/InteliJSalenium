package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by PTeledzinski on 16.05.2018.
 */
public class PassengersPage {

    final WebDriver driver;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=title]")
    public static WebElement Title;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"1_title\"]")
    public static WebElement TitleAdult1;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"2_title\"]")
    public static WebElement TitleAdult2;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"3_title\"]")
    public static WebElement TitleAdult3;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"4_title\"]")
    public static WebElement TitleAdult4;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"1_sex\"]")
    public static WebElement Sex1;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"2_sex\"]")
    public static WebElement Sex2;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"3_sex\"]")
    public static WebElement Sex3;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"4_sex\"]")
    public static WebElement Sex4;

    @FindBy(css = ".autofillWrapper>input[id$=name]")
    public static WebElement FirstName;

    @FindBy(css = ".custom-input>input[id$=name]")
    public static WebElement FirstNameIE11;

    @FindBy(css = ".autofillWrapper>input[id$=\"1_name\"]")
    public static WebElement FirstName1;

    @FindBy(css = ".autofillWrapper>input[id$=\"2_name\"]")
    public static WebElement FirstName2;

    @FindBy(css = ".autofillWrapper>input[id$=\"3_name\"]")
    public static WebElement FirstName3;

    @FindBy(css = ".autofillWrapper>input[id$=\"4_name\"]")
    public static WebElement FirstName4;

    @FindBy(css = ".autofillWrapper>input[id$=surname]")
    public static WebElement Surname;

    @FindBy(css = ".custom-input>input[id$=surname]")
    public static WebElement SurnameIE11;

    @FindBy(css = ".autofillWrapper>input[id$=\"1_surname\"]")
    public static WebElement Surname1;

    @FindBy(css = ".autofillWrapper>input[id$=\"2_surname\"]")
    public static WebElement Surname2;

    @FindBy(css = ".autofillWrapper>input[id$=\"3_surname\"]")
    public static WebElement Surname3;

    @FindBy(css = ".autofillWrapper>input[id$=\"4_surname\"]")
    public static WebElement Surname4;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=day]")
    public static WebElement DayOfBirth;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"1_date_birth_day\"]")
    public static WebElement DayOfBirth1;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"2_date_birth_day\"]")
    public static WebElement DayOfBirth2;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"3_date_birth_day\"]")
    public static WebElement DayOfBirth3;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"4_date_birth_day\"]")
    public static WebElement DayOfBirth4;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=month]")
    public static WebElement MonthOfBirth;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"1_date_birth_month\"]")
    public static WebElement MonthOfBirth1;


    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"2_date_birth_month\"]")
    public static WebElement MonthOfBirth2;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"3_date_birth_month\"]")
    public static WebElement MonthOfBirth3;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"4_date_birth_month\"]")
    public static WebElement MonthOfBirth4;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=year]")
    public static WebElement YearOfBirth;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"1_date_birth_year\"]")
    public static WebElement YearOfBirth1;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"2_date_birth_year\"]")
    public static WebElement YearOfBirth2;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"3_date_birth_year\"]")
    public static WebElement YearOfBirth3;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"4_date_birth_year\"]")
    public static WebElement YearOfBirth4;

    //Next person Young

    @FindBy(css = ".autofillWrapper>input[id$=\"young_1_name\"]")
    public static WebElement FirstNameYoung1;

    @FindBy(css = ".autofillWrapper>input[id$=\"young_2_name\"]")
    public static WebElement FirstNameYoung2;

    @FindBy(css = ".autofillWrapper>input[id$=\"young_3_name\"]")
    public static WebElement FirstNameYoung3;

    @FindBy(css = ".autofillWrapper>input[id$=\"young_4_name\"]")
    public static WebElement FirstNameYoung4;


    @FindBy(css = ".autofillWrapper>input[id$=\"young_1_surname\"]")
    public static WebElement SurnameYoung1;

    @FindBy(css = ".autofillWrapper>input[id$=\"young_2_surname\"]")
    public static WebElement SurnameYoung2;

    @FindBy(css = ".autofillWrapper>input[id$=\"young_3_surname\"]")
    public static WebElement SurnameYoung3;

    @FindBy(css = ".autofillWrapper>input[id$=\"young_4_surname\"]")
    public static WebElement SurnameYoung4;


    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"young_1_date_birth_day\"]")
    public static WebElement DayOfBirthYoung1;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"young_2_date_birth_day\"]")
    public static WebElement DayOfBirthYoung2;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"young_3_date_birth_day\"]")
    public static WebElement DayOfBirthYoung3;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"young_4_date_birth_day\"]")
    public static WebElement DayOfBirthYoung4;


    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"young_1_date_birth_month\"]")
    public static WebElement MonthOfBirthYoung1;


    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"young_2_date_birth_month\"]")
    public static WebElement MonthOfBirthYoung2;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"young_3_date_birth_month\"]")
    public static WebElement MonthOfBirthYoung3;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"young_4_date_birth_month\"]")
    public static WebElement MonthOfBirthYoung4;


    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"young_1_date_birth_year\"]")
    public static WebElement YearOfBirthYoung1;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"young_2_date_birth_year\"]")
    public static WebElement YearOfBirthYoung2;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"young_3_date_birth_year\"]")
    public static WebElement YearOfBirthYoung3;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"young_4_date_birth_year\"]")
    public static WebElement YearOfBirthYoung4;
    //END next person Young

    //Next person Children

    @FindBy(css = ".autofillWrapper>input[id$=\"child_1_name\"]")
    public static WebElement FirstNamechild1;

    @FindBy(css = ".autofillWrapper>input[id$=\"child_2_name\"]")
    public static WebElement FirstNamechild2;

    @FindBy(css = ".autofillWrapper>input[id$=\"child_3_name\"]")
    public static WebElement FirstNamechild3;

    @FindBy(css = ".autofillWrapper>input[id$=\"child_4_name\"]")
    public static WebElement FirstNamechild4;


    @FindBy(css = ".autofillWrapper>input[id$=\"child_1_surname\"]")
    public static WebElement Surnamechild1;

    @FindBy(css = ".autofillWrapper>input[id$=\"child_2_surname\"]")
    public static WebElement Surnamechild2;

    @FindBy(css = ".autofillWrapper>input[id$=\"child_3_surname\"]")
    public static WebElement Surnamechild3;

    @FindBy(css = ".autofillWrapper>input[id$=\"child_4_surname\"]")
    public static WebElement Surnamechild4;


    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"child_1_date_birth_day\"]")
    public static WebElement DayOfBirthchild1;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"child_2_date_birth_day\"]")
    public static WebElement DayOfBirthchild2;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"child_3_date_birth_day\"]")
    public static WebElement DayOfBirthchild3;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"child_4_date_birth_day\"]")
    public static WebElement DayOfBirthchild4;


    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"child_1_date_birth_month\"]")
    public static WebElement MonthOfBirthchild1;


    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"child_2_date_birth_month\"]")
    public static WebElement MonthOfBirthchild2;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"child_3_date_birth_month\"]")
    public static WebElement MonthOfBirthchild3;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"child_4_date_birth_month\"]")
    public static WebElement MonthOfBirthchild4;


    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"child_1_date_birth_year\"]")
    public static WebElement YearOfBirthchild1;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"child_2_date_birth_year\"]")
    public static WebElement YearOfBirthchild2;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"child_3_date_birth_year\"]")
    public static WebElement YearOfBirthchild3;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"child_4_date_birth_year\"]")
    public static WebElement YearOfBirthchild4;
    //END next person Children

    //Next person Infant

    @FindBy(css = ".autofillWrapper>input[id$=\"inf_1_name\"]")
    public static WebElement FirstNameInfant1;

    @FindBy(css = ".autofillWrapper>input[id$=\"inf_2_name\"]")
    public static WebElement FirstNameInfant2;

    @FindBy(css = ".autofillWrapper>input[id$=\"inf_3_name\"]")
    public static WebElement FirstNameInfant3;

    @FindBy(css = ".autofillWrapper>input[id$=\"inf_4_name\"]")
    public static WebElement FirstNameInfant4;


    @FindBy(css = ".autofillWrapper>input[id$=\"inf_1_surname\"]")
    public static WebElement SurnameInfant1;

    @FindBy(css = ".autofillWrapper>input[id$=\"inf_2_surname\"]")
    public static WebElement SurnameInfant2;

    @FindBy(css = ".autofillWrapper>input[id$=\"inf_3_surname\"]")
    public static WebElement SurnameInfant;

    @FindBy(css = ".autofillWrapper>input[id$=\"inf_4_surname\"]")
    public static WebElement SurnameInfant4;


    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"inf_1_date_birth_day\"]")
    public static WebElement DayOfBirthInfant1;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"inf_2_date_birth_day\"]")
    public static WebElement DayOfBirthInfant2;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"inf_3_date_birth_day\"]")
    public static WebElement DayOfBirthInfant3;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"inf_4_date_birth_day\"]")
    public static WebElement DayOfBirthInfant4;


    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"inf_1_date_birth_month\"]")
    public static WebElement MonthOfBirthInfant1;


    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"inf_2_date_birth_month\"]")
    public static WebElement MonthOfBirthInfant2;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"inf_3_date_birth_month\"]")
    public static WebElement MonthOfBirthInfant3;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"inf_4_date_birth_month\"]")
    public static WebElement MonthOfBirthInfant4;


    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"inf_1_date_birth_year\"]")
    public static WebElement YearOfBirthInfant1;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"inf_2_date_birth_year\"]")
    public static WebElement YearOfBirthInfant2;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"inf_3_date_birth_year\"]")
    public static WebElement YearOfBirthInfant3;

    @FindBy(css = ".custom-select:nth-child(2)>select[id$=\"inf_4_date_birth_year\"]")
    public static WebElement YearOfBirthInfant4;
    //END next person Children

    @FindBy(css = ".custom-input>input[id$=\"email\"]")
    public static WebElement Email;

    @FindBy(css = ".custom-input>input[id$=\"phone\"]")
    public static WebElement Phone;

    @FindBy(css = ".checkbox>input[id$=\"termsUseCheckboxCheckbox\"]")
    public static WebElement CheckboxAccept;

    @FindBy(css = ".nr-cart--buttons>button")
    public static WebElement BigContinue;

    @FindBy(css = ".nr-cart--buttons--tablet>button")
    public static WebElement SmallContinue;

    @FindBy(css = ".btn.btn-type3[onclick*=\"accept\"]")
    public static WebElement PopupAccept;

    @FindBy(css = "#insuranceLoading > div:nth-child(1) > div.hidden-xs > table > tbody > tr.js-insuranceButtons.insuranceButtons > td.text-center")
    public static WebElement Insurance;

    @FindBy(css = ".checkbox>input[id*=InsuranceCheckBoxCheckbox]")
    public static WebElement InsuranceCheckbox;



    public PassengersPage(WebDriver driver){

        this.driver = driver;
    }

}
