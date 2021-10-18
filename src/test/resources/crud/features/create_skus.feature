Feature: Create SKUs
  Test cases for creating SKUs

  @create
  Scenario Outline: create positive cases
	Given a sku <sku>, description <description>, and price <price>
	When sending a create POST request
	Then the status should be a success
	And the create response body should valid and match data
	And validate persistence with GET

	Examples:
	  | sku        | description   | price  |
	  | "berliner" | "Jelly donut" | "2.99" |

  Scenario Outline: create negative cases
	Given a sku <sku>, description <description>, and price <price>
	When sending a create POST request
	Then the status code should match <status>
	And the response body should match <response>

	Examples:
	  | sku | description | price | status | response                |
	  | ""  | ""          | ""    | 502    | "Internal server error" |
