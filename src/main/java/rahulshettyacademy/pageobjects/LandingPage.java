package rahulshettyacademy.pageobjects;

import rahulshettyacademy.abstractCompenents.AbstractCompenent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractCompenent {

    WebDriver driver;

    public LandingPage(WebDriver driver)
    {
//        Om de driver ook naar de parent te sturen creeer een super variable bij iedere child
        super(driver);
//        initialization
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

//    WebElement userEmail = driver.findElement(By.id("userEmail"));
//    Find object by using Selenium PageFactory design and above written will be not needed anymore
    @FindBy(id="userEmail")
    private WebElement userEmail;

    @FindBy(id="userPassword")
    private WebElement userPassword;

    @FindBy(id="login")
    private WebElement submit;

    @FindBy(css=".ngx-toastr.toast-error")
    private WebElement errorMessage;

    public ProductCatalogue loginApplication(String email, String password)
    {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submit.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }
    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMessage() {
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }
}
