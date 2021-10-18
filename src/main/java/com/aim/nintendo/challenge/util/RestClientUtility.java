package com.aim.nintendo.challenge.util;

import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.specification.RequestSpecification;
import java.util.UUID;
import lombok.Getter;

public class RestClientUtility {

  private RestClientUtility() {
    throw new UnsupportedOperationException(TestConstants.UTILITY_EXCEPTION);
  }

  public static RequestSpecification baseRequest() {
    String baseURI = System.getProperty(TestConstants.SKU_CRUD_URI);
    return RestAssured.given()
        .config(objectMapperConfig())
        .baseUri(baseURI)
        .contentType(ContentType.JSON)
        // ToDo: Add request headers
        /*.headers(
        new Headers(
            new Header())*/
        .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
  }

  private static RestAssuredConfig objectMapperConfig() {
    return RestAssured.config()
        .objectMapperConfig(
            new ObjectMapperConfig()
                .jackson2ObjectMapperFactory(
                    (cls, charset) -> new MapperUtility().getObjectMapper()));
  }
}
