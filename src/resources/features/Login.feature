@Login
Feature: User Login

    @BG_Login_01 @CF
    Scenario: User login with into mobile app using valid credentials
        Given User login as "GroomCredentials"
        Then User verify the successful login
