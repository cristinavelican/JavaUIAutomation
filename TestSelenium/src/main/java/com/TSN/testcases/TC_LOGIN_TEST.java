package com.TSN.testcases;

import com.TSN.datasource.DataToProvide;
import com.TCR.pages.LoginPage;
import org.testng.annotations.Test;

public class TC_LOGIN_TEST extends DataToProvide {

    @Test(dataProvider = "data_login")
    public void loginWithCorrectCredentials(String email, String password) {
        new LoginPage(driver)
                .goToBaseUrl(baseUrl)
                .writeEmail(email)
                .writePassword(password)
                .clickLoginButton()
                .navigateToDemoPage()
                .assertCorrectPage("https://mysbapp-359ce.firebaseapp.com/demo");

    }
}
