package org.amazon.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class AmazonTests {

    static WebDriver driver;

    @Test
    void searchNikon() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "libs/chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("https://amazon.com");

        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));

        driver.findElement(By.id("twotabsearchtextbox"))
                .sendKeys("Nikon");

        driver.findElement(By.id("nav-search-submit-text"))
                .submit();

        Select select = new Select(driver.findElement(By.cssSelector("#s-result-sort-select")));

        select.selectByValue("price-desc-rank");

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[3]/div[1]/div[2]/div/span/div/div/div[2]/div[1]/div/div/span/a"))
                .click();

        String productTopic = driver.findElement(By.id("productTitle"))
                .getText();

        assertTrue(productTopic.contains("Nikon D3X"));
    }
    @AfterAll
    static void afterAll() {
        driver.quit();
    }
}
