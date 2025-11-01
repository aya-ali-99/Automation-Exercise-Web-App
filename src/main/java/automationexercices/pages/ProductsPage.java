package automationexercices.pages;

import automationexercices.drivers.GUIDriver;
import automationexercices.pages.components.NavigationBarComponent;
import automationexercices.utils.dataReader.PropertyReader;
import automationexercices.utils.logs.LogsManager;
import automationexercices.validations.Verification;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductsPage {
    private final GUIDriver driver;
    public NavigationBarComponent navigationBar;

    public ProductsPage(GUIDriver driver) {
        this.driver = driver;
        navigationBar = new NavigationBarComponent(driver);
    }

    // Variables
    private String productPage = "/products";

    // Locators
    private final By searchField = By.id("search_product");
    private final By searchButton = By.id("submit_search");
    private final By itemAddedLabel = By.cssSelector(".modal-body > p");
    private final By viewCartButton = By.cssSelector("p > [href=\"/view_cart\"]");
    private final By continueShoppingButton = By.cssSelector(".modal-footer >button");

    // Dynamic Locators
    private By productName(String productName) {
        return By.xpath("//div[@class='overlay-content'] /p[.='" + productName + "']");
    }

    private By productPrice(String productName) {
        return By.xpath("//div[@class='overlay-content'] /p[.='" + productName + "'] //preceding-sibling::h2");
    }

    private By hoverOnProduct(String productName) {
        return By.xpath("//div[@class='productinfo text-center'] /p[.='" + productName + "']");
    }

    private By addToCartButton(String productName) {
        return By.xpath("//div[@class='productinfo text-center'] /p[.='" + productName + "'] //following-sibling::a");
    }

    private By viewProduct(String productName) {
        return By.xpath("//p[.='" + productName + "'] //following::div[@class='choose'][1]");
    }

    // Actions
    @Step("Navigate to Products Page")
    public ProductsPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + productPage);
        return this;
    }

    @Step("Search for product {productName}")
    public ProductsPage searchForProduct(String productName) {
        driver.element()
                .type(searchField, productName)
                .click(searchButton);
        return this;
    }

    @Step("Click on add to cart for {productName}")
    public ProductsPage clickOnAddToCart(String productName) {
        driver.element()
                .hover(hoverOnProduct(productName))
                .click(addToCartButton(productName));
        return this;
    }

    @Step("Click on view product for {productName}")
    public ProductDetailsPage clickOnViewProduct(String productName) {
        driver.element().click(viewProduct(productName));
        return new ProductDetailsPage(driver);
    }

    @Step("Click on view cart button")
    public ProductsPage clickOnViewCart() {
        driver.element().click(viewCartButton);
        return this;
    }

    @Step("Click on continue shopping button")
    public ProductsPage clickOnContinueShopping() {
        driver.element().click(continueShoppingButton);
        return this;
    }

    // Validations
    @Step("Validate product details for {productName} with price {productPrice}")
    public ProductsPage validateProductDetails(String productName, String productPrice) {
        String actualProductName = driver.element().getText(productName(productName));
        String actualProductPrice = driver.element().getText(productPrice(productName));
        LogsManager.info("Actual product name: " + actualProductName + ", with price: " + actualProductPrice);
        driver.validation().Equals(actualProductName, productName, "Product name does not match");
        driver.validation().Equals(actualProductPrice, productPrice, "Product price does not match");
        return this;
    }

    @Step("Validate item added label contains {expectedLabel}")
    public ProductsPage validateItemAddedLabel(String expectedLabel) {
        String actualLabel = driver.element().getText(itemAddedLabel);
        LogsManager.info("Actual item added label: " + actualLabel);
        driver.verification().Equals(actualLabel, expectedLabel, "Item added label does not match");
        return this;
    }

}
