package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createProductSuccess(ChromeDriver driver) {
        driver.get(baseUrl + "/product/list");
        WebElement createButton = driver.findElement(By.linkText("Create Product"));
        createButton.click();
        String productName = "Sampo Cap Bango";
        int productQuantity = 100;

        WebElement nameInput = driver.findElement(By.id("nameInput"));
        nameInput.clear();
        nameInput.sendKeys(productName);

        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(productQuantity));

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        assertEquals(baseUrl + "/product/list", driver.getCurrentUrl());

        List<WebElement> productCells = driver.findElements(By.tagName("td"));
        boolean isNameFound = false;
        boolean isQuantityFound = false;

        for (WebElement cell : productCells) {
            if (cell.getText().equals(productName)) {
                isNameFound = true;
            }
            if (cell.getText().equals(String.valueOf(productQuantity))) {
                isQuantityFound = true;
            }
        }

        assertTrue(isNameFound, "Product name should be visible in the list");
        assertTrue(isQuantityFound, "Product quantity should be visible in the list");

    }
}
