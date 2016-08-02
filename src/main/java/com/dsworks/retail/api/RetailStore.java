package com.dsworks.retail.api;


import com.dsworks.retail.model.Product;

public interface RetailStore {

    void saveProduct(Product product) throws Exception;

    Product getProductById(int id) throws Exception;

    void updateProduct (Product product) throws Exception;
}
