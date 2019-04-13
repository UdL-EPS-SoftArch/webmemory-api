Feature: Stop Game
  In order to stop a current game
  As an admin
  I want to stop a current game

  Scenario: Stop a current game as admin
    Given I login as "admin" with password "password"
    When I stop a game with the name "game1"
    Then The response code is 200

  Scenario: Stop a current game with already existing name
    Given I login as "admin" with password "password"
    And I stop a game with the name "game1"
    When I stop a game with the name "game1" that has already been stopped
    Then The response code is 410
    