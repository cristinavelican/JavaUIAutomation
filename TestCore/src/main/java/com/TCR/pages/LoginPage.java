package com.TCR.pages;

import com.TCR.base.BaseMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BaseMethods {

    private By emailInput = By.id("email");
    private By passInput = By.id("password");
    private By loginButton = By.xpath("//*[@id=\"root\"]/div/main/div/form/button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage goToBaseUrl(String baseUrl) {
        driver.get(baseUrl);
        return this;
    }

    public LoginPage writeEmail(String email) {
       writeText(emailInput,email);
        return this;
    }

    public LoginPage writePassword(String password) {
        writeText(passInput,password);
        return this;
    }

    public LoginPage clickLoginButton() {
        click(loginButton);
        return this;
    }

    public DemoPage navigateToDemoPage() {
        return new DemoPage(driver);
    }
}
