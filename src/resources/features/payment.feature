@Payment
Feature: User Login

    @BG_Login_01 @CF
    Scenario: User login with into mobile app using valid credentials
        Given User login as "GroomCredentials"
        And User verify the successful login
