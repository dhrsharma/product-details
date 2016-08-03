package com.dsworks.retail.resource;

import com.codahale.metrics.annotation.Timed;
import com.dsworks.retail.beans.ProductInfo;
import com.dsworks.retail.beans.ResponseStatus;
import com.dsworks.retail.exception.RetailException;
import com.dsworks.retail.service.RetailService;
import com.dsworks.retail.util.AppCodeConstants;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductDetailsResource {

    private static Logger LOG = LoggerFactory.getLogger(ProductDetailsResource.class);

    private final RetailService service;

    @Inject
    public ProductDetailsResource(final RetailService rtlService) {
        this.service = rtlService;
    }

    @GET
    @Timed
    @Path("/{id}")
    public Response getProductInfo(@PathParam("id") String productId) throws RetailException {

        LOG.info("Request received for ID {}", productId);

        ProductInfo productResponse = service.getProductInfo(productId);

        return Response.status(Response.Status.OK).entity(productResponse).build();
    }


    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(@Valid ProductInfo productInfo) throws RetailException {
        LOG.info("Update Request received for product {}", productInfo.getId());

        service.createProduct(productInfo);

        return Response.status(Response.Status.CREATED).entity(new ResponseStatus().setCode(AppCodeConstants.SUCCESS
                                                                                                    .getCode())
                                                                       .setMessage(AppCodeConstants.SUCCESS
                                                                                           .getMessage())).build();
    }

    @PUT
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProductPrice(@Valid ProductInfo productInfo) throws RetailException {
        LOG.info("Update Request received for product {}", productInfo.getId());

        service.updatePriceById(productInfo);

        return Response.status(Response.Status.CREATED).entity(new ResponseStatus().setCode(AppCodeConstants.SUCCESS
                                                                                                    .getCode())
                                                                       .setMessage(AppCodeConstants.SUCCESS
                                                                                           .getMessage())).build();
    }
}
