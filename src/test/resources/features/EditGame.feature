Feature: Edit Game
  In order to edit a created game
  As an admin
  I want to edit a created game

  Scenario: Edit game reward as an admin
    Given I login as "admin" with password "password"
    When I create a game with the name "game1"
    And I set the reward value 100 of game with the id 1
    Then The response code is 200

  Scenario: Edit game reward while unauthenticated
    And I set the reward value 100 of game with the id 1
    Then The response code is 401

  Scenario: Stop a current game as admin
    Given I login as "admin" with password "password"
    When I create a game with the name "game1"
    And I stop a game with the id 1
    Then The response code is 200

  Scenario: Stop a current game while unauthenticated
    And I stop a game with the id 1
    Then The response code is 401