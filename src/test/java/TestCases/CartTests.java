package TestCases;

import Base.BasePage;
import Pages.CartPage;
import Pages.HomePage;
import Pages.ProductDetailPage;
import Pages.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTests extends BasePage {

    public CartTests(){

        super();
    }

    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private ProductDetailPage productDetailPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setUp(){

        intilizeConfiguration();
        homePage = new HomePage();
        searchResultsPage = new SearchResultsPage();
        productDetailPage = new ProductDetailPage();
        cartPage = new CartPage();
    }

    @AfterMethod
    public void tearDown(){

        closeConfiguration();
    }

    @Test(priority = 1)
    public void testAddSingleProductToCart(){

        homePage.enterSearchTerm("i phone15 pro max");
        searchResultsPage = homePage.clickSearchButton();
        productDetailPage = searchResultsPage.clickOnFirstResult();
        productDetailPage.clickAddToCartButton();
        cartPage = productDetailPage.clickCartButton();
        String actualProduct = cartPage.getProductTitle();
        String expectedProduct = "Apple iPhone 15 Pro Max (256 GB) - Black Titanium";
        Assert.assertEquals(actualProduct,expectedProduct, "Product title in the cart does not match the expected product title.");

        int cartCount = cartPage.getCartItemCount();
        System.out.println(cartCount);
        Assert.assertEquals(cartCount,1, "The cart count is incorrect; expected 1 item in the cart.");

    }
}
