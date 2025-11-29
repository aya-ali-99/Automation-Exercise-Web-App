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
@Story("Products Management")
@Severity(SeverityLevel.CRITICAL)
@Owner("Aya")
public class ProductsTest extends BaseTest {

    // Tests

    @Test
    @Description("Verify searching for product without logging in")
    public void searchForProductWithoutLoginTC(){
        new ProductsPage(driver)
                .navigate()
                .searchForProduct(testData.getJsonData("searchedProduct.name"))
                .validateProductDetails(
                        testData.getJsonData("searchedProduct.name"),
                        testData.getJsonData("searchedProduct.price"));
    }


    @Test
    @Description("Verify product added to cart without logging in")
    public void addProductToCartWithoutLoginTC(){
        new ProductsPage(driver)
                .navigate()
                .clickOnAddToCart(testData.getJsonData("product1.name"))
                .validateItemAddedLabel(
                        testData.getJsonData("messages.cartAdded"));
    }

    @Test
    @Description("Verify category products are displayed")
    public void chooseKidsDressCategoryTC(){
        new ProductsPage(driver)
                .navigate()
                .clickOnCategoryChoice(
                        testData.getJsonData("categoryKids.categoryName")
                )
                .clickOnKidsDressChoice()
                .verifyCategoryProductsAreDisplayed(
                        testData.getJsonData("categoryKids.categoryLabel")
                )
                .clickOnCategoryChoice(
                        testData.getJsonData("categoryWomen.categoryName")
                )
                .clickOnWomenTopsChoice()
                .verifyCategoryProductsAreDisplayed(
                        testData.getJsonData("categoryWomen.categoryLabel")
                );
    }


    // Configurations
    @BeforeClass
    public void preCondition(){
        testData = new JsonReader("products-data");
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
