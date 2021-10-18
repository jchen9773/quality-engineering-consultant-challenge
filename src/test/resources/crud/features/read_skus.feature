Feature: Read SKUs
  Test cases for reading SKUS

  @create
  Scenario: read all request
	Given a random sku is created
	When sending a read all GET request
	Then the status should be a success
	And the read all response body should be valid and contain created sku

  Scenario Outline: read by id negative cases
	Given an id param <id>
	When sending a read by id GET request
	Then the status should be a success
	And the read response body should have no sku

	Examples:
	  | id         |
	  | """"       |
	  | " "        |
	  | 1234567890 |
	  | !@#$%^&*   |
