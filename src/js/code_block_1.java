package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(id = "user-name")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "login-button")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }
}

package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingPage {
    WebDriver driver;

    @FindBy(className = "inventory_item")
    WebElement firstItem;

    @FindBy(className = "shopping_cart_link")
    WebElement cartButton;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    @FindBy(id = "first-name")
    WebElement firstNameField;

    @FindBy(id = "last-name")
    WebElement lastNameField;

    @FindBy(id = "postal-code")
    WebElement zipCodeField;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(id = "finish")
    WebElement finishButton;

    public ShoppingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addFirstItemToCart() {
        firstItem.click();
    }

    public void goToCart() {
        cartButton.click();
    }

    public void proceedToCheckout() {
        checkoutButton.click();
    }

    public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void enterZipCode(String zipCode) {
        zipCodeField.sendKeys(zipCode);
    }

    public void clickContinue() {
        continueButton.click();
    }

    public void clickFinish() {
        finishButton.click();
    }
}

package com.example.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SauceDemoTests {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
            {"https://www.saucedemo.com/", "standard_user", "secret_sauce"}
        };
    }

    @Test(dataProvider = "loginData")
    public void testLoginFunctionality(String url, String username, String password) {
        WebDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        driver.get(url);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @Test(dependsOnMethods = {"testLoginFunctionality"})
    public void testShoppingFunctionality() {
        WebDriver driver = new ChromeDriver();
        ShoppingPage shoppingPage = new ShoppingPage(driver);
        shoppingPage.addFirstItemToCart();
        shoppingPage.goToCart();
        shoppingPage.proceedToCheckout();
        shoppingPage.enterFirstName("Test");
        shoppingPage.enterLastName("User");
        shoppingPage.enterZipCode("12345");
        shoppingPage.clickContinue();
        shoppingPage.clickFinish();
    }
}