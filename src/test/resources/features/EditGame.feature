Feature: Edit Game
  In order to edit a created game
  As an admin
  I want to edit a created game

  Scenario: Edit game as an admin
    Given I login as "admin" with password "password"
    When I set the buy-in value 1 of game
    Then The response code is 200

  Scenario: Edit game as an admin with currency less than zero
    Given I login as "admin" with password "password"
    And I set the buy-in value 1 of game
    When I set the buy-in value 1 of game "game1" to less than zero
    Then The response code is 409