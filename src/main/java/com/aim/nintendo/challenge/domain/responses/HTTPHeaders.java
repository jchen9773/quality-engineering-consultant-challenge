package com.aim.nintendo.challenge.domain.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HTTPHeaders {

  String server;
  String date;

  @JsonProperty("content-type")
  String contentType;

  @JsonProperty("content-length")
  String contentLength;

  String connection;

  @JsonProperty("x-amzn-requestid")
  String xAmznRequestId;

	@JsonProperty("x-amz-crc32")
	String xAmzCrc32;
}
