Feature: Delete SKUs
  Test cases for deleting SKUS

  Scenario: delete request
	Given a random sku is created
	When sending a delete request
	Then the status should be a success
	And validate delete persistence with GET

  Scenario Outline: delete negative cases
	Given an id param <id>
	When sending a delete request
	Then the status code should match <status>

	Examples:
	  | id       | status |
	  | ""       | 200    |
	  | "%%"     | 200    |
	  | " "      | 200    |
	  | !@#$%^&* | 200    |
