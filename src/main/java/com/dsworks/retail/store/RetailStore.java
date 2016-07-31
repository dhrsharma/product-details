package com.dsworks.retail.store;


import com.dsworks.retail.model.Product;

public interface RetailStore {

    void saveProduct(Product product) throws Exception;

    Product getProductById(String id) throws Exception;

    void updateProduct (Product product) throws Exception;
}
