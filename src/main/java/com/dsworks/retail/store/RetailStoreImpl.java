package com.dsworks.retail.store;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.dsworks.retail.api.RetailStore;
import com.dsworks.retail.model.Product;
import com.dsworks.retail.model.RetailProductAttributes;
import com.google.inject.Inject;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.set;


public class RetailStoreImpl implements RetailStore<Product> {

    private final Session session;

    @Inject
    public RetailStoreImpl(final Session session) {
        this.session = session;
    }

    /**
     * {@inheritDoc}
     */
    public Product getProductById(final int id) throws Exception {
        Product product = null;
        Row productRow = getRowById(id);
        if (productRow != null) {
            product = new Product().setId(productRow.getInt
                    (RetailProductAttributes.ID.getValue())).setName
                    (productRow.getString(RetailProductAttributes.NAME
                                                  .getValue())).setPriceValue
                    (productRow.getDouble(RetailProductAttributes.PRICE
                                                  .getValue())).setCurrency
                    (productRow.getString(RetailProductAttributes.CURRENCY
                                                  .getValue()));
        }
        return product;
    }

    /**
     * {@inheritDoc}
     */
    public void saveProduct(final Product product) throws Exception {
        if (product != null) {
            session.execute(QueryBuilder.insertInto
                    (RetailProductAttributes.PRODUCT_RETAIL.getValue())
                                                   .ifNotExists().value
                            (RetailProductAttributes.ID.getValue(),
                             product.getId()).value
                            (RetailProductAttributes.NAME.getValue(), product
                                    .getName()).value
                            (RetailProductAttributes.PRICE.getValue(),
                             product.getPriceValue()).value
                            (RetailProductAttributes.CURRENCY.getValue(),
                             product.getCurrency()));
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean updateProductPriceById(final Product product) throws Exception {
        boolean updateStatus = false;

        if (product != null && getRowById(product.getId()) != null) {
            Statement updateCQL = QueryBuilder.update(RetailProductAttributes
                                                              .PRODUCT_RETAIL
                                                              .getValue())
                    .with(set(RetailProductAttributes.PRICE.getValue(),
                              Double.valueOf(product.getPriceValue()))).where
                            (eq(RetailProductAttributes.ID.getValue(),
                                Integer.valueOf(product.getId())));
            updateStatus = session.execute(updateCQL)
                    .wasApplied();
        }
        return updateStatus;
    }

    /**
     * @param id
     * @return {@link Row}
     */
    private Row getRowById(final int id) {
        return session.execute(QueryBuilder.select().from
                (RetailProductAttributes.PRODUCT_RETAIL
                         .getValue()).where(eq(RetailProductAttributes.ID.getValue(), id))).one();
    }
}
