package com.dsworks.retail.beans;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Price {

    @JsonProperty
    private String value;

    @JsonProperty("currency_code")
    private String currencyCode;

    public String getValue() {
        return value;
    }

    public Price setValue(String value) {
        this.value = value;
        return this;
    }

    @JsonProperty("currency_code")
    public String getCurrencyCode() {
        return currencyCode;
    }

    public Price setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }
}
