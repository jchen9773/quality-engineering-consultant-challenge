package com.aim.nintendo.challenge.util;

import com.aim.nintendo.challenge.domain.requests.CreateSkuRequest;
import com.aim.nintendo.challenge.domain.responses.CreateSkuResponse;
import com.aim.nintendo.challenge.domain.responses.ErrorResponse;
import com.aim.nintendo.challenge.domain.responses.ReadSkuResponse;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class MapperUtility {

  private ObjectMapper objectMapper;

  public MapperUtility() {
    // ToDo: Add Global serializers for data types
    /*SimpleModule testModule = new SimpleModule();
    testModule.addDeserializer();*/
    var mapper = new ObjectMapper();
    mapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
    mapper.configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(), true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.setSerializationInclusion(Include.NON_NULL);
    // mapper.registerModule(testModule);
    this.objectMapper = mapper;
  }

  // Request mapping
  public CreateSkuRequest mapCreateSkuRequest(String src) throws JsonProcessingException {
    return mapValue(src, CreateSkuRequest.class);
  }

  // Response mapping
  public CreateSkuResponse mapCreateSkuResponse(String src) throws JsonProcessingException {
    return mapValue(src, CreateSkuResponse.class);
  }

  public ReadSkuResponse mapReadSkuResponse(String src) throws JsonProcessingException {
    return mapValue(src, ReadSkuResponse.class);
  }

  public ErrorResponse mapErrorResponse(String src) throws JsonProcessingException {
    return mapValue(src, ErrorResponse.class);
  }

  // General mapping
  public String mapToString(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      log.error(TestConstants.ERROR_PROCESSING + object, e);
    }
    return null;
  }

  public <T> List<T> mapToList(String src, Class<T> clazz) throws JsonProcessingException {
    var javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
    return objectMapper.readValue(src, javaType);
  }

  private <T> T mapValue(String src, Class<T> valueType) throws JsonProcessingException {
    return objectMapper.readValue(src, valueType);
  }
}
