Feature: Flights

  @Flights
  Scenario Outline: Book a multiple destination flight

    * I read test data from "Booking" "Flights" by id "<TC_ID>"
    * I am on the booking flights page
    * I select multiple destination option
    * I enter destinations
    * I click search flights button
    #    * I verify results

    Examples:
      | TC_ID  |
      | FL_001 |
      | FL_002 |