package com.dsworks.retail.service;


import com.dsworks.retail.beans.ProductInfoResponse;
import com.dsworks.retail.exception.RetailException;
import com.dsworks.retail.model.Product;
import com.dsworks.retail.store.RetailStore;
import com.dsworks.retail.util.AppCodeConstants;
import com.dsworks.retail.util.ApplicationUtil;
import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

public class RetailService {

    private static Logger LOG = LoggerFactory.getLogger(RetailService.class);

    private final RetailStore dataStore;

    @Inject
    public RetailService(final RetailStore store) {
        this.dataStore = store;
    }

    public ProductInfoResponse getProductInfo(final String id) throws RetailException {
        ProductInfoResponse response = null;
        if(StringUtils.isBlank(id)){
            throw new RetailException(Response.Status.NOT_FOUND, AppCodeConstants.RESOURCE_NOT_FOUND.getCode(),
                    AppCodeConstants.RESOURCE_NOT_FOUND.getMessage());
        }
        try {
            Product product = dataStore.getProductById(id);
            response = ApplicationUtil.convertToProductResponse(product);
        } catch (Exception ex) {
            LOG.error("Error while retrieving Product Details with Message - {}", ex.getMessage());
            throw new RetailException(Response.Status.NOT_FOUND, AppCodeConstants.DATABASE_ERROR.getCode(),
                    AppCodeConstants.DATABASE_ERROR.getMessage());
        }
        return response;
    }

}
