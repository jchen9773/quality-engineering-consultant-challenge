package com.aim.nintendo.challenge.domain.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReadSkuResponse {

  @JsonProperty("Item")
  CreateSkuResponse createSkuResponse;

  @JsonProperty("ResponseMetadata")
  ResponseMetaData responseMetaData;
}
