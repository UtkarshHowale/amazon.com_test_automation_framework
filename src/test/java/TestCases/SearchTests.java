package TestCases;

import Base.BasePage;
import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTests extends BasePage {

    private HomePage homePage;

    @BeforeMethod
    public void setUp(){

        intilizeConfiguration();
        homePage = new HomePage();
    }

    @AfterMethod
    public void tearDown(){

        closeConfiguration();
    }

    @Test(priority = 1)
    public void testValidProductSearch(){

        homePage.enterSearchTerm("i phone16 pro max");
        homePage.clickSearchButton();
        Assert.assertTrue(driver.getTitle().contains("i phone16 pro max"), "Search results are not displayed correctly.");
    }

    @Test(priority = 2)
    public void testInvalidProductSearch(){

        homePage.enterSearchTerm("bgutrjkekerer343253");
        homePage.clickSearchButton();
        String actualErrorMessage = homePage.getNoResultsMessage();
        String expectedErrorMessage = "No results for bgutrjkekerer343253.";
        Assert.assertEquals(actualErrorMessage,expectedErrorMessage, "No results message is not displayed for an invalid search term.");
    }
}
