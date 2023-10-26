@Login
Feature: User can verify the event flows

    @BG_guest_verification_01 @CF
    Scenario: User can create the guest and verify
        Given User login as "GroomCredentials"
        And User verify the successful login
        And User click on the "Guests" icon
        When User click on Plus icon
        And User click on "New Guest"
        And User is at "New Guest" screen
        And User enter the Name as "abc 123"
        And User enter the Phone as "2313131"
        And User enter the Email as "abc@gmail.com"
        And User click on "rsvp" group on "yes" radio button
        And User click on "companions" group on "Adult" radio button
        And User click on "guest" group on "Family" radio button
        Then User save the guest


#    com.bridesandgrooms.event:id/guestgroup
#    com.bridesandgrooms.event:id/companionsgroup



#    //android.widget.FrameLayout[@content-desc='Guests']

