package automationexercises.pages;

import automationexercises.drivers.GUIDriver;
import automationexercises.utils.dataReader.PropertyReader;
import automationexercises.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductDetailsPage {
    private GUIDriver driver;

    public ProductDetailsPage(GUIDriver driver) {
        this.driver = driver;
    }

    // Variables
    private String productDetailsPage = "/product_details/1";

    // Locators
    private final By productName = By.cssSelector(".product-information h2");
    private final By productPrice = By.cssSelector(".product-information span span");
    private final By addToCartButton = By.cssSelector(".product-information button");
    private final By quantityInput = By.cssSelector("#quantity");
    private final By reviewName = By.id("name");
    private final By reviewEmail = By.id("email");
    private final By reviewTextArea = By.cssSelector("[placeholder=\"Add Review Here!\"]");
    private final By submitReviewButton = By.xpath("//*[@id=\"button-review\"]");
    private final By reviewSuccessMessage = By.cssSelector(".alert-success span");  //Thank you for your review.
    private final By viewCartBtn = By.cssSelector(".modal-content u");

    // Actions
    @Step("Navigate to products details")
    public ProductDetailsPage navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + productDetailsPage);
        return this;
    }

    @Step("Add product to cart")
    public ProductDetailsPage clickOnAddToCartButton(){
        driver.element().click(addToCartButton);
        return this;
    }

    @Step("Add Review")
    public ProductDetailsPage addReview(String name, String email, String review){
        driver.element().type(reviewName, name)
                .type(reviewEmail, email)
                .type(reviewTextArea, review)
                .click(submitReviewButton);
        return this;
    }

    @Step("Add quantity")
    public ProductDetailsPage addQuantity(String quantity){
        driver.element().type(quantityInput, quantity);
        return this;
    }

    @Step("Click on view cart button")
    public CartPage clickOnViewCartButton(){
        driver.element().click(viewCartBtn);
        return new CartPage(driver);
    }

    // Verifications
    @Step("Verify product details")
    public ProductDetailsPage validateProductDetails(String productName, String productPrice){
        String actualProductName = driver.element().getText(this.productName);
        String actualProductPrice = driver.element().getText(this.productPrice);
        LogsManager.info("Actual product name: " + actualProductName + ", actual price: " + actualProductPrice);
        driver.validation().Equals(actualProductName, productName, "Product name does not match");
        driver.validation().Equals(actualProductPrice, productPrice, "Product price does not match");
        return this;
    }

    @Step("Verify review success message")
    public ProductDetailsPage validateReviewSuccessMessage(String SuccessMessage){
        String actualSuccessMessage = driver.element().getText(reviewSuccessMessage);
        LogsManager.info("Actual review success message: " + actualSuccessMessage);
        driver.verification().Equals(actualSuccessMessage, SuccessMessage, "Review success message does not match");
        return this;
    }
}
