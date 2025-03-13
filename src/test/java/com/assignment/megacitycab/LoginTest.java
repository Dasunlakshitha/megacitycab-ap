package com.assignment.megacitycab;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();  // Automatically sets up ChromeDriver
        driver = new ChromeDriver();  // Opens Chrome browser
        driver.manage().window().maximize();  // Maximizes the window
    }

    @Test
    void testValidLogin() {
        driver.get("http://localhost:8080/login");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement usernameInput = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.name("email"))
            );
            usernameInput.sendKeys("customer@gmail.com");

            WebElement passwordInput = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.name("password"))
            );
            passwordInput.sendKeys("password123");

            WebElement loginButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("login-button"))
            );
            loginButton.click();

            wait.until(ExpectedConditions.urlContains("dashboard"));
            assertTrue(driver.getCurrentUrl().contains("dashboard"));
        } catch (Exception e) {
            System.out.println("Page Source: " + driver.getPageSource());
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshot, new File("screenshot.png"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw e;
        }
    }

    @Test
    void testInvalidLogin() {
        driver.get("http://localhost:8080/login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement usernameInput = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.name("email"))
            );
            usernameInput.sendKeys("invalidUser@gmail.com");

            WebElement passwordInput = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.name("password"))
            );
            passwordInput.sendKeys("wrongPassword");

            WebElement loginButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("login-button"))
            );
            loginButton.click();

            WebElement errorMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage"))
            );
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            assertEquals("Login failed", alertText);
            //  alert.accept();
        } catch (Exception e) {
            System.out.println("Page Source: " + driver.getPageSource());
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshot, new File("screenshot.png"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            throw e;
        }
    }

    @AfterEach
    void tearDown() {
        driver.quit();  // Closes the browser after test execution
    }
}
