package Main;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BasedTimeFunctions extends MainTest{

    private String startDate = "";
    private String endDate = "";

    public void DataChecker(String baseUrl, String localization, String from, String to, XSSFCell departureData, XSSFCell returnData) throws Exception{

//        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.get(baseUrl + localization);
        ImplicitWait(driver);

        //TIME configuration
        String dataFromDeparture = String.valueOf(departureData);
        if (dataFromDeparture.length() > 0){
            dataFromDeparture = dataFromDeparture.substring(0, (dataFromDeparture.length() - 2));
        }

        String dataFromReturn = String.valueOf(returnData);
        if (dataFromReturn.length() > 0){
            dataFromReturn = dataFromReturn.substring(0, (dataFromReturn.length() - 2));
        }

        //Data Formats
        String euTime = "dd.MM.yyyy";
        String huTime = "yyyy.MM.dd";
        String usTime = "MM.dd.yyyy";

        String actualTime;
        if (localization.contains("us")){
            actualTime = usTime;
        } else if (localization.startsWith("hu/hu")){
            actualTime = huTime;
        } else {
            actualTime = euTime;
        }

        String timeStamp = new SimpleDateFormat(actualTime).format(Calendar.getInstance().getTime());

        SimpleDateFormat sdf = new SimpleDateFormat(actualTime);

        Calendar b = Calendar.getInstance();
        Calendar c = Calendar.getInstance();

        try {
            b.setTime(sdf.parse(timeStamp));
        } catch (ParseException e){
            e.printStackTrace();
        }
        try {
            c.setTime(sdf.parse(timeStamp));
        } catch (ParseException e){
            e.printStackTrace();
        }


        //Number of Days to add
        b.add(Calendar.DAY_OF_MONTH, Integer.parseInt((dataFromReturn)));
        c.add(Calendar.DAY_OF_MONTH, Integer.parseInt((dataFromDeparture)));

        //Date after adding the days to the given date
        String departureTimeData = sdf.format(c.getTime());
        String returnTimeData = sdf.format(b.getTime());

        //Displaying the new Date after addition of Days
//        System.out.println("Data wylotu: " + departureTimeData);
//        System.out.println("Data powrotu: " + returnTimeData);

        //Test start
        String start = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        System.out.println("Lokalizacja:\u0009" + localization + "\nLot z:\u0009\u0009\u0009" + from + "\nLot do:\u0009\u0009\u0009" + to + "\nData wylotu:\u0009" + departureTimeData + "\nData powrotu:\u0009" + returnTimeData + "\nStart testu:\u0009" + start + "\n");

        try {
            GetScreenshot.capture("HomePagePRE2 " + localization + from + to + departureData + returnData);
        } catch (IOException e){
            e.printStackTrace();
        }

        Cookie cookie = driver.manage().getCookieNamed("JSESSIONID");
        System.out.println("HomePage JSESSIONID: " + cookie.getValue());

        startDate = departureTimeData;
        endDate = returnTimeData;

    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

}
