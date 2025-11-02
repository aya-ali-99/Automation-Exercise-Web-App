package automationexercices.tests.ui;

import automationexercices.drivers.GUIDriver;
import automationexercices.pages.ProductsPage;
import automationexercices.pages.components.NavigationBarComponent;
import automationexercices.tests.BaseTest;
import automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductDetailsTest extends BaseTest {


    @Epic("Automation Exercise ")
    @Feature("UI Products Management")
    @Story("Product Details")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Aya")
    // Tests
    @Description("Verify product details")
    @Test
    public void verifyProductDetailsTC(){
        new ProductsPage(driver)
                .navigate()
                .clickOnViewProduct(testData.getJsonData("product.name"))
                .validateProductDetails(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"));
    }

    @Description("Verify product review success message")
    @Test
    public void verifyProductReviewSuccessMessageTC(){
        new ProductsPage(driver)
                .navigate()
                .clickOnViewProduct(testData.getJsonData("product.name"))
                .addReview(
                        testData.getJsonData("review.name"),
                        testData.getJsonData("review.email"),
                        testData.getJsonData("review.review"))
                .validateReviewSuccessMessage(testData.getJsonData("messages.review"));
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
