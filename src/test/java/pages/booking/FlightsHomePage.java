package pages.booking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;
import pages.booking.components.FooterComponent;
import pages.booking.components.HeaderComponent;

import java.util.Map;

public class FlightsHomePage extends BasePage {

    public HeaderComponent headerComponent;
    public FooterComponent footerComponent;

    public FlightsHomePage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver);
        footerComponent = new FooterComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='css-17g2hv0-radio-group']/li[3]")
    WebElement multipleDestinationRadio;

    public void clickMultipleDestinationOption() {
        clickElement(multipleDestinationRadio);
    }

    public void enterDestinations(Map<String, String> data) {
        //









    }

}