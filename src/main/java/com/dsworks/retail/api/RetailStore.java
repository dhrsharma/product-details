package com.dsworks.retail.api;


import com.dsworks.retail.model.Product;

public interface RetailStore {

    /**
     * Creates a new product if one does not exists.
     *
     * @param product {@link Product}
     * @throws Exception
     */
    void saveProduct(Product product) throws Exception;

    /**
     * Finds a product with its ID.
     *
     * @param id
     * @return {@link Product}
     * @throws Exception
     */
    Product getProductById(int id) throws Exception;

    /**
     * Finds product by ID and updates Product's price if one exists.
     *
     * @param {@link Product}
     * @return boolean true if product existing product is updated, else false
     * @throws Exception
     */
    boolean updateProductPriceById(Product product) throws Exception;
}
