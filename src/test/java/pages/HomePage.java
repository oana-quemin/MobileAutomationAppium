package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage {
    AndroidDriver driver;

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void clickBrowseTab() {
        WebElement browseTab = driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Browse\"]/android.widget.FrameLayout/android.widget.ImageView"));
        browseTab.click();
    }
}
