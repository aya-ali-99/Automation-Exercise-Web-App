package automationexercises.pages;

import automationexercises.drivers.GUIDriver;
import automationexercises.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CheckoutPage {
    private GUIDriver driver;

    public CheckoutPage(GUIDriver driver) {
        this.driver = driver;
    }

    // Variables
    private final String checkoutEndPoint = "/checkout/checkout";

    // Locators
    private final By commentField = By.cssSelector(".form-control");
    private final By placeOrderBtn = By.cssSelector("[href=\"/payment\"]");

    //delivery address
    private final By deliveryName = By.xpath("//ul[@id='address_delivery'] /li[@class='address_firstname address_lastname']");
    private final By deliveryCompany = By.xpath("//ul[@id='address_delivery'] /li[@class='address_address1 address_address2'][1]");
    private final By deliveryAddress1 = By.xpath("//ul[@id='address_delivery'] /li[@class='address_address1 address_address2'][2]");
    private final By deliveryAddress2 = By.xpath("//ul[@id='address_delivery'] /li[@class='address_address1 address_address2'][3]");
    private final By deliveryCityStateZip = By.xpath("//ul[@id='address_delivery'] /li[@class='address_city address_state_name address_postcode']");
    private final By deliveryCountry = By.xpath("//ul[@id='address_delivery'] /li[@class='address_country_name']");
    private final By deliveryPhone = By.xpath("//ul[@id='address_delivery'] /li[@class='address_phone']");
    //billing address
    private final By billingName = By.xpath("//ul[@id='address_invoice'] /li[@class='address_firstname address_lastname']");
    private final By billingCompany = By.xpath("//ul[@id='address_invoice'] /li[@class='address_address1 address_address2'][1]");
    private final By billingAddress1 = By.xpath("//ul[@id='address_invoice'] /li[@class='address_address1 address_address2'][2]");
    private final By billingAddress2 = By.xpath("//ul[@id='address_invoice'] /li[@class='address_address1 address_address2'][3]");
    private final By billingCityStateZip = By.xpath("//ul[@id='address_invoice'] /li[@class='address_city address_state_name address_postcode']");
    private final By billingCountry = By.xpath("//ul[@id='address_invoice'] /li[@class='address_country_name']");
    private final By billingPhone = By.xpath("//ul[@id='address_invoice'] /li[@class='address_phone']");


    // Actions
    @Step("Navigate to checkout page")
    public CheckoutPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + checkoutEndPoint);
        return this;
    }

    @Step("Enter comment")
    public CheckoutPage enterComment(String comment) {
        driver.element().type(commentField, comment);
        return this;
    }

    @Step("Click on place order button")
    public PaymentPage clickPlaceOrderBtn() {
        driver.element().click(placeOrderBtn);
        return new PaymentPage(driver);
    }

    // Validations
    @Step("Verify delivery address")
    public CheckoutPage verifyDeliveryAddress(String title ,String fName,String lName, String company, String address1, String address2,
                                              String city,String state, String zip, String country, String phone) {
        driver.validation().Equals(driver.element().getText(deliveryName),(title+". "+fName+" "+lName) , " Delivery Name is not matched")
                .Equals(driver.element().getText(deliveryCompany),company , " Delivery Company is not matched")
                .Equals(driver.element().getText(deliveryAddress1),address1 , " Delivery Address1 is not matched")
                .Equals(driver.element().getText(deliveryAddress2),address2 , " Delivery Address2 is not matched")
                .Equals(driver.element().getText(deliveryCityStateZip),city+" "+state+" "+zip , " Delivery City State Zip is not matched")
                .Equals(driver.element().getText(deliveryCountry),country , " Delivery Country is not matched")
                .Equals(driver.element().getText(deliveryPhone),phone , " Delivery Phone is not matched");
        return this;
    }

    @Step("Verify billing address")
    public CheckoutPage verifyBillingAddress(String title ,String fName,String lName, String company, String address1, String address2,
                                             String city,String state, String zip, String country, String phone) {
        driver.validation().Equals(driver.element().getText(billingName),(title+". "+fName+" "+lName) , " Billing Name is not matched")
                .Equals(driver.element().getText(billingCompany),company , " Billing Company is not matched")
                .Equals(driver.element().getText(billingAddress1),address1 , " Billing Address1 is not matched")
                .Equals(driver.element().getText(billingAddress2),address2 , " Billing Address2 is not matched")
                .Equals(driver.element().getText(billingCityStateZip),city+" "+state+" "+zip , " Billing City State Zip is not matched")
                .Equals(driver.element().getText(billingCountry),country , " Billing Country is not matched")
                .Equals(driver.element().getText(billingPhone),phone , " Billing Phone is not matched");
        return this;
    }

}
