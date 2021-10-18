package com.aim.nintendo.challenge.domain.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseMetaData {

  @JsonProperty("RequestId")
  String requestId;

  @JsonProperty("HTTPStatusCode")
  int hTTPStatusCode;

  @JsonProperty("HTTPHeaders")
  HTTPHeaders httpHeaders;

  @JsonProperty("RetryAttempts")
  int retryAttempts;
}
