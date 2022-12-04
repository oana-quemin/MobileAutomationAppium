package tests;

import helpers.Helpers;
import org.junit.jupiter.api.Test;
import pages.*;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddFuelEntryTest extends BaseTest {

    @Test
    public void addFuel() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        LandingPage landingPage = new LandingPage(driver);
        PermissionsPage permissionsPage = new PermissionsPage(driver);
        HomePage homePage = new HomePage(driver);
        BrowsePage browsePage = new BrowsePage(driver);
        VehiclesPage vehiclesPage = new VehiclesPage(driver);
        Date today = Calendar.getInstance().getTime();

        loginPage.login(Constants.USERNAME, Constants.PASSWORD);
       // landingPage.selectAccount();
        permissionsPage.selectWhileUsingTheAppPermission();
        homePage.clickBrowseTab();
        browsePage.selectVehiclesEntry();
        vehiclesPage.selectVehicle("001 FuelEntry - Android");
        vehiclesPage.addNewFuelEntry(Constants.PRICEPERGALLON, Constants.GALLONS, Constants.ODOMETER);
        vehiclesPage.goToFuelLog();

        assertEquals(vehiclesPage.getPriceFromFuelLog(), "$" + Constants.PRICEPERGALLON);
        assertEquals(vehiclesPage.getGallonsFromFuelLog(), Constants.GALLONS);
        assertEquals(vehiclesPage.getOdometerFromFuelLog(), Constants.ODOMETER);

        //verify the date is today
        String todayFormatted = Helpers.convertDateToString(today).toUpperCase();
        assertTrue(vehiclesPage.getDateFromFuelLog().contains(todayFormatted));

        vehiclesPage.selectFirstFuelFromLog();
        vehiclesPage.selectDocuments();
        //verify document has been uploaded
        assertEquals(vehiclesPage.getDocumentName(), Constants.FILENAME_TO_UPLOAD);
    }

}
