package automationexercices.tests.ui;


import api.UserManagementAPI;
import automationexercices.drivers.GUIDriver;
import automationexercices.pages.*;
import automationexercices.pages.components.NavigationBarComponent;
import automationexercices.tests.BaseTest;
import automationexercices.utils.TimeManager;
import automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.*;

@Epic("Automation Exercise ")
@Feature("UI Checkout Management")
@Severity(SeverityLevel.CRITICAL)
@Owner("Aya")
public class CheckoutTest extends BaseTest {
    String timeStamp = TimeManager.getSimpleTimestamp();

    // Tests

    // Place order scenario 1: register & login before checkout

    @Test(groups = {"scenario_1"})
    @Story("Place order scenario 1: register & login before checkout")
    @Description("Register new account")
    public void registerNewAccountTC(){
        new UserManagementAPI().createRegisterUserAccount(
                        testData.getJsonData("name"),
                        (testData.getJsonData("email")+ timeStamp +"@gmail.com"),
                        testData.getJsonData("password"),
                        testData.getJsonData("titleFemale"),
                        testData.getJsonData("day"),
                        testData.getJsonData("month"),
                        testData.getJsonData("year"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("companyName"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("country"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("state"),
                        testData.getJsonData("city"),
                        testData.getJsonData("mobileNumber"))
                .verifyUserIsCreatedSuccessfully();
    }


    @Test(dependsOnMethods = {"registerNewAccountTC"}, groups = {"scenario_1"})
    @Story("Place order scenario 1: register & login before checkout")
    @Description("Login to account")
    public void loginToAccountTC(){
        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.getJsonData("email")+ timeStamp+"@gmail.com")
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton()
                .navigationBar
                .verifyUserLabel(testData.getJsonData("name"));
    }


    @Test(groups = {"scenario_1"}, dependsOnMethods = {"loginToAccountTC", "registerNewAccountTC"})
    @Story("Place order scenario 1: register & login before checkout")
    @Description("Add product to cart")
    public void addProductToCartTC(){
        new ProductsPage(driver)
                .navigate()
                .clickOnAddToCart(testData.getJsonData("product.name"))
                .validateItemAddedLabel(testData.getJsonData("messages.cartAdded"))
                .clickOnViewCart()
                .verifyProductDetailsInCart(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"),
                        testData.getJsonData("product.quantity"),
                        testData.getJsonData("product.total"));
    }


    @Test(groups = {"scenario_1"},
            dependsOnMethods = {"addProductToCartTC", "loginToAccountTC", "registerNewAccountTC"})
    @Story("Place order scenario 1: register & login before checkout")
    @Description("Checkout with login")
    public void checkoutTC(){
        new CartPage(driver)
                .navigate()
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
                        testData.getJsonData("mobileNumber")
                )
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
                        testData.getJsonData("mobileNumber")
                );

    }


    @Test(groups = {"scenario_1"},
            dependsOnMethods = {"checkoutTC", "addProductToCartTC", "loginToAccountTC", "registerNewAccountTC"})
    @Story("Place order scenario 1: register & login before checkout")
    @Description("Delete account")
    public void deleteAccountTC(){
        new UserManagementAPI().deleteUserAccount(
                        testData.getJsonData("email")+ timeStamp+"@gmail.com",
                        testData.getJsonData("password"))
                .verifyUserIsDeletedSuccessfully();
    }

    // Place order scenario 2: register & login before checkout


    @Test(groups = {"scenario_2"}, dependsOnGroups = {"scenario_1"})
    @Story("Place order scenario 2: register & login after checkout")
    @Description("Add product to cart")
    public void addProductToCartBeforeLoginTC(){
        new ProductsPage(driver)
                .navigate()
                .clickOnAddToCart(testData.getJsonData("product.name"))
                .validateItemAddedLabel(testData.getJsonData("messages.cartAdded"))
                .clickOnViewCart()
                .verifyProductDetailsInCart(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"),
                        testData.getJsonData("product.quantity"),
                        testData.getJsonData("product.total"));
    }


    @Test(groups = {"scenario_2"},
            dependsOnGroups = {"scenario_1"},
            dependsOnMethods = {"addProductToCartBeforeLoginTC"})
    @Story("Place order scenario 2: register & login after checkout")
    @Description("Checkout without login")
    public void checkoutBeforeLoginTC(){
        new CartPage(driver)
                .navigate()
                .clickOnProceedToCheckoutButtonWithoutLogin()
                .clickOnRegisterLoginButton()
                .verifySignupLabelVisible();
    }


    @Test(groups = {"scenario_2"},
            dependsOnGroups = {"scenario_1"},
            dependsOnMethods = {"addProductToCartBeforeLoginTC", "checkoutBeforeLoginTC"})
    @Story("Place order scenario 2: register & login after checkout")
    @Description("Sign up with valid data")
    public void validSignUpTC(){
        timeStamp+=1;
        new SignupLoginPage(driver)
                .enterSignupName(testData.getJsonData("name"))
                .enterSignupEmail(testData.getJsonData("email")+ timeStamp+"@gmail.com")
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

    @Test(groups = {"scenario_2"},
            dependsOnGroups = {"scenario_1"},
            dependsOnMethods = {"addProductToCartBeforeLoginTC", "checkoutBeforeLoginTC", "validSignUpTC"})
    @Story("Place order scenario 2: register & login after checkout")
    @Description("Checkout after registering")
    public void checkoutAfterLoginTC(){
        new CartPage(driver)
                .navigate()
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
                        testData.getJsonData("mobileNumber")
                )
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
                        testData.getJsonData("mobileNumber")
                );
    }


    @Test(groups = {"scenario_2"},
            dependsOnGroups = {"scenario_1"},
            dependsOnMethods = {"addProductToCartBeforeLoginTC", "checkoutBeforeLoginTC", "validSignUpTC", "checkoutAfterLoginTC"})
    @Story("Place order scenario 2: register & login after checkout")
    @Description("Delete account")
    public void deleteAccountScenario2TC(){
        new UserManagementAPI().deleteUserAccount(
                        testData.getJsonData("email")+ timeStamp+"@gmail.com",
                        testData.getJsonData("password"))
                .verifyUserIsDeletedSuccessfully();
    }

    // Configurations
    @BeforeClass
    public void setUp(){
        testData = new JsonReader("checkout-data");
        driver = new GUIDriver();
        new NavigationBarComponent(driver).navigate();
    }

    @AfterClass
    public void tearDown() {
        driver.quitDriver();
    }
}
