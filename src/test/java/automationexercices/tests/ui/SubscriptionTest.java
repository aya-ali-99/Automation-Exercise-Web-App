package automationexercices.tests.ui;

import automationexercices.drivers.GUIDriver;
import automationexercices.pages.components.NavigationBarComponent;
import automationexercices.pages.components.SubscriptionComponent;
import automationexercices.tests.BaseTest;
import automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@Epic("Automation Exercise ")
@Feature("Subscription")
@Owner("Aya")
public class SubscriptionTest extends BaseTest {

    // Tests
    @Test
    @Story("Subscription with valid Email")
    @Description("Verify that a user can successfully subscribe to the newsletter from Home Page")
    @Severity(SeverityLevel.NORMAL)
    public void SubscriptionFromHomePageTC(){
        new NavigationBarComponent(driver)
                .clickOnHomeButton();
        new SubscriptionComponent(driver)
                .fillSubscriptionForm(testData.getJsonData("email"))
                .clickSubmitSubscriptionBtn()
                .verifySuccessMessage(testData.getJsonData("message"));
    }

    // Tests
    @Test
    @Story("Subscription with valid Email")
    @Description("Verify that a user can successfully subscribe to the newsletter from Cart Page")
    @Severity(SeverityLevel.NORMAL)
    public void SubscriptionFromCartPageTC(){
        new NavigationBarComponent(driver)
                .clickOnCartButton();
        new SubscriptionComponent(driver)
                .fillSubscriptionForm(testData.getJsonData("email"))
                .clickSubmitSubscriptionBtn()
                .verifySuccessMessage(testData.getJsonData("message"));
    }

    // Configurations
    @BeforeClass
    public void setUp(){
        testData = new JsonReader("subscription-data");
        driver = new GUIDriver();
        new NavigationBarComponent(driver).navigate();
    }

    @AfterClass
    public void tearDown() {
        driver.quitDriver();
    }
}
