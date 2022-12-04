package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PermissionsPage {
    AndroidDriver driver;

    public PermissionsPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void selectWhileUsingTheAppPermission() {
        WebElement whileUsingTheApp = driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"));
        whileUsingTheApp.click();
    }

}
