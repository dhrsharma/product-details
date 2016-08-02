package com.dsworks.retail.store;


import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.dsworks.retail.api.RetailStore;
import com.dsworks.retail.model.Product;
import com.dsworks.retail.model.RetailProductAttributes;
import com.dsworks.retail.module.ManagedCassandraConnector;
import com.google.inject.Inject;


public class RetailStoreImpl implements RetailStore {

    private final ManagedCassandraConnector connector;

    private static final String SELECT_CQL_BY_ID = "SELECT * from retail.product_retail WHERE id = ?";

    @Inject
    public RetailStoreImpl(final ManagedCassandraConnector connector){
        this.connector = connector;
    }

    public Product getProductById(final int id) throws Exception {
        Product product = null;
        ResultSet resultSet = connector.getSession().execute(SELECT_CQL_BY_ID, id);
        Row productRow = resultSet.one();
        if(productRow != null){
            product = new Product()
                    .setId(String.valueOf(productRow.getInt(RetailProductAttributes.ID.getValue())))
                    .setName(productRow.getString(RetailProductAttributes.NAME.getValue()))
                    .setPriceValue(String.valueOf(productRow.getDouble(RetailProductAttributes.PRICE.getValue())))
                    .setCurrency(productRow.getString(RetailProductAttributes.CURRENCY.getValue()));
        }
        return product;
    }

    public void saveProduct(Product product) throws Exception {

    }

    public void updateProduct(Product product) throws Exception {

    }

}
