package rahulshettyacademy.pageobjects;

import rahulshettyacademy.abstractCompenents.AbstractCompenent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractCompenent {

    WebDriver driver;

    public CheckoutPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="[placeholder='Select Country']" )
    private WebElement country;

    @FindBy(css=".ta-item:nth-of-type(1)" )
    private WebElement selectCountry;

    @FindBy(css=".action__submit" )
    private WebElement submit;

//   private By results = By.cssSelector(".ta-results");

    public void selectCountry(String countryName)
    {
        Actions a= new Actions(driver);
        a.sendKeys(country, countryName).build().perform();
//        waitForElementToAppear(By.cssSelector(".ta-results"));
        selectCountry.click();

    }

    public ConfirmationPage submitOrder()
    {
        submit.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }



}
