Feature: Flights scenarios

  @Flightsxxxxxxxxxxxxxx
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

  @Flights
  Scenario Outline: Book a return flight flight

    * I read test data from "Booking" "Flights" by id "<TC_ID>"
    * I am on the booking flights page
    And Ienter destination return flight
    And Check date for return flight
    * I click search flights button
    Then I verify results

    Examples:
      | TC_ID  |
      | FL_001 |
