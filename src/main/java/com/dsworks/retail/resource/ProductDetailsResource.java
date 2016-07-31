package com.dsworks.retail.resource;

import com.codahale.metrics.annotation.Timed;
import com.dsworks.retail.beans.ProductInfoResponse;
import com.dsworks.retail.exception.RetailException;
import com.dsworks.retail.service.RetailService;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/products/{id}")
@Produces(MediaType.APPLICATION_JSON)
public class ProductDetailsResource {

    private static Logger LOG = LoggerFactory.getLogger(ProductDetailsResource.class);

    private final RetailService service;

    @Inject
    public ProductDetailsResource (final RetailService rtlService){
        this.service = rtlService;
    }

    @GET
    @Timed
    public ProductInfoResponse getProductInfo(@PathParam("id") String productId) throws RetailException{

        LOG.info("Request received for ID {}", productId);

        ProductInfoResponse productResponse = service.getProductInfo(productId);

        return productResponse;

//        return Response.status(Response.Status.OK).entity(productResponse).build();

    }
}
