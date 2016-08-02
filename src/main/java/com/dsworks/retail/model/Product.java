package com.dsworks.retail.model;


public class Product {

    private String id;
    private String name;
    private String priceValue;
    private String currency;

    public String getId() {
        return id;
    }

    public Product setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getPriceValue() {
        return priceValue;
    }

    public Product setPriceValue(String priceValue) {
        this.priceValue = priceValue;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public Product setCurrency(String currency) {
        this.currency = currency;
        return this;
    }
}
