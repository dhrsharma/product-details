package com.dsworks.retail;

import com.dsworks.retail.beans.ProductInfo;
import com.dsworks.retail.beans.ResponseStatus;
import com.dsworks.retail.config.AppConfiguration;
import com.dsworks.retail.model.RetailProductAttributes;
import com.dsworks.retail.util.AppCodeConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.cassandraunit.CassandraCQLUnit;
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class ProductDetailAppRealIntegrationTest extends Assert {

    private static final String RETAIL_URL = "http://localhost:9000/myretail";

    private static final String RETAIL_PATH = "products";

    private static final String SERVICE_VERSION = "v1";

    private static final String TEST_PRODUCT_ID = "12312";

    private final ObjectMapper objectMapper = Jackson.newObjectMapper();

    @ClassRule
    public static final CassandraCQLUnit CQL_UNIT = new CassandraCQLUnit(new ClassPathCQLDataSet("retail.cql",
                                                                                                 RetailProductAttributes
                                                                                                         .REATIL_KEYSPACE
                                                                                                         .getValue()));
    @ClassRule
    public static final DropwizardAppRule<AppConfiguration> RULE =
            new DropwizardAppRule<>(ProductDetailApplication.class, ResourceHelpers.resourceFilePath("test.yml"));

    private static final Client CLIENT = ClientBuilder.newClient();

    @Test
    public void testRetailServiceWithGET() throws Exception {
        Response response = responseForGET(TEST_PRODUCT_ID);
        ProductInfo productInfo = response.readEntity(ProductInfo.class);
        assertEquals(response.getStatus(), 200);
        assertNotNull(productInfo);
        assertEquals("MacBook Pro 12312", productInfo.getName());
    }

    @Test
    public void testGETNameService() throws Exception {
        Response response = CLIENT.target(RETAIL_URL).path(RETAIL_PATH).path("v1").path(TEST_PRODUCT_ID).request()
                .get();
        String productName = response.readEntity(String.class);
        assertEquals(response.getStatus(), 200);
        assertNotNull(productName);
        assertEquals("MacBook Pro 12312", productName);
    }

    @Test
    public void testGETRetailServiceWithNonexistentID() throws Exception {
        Response response = responseForGET("45");
        assertResponse(Response.Status.NOT_FOUND, AppCodeConstants.RESOURCE_NOT_FOUND, response);
    }

    @Test
    public void testRetailServiceWithPOST() throws Exception {
        final String postJson = "{\"id\":76575,\"name\":\"Samsung Galaxy S6\"," +
                "\"current_price\":{\"value\": 599.89,\"currency_code\":\"USD\"}}";
        ProductInfo productInfo = objectMapper.readValue(postJson, ProductInfo.class);
        Response response = CLIENT.target(RETAIL_URL).path(RETAIL_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(productInfo, MediaType.APPLICATION_JSON_TYPE));
        assertResponse(Response.Status.CREATED, AppCodeConstants.SUCCESS, response);
    }

    @Test
    public void testRetailServiceWithPUT() throws Exception {
        final String postJson = "{\"id\":12312, \"current_price\":{\"value\": 876.75}}";
        ProductInfo productInfo = objectMapper.readValue(postJson, ProductInfo.class);
        Response response = CLIENT.target(RETAIL_URL).path(RETAIL_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.entity(productInfo, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(201, response.getStatus());
        Response getResponse = responseForGET(TEST_PRODUCT_ID);
        ProductInfo updatedProductInfo = getResponse.readEntity(ProductInfo.class);
        assertEquals(200, getResponse.getStatus());
        assertNotNull(updatedProductInfo);
        assertEquals("MacBook Pro 12312", updatedProductInfo.getName());
        assertEquals("876.75", updatedProductInfo.getPrice().getValue());
    }

    @Test
    public void testPUTRetailServiceWithNoIDInBackend() throws Exception {
        final String postJson = "{\"id\":10, \"current_price\":{\"value\": 876.75}}";
        ProductInfo productInfo = objectMapper.readValue(postJson, ProductInfo.class);
        Response response = CLIENT.target(RETAIL_URL).path(RETAIL_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.entity(productInfo, MediaType.APPLICATION_JSON_TYPE));
        assertResponse(Response.Status.NOT_FOUND, AppCodeConstants.RESOURCE_NOT_FOUND, response);
    }

    @Test
    public void testInvalidPathParamForGET() throws Exception {
        Response response = responseForGET("e22r3*");
        assertResponse(Response.Status.BAD_REQUEST, AppCodeConstants.INPUT_VALIDATION_FAILED, response);
    }

    @Test
    public void testInvalidIDInJsonBody() throws Exception {
        final String putJson = "{\"id\":\"\",\"name\":\"Samsung Galaxy S6\"," +
                "\"current_price\":{\"value\": 599.89,\"currency_code\":\"USD\"}}";
        ProductInfo productInfo = objectMapper.readValue(putJson, ProductInfo.class);
        Response response = CLIENT.target(RETAIL_URL).path(RETAIL_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(productInfo, MediaType.APPLICATION_JSON_TYPE));
        assertResponse(Response.Status.BAD_REQUEST, AppCodeConstants.INPUT_VALIDATION_FAILED, response);
    }

    /**
     * @param pathParam
     * @return
     */
    private Response responseForGET(final String pathParam) {
        return CLIENT.target(RETAIL_URL).path(RETAIL_PATH).path(pathParam).request(MediaType
                                                                                           .APPLICATION_JSON_TYPE)
                .get();
    }

    /**
     * @param status
     * @param appCodeConstants
     * @param response
     */
    private void assertResponse(final Response.Status status, final AppCodeConstants appCodeConstants, final Response
            response) {
        assertEquals(response.getStatus(), status.getStatusCode());
        ResponseStatus responseStatus = response.readEntity(ResponseStatus.class);
        assertNotNull(responseStatus);
        assertEquals(responseStatus.getCode(), appCodeConstants.getCode());
        assertEquals(responseStatus.getMessage(), appCodeConstants.getMessage());
    }
}
