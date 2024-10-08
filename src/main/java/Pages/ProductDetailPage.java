package Pages;

import Base.BasePage;
import Utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductDetailPage extends BasePage {

    public ProductDetailPage(){

        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='a-section a-spacing-none a-padding-none']//input[@id='add-to-cart-button']")
    WebElement addCartButton;

    @FindBy(xpath = "//input[@aria-labelledby='attach-sidesheet-view-cart-button-announce']")
    WebElement cartButton;

    /**
     * Clicks on the 'Add to Cart' button for a selected product.
     *
     * This method waits until the 'Add to Cart' button is visible and clickable,
     * then performs the click action to add the product to the shopping cart.
     */
    public void  clickAddToCartButton() {

        TestUtils.waitUntilElementToBeClickable(addCartButton, 20);
        test.get().info("'Add to Cart' button is visible and clickable.");
        addCartButton.click();
        test.get().info("Clicked on 'Add to Cart' button. Product should be added to the cart.");


    }

    /**
     * Clicks on the 'View Cart' button on the Amazon product page.
     *
     */
    public CartPage clickCartButton() {

        test.get().info("Waiting for the 'View Cart' button to be visible on the page.");

        TestUtils.waitUntilVisibilityOfElement(cartButton, 10);
        test.get().info("'View Cart' button is now visible.");
        cartButton.click();
        test.get().info("Clicked on the 'View Cart' button.");

        return new CartPage();
    }


}
