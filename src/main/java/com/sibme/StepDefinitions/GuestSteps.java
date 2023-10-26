package com.sibme.StepDefinitions;

import com.sibme.pages.GuestPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class GuestSteps {

    GuestPage guestPage = new GuestPage();

    @And("User click on the {string} icon")
    public void userClickOnTheIcon(String iconName) {
        guestPage.moveToGuestPage(iconName);
    }

    @And("User click on Plus icon")
    public void userClickOnPlusIcon() {
        guestPage.clickOnPlus();
    }

    @And("User click on {string}")
    public void userClickOn(String buttonName) {
        guestPage.clickOnButton(buttonName);
    }

    @And("User is at {string} screen")
    public void userIsAtScreen(String screenName) {
        guestPage.verifyScreenTitle(screenName);
    }

    @And("User enter the Name as {string}")
    public void userEnterTheNameAs(String guestName) {
        guestPage.enterGuestName(guestName);
    }

    @And("User enter the Email as {string}")
    public void userEnterTheEmailAs(String guestEmail) {
        guestPage.enterGuestEmail(guestEmail);
    }

    @And("User enter the Phone as {string}")
    public void userEnterThePhoneAs(String guestPhoneNumber) {
        guestPage.enterGuestPhone(guestPhoneNumber);
    }

    @And("User click on {string} group on {string} radio button")
    public void userClickOnGroupOnRadioButton(String groupName, String radioButtonName) {
        guestPage.clickOnRadioButton(groupName, radioButtonName);
    }

    @Then("User save the guest")
    public void userSaveTheGuest() {
        guestPage.saveGuest();
    }
}
