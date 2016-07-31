package com.dsworks.retail.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dhruvsharma on 7/30/16.
 */
public class Price {
    @JsonProperty
    private String value;
    @JsonProperty("currency_code")
    private String currencyCode;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
