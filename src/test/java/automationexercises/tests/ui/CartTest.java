package automationexercises.tests.ui;

import automationexercises.drivers.GUIDriver;
import automationexercises.pages.ProductsPage;
import automationexercises.pages.components.NavigationBarComponent;
import automationexercises.tests.BaseTest;
import automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("Cart Management")
@Story("View Cart")
@Severity(SeverityLevel.CRITICAL)
@Owner("Aya")
public class CartTest extends BaseTest {

    // Tests
    @Test
    @Story("View Cart")
    @Description("Verify product details in cart without login")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyProductDetailsInCartWithoutLogin() {
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

    @Test
    @Story("Remove from Cart")
    @Description("Verify product is removed successfully from cart")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyProductIsRemovedSuccessfullyFromCart() {
        new ProductsPage(driver)
                .navigate()
                .clickOnAddToCart(testData.getJsonData("product.name"))
                .clickOnContinueShopping()
                .clickOnAddToCart(testData.getJsonData("product2.name"))
                .clickOnViewCart()
                .verifyProductDetailsInCart(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"),
                        testData.getJsonData("product.quantity"),
                        testData.getJsonData("product.total"))
                .removeProductFromCart(testData.getJsonData("product.name"))
                .verifyProductIsRemovedFromCart(testData.getJsonData("product.name"));
    }

    // Configurations
    @BeforeClass
    public void preCondition() {
        testData = new JsonReader("cart-data");
    }

    @BeforeMethod
    public void setup() {
        driver = new GUIDriver();
        new NavigationBarComponent(driver).navigate();
    }

    @AfterMethod
    public void tearDown() {
        driver.quitDriver();
    }
}
