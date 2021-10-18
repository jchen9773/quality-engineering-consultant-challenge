package com.aim.nintendo.challenge.util.enums;

public class SkuCrudURI {
  private static final String SKU_CRUD_BASE = "/dev/skus";

  public enum SkuCrudEnum {
    CREATE(""),
    READ("/{id}"),
		READ_ALL(""),
    UPDATE(""),
    DELETE("/{id}");

    final String value;

    SkuCrudEnum(String value) {
      this.value = value;
    }

    public static String createURI(SkuCrudEnum skuCrudEnum) {
      //noinspection StringBufferReplaceableByString
      return new StringBuilder().append(SKU_CRUD_BASE).append(skuCrudEnum.value).toString();
    }
  }
}
