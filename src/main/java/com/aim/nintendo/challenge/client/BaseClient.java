package com.aim.nintendo.challenge.client;

import static com.aim.nintendo.challenge.util.RestClientUtility.baseRequest;

import io.restassured.response.Response;

public abstract class BaseClient {

  protected Response post(String requestBody, String basePath) {
    return baseRequest().basePath(basePath).body(requestBody).when().post();
  }

  protected Response post(Object object, String basePath) {
    return baseRequest().basePath(basePath).body(object).when().post();
  }

  protected Response get(String basePath) {
    return baseRequest().basePath(basePath).when().get();
  }

  protected Response get(String basePath, String queryName, Object... queryValues) {
    return baseRequest().basePath(basePath).queryParam(queryName, queryValues).when().get();
  }

  protected Response get(String basePath, String pathParam) {
    return baseRequest().get(basePath, pathParam);
  }

  protected Response delete(String basePath, String queryName, Object... queryValues) {
    return baseRequest().basePath(basePath).queryParam(queryName, queryValues).when().delete();
  }

  protected Response delete(String basePath, String pathParam) {
    return baseRequest().delete(basePath, pathParam);
  }
}
