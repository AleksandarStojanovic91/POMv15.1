Feature: Flights

  @Flights
  Scenario: Book a multiple destination flight

    Given I am on the booking flights page
    And I select multiple destination option
    And I enter destinations