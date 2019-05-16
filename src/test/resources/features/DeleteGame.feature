Feature: Delete Game
  In order to delete a game
  As an admin
  I want to delete a game

  Scenario: Admin tries to delete a finished game
    Given I login as "admin" with password "password"
    When I create a game with the id "game1"
    And I stop a game with the id "game1"
    And I delete a game with id "game1"
    And The game with id "game1" does not exist

  Scenario: Admin tries to delete a game not finished
    Given I login as "admin" with password "password"
    When I create a game with the id "game1"
    And I delete a game with id "game1"
    Then The response code is 403

  Scenario: Remove a game that does not exist
    Given I login as "admin" with password "password"
    When I delete a game with id "game1"
    Then The response code is 404
