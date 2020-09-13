Feature: PostTest

  Scenario: Test post method
    Given the user get list of all '/users'
    When the user posts desired user
      | name  | job    |
      | Tom   | barman |
    Then the user verifies that previous user is posted