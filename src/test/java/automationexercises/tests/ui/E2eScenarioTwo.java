package automationexercises.tests.ui;

import api.UserManagementAPI;
import automationexercises.drivers.GUIDriver;
import automationexercises.pages.*;
import automationexercises.pages.components.NavigationBarComponent;
import automationexercises.tests.BaseTest;
import automationexercises.utils.TimeManager;
import automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@Epic("Automation Exercise")
@Feature("E2E Scenarios")
@Owner("Aya")
public class E2eScenarioTwo extends BaseTest {

    String timeStamp = TimeManager.getSimpleTimestamp();

    // Tests

    // Place order scenario 2: register & login before checkout

    @Test(groups = { "scenario_2" })
    @Story("Place order scenario 2: register & login after checkout")
    @Description("Add product to cart")
    @Severity(SeverityLevel.CRITICAL)
    public void addProductToCartBeforeLoginTC() {
        new ProductsPage(driver)
                .navigate()
                .clickOnCategoryChoice(
                        testData.getJsonData("categoryWomen.categoryName"))
                .clickOnWomenTopsChoice()
                .verifyCategoryProductsAreDisplayed(
                        testData.getJsonData("categoryWomen.categoryLabel"))
                .clickOnAddToCart(testData.getJsonData("product.name"))
                .validateItemAddedLabel(testData.getJsonData("messages.cartAdded"))
                .clickOnContinueShopping()
                .clickOnAddToCart(testData.getJsonData("product2.name"))
                .validateItemAddedLabel(testData.getJsonData("messages.cartAdded"))
                .clickOnViewCart()
                .verifyProductDetailsInCart(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"),
                        testData.getJsonData("product.quantity"),
                        testData.getJsonData("product.total"))
                .verifyProductDetailsInCart(
                        testData.getJsonData("product2.name"),
                        testData.getJsonData("product2.price"),
                        testData.getJsonData("product2.quantity"),
                        testData.getJsonData("product2.total"));
    }

    @Test(groups = { "scenario_2" }, dependsOnMethods = {"addProductToCartBeforeLoginTC"})
    @Story("Place order scenario 2: register & login after checkout")
    @Description("Remove product from cart")
    @Severity(SeverityLevel.CRITICAL)
    public void removeProductFromCartTC() {
        new CartPage(driver)
                .navigate()
                .removeProductFromCart(testData.getJsonData("product2.name"))
                .verifyProductIsRemovedFromCart(testData.getJsonData("product2.name"));
    }

    @Test(groups = { "scenario_2" }, dependsOnMethods = {"removeProductFromCartTC",
            "addProductToCartBeforeLoginTC" })
    @Story("Place order scenario 2: register & login after checkout")
    @Description("Checkout without login")
    @Severity(SeverityLevel.CRITICAL)
    public void checkoutBeforeLoginTC() {
        new CartPage(driver)
                .navigate()
                .clickOnProceedToCheckoutButtonWithoutLogin()
                .clickOnRegisterLoginButton()
                .verifySignupLabelVisible();
    }

    @Test(groups = { "scenario_2" }, dependsOnMethods = {"removeProductFromCartTC",
            "addProductToCartBeforeLoginTC", "checkoutBeforeLoginTC" })
    @Story("Place order scenario 2: register & login after checkout")
    @Description("Sign up with valid data")
    @Severity(SeverityLevel.CRITICAL)
    public void validSignUpTC() {
        timeStamp += 1;
        new SignupLoginPage(driver)
                .enterSignupName(testData.getJsonData("name"))
                .enterSignupEmail(testData.getJsonData("email") + timeStamp + "@gmail.com")
                .clickSignupButton();
        new SignupPage(driver)
                .enterPassword(testData.getJsonData("password"))
                .selectBirtDate(testData.getJsonData("day"),
                        testData.getJsonData("month"),
                        testData.getJsonData("year"))
                .selectNewsletter()
                .selectSpecialOffers()
                .selectTitle(testData.getJsonData("titleFemale"))
                .enterFirstName(testData.getJsonData("firstName"))
                .enterLastName(testData.getJsonData("lastName"))
                .enterCompany(testData.getJsonData("companyName"))
                .enterAddress1(testData.getJsonData("address1"))
                .enterAddress2(testData.getJsonData("address2"))
                .selectCountry(testData.getJsonData("country"))
                .enterState(testData.getJsonData("state"))
                .enterCity(testData.getJsonData("city"))
                .enterZipcode(testData.getJsonData("zipcode"))
                .enterMobileNumber(testData.getJsonData("mobileNumber"))
                .clickCreateAccountButton()
                .verifyAccountCreatedLabel();

    }

