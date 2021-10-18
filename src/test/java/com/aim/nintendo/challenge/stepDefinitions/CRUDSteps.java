package com.aim.nintendo.challenge.stepDefinitions;

import com.aim.nintendo.challenge.client.skucrud.SkuCrudClient;
import com.aim.nintendo.challenge.domain.requests.CreateSkuRequest;
import com.aim.nintendo.challenge.domain.responses.CreateSkuResponse;
import com.aim.nintendo.challenge.domain.responses.ErrorResponse;
import com.aim.nintendo.challenge.domain.responses.ReadSkuResponse;
import com.aim.nintendo.challenge.util.AssertionUtility;
import com.aim.nintendo.challenge.util.MapperUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.List;

public class CRUDSteps {

  private final SkuCrudClient skuCrudClient = new SkuCrudClient();
  private final MapperUtility mapperUtility = new MapperUtility();

  private Response actualResponse;
  private CreateSkuRequest createSkuRequest;
  private CreateSkuResponse createSkuResponse;
  private String id;

  @Given("a sku {string}, description {string}, and price {string}")
  public void a_sku_description_and_price(String sku, String description, String price) {
    this.createSkuRequest =
        CreateSkuRequest.builder().sku(sku).description(description).price(price).build();
  }

  @Given("a random sku is created")
  public void a_random_sku_is_created() throws JsonProcessingException {
    this.createSkuRequest = CreateSkuRequest.builder().random().build();
    Response response = skuCrudClient.createSku(this.createSkuRequest);
    AssertionUtility.validateSuccess(response.getStatusCode());
    this.createSkuResponse = mapperUtility.mapCreateSkuResponse(response.getBody().asString());
		this.id = this.createSkuRequest.getSku();
  }

  @Given("an id param {}")
  public void an_id_param(String id) {
    this.id = id;
  }

  @When("sending a create POST request")
  public void sending_a_create_POST_request() {
    this.actualResponse = skuCrudClient.createSku(this.createSkuRequest);
  }

	@When("sending an update POST request")
	public void sending_an_update_POST_request() {
		this.actualResponse = skuCrudClient.updateSku(this.createSkuRequest);
	}

  @When("sending a read all GET request")
  public void sending_a_read_all_GET_request() {
    this.actualResponse = skuCrudClient.readAllSku();
  }

  @When("sending a read by id GET request")
  public void sending_a_read_by_id_GET_request() {
    this.actualResponse = skuCrudClient.readSku(this.id);
  }

  @When("sending a delete request")
  public void sending_a_delete_request() {
    this.actualResponse = skuCrudClient.deleteSku(this.id);
  }

  @When("a description {string} and price {string}")
  public void a_description_and_price(String description, String price) {
    this.createSkuRequest =
        CreateSkuRequest.builder()
            .sku(this.createSkuRequest.getSku())
            .description(description)
            .price(price)
            .build();
  }

  @Then("the status should be a success")
  public void the_status_should_be_a_success() {
    AssertionUtility.validateSuccess(this.actualResponse.getStatusCode());
  }

  @Then("the status code should match {int}")
  public void the_status_code_should_match(int status) {
    AssertionUtility.validateStatusCode(this.actualResponse.getStatusCode(), status);
  }

  @And("the create response body should valid and match data")
  public void the_create_response_body_should_valid_and_match_data()
      throws JsonProcessingException {
    this.createSkuResponse =
        mapperUtility.mapCreateSkuResponse(this.actualResponse.getBody().asString());
    AssertionUtility.validateCreateResponse(this.createSkuRequest, this.createSkuResponse);
  }

  @And("the read all response body should be valid and contain created sku")
  public void the_read_all_response_body_should_be_valid_and_contain_created_sku()
      throws JsonProcessingException {
    List<CreateSkuResponse> createSkuResponseList =
        mapperUtility.mapToList(actualResponse.getBody().asString(), CreateSkuResponse.class);
    AssertionUtility.containsSku(createSkuResponseList, this.createSkuResponse);
  }

  @And("the response body should match {string}")
  public void the_response_body_should_match(String response) throws JsonProcessingException {
    ErrorResponse errorResponse =
        mapperUtility.mapErrorResponse(this.actualResponse.getBody().asString());
    AssertionUtility.validateResponseBody(errorResponse.getMessage(), response);
  }

  @And("the read response body should have no sku")
  public void the_read_response_body_should_have_no_sku() throws JsonProcessingException {
    ReadSkuResponse readSkuResponse =
        mapperUtility.mapReadSkuResponse(this.actualResponse.getBody().asString());
    AssertionUtility.validateNull(readSkuResponse.getCreateSkuResponse());
  }

  @And("validate persistence with GET")
  public void validate_persistence_with_GET() throws JsonProcessingException {
    Response response = this.skuCrudClient.readSku(this.createSkuRequest.getSku());
    AssertionUtility.validateSuccess(response.getStatusCode());
    ReadSkuResponse readSkuResponse =
        mapperUtility.mapReadSkuResponse(response.getBody().asString());
    AssertionUtility.compareReadResponse(readSkuResponse, this.createSkuResponse);
  }

  @And("validate delete persistence with GET")
  public void validate_delete_persistence_with_GET() throws JsonProcessingException {
    Response response = this.skuCrudClient.readSku(this.createSkuRequest.getSku());
    AssertionUtility.validateSuccess(response.getStatusCode());
    ReadSkuResponse readSkuResponse =
        mapperUtility.mapReadSkuResponse(response.getBody().asString());
    AssertionUtility.validateNull(readSkuResponse.getCreateSkuResponse());
  }

  @After("@create")
  public void cleanUpSku(@SuppressWarnings("unused") Scenario scenario) {
    Response response = skuCrudClient.deleteSku(this.createSkuRequest.getSku());
    AssertionUtility.validateSuccess(response.getStatusCode());
  }
}
