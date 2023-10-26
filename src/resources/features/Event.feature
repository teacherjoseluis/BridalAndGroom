@Login
Feature: User can verify the event flows

    @BG_event_verification_01 @CF
    Scenario: User can verify the details of created event
        Given User login as "GroomCredentials"
        And User verify the successful login
        Then User verify the event name as "Wedding"
