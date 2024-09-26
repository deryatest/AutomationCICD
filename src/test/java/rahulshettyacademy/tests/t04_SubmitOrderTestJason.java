package rahulshettyacademy.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.*;
import rahulshettyacademy.testcomponents.BaseTests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class t04_SubmitOrderTestJason extends BaseTests {
    String productName = "ZARA COAT 3";
    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String,String>input ) throws IOException, InterruptedException {
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("productName"));
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();

        checkoutPage.selectCountry("Netherlands");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        }

    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest()
    {
      ProductCatalogue productCatalogue = landingPage.loginApplication("durcuk@gmail.com", "YeniSifreler@#01");
      OrdersPage ordersPage =productCatalogue.goToOrdersPage();
      Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
    }

    @DataProvider
    public Object [][] getData() throws IOException {
//        de method getJsonDataToMap wordt uit BaseTest geërfd en is DataReader file overbodig geworden
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
        return new Object [][] { {data.get(0)}, {data.get(1)}};
    }
}
