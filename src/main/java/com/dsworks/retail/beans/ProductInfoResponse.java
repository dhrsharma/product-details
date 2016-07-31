package com.dsworks.retail.beans;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ProductInfoResponse {
    //{"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}

    @JsonProperty
    private String id;
    @JsonProperty
    private String name;
    @JsonProperty("current_price")
    private Price price;

    public String getId() {
        return id;
    }

    public ProductInfoResponse setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductInfoResponse setName(String name) {
        this.name = name;
        return this;
    }

    public Price getPrice() {
        return price;
    }

    public ProductInfoResponse setPrice(Price price) {
        this.price = price;
        return this;
    }
}
