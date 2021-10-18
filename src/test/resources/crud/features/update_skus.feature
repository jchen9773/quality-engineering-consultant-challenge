Feature: Update SKUs
  Test cases for updating SKUs

  @create
  Scenario Outline: update positive cases
	Given a random sku is created
	And a description <description> and price <price>
	When sending an update POST request
	Then the status should be a success
	And the create response body should valid and match data
	And validate persistence with GET

	Examples:
	  | description | price  |
	  | "clearance" | "1.00" |
	  | "sale"      | "5.00" |
	  | "GWP"       | "0.00" |
