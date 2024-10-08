package TestCases;

import Base.BasePage;
import Pages.HomePage;
import Pages.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTests extends BasePage {

    private HomePage homePage;
    private SearchResultsPage searchResultsPage;

    @BeforeMethod
    public void setUp(){

        intilizeConfiguration();
        homePage = new HomePage();
        searchResultsPage = new SearchResultsPage();
    }

    @AfterMethod
    public void tearDown(){

        closeConfiguration();
    }

    @Test(priority = 1)
    public void testValidProductSearch(){

        homePage.enterSearchTerm("i phone16 pro max");
        searchResultsPage = homePage.clickSearchButton();
        Assert.assertTrue(driver.getTitle().contains("i phone16 pro max"), "Search results are not displayed correctly.");
    }

    @Test(priority = 2)
    public void testInvalidProductSearch(){

        homePage.enterSearchTerm("xgthugftdsdssadsdsadsa");
        searchResultsPage = homePage.clickSearchButton();
        String actualErrorMessage = searchResultsPage.getNoResultsMessage();
        String expectedErrorMessage = "No results for";
        Assert.assertEquals(actualErrorMessage,expectedErrorMessage, "No results message is not displayed for an invalid search term.");
    }
}
