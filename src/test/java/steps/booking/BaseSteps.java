package steps.booking;

import excel.ExcelSupport;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Reporter;
import pages.booking.FlightsHomePage;
import pages.booking.StaysHomePage;
import tests.BaseTest;

import java.io.IOException;
import java.util.Map;

public class BaseSteps extends BaseTest {

    String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
    String env = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    String wait = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("wait");
    String quit = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("quit");

    Map<String, String> data;

    @Before
    public void setup() throws Exception {
        init(browser, wait);
        openBookingApp(env);
    }

    @After
    public void tearDown() {
        if (quit.equalsIgnoreCase("Yes")) {
            quit();
        }
    }

    @Given("I read test data from {string} {string} by row {string}")
    public void iReadTestDataFromExcelByRow(String fileName, String sheetName, String rowNum) throws IOException {
        data = new ExcelSupport().getDataByRow(fileName, sheetName, rowNum);
    }

    @Given("I read test data from {string} {string} by id {string}")
    public void iReadTestDataFromExcelByID(String fileName, String sheetName, String id) throws Exception {
        data = new ExcelSupport().getDataByID(fileName, sheetName, id);
    }

    @Given("I am on booking stays page")
    public void iAmOnBookingStaysPage() {
//
    }

    @When("I enter destination location")
    public void iEnterDestinationLocation() {
        new StaysHomePage(driver).setLocation(data.get("location"));
    }

    @And("I enter check in date")
    public void iEnterCheckInDate() {
        StaysHomePage staysHomePage = new StaysHomePage(driver);

        staysHomePage.openCalendar();
        staysHomePage.setDate(data.get("checkInDate"));
    }

    @And("I enter check out date")
    public void iEnterCheckOutDate() {
        new StaysHomePage(driver).setDate(data.get("checkOutDate"));
    }

    @And("I add adults")
    public void iAddAdults() {
        StaysHomePage staysHomePage = new StaysHomePage(driver);

        staysHomePage.openGuestMenu();
        staysHomePage.addAdults(data.get("numAdults"));
    }

    @And("I add children")
    public void iAddChildren() throws InterruptedException {
        StaysHomePage staysHomePage = new StaysHomePage(driver);
        staysHomePage.addChildren(data.get("numChildren"), data.get("childrenAges"));
    }

    @And("I add rooms")
    public void iAddRooms() {
        new StaysHomePage(driver).addRooms(data.get("numRooms"));
    }

    @And("I click search button")
    public void iClickSearchButton() {
        new StaysHomePage(driver).clickSearchButton();
    }

    @Given("I am on the booking flights page")
    public void iAmOnTheBookingFlightsPage() {
        new StaysHomePage(driver).headerComponent.navigateToFlightsPage();
    }

    @And("I select multiple destination option")
    public void iSelectMultipleDestinationOption() {
        new FlightsHomePage(driver).clickMultipleDestinationOption();
    }

    @And("I enter destinations")
    public void iEnterDestinations() throws InterruptedException {
        new FlightsHomePage(driver).enterDestinations(data);
    }

    @And("I click search flights button")
    public void iClickSearchFlightsButton() {
        new FlightsHomePage(driver).search();
    }

    @And("Ienter destination return flight")
    public void ienterDestinationReturnFlight() {
        new FlightsHomePage(driver).enterDataReturnFlight(data.get("origin1"),data.get("destination1"));
    }

    @And("Check date for return flight")
    public void checkDateForReturnFlight() {
        FlightsHomePage hp = new FlightsHomePage(driver);
        hp.openCalendarReturnFlight();
        hp.setDate(data.get("month3"),data.get("day3"));
        hp.setDate(data.get("month4"),data.get("day4"));
    }

    @Then("I verify results")
    public void iVerifyResults() throws InterruptedException {
        FlightsHomePage hp = new FlightsHomePage(driver);
        hp.verifyReturnFlightResults(data.get("expectedText3"),data.get("expectedText4"));
    }
}