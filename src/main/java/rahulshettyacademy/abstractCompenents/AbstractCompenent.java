package rahulshettyacademy.abstractCompenents;

import rahulshettyacademy.pageobjects.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rahulshettyacademy.pageobjects.OrdersPage;

import java.time.Duration;

public class AbstractCompenent {

    WebDriver driver;
    public AbstractCompenent(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[routerlink*='/dashboard/cart']")
    WebElement cartHeader;

    @FindBy(css = "[routerlink*='/myorders']")
    WebElement ordersHeader;

    public void waitForElementToAppear(By findBy)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
    }

    public void waitForWebElementToAppear(WebElement findBy)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
        //om dit gedeelte te versnellen omdat er nog een spinner draait op de site moet je een andere wait gebruiken
        Thread.sleep(1000);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public CartPage goToCartPage()
    {
        cartHeader.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrdersPage goToOrdersPage()
    {
        ordersHeader.click();
        OrdersPage ordersPage = new OrdersPage(driver);
        return ordersPage;
    }

}
