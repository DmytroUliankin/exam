package exam;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Exam {

    private WebDriver browser;

    @FindBy(xpath = "//*[@class=\"hekEU\"]")
    private WebElement search;

    public Exam(WebDriver driver) {
        this.browser = driver;
        PageFactory.initElements(driver, this);
    }

    public void sortProductsDescAndCheckPriceSort() {
        search.sendKeys("computer");
        search.sendKeys(Keys.ENTER);

        String currentSearchUrl = browser.getCurrentUrl();
        browser.get(currentSearchUrl + "&sort=-price");
    }

}
