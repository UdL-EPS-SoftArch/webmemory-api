Feature: Deposit Currency
  In order to to give currency to a player
  As an admin
  I want to deposit currency to them

  Scenario: Deposit currency as an admin
    Given I login as "admin" with password "password1"
    And I register a new player with username "player1", email "newPlayer@webmemory.org" and password "password1"
    When I deposit 10 currency into the wallet of player with username "player1"
    Then The response code is 200
    And Player with username "newPlayer" has 10 currency

  Scenario: Remove currency as an admin
    Given I login as "admin" with password "password1"
    And I register a new player with username "player1", email "newPlayer@webmemory.org" and password "password1"
    When I remove 10 currency from the wallet of player with username "player1"
    Then The response code is 200
    And Player with username "player1" has 10 currency
