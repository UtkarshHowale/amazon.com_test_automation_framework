package Pages;

import Base.BasePage;
import Utils.TestUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BasePage {

    public HomePage() {

        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchField;

    @FindBy(xpath = "//div[@class=\"left-pane-results-container\"]//div[@class=\"s-suggestion-container\"]")
    List<WebElement> searchSuggestions;

    @FindBy(css = "#nav-search-submit-button")
    WebElement searchButton;

    /**
     * Enters the given search term into the search field.
     *
     * @param searchTerm The term to be searched.
     */
    public void enterSearchTerm(String searchTerm) {

        TestUtils.waitUntilVisibilityOfElement(searchField, 10);

        test.get().info("Entering the search term: " + searchTerm);
        searchField.sendKeys(searchTerm);
        test.get().info("Search term '" + searchTerm + "' entered successfully.");
    }

    /**
     * Clicks on the search button to submit the search.
     */
    public SearchResultsPage clickSearchButton() {

        TestUtils.waitUntilElementToBeClickable(searchButton, 10);
        test.get().info("Clicking on the search button.");
        searchButton.click();
        test.get().info("Search button clicked successfully.");

        return new SearchResultsPage();
    }
}
