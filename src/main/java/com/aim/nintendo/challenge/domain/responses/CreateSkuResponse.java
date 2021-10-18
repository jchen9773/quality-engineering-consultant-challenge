package com.aim.nintendo.challenge.domain.responses;

import lombok.Data;

@Data
public class CreateSkuResponse {

  String sku;
  String description;
  String price;
  String createdAt;
  String updatedAt;
}
