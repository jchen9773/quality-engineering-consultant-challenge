package com.aim.nintendo.challenge.domain.requests;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateSkuRequest {

  String sku;
  String description;
  String price;

	public static class CreateSkuRequestBuilder {
		public CreateSkuRequestBuilder random() {
			this.sku = UUID.randomUUID().toString();
			this.description = UUID.randomUUID().toString();
			this.price = UUID.randomUUID().toString();

			return this;
		}
	}
}
