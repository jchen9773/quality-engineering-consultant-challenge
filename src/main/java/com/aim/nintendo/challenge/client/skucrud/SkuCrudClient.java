package com.aim.nintendo.challenge.client.skucrud;

import static com.aim.nintendo.challenge.util.enums.SkuCrudURI.SkuCrudEnum.CREATE;
import static com.aim.nintendo.challenge.util.enums.SkuCrudURI.SkuCrudEnum.DELETE;
import static com.aim.nintendo.challenge.util.enums.SkuCrudURI.SkuCrudEnum.READ;
import static com.aim.nintendo.challenge.util.enums.SkuCrudURI.SkuCrudEnum.READ_ALL;
import static com.aim.nintendo.challenge.util.enums.SkuCrudURI.SkuCrudEnum.UPDATE;
import static com.aim.nintendo.challenge.util.enums.SkuCrudURI.SkuCrudEnum.createURI;

import com.aim.nintendo.challenge.client.BaseClient;
import com.aim.nintendo.challenge.domain.requests.CreateSkuRequest;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SkuCrudClient extends BaseClient {

  public Response createSku(CreateSkuRequest createSkuRequest) {
    Response response = post(createSkuRequest, createURI(CREATE));
    log.debug(response.asString());
    return response;
  }

  public Response readSku(String id) {
    Response response = get(createURI(READ), id);
    log.debug(response.asString());
    return response;
  }

	public Response readAllSku() {
		Response response = get(createURI(READ_ALL));
		log.debug(response.asString());
		return response;
	}

  public Response updateSku(CreateSkuRequest createSkuRequest) {
    Response response = post(createSkuRequest, createURI(UPDATE));
    log.debug(response.asString());
    return response;
  }

  public Response deleteSku(String id) {
    Response response = delete(createURI(DELETE), id);
    log.debug(response.asString());
    return response;
  }
}
