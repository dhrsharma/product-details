package com.dsworks.retail.beans;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ProductInfo {

    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty("current_price")
    private Price price = new Price();

    public String getId() {
        return id;
    }

    public ProductInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductInfo setName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("current_price")
    public Price getPrice() {
        return price;
    }

    public ProductInfo setPrice(Price price) {
        this.price = price;
        return this;
    }
}
