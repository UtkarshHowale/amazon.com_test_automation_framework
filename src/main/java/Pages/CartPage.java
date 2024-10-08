package Pages;

import Base.BasePage;
import Utils.TestUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {

    public CartPage(){

        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".a-truncate-cut")
    WebElement productTitle;

    @FindBy(id = "nav-cart-count")
    WebElement cartCount;

    /**
     * Retrieves the title of the currently selected product.
     *
     * This method waits until the product title element is visible on the product detail page.
     * It then captures and returns the title text.
     *
     * @return String - The title of the product displayed on the page.
     */
    public String getProductTitle() {

        TestUtils.waitUntilVisibilityOfElement(productTitle, 10);
        test.get().info("Product title is visible on the page.");
        String title = productTitle.getText();
        test.get().info("Retrieved product title: " + title);

        return title;
    }


    /**
     * Retrieves the current item count in the shopping cart.
     *
     * This method waits until the cart count element is visible, then captures and returns the count.
     *
     * @return int - The number of items currently present in the shopping cart.
     */
    public int getCartItemCount() {

        TestUtils.waitUntilVisibilityOfElement(cartCount, 10);
        test.get().info("Cart count element is visible.");
        String cartCountText = cartCount.getText();
        test.get().info("Current cart count retrieved: " + cartCountText);
        return Integer.parseInt(cartCountText);
    }
}
