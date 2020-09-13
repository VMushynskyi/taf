Feature: GetTest

  Scenario: Test get method
    When the user get list of all '/users'
    Then the user verifies the appropriate list of users
        | name    | job      |
        | Tom     | Burglar  |
        | John    | SalesMan |
        | Douglas | Driver   |
        | Tim     | Engineer |
        | Red     | Agent    |