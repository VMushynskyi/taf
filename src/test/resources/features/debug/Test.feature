Feature: Test

  Scenario: Test get method
    When the user get list of all '/users'
    Then the user verifies the appropriate list of users
      | name    | job      |
      | Tom     | Burglar  |
      | John    | SalesMan |
      | Douglas | Driver   |
      | Tim     | Engineer |
      | Red     | Agent    |

  Scenario: Test post method
    Given the user get list of all '/users'
    When the user posts desired user
      | name  | job    |
      | Tom   | barman |
    Then the user verifies that previous user is posted

  Scenario: Test put method
    When the user updates name with 'Ivor' and job 'Pilot' for user with '2' id
    Then the user verifies that previous user with '2' was updated

  Scenario: Test delete method
    When the user deletes desired user from list by '1' id
    Then the user verifies that appropriate user with '1' id