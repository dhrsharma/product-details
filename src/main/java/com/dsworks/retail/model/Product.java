package com.dsworks.retail.model;


public class Product {

    private int id;
    private String name;
    private double priceValue;
    private String currency;

    public int getId()
    {
        return id;
    }

    public Product setId(int id)
    {
        this.id = id;
        return this;
    }

    public String getName()
    {
        return name;
    }

    public Product setName(String name)
    {
        this.name = name;
        return this;
    }

    public double getPriceValue()
    {
        return priceValue;
    }

    public Product setPriceValue(double priceValue)
    {
        this.priceValue = priceValue;
        return this;
    }

    public String getCurrency()
    {
        return currency;
    }

    public Product setCurrency(String currency)
    {
        this.currency = currency;
        return this;
    }
}
