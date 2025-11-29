package automationexercises.tests.ui;

import automationexercises.drivers.GUIDriver;
import automationexercises.pages.components.NavigationBarComponent;
import automationexercises.tests.BaseTest;
import automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@Epic("Automation Exercise ")
@Feature("UI Contact Us Form")
@Owner("Aya")
public class ContactUsTest extends BaseTest {

    // Tests
    @Test
    @Story("Valid Contact form submission")
    @Description("Verify that a user can successfully submit the contact form with a file attachment")
    @Severity(SeverityLevel.NORMAL)
    public void ContactUsFromWithUploadingFileTC(){
        new NavigationBarComponent(driver)
                .clickOnContactUsButton()
                .fillContactUsForm(testData.getJsonData("name"),
                        testData.getJsonData("email"),
                        testData.getJsonData("subject"),
                        testData.getJsonData("message"))
                .uploadFileInContactUsPage(testData.getJsonData("filePath"))
                .clickSubmitBtn()
                .verifySuccessMessage();

    }
    @Test
    @Story("Valid Contact form submission")
    @Description("Verify that a user can successfully submit the contact form without a file attachment")
    @Severity(SeverityLevel.NORMAL)
    public void ContactUsFromWithoutUploadingFileTC(){
        new NavigationBarComponent(driver)
                .clickOnContactUsButton()
                .fillContactUsForm(testData.getJsonData("name"),
                        testData.getJsonData("email"),
                        testData.getJsonData("subject"),
                        testData.getJsonData("message"))
                .clickSubmitBtn()
                .verifySuccessMessage();
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
