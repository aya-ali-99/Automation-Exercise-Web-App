package automationexercises.pages;

import automationexercises.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class PaymentPage {
    private GUIDriver driver;
    public PaymentPage(GUIDriver driver) {
        this.driver = driver;
    }

    // Variables
    private final String paymentEndPoint = "/payment";

    // Locators
    private final By nameOnCard = By.cssSelector("[name=\"name_on_card\"]");
    private final By cardNumber = By.cssSelector("[name=\"card_number\"]");
    private final By cvc = By.cssSelector("[name=\"cvc\"]");
    private final By expiryMonth = By.cssSelector("[name=\"expiry_month\"]");
    private final By expiryYear = By.cssSelector("[name=\"expiry_year\"]");
    private final By payAndConfirmBtn = By.id("submit");
    private final By successMessage = By.cssSelector("#form > div > div > div > p");  //Congratulations! Your order has been confirmed!
    private final By downloadInvoiceBtn = By.cssSelector("[class=\"btn btn-default check_out\"]");

    // Actions
    @Step("Fill card information")
    public PaymentPage fillCardInfo(String nameOnCard, String cardNumber, String cvc, String expiryMonth, String expiryYear) {
        driver.element().type(this.nameOnCard, nameOnCard);
        driver.element().type(this.cardNumber, cardNumber);
        driver.element().type(this.cvc, cvc);
        driver.element().type(this.expiryMonth, expiryMonth);
        driver.element().type(this.expiryYear, expiryYear);
        return this;
    }

    @Step("Click on pay and confirm button")
    public PaymentPage clickPayAndConfirmBtn() {
        driver.element().click(payAndConfirmBtn);
        return this;
    }

    @Step("Click on download invoice button")
    public PaymentPage clickDownloadInvoiceBtn() {
        driver.element().click(downloadInvoiceBtn);
        return this;
    }

    // Validations
    @Step("Verify success message")
    public PaymentPage verifySuccessMessage() {
        String actualSucessMsg = driver.element().getText(successMessage);
        driver.verification().Equals(actualSucessMsg, "Congratulations! Your order has been confirmed!", "Success message does not match");
        return this;
    }

    @Step("Verify download invoice file")
    public PaymentPage verifyDownloadInvoiceFile(String invoiceName) {
        driver.verification().assertFileExists(invoiceName, "Invoice file does not exist");
        return this;
    }
}
