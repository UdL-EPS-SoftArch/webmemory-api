Feature: Delete Game
  In order to delete a game
  As an admin
  I want to delete a game

  Scenario: Admin tries to delete a game not started
    Given I login as "admin" with password "password"
    And I create a game with the name "game1"
    When I delete a game with id "game1"
    Then The response code is 204
    And The game with id "game1" does not exist

  Scenario: Remove a game that does not exist
    Given I login as "admin" with password "password"
    When I delete a game with id "game1"
    Then The response code is 404