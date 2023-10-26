package com.sibme.StepDefinitions;
//import cucumber.api.java.en.Given;
//import com.sibme.pages.LoginPage;

import com.sibme.pages.EventPage;
import io.cucumber.java.en.Then;

public class EventSteps {

    EventPage eventPage = new EventPage();

    @Then("User verify the event name as {string}")
    public void userVerifyTheEventNameAs(String eventName) {
        eventPage.verifyEventName(eventName);
    }

}
