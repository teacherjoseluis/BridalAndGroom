package com.sibme.StepDefinitions;
//import cucumber.api.java.en.Given;
//import com.sibme.pages.LoginPage;

import com.sibme.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LoginSteps {

    LoginPage loginPage = new LoginPage();

    @Given("User login as {string}")
    public void user_login_as(String userType) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        loginPage.loginIntoApp(userType);
    }

    @Then("User verify the successful login")
    public void userVerifyTheSuccessfulLogin() {
        loginPage.verifySuccessfulLoginMessage();
    }


}
