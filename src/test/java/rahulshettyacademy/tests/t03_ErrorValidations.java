package rahulshettyacademy.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testcomponents.BaseTests;
import rahulshettyacademy.testcomponents.Retry;

import java.io.IOException;
import java.util.List;

public class t03_ErrorValidations extends BaseTests {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws IOException, InterruptedException {
        landingPage.loginApplication("durcuk@gmail.com", "YeniSifreler@#0");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

        }
    @Test
    public void ProductErrorValidation() throws IOException, InterruptedException {
        String productname = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("durcuk@gmail.com", "YeniSifreler@#01");

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productname);
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);
    }

}
