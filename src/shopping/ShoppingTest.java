package shopping;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utilities.Utility;

public class ShoppingTest extends Utility {
    String baseUrl = "https://mobile.x-cart.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyThatUserShouldPlaceOrderSuccessfullyForAvengersFabrikationsPlush() throws InterruptedException {
        //mouse hover on the hot deal
        mouseHoverOnly(By.xpath("//ul[@class='nav navbar-nav top-main-menu']//span[@class='primary-title'][normalize-space()='Hot deals']"));
        //mouse hover on the bestseller
        mouseHoverClick(By.xpath("//*[@id=\"header-area\"]/div[1]/div/div[4]/div/ul/li[2]/ul/li[2]/a/span"));
        //verify the text bestseller
        verifyText("Bestsellers", getTextFromElement(By.xpath("//h1[@id='page-title']")), "Error-Message not displayed");
        // mouse hover on the sort by list and click on a-z
        mouseHoverOnly(By.xpath("//span[@class='sort-by-label']"));
        mouseHoverClick(By.xpath("//a[normalize-space()='Name A - Z']"));
        mouseHoverOnly(By.xpath("//*[@id=\"content\"]/div/div/div[2]/div/div/div[3]/ul/li[1]/div/div[2]/div[4]/div/button/span"));
        mouseHoverClick(By.xpath("//*[@id=\"content\"]/div/div/div[2]/div/div/div[3]/ul/li[1]/div/div[2]/div[4]/div/button/span"));
        //verify the text message
        verifyText("Product has been added to your cart", getTextFromElement(By.xpath("//li[@class='info']")), "Failed to add");
        //click on x sign and close
        clickOnElement(By.xpath("//a[@title='Close']"));
        //click on cart and view cart
        clickOnElement(By.xpath("//div[@title='Your cart']"));
        mouseHoverOnly(By.xpath("//*[@id=\"header\"]/div[4]/div[3]/div/a[1]/span"));
        mouseHoverClick(By.xpath("//*[@id=\"header\"]/div[4]/div[3]/div/a[1]/span"));
        //verify the text message
        verifyText("Your shopping cart - 1 item", getTextFromElement(By.xpath("//h1[contains(text(),'Your shopping cart - 1 item')]")), "Error, Message not displayed as expected");
        //verify the subtotal
        verifyText("$299.00", getTextFromElement(By.xpath("//p[@class='subtotal']")), "Error, Message not displayed as expected");
        //click on check out button
        clickOnElement(By.xpath("//button[contains(@class,'regular-button regular-main-button checkout')]"));
        // verify the text message
        verifyText("Log in to your account", getTextFromElement(By.xpath("//h3[contains(text(),'Log in to your account')]")), "Page not found");
        // send email ti email element
        sendTextToElement(By.xpath("//input[@id='email']"), "shahbella55@gmail.com");
        //click on continue button
        clickOnElement(By.xpath("//button[contains(@class,'regular-button anonymous-continue-button submit')]"));
        verifyText("Secure Checkout", getTextFromElement(By.xpath("//h1[normalize-space()='Secure Checkout']")), "Page not founf");
        sendTextToElement(By.xpath("//input[@id='shippingaddress-firstname']"), "Bella");
        sendTextToElement(By.xpath("//input[@id='shippingaddress-lastname']"), "Shah");
        sendTextToElement(By.xpath("//input[@id='shippingaddress-street']"), "156 Ealing Road");
        sendTextToElement(By.xpath("//input[@id='shippingaddress-city']"), Keys.BACK_SPACE + "London");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='shippingaddress-country-code']"), "United Kingdom");
        sendTextToElement(By.xpath("//input[@id='shippingaddress-custom-state']"), "London");
        sendTextToElement(By.xpath("//input[@id='shippingaddress-zipcode']"), Keys.BACK_SPACE + "6");
        clickOnElement(By.xpath("//input[@id='create_profile']"));
        sendTextToElement(By.xpath("//input[@id='password']"), "Bella123");
        clickOnElement(By.xpath("//span[normalize-space()='Local shipping']"));
        clickOnElement(By.xpath("//span[normalize-space()='COD']"));
        verifyText("Place order: $311.03", getTextFromElement(By.xpath("//span[contains(text(),'$311.03')]")), "Error, Message not displayed as expected");
        clickOnElement(By.xpath("//button[@class='btn regular-button regular-main-button place-order submit']"));
        verifyText("Thank you for your order", getTextFromElement(By.xpath("//h1[contains(.,'Thank you for your order')]")), "Error, Message not displayed as expected");
    }

    @Test
    public void verifyThatUserShouldClearShoppingCartSuccessfully() throws InterruptedException {
        mouseHoverOnly(By.xpath("//ul[@class='nav navbar-nav top-main-menu']//span[@class='primary-title'][normalize-space()='Hot deals']"));
        mouseHoverClick(By.xpath("(//span[contains(text(),'Bestsellers')])[2]"));
        verifyText("Bestsellers", getTextFromElement(By.xpath("//h1[@id='page-title']")), "Error-Message not displayed");
        mouseHoverOnly(By.xpath("//span[@class='sort-by-label']"));
        mouseHoverClick(By.xpath("//a[normalize-space()='Name A - Z']"));
        Thread.sleep(3000);
        mouseHoverOnly(By.xpath("//a[@class='product-thumbnail next-previous-assigned']"));
        Thread.sleep(3000);
        mouseHoverClick(By.xpath("//button[contains(@class,'btn  regular-button add-to-cart product-add2cart productid-13')]"));
        verifyText("Product has been added to your cart", getTextFromElement(By.xpath("//li[contains(text(),'Product has been added to your cart')]")), "Error, Message not displayed");
        clickOnElement(By.xpath("//a[@title='Close']"));
        clickOnElement(By.xpath("//div[@title='Your cart']"));
        Thread.sleep(2000);
        clickOnElement(By.xpath("//a[@class='regular-button cart']"));
        verifyText("Your shopping cart - 1 item", getTextFromElement(By.xpath("//h1[contains(text(),'Your shopping cart - 1 item')]")), "Error, Message not displayed as expected");
        clickOnElement(By.xpath("//a[normalize-space()='Empty your cart']"));
        Thread.sleep(2000);
        String Message = getTextFromAlert();
        verifyText("Are you sure you want to clear your cart?", Message, "Error, Message not displayed as expected");
        acceptAlert();
        verifyText("Item(s) deleted from your cart", getTextFromElement(By.xpath("//li[contains(.,'Item(s) deleted from your cart')]")), "Error, Message not displayed");
        Thread.sleep(2000);
        clickOnElement(By.xpath("//a[@title='Close']"));
        Thread.sleep(2000);
        verifyText("Your cart is empty", getTextFromElement(By.xpath("//h1[contains(.,'Your cart is empty')]")), "Error, Message not displayed");

    }

    @After
    public void tearDown() {
        closeBrowser();
    }


}




