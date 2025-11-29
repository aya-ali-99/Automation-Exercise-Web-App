package automationexercises.pages.components;

import automationexercises.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SubscriptionComponent {
    private GUIDriver driver;

    public SubscriptionComponent(GUIDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By subscriptionEmail = By.id("susbscribe_email");
    private final By submitSubscriptionBtn = By.id("subscribe");
    private final By successMessage = By.cssSelector("#success-subscribe>div");

    // Actions
    @Step("Fill Subscription Form")
    public SubscriptionComponent fillSubscriptionForm(String email){
        driver.element().type(subscriptionEmail, email);
        return this;
    }

    @Step("Click on submit subscription button")
    public SubscriptionComponent clickSubmitSubscriptionBtn(){
        driver.element().click(submitSubscriptionBtn);
        return this;
    }

    // Validations

    @Step("Verify subscription success message")
    public SubscriptionComponent verifySuccessMessage(String expectedMsg){
        driver.verification()
                .Equals(driver.element().getText(successMessage),
                        expectedMsg,
                        "Subscription success message is not displayed");
        return this;
    }


}
