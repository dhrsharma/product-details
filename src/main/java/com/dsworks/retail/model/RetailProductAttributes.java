package com.dsworks.retail.model;


public enum RetailProductAttributes {
    REATIL_KEYSPACE("retail"), PRODUCT_RETAIL("product_retail"), ID("id"), NAME("name"), PRICE("price"), CURRENCY
            ("currency");

    private final String value;

    RetailProductAttributes(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
