package Pages;

import Base.BasePage;
import Utils.TestUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends BasePage {

    public SearchResultsPage(){

        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()=\"No results for \"]")
    WebElement invalidText;
    @FindBy(xpath = "//div[@data-component-type=\"s-search-result\"]//span[@class=\"a-size-medium a-color-base a-text-normal\"]")
    List<WebElement> searchResults;


    /**
     * Method to verify if the 'No results' message is displayed and capture the message.
     *
     * @return String - Returns the full error message text if displayed; otherwise, returns an empty string.
     */
    public String getNoResultsMessage() {

        test.get().info("Fetching the 'No results' message text from the page.");
        String messageText = invalidText.getText();
        test.get().info("Captured 'No results' message: " + messageText);
        return messageText;

    }

    /**
     * Method to retrieve all the search result titles from the search results.
     *
     * @return List<String> - Returns a list of product titles from the search results.
     */
    public List<String> getSearchResultsTitles() {

        // Wait until the first result is visible
        TestUtils.waitUntilVisibilityOfElement(searchResults.get(0), 10);

        test.get().info("Extracting text from each search result element.");
        List<String> resultTitles = new ArrayList<>();

        for (WebElement result : searchResults) {
            String titleText = result.getText();
            test.get().info("Found product title: " + titleText);
            resultTitles.add(titleText);
        }

        test.get().info("Total number of search results found: " + resultTitles.size());
        return resultTitles;
    }

    public ProductDetailPage clickOnFirstResult(){

        TestUtils.waitUntilVisibilityOfElement(searchResults.get(0), 10);
        test.get().info("First search result is visible. Proceeding to click on it.");
        System.out.println("First search result is: " + searchResults.get(0).getText());
        test.get().info("First search result is: " + searchResults.get(0).getText());
        searchResults.get(0).click();
        test.get().info("Clicked on the first search result to open the product details page.");

        TestUtils.switchToNewWindow(driver);
        TestUtils.scrollDownBy(1000);

        return new ProductDetailPage();
    }
}
