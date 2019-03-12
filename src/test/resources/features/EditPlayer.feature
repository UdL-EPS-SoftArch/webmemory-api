Feature: Edit Player
  In order to edit a player user
  As an admin
  I want to edit player users

    Scenario: Edit player as an admin
        Given I login as "admin" with password "password"
        And I register a new player with username "newPlayer", email "newPlayer@webmemory.org" and password "password"
        When I edit player with new username "newPlayer", new email "newPlayer_2@webmemory.org" and new password "password"
        Then The response code is 200
        And It has been edited a player with username "newPlayer2" and email "newPlayer_2@webmemory.org", the password is not returned
        And Player "newPlayer" has had its username changed to "newPlayer2" and email to "newPlayer_2@webmemory.org"

    Scenario: Edit player as user
        Given I login as "admin" with password "password"
        And I register a new player with username "newPlayer", email "newPlayer@webmemory.org" and password "password"
        Given I login as "user" with password "password"
        When I edit player with new username "newPlayer", new email "newPlayer_2@webmemory.org" and new password "password"
        Then The response code is 200
        And Player "newPlayer" has not been edited

    Scenario: Edit a player without authenticating
        Given I'm not logged in
        When I edit player with new username "newPlayer", new email "newPlayer@webmemory.org>" and new password "password"
        Then The response code is 401
        And Player "newPlayer" has not been edited

    Scenario: Edit player with invalid email
        Given I login as "admin" with password "password"
        And I register a new player with username "newPlayer", email "newPlayer@webmemory.org" and password "password"
        When I edit player with new username "newPlayer" and new email "newPlayerwebmemory.org"
        Then The response code is 400
        And The error message is "must be a well-formed email address"
        And Player "newPlayer" has not been edited

    Scenario: Edit player with invalid password
        Given I login as "admin" with password "password"
        And I register a new player with username "newPlayer", email "newPlayer@webmemory.org" and password "password"
        When I edit player with new username "newPlayer" and new password "123"
        Then The response code is 400
        And The error message is "length must be between 8 and 256"
        And Player "newPlayer" has not been edited

    Scenario: Edit player with invalid email and invalid password
        Given I login as "admin" with password "password"
        And I register a new player with username "newPlayer", email "newPlayer@webmemory.org" and password "password"
        When I edit player with username "newPlayer", new email "newPlayerwebmemory.org" and new password "1234"
        Then The response code is 400
        And The error message is "must be a well-formed email address"
        And The error message is "length must be between 8 and 256"
        And Player "newPlayer" has not been edited
        