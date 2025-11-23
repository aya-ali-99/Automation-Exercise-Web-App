package automationexercices.tests.ui;

import automationexercices.drivers.GUIDriver;
import automationexercices.pages.ProductsPage;
import automationexercices.pages.components.NavigationBarComponent;
import automationexercices.tests.BaseTest;
import automationexercices.utils.TimeManager;
import automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.openqa.selenium.By;
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
    @Description("Search for product and validate its details without logging in")
    @Test
    public void searchForProductWithoutLoginTC(){
        new ProductsPage(driver)
                .navigate()
                .searchForProduct(testData.getJsonData("searchedProduct.name"))
                .validateProductDetails(
                        testData.getJsonData("searchedProduct.name"),
                        testData.getJsonData("searchedProduct.price"));
    }

    @Description("Add product to cart without logging in")
    @Test
    public void addProductToCartWithoutLoginTC(){
        new ProductsPage(driver)
                .navigate()
                .clickOnAddToCart(testData.getJsonData("product1.name"))
                .validateItemAddedLabel(
                        testData.getJsonData("messages.cartAdded"));
    }

    @Test
    @Description("Choose Kids Dress Category")
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
