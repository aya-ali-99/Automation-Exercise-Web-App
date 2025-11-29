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


@Epic("Automation Exercise ")
@Feature("UI Products Management")
@Story("Product Details")
@Severity(SeverityLevel.CRITICAL)
@Owner("Aya")
public class ProductDetailsTest extends BaseTest {

    // Tests

    @Test
    @Description("Verify product details")
    public void verifyProductDetailsWithoutLoginTC(){
        new ProductsPage(driver)
                .navigate()
                .clickOnViewProduct(testData.getJsonData("product.name"))
                .validateProductDetails(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"));
    }


    @Test
    @Description("Verify product review success message")
    public void verifyProductReviewSuccessMessageWithoutLoginTC(){
        new ProductsPage(driver)
                .navigate()
                .clickOnViewProduct(testData.getJsonData("product.name"))
                .addReview(
                        testData.getJsonData("review.name"),
                        testData.getJsonData("review.email"),
                        testData.getJsonData("review.review"))
                .validateReviewSuccessMessage(testData.getJsonData("messages.review"));
    }

    @Test
    @Description("Verify product quantity in cart")
    public void verifyProductQuantityInCartWithoutLoginTC(){
        new ProductsPage(driver)
                .navigate()
                .clickOnViewProduct(testData.getJsonData("product.name"))
                .addQuantity(testData.getJsonData("addQuantity"))
                .clickOnAddToCartButton()
                .clickOnViewCartButton()
                .verifyProductQuantityInCart(testData.getJsonData("product.name"),
                        testData.getJsonData("addQuantity"));
    }



    // Configurations
    @BeforeClass
    public void preCondition(){
        testData = new JsonReader("product-details-data");
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
