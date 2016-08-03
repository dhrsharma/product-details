package com.dsworks.retail.util;

import com.dsworks.retail.beans.Price;
import com.dsworks.retail.beans.ProductInfo;
import com.dsworks.retail.model.Product;
import org.apache.commons.lang3.StringUtils;


public final class ApplicationUtil {

    public static ProductInfo convertToProductInfoResponse(final Product product)
    {
        if (product == null)
        {
            return null;
        }
        return new ProductInfo().setId(String.valueOf(product.getId()))
                .setName(product.getName())
                .setPrice(new Price().setValue(String.valueOf(product.getPriceValue())).setCurrencyCode(product.getCurrency()));
    }


    public static Product convertToProduct(final ProductInfo productInfo)
    {
        if (productInfo == null)
        {
            return null;
        }
        return new Product().setId(Integer.parseInt(productInfo.getId()))
                .setName(productInfo.getName())
                .setPriceValue(StringUtils.isNotBlank(productInfo.getPrice().getValue()) ? Double.parseDouble(productInfo.getPrice().getValue()) : 0.0)
                .setCurrency(productInfo.getPrice().getCurrencyCode());
    }
}
