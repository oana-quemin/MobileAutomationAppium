package pages;

import helpers.Helpers;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import tests.Constants;

import java.util.List;

public class VehiclesPage {
    AndroidDriver driver;

    public VehiclesPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void selectVehicle(String vehicle) {
        List<WebElement> vehicleEntries = driver.findElements(By.id("com.fleetio.go_app:id/item_vehicle_txt_name"));
        for (WebElement vehicleEntry : vehicleEntries) {
            if (vehicleEntry.getText().equals(vehicle)) {
                vehicleEntry.click();
                return;
            }
        }
    }

    public void addNewFuelEntry(String pricePerGallonValue, String gallonValue, String odometerValue) throws InterruptedException {
        WebElement plusSign = driver.findElement(By.id("com.fleetio.go_app:id/quick_add_fab"));
        plusSign.click();

        Thread.sleep(3000);
        WebElement fuelEntryListItem = driver.findElement(By.id("com.fleetio.go_app:id/dialog_bottom_sheet_vehicle_overview_quick_add_btn_fuel_entry"));
        fuelEntryListItem.click();

        //add the information about the fuel entry
        WebElement pricePerGallon = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.EditText"));
        pricePerGallon.clear();
        pricePerGallon.sendKeys(pricePerGallonValue);

        WebElement gallons = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]/android.widget.EditText"));
        gallons.click();
        gallons.sendKeys(gallonValue);

        WebElement odometer = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[5]/android.widget.EditText"));
        odometer.click();
        odometer.sendKeys(odometerValue);

        //scroll down the page and add a new document
        Helpers.scrollToText(driver, "Add Documents");

        uploadDocument();

        WebElement saveBtn = driver.findElement(By.id("com.fleetio.go_app:id/menu_item_save"));
        saveBtn.click();
    }

    private void uploadDocument() throws InterruptedException {
        WebElement addDocumentsButton = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]/android.widget.TextView[1]"));
        addDocumentsButton.click();

        WebElement chooseFileButton = driver.findElement(By.id("com.fleetio.go_app:id/dialog_bottom_sheet_add_document_btn_choose_file"));
        chooseFileButton.click();

        //When first running the test, File app opens on Recent page.
        //After running the test once, File app opens on the last opened page, which is Downloads page
        //Due to this, whenever I open File app, I first go to Recent, then Downloads as it was clicking on Recent instead of Downloads
        WebElement menu = driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Show roots\"]"));
        menu.click();

        WebElement recent = Helpers.findElementByText(driver, "Recent");
        recent.click();

        menu.click();

        WebElement downloads = Helpers.findElementByText(driver, "Downloads");
        downloads.click();

        WebElement file = Helpers.findElementByText(driver, Constants.FILENAME_TO_UPLOAD);
        file.click();

        //waiting until the file is saved
        Thread.sleep(2000);
    }

    public void goToFuelLog() {
        WebElement saveBtn = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/androidx.cardview.widget.CardView[2]/android.view.ViewGroup/android.widget.TextView[1]"));
        saveBtn.click();
    }

    public String getDateFromFuelLog() {
        WebElement dateElement = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/androidx.cardview.widget.CardView[1]/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView[1]"));
        return dateElement.getText();
    }

    public String getPriceFromFuelLog() {
        WebElement priceElement = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/androidx.cardview.widget.CardView[1]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.TextView[1]"));
        return priceElement.getText();
    }

    public String getOdometerFromFuelLog() {
        WebElement odometerElement = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/androidx.cardview.widget.CardView[1]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.TextView[1]"));
        return odometerElement.getText();
    }

    public String getGallonsFromFuelLog() {
        WebElement gallonsElement = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/androidx.cardview.widget.CardView[1]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]"));
        return gallonsElement.getText();
    }

    public void selectFirstFuelFromLog() {
        WebElement firstEntry = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/androidx.cardview.widget.CardView[1]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]"));
        firstEntry.click();
    }

    public void selectDocuments() {
        Helpers.scrollToText(driver, "Documents");
        WebElement documentsEntry = Helpers.findElementByText(driver, "Documents");
        documentsEntry.click();
    }

    public String getDocumentName() {
        WebElement docElement = driver.findElement(By.id("com.fleetio.go_app:id/item_document_txt_name"));
        return docElement.getText();
    }

}
