package exam;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.*;

class ExamTest {

    private static final String URL = "https://prom.ua/";

    private static WebDriver browser;

    private Exam exam;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.gecko.driver", "/Applications/Firefox.app/Contents/MacOS/geckodriver");
        browser = new FirefoxDriver();
        browser.get(URL);
    }

    @AfterEach
    void close() {
        browser.close();
    }

    @Test
    void checkProductPrice() {
        exam = PageFactory.initElements(browser, Exam.class);
        exam.sortProductsDescAndCheckPriceSort();

        double firstPrice = getPrice("1104240457");
        double secondPrice = getPrice("24027402");
        double thirdPrice = getPrice("777705653");
        double tenthPrice = getPrice("1029694940");

        assertTrue(firstPrice > secondPrice);
        assertTrue(secondPrice > thirdPrice);
        assertTrue(firstPrice > tenthPrice && secondPrice > tenthPrice && thirdPrice > tenthPrice);
    }

    private double getPrice(String id) {
        WebElement product = browser.findElement(By.xpath("//*[@data-product-id=\"" + id + "\"]"));
        String[] rowPrice = product.getText().split("\n");
        String priceValue = rowPrice[3].replace(" ", "");
        return Double.parseDouble(priceValue.substring(0, priceValue.length() - 3));
    }

}