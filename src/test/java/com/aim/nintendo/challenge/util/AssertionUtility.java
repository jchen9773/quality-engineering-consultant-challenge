package com.aim.nintendo.challenge.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.aim.nintendo.challenge.domain.requests.CreateSkuRequest;
import com.aim.nintendo.challenge.domain.responses.CreateSkuResponse;
import com.aim.nintendo.challenge.domain.responses.ReadSkuResponse;
import java.util.List;

public final class AssertionUtility {

  private AssertionUtility() {
    throw new UnsupportedOperationException(TestConstants.UTILITY_EXCEPTION);
  }

  public static void validateStatusCode(int actualStatus, int expectedStatus) {
    assertThat(actualStatus).isEqualTo(expectedStatus);
  }

  public static void validateSuccess(int actualStatus) {
    validateStatusCode(actualStatus, 200);
  }

  public static void validateNotNull(Object object) {
    assertThat(object).isNotNull();
  }

	public static void validateNull(Object object) {
		assertThat(object).isNull();
	}

  public static void validateCreateResponse(CreateSkuRequest request, CreateSkuResponse response) {
    assertThat(response.getSku()).isEqualTo(request.getSku());
    assertThat(response.getDescription()).isEqualTo(request.getDescription());
    assertThat(response.getPrice()).isEqualTo(request.getPrice());
    validateNotNull(response.getCreatedAt());
    validateNotNull(response.getUpdatedAt());
  }

  public static void validateResponseBody(String actualResponse, String expectedResponse) {
    assertThat(actualResponse).isEqualTo(expectedResponse);
  }

	public static void containsSku(List<CreateSkuResponse> createSkuResponseList, CreateSkuResponse expectedSku) {
		assertThat(createSkuResponseList).contains(expectedSku);
	}

  public static void compareReadResponse(
      ReadSkuResponse actualReadSkuResponse, CreateSkuResponse expectedResponse) {
    assertThat(actualReadSkuResponse.getCreateSkuResponse()).isEqualTo(expectedResponse);
  }
}
