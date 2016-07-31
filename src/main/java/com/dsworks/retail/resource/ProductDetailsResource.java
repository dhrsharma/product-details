package com.dsworks.retail.resource;

import com.codahale.metrics.annotation.Timed;
import com.dsworks.retail.model.ProductInfoResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by dhruvsharma on 7/30/16.
 */
@Path("/products/{id}")
@Produces(MediaType.APPLICATION_JSON)
public class ProductDetailsResource {

    @GET
    @Timed
    public ProductInfoResponse getProductInfo(@PathParam("id") String productId) {
        return new ProductInfoResponse();
    }

}
