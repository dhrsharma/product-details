package com.dsworks.retail.util;

import com.dsworks.retail.beans.Price;
import com.dsworks.retail.beans.ProductInfoResponse;
import com.dsworks.retail.model.Product;


public final class ApplicationUtil {

    public static ProductInfoResponse convertToProductResponse(final Product product) {
        if (product == null) {
            return null;
        }
        return new ProductInfoResponse().setId(product.getId()).setName(product.getName())
                .setPrice(new Price().setValue(product.getPriceValue()).setCurrencyCode(product.getCurrency()));
    }
}
