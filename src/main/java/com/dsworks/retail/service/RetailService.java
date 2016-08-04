package com.dsworks.retail.service;


import com.dsworks.retail.api.RetailStore;
import com.dsworks.retail.beans.ProductInfo;
import com.dsworks.retail.config.AppConfiguration;
import com.dsworks.retail.exception.RetailException;
import com.dsworks.retail.model.Product;
import com.dsworks.retail.util.AppCodeConstants;
import com.dsworks.retail.util.ApplicationUtil;
import com.google.inject.Inject;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

public class RetailService {

    private static Logger LOG = LoggerFactory.getLogger(RetailService.class);

    private final RetailStore<Product> dataStore;

    private final Client client;

    private final AppConfiguration configuration;

    private final String numberRegex = "\\d+";

    @Inject
    public RetailService(final RetailStore<Product> store, final Client client, final AppConfiguration configuration) {
        this.dataStore = store;
        this.client = client;
        this.configuration = configuration;
    }

    /**
     * @param id
     * @return {@link ProductInfo}
     * @throws RetailException
     */
    public ProductInfo getProductInfo(final String id) throws RetailException {
        ProductInfo response = null;
        if (StringUtils.isNotBlank(id) && !id.matches(numberRegex)) {
            throw new RetailException(Response.Status.BAD_REQUEST, AppCodeConstants.INPUT_VALIDATION_FAILED.getCode()
                    , AppCodeConstants.INPUT_VALIDATION_FAILED.getMessage());
        }
        try {
            Product product = dataStore.getProductById(Integer.parseInt(id));
            response = ApplicationUtil.convertToProductInfoResponse(product);
            if (response != null)
                response.setName(getNameByID(id));
        } catch (Exception ex) {
            LOG.error("Error while retrieving Product Details with Message - {}", ex.getMessage());
            throw new RetailException(Response.Status.SERVICE_UNAVAILABLE, AppCodeConstants.DATABASE_ERROR.getCode(),
                                      AppCodeConstants.DATABASE_ERROR.getMessage());
        }
        if (response == null) {
            throw new RetailException(Response.Status.NOT_FOUND, AppCodeConstants.RESOURCE_NOT_FOUND.getCode(),
                                      AppCodeConstants.RESOURCE_NOT_FOUND.getMessage());
        }
        return response;
    }

    /**
     * @param {@link ProductInfo}
     */
    public void createProduct(final ProductInfo productInfo) throws RetailException {
        validateID(productInfo.getId());
        try {
            dataStore.saveProduct(ApplicationUtil.convertToProduct(productInfo));
        } catch (Exception ex) {
            LOG.error("Error while creating Product with Message - {}", ex.getMessage());
            throw new RetailException(Response.Status.SERVICE_UNAVAILABLE, AppCodeConstants.DATABASE_ERROR.getCode(),
                                      AppCodeConstants.DATABASE_ERROR.getMessage());
        }
    }

    /**
     * @param {@link ProductInfo}
     * @throws RetailException
     */
    public void updatePriceById(final ProductInfo productInfo) throws RetailException {
        validateID(productInfo.getId());
        boolean updateStatus = false;
        try {
            updateStatus = dataStore.updateProductPriceById(ApplicationUtil.convertToProduct(productInfo));
        } catch (Exception ex) {
            LOG.error("Error while updating Product Price with Message - {}", ex.getMessage());
            throw new RetailException(Response.Status.SERVICE_UNAVAILABLE, AppCodeConstants.DATABASE_ERROR.getCode(),
                                      AppCodeConstants.DATABASE_ERROR.getMessage());
        }
        if (!updateStatus) {
            LOG.error("Price Update Unsuccessful for Product {}", productInfo.getId());
            throw new RetailException(Response.Status.NOT_FOUND, AppCodeConstants.RESOURCE_NOT_FOUND.getCode(),
                                      AppCodeConstants.RESOURCE_NOT_FOUND.getMessage());
        }
    }

    /**
     * @param id
     * @throws RetailException
     */
    private void validateID(final String id) throws RetailException {
        if (StringUtils.isBlank(id)) {
            throw new RetailException(Response.Status.BAD_REQUEST, AppCodeConstants.INPUT_VALIDATION_FAILED.getCode()
                    , AppCodeConstants.INPUT_VALIDATION_FAILED.getMessage());
        }
    }

    /**
     * @param id
     * @return String
     */
    private String getNameByID(final String id) {
        Response response = client.target(configuration.getNameServiceURL()).path(id).request().get();

        if (response.getStatus() == 200) {
            return response.readEntity(String.class);
        }
        return RandomStringUtils.randomAlphabetic(8) + " Smart TV";
    }
}
