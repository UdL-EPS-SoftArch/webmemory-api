Feature: Delete Player
  In order to delete a player account
  As an admin
  I want to delete a player account

  Scenario: Delete a player as admin
    Given I login as "admin" with password "password"
    And I register a player with username "player1", email "player1@webmemory.org" and password "password1"
    When I delete a player with username "player1"
    Then The response code is 204
    And The player with the username "player1" has been deleted

  Scenario: Delete a player without without authenticating
    Given I'm not logged in
    When I delete a player with username "player1"
    Then The response code is 401

  Scenario: Delete a player that doesn't exist as an admin
    Given I login as "admin" with password "password"
    When I delete a player with username "player1"
    Then The response code is 404