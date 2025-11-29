package automationexercises.pages;

import automationexercises.drivers.GUIDriver;
import automationexercises.utils.dataReader.PropertyReader;
import automationexercises.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartPage {
    private GUIDriver driver;
    public CartPage(GUIDriver driver) {
        this.driver = driver;
    }

    // Variables
    private String cartPage = "/view_cart";

    // Locators
    private final By proceedToCheckOutBtn = By.cssSelector(".col-sm-6 a");
    private final By checkOutLabel = By.cssSelector(".modal-dialog");
    private final By registerLoginBtn = By.cssSelector(".modal-body u");
    private final By continueOnCart = By.cssSelector(".modal-footer button");

    // Dynamic Locators
    private final By getProductName(String productName){
        return By.xpath("(//h4  /a[.='" + productName + "'])[1]");
    }

    private By productPrice(String productName) {
        return By.xpath("(//h4  /a[.='" + productName + "'] //following::td[@class='cart_price'] /p)[1]");
    }

    private By productQuantity(String productName) {
        return By.xpath("(//h4  /a[.='" + productName + "'] //following::td[@class='cart_quantity'] /button)[1]");
    }

    private By productTotal(String productName) {
        return By.xpath("(//h4  /a[.='"+productName+"'] //following::td[@class='cart_total'] /p)[1]");
    }

    private By removeProductDL(String productName) {
        return By.xpath("(//h4  /a[.='"+productName+"'] //following::td[@class='cart_delete'] /a)[1]");
    }

    // Actions
    @Step("Navigate to view cart page")
    public CartPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + cartPage);
        return this;
    }

    @Step("Click on Proceed to Checkout button")
    public CheckoutPage clickOnProceedToCheckoutButton() {
        driver.element().click(proceedToCheckOutBtn);
        return new CheckoutPage(driver);
    }

    @Step("Click on Proceed to Checkout button")
    public CartPage clickOnProceedToCheckoutButtonWithoutLogin() {
        driver.element().click(proceedToCheckOutBtn);
        return this;
    }

    @Step("Remove product from the cart")
    public CartPage removeProductFromCart(String productName) {
        driver.element().click(removeProductDL(productName));
        return this;
    }

    @Step("Click on continue on cart button")
    public CartPage clickOnContinueOnCartButton() {
        driver.element().click(continueOnCart);
        return this;
    }

    @Step("Click on register/login button")
    public SignupLoginPage clickOnRegisterLoginButton() {
        driver.element().click(registerLoginBtn);
        return new SignupLoginPage(driver);
    }

    // Verifications
    @Step("Verify product details in cart")
    public CartPage verifyProductDetailsInCart(String productName, String productPrice, String productQuantity, String productTotal) {
        String actualProductName = driver.element().getText(getProductName(productName));
        String actualProductPrice = driver.element().getText(productPrice(productName));
        String actualProductQuantity = driver.element().getText(productQuantity(productName));
        String actualProductTotal = driver.element().getText(productTotal(productName));
        LogsManager.info("Actual product name:" + productName
                + ", actual product price:" + productPrice
                + ", actual product quantity:" + productQuantity
                + ", actual product total:" + productTotal);
        driver.validation()
                .Equals(actualProductName,
                        productName, "Product name does not match")
                .Equals(actualProductPrice,
                        productPrice, "Product price does not match")
                .Equals(actualProductQuantity,
                        productQuantity, "Product quantity does not match")
                .Equals(actualProductTotal,
                        productTotal, "Product total does not match");
        return this;
    }

    @Step("Verify product quantity in cart")
    public CartPage verifyProductQuantityInCart(String productName, String productQuantity) {
        String actualProductQuantity = driver.element().getText(productQuantity(productName));
        driver.verification().Equals(productQuantity,
                actualProductQuantity, "Product quantity does not match");
        return this;
    }

    @Step("Verify check out label")
    public CartPage verifyCheckOutLabel() {
        String actualCheckOutLabel = driver.element().getText(checkOutLabel);
        driver.verification().Equals("Checkout",
                actualCheckOutLabel, "Check out label does not match");
        return this;
    }

    @Step("Verify product is removed from cart")
    public CartPage verifyProductIsRemovedFromCart(String productName) {
        driver.verification().isElementNotVisible(getProductName(productName));
        return this;
    }
}
