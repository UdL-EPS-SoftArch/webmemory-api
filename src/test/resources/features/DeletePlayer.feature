Feature: Register Player
  In order to delete an unneeded player user
  As an admin
  I want to delete an existing player account

  Scenario: Delete existing player as an admin
    Given I login as "admin" with password "password"
    And I register player with username "newPlayer", email "email@email.com" and password "password"
    When I delete player with username "newPlayer"
    Then The response code is 204

  Scenario: Delete existing player as a player
    Given I login as "admin" with password "password"
    And I register player with username "newPlayer", email "email@email.com" and password "password"
    Given I login as "player" with password "password"
    When I delete a player with username "newPlayer"
    Then The response code is 403
    And Player with username "newPlayer" has not been deleted

  Scenario: Remove player that doesn't exist as an admin
    Given I login as "admin" with password "password"
    When I delete player with username "newPlayer"
    Then The response code is 404

  Scenario: Remove player that doesn't exist as a player
    Given I login as "player" with password "password"
    When I delete player with username "newPlayer"
    Then The response code is 403

  Scenario: Remove player while not logged in
    Given I am not logged in
    When I delete player with username "newPlayer"
    Then The response code is 401