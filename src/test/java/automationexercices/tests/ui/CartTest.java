package automationexercices.tests.ui;

import automationexercices.drivers.GUIDriver;
import automationexercices.pages.CartPage;
import automationexercices.pages.ProductsPage;
import automationexercices.pages.components.NavigationBarComponent;
import automationexercices.tests.BaseTest;
import automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



@Epic("Automation Exercise ")
@Feature("UI Cart Management")
@Story("Cart Details")
@Severity(SeverityLevel.CRITICAL)
@Owner("Aya")
public class CartTest extends BaseTest {

    // Tests
    @Test
    @Description("Verify product details in cart without login")
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
    @Description("Verify product is removed successfully from cart")
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
    public void preCondition(){
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
