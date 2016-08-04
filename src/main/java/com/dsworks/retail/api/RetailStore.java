package com.dsworks.retail.api;


public interface RetailStore<T extends RetailMarker> {

    /**
     * Creates a new product if one does not exists.
     * @param t
     * @throws Exception
     */
    void saveProduct(T t) throws Exception;

    /**
     * Finds a product with its ID.
     *
     * @param id
     * @return T
     * @throws Exception
     */
    T getProductById(int id) throws Exception;

    /**
     * Finds product by ID and updates Product's price if one exists.
     *
     * @param t
     * @return boolean true if product existing product is updated, else false
     * @throws Exception
     */
    boolean updateProductPriceById(T t) throws Exception;
}
