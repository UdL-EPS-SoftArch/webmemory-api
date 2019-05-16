Feature: Create Game
  In order to create a new game and have it be listed on the application
  As an admin
  I want to create a new game

  Scenario: Create new game as admin
    Given I login as "admin" with password "password"
    When I create a game with the id "game1"
    Then The response code is 201
    And A game with the id and name "game1" has been created

  Scenario: Create new game with already existing name
    Given I login as "admin" with password "password"
    When I create a game with the id "game1"
    And I create a game with the id "game1"
    Then The response code is 409

  #Scenario: Create new game with buy in value below zero
  #  Given I login as "admin" with password "password"
  #  And I create a game with the name "game1"
  #  When I create a new game with the name "game1" and set the buy in value 1 below zero
  #  Then The response code is 409