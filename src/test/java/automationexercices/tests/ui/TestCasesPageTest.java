package automationexercices.tests.ui;

import automationexercices.drivers.GUIDriver;
import automationexercices.pages.components.NavigationBarComponent;
import automationexercices.tests.BaseTest;
import automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@Epic("Automation Exercise ")
@Feature("UI Test Cases Page")
@Owner("Aya")
public class TestCasesPageTest extends BaseTest {

    @Test
    @Story("Test cases page access")
    @Description("Verify that a user can successfully navigate to the Test Cases page")
    @Severity(SeverityLevel.CRITICAL)
    public void TestCasesPageTest(){
        new NavigationBarComponent(driver)
                .clickOnTestCasesButton()
                .verifyTestCasesPage();
    }


    // Configurations
    @BeforeClass
    public void setUp(){
        testData = new JsonReader("contactus-data");
        driver = new GUIDriver();
        new NavigationBarComponent(driver).navigate();
    }

    @AfterClass
    public void tearDown() {
        driver.quitDriver();
    }
}