    @Test(groups = { "scenario_2" }, dependsOnMethods = {"removeProductFromCartTC",
            "addProductToCartBeforeLoginTC", "checkoutBeforeLoginTC", "validSignUpTC" })
    @Story("Place order scenario 2: register & login after checkout")
    @Description("Checkout after registering")
    @Severity(SeverityLevel.CRITICAL)
    public void checkoutAfterLoginTC() {
        new CartPage(driver)
                .navigate()
                .verifyProductDetailsInCart(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"),
                        testData.getJsonData("product.quantity"),
                        testData.getJsonData("product.total"))
                .clickOnProceedToCheckoutButton()
                .verifyDeliveryAddress(
                        testData.getJsonData("titleFemale"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("companyName"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("city"),
                        testData.getJsonData("state"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("country"),
                        testData.getJsonData("mobileNumber"))
                .verifyBillingAddress(
                        testData.getJsonData("titleFemale"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("companyName"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("city"),
                        testData.getJsonData("state"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("country"),
                        testData.getJsonData("mobileNumber"));
    }

    @Test(groups = { "scenario_2" }, dependsOnMethods = {"removeProductFromCartTC",
            "addProductToCartBeforeLoginTC", "checkoutBeforeLoginTC", "validSignUpTC",
            "checkoutAfterLoginTC" })
    @Story("Place order scenario 2: register & login after checkout")
    @Description("Payment")
    @Severity(SeverityLevel.CRITICAL)
    public void paymentScenario2TC() {
        new CheckoutPage(driver)
                .clickPlaceOrderBtn()
                .fillCardInfo(
                        testData.getJsonData("card.cardName"),
                        testData.getJsonData("card.cardNumber"),
                        testData.getJsonData("card.cvc"),
                        testData.getJsonData("card.exMonth"),
                        testData.getJsonData("card.exYear"))
                .clickPayAndConfirmBtn()
                .verifySuccessMessage()
                .clickDownloadInvoiceBtn();
    }

    @Test(groups = { "scenario_2" }, dependsOnMethods = {"removeProductFromCartTC",
            "addProductToCartBeforeLoginTC", "checkoutBeforeLoginTC", "validSignUpTC",
            "checkoutAfterLoginTC", "paymentScenario2TC" })
    @Story("Place order scenario 2: register & login after checkout")
    @Description("Verify invoice file")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyInvoiceFileScenario2TC() {
        new PaymentPage(driver)
                .clickDownloadInvoiceBtn()
                .verifyDownloadInvoiceFile(
                        testData.getJsonData("invoiceName"));
    }

    @Test(groups = { "scenario_2" }, dependsOnMethods = {"removeProductFromCartTC",
            "addProductToCartBeforeLoginTC", "checkoutBeforeLoginTC", "validSignUpTC",
            "checkoutAfterLoginTC", "paymentScenario2TC", "verifyInvoiceFileScenario2TC" })
    @Story("Place order scenario 2: register & login after checkout")
    @Description("Delete account")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteAccountScenario2TC() {
        new UserManagementAPI().deleteUserAccount(
                        testData.getJsonData("email") + timeStamp + "@gmail.com",
                        testData.getJsonData("password"))
                .verifyUserIsDeletedSuccessfully();
    }


    // Configurations
    @BeforeClass
    public void setUp() {
        testData = new JsonReader("checkout-data");
        driver = new GUIDriver();
        new NavigationBarComponent(driver).navigate();
    }

    @AfterClass
    public void tearDown() {
        driver.quitDriver();
    }
}
