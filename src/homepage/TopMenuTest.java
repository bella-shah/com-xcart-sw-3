package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {
    String baseUrl = "https://mobile.x-cart.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToShippingPageSuccessfully() {
        //click
        clickOnElement(By.xpath("(//span[contains(text(),'Shipping')])[2]"));
        //this is from requirement
        String expectedMessage = "Shipping";
        String actualMessage = getTextFromElement(By.xpath("//h1[@id='page-title']"));
    }

    @Test
    public void verifyUserShouldNavigateToNewPageSuccessfully() {
        //click
        clickOnElement(By.xpath("(//span[contains(text(),'New!')])[2]"));
        //this is from requirement
        String expectedMessage = "New!";
        String actualMessage = getTextFromElement(By.xpath("//h1[@id='page-title']"));
    }

    @Test
    public void verifyUserShouldNavigateToComingSoonPageSuccessfully() {
        //click
        clickOnElement(By.xpath("(//span[contains(text(),'Coming soon')])[2]"));
        //this message is from requirement
        String expectedMessage = "Coming Soon";
        String actualMessage = getTextFromElement(By.xpath("//h1[@id='page-title']"));
    }

    @Test
    public void verifyUserShouldNavigateToContactUsPageSuccessfully() {
        //click
        clickOnElement(By.xpath("(//span[contains(text(),'Contact us')])[2]"));
        //this message is from requirement
        String expectedMessage = "Contact Us";
        String actualMessage = getTextFromElement(By.xpath("//h1[@id='page-title']"));
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}

