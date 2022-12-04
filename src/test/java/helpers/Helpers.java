package helpers;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helpers {

    public static void scrollToText(AndroidDriver driver, String text) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                        ".scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))"));
    }

    public static WebElement findElementByText(AndroidDriver driver, String text) {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text + "\")"));
    }

    public static String convertDateToString(Date date){
        //date format: THU, DEC 1, 2022
        String pattern = "EEE, MMM d, yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

}
