package com.dsworks.retail.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dhruvsharma on 7/30/16.
 */
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

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
