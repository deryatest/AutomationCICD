package rahulshettyacademy.pageobjects;

import rahulshettyacademy.abstractCompenents.AbstractCompenent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractCompenent {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver)
    {
//        Om de driver ook naar de parent te sturen creeer een super variable bij iedere child
        super(driver);
//        initialization
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

//    List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
    @FindBy (css=".mb-3" )
    private List<WebElement> products;

    @FindBy (css=".ng-animating" )
    private WebElement spinner;

    private By productsBy = By.cssSelector(".mb-3");
    private By addToCart = By.cssSelector(".card-body button:last-of-type");
    private By toastMessage = By.cssSelector("#toast-container");

    public List<WebElement> getProductList()
    {
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName)
    {
        WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText()
                .equals(productName)).findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String productName) throws InterruptedException {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);

    }

}
