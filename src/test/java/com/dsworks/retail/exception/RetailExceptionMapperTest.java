package com.dsworks.retail.exception;


import com.dsworks.retail.beans.ResponseStatus;
import com.dsworks.retail.util.AppCodeConstants;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

public class RetailExceptionMapperTest extends Assert {

    private final RetailExceptionMapper retailExceptionMapper = new RetailExceptionMapper();

    @Test
    public void testToResponseWithRetailExceptionInstance() {
        final RetailException retailException = new RetailException(Response.Status.SERVICE_UNAVAILABLE,
                                                                    AppCodeConstants.DATABASE_ERROR.getCode(),
                                                                    AppCodeConstants.DATABASE_ERROR.getMessage());
        Response response = retailExceptionMapper.toResponse(retailException);

        assertNotNull(response);
        assertTrue(response.getStatus() == Response.Status.SERVICE_UNAVAILABLE.getStatusCode());
        commonAssert(response, AppCodeConstants.DATABASE_ERROR);
    }

    @Test
    public void testToResponseWithNotFoundExceptionInstance() {
        Response response = retailExceptionMapper.toResponse(new NotFoundException());

        assertNotNull(response);
        assertTrue(response.getStatus() == Response.Status.NOT_FOUND.getStatusCode());
        commonAssert(response, AppCodeConstants.RESOURCE_NOT_FOUND);
    }

    @Test
    public void testToResponseWithRandomException() {
        Response response = retailExceptionMapper.toResponse(new Exception());

        assertNotNull(response);
        assertTrue(response.getStatus() == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        commonAssert(response, AppCodeConstants.UNEXPECTED_ERROR);
    }

    private void commonAssert(final Response response, final AppCodeConstants appCodeConstants) {
        assertTrue(response.getEntity() instanceof ResponseStatus);
        assertTrue(appCodeConstants.getCode().equals(((ResponseStatus) response.getEntity()).getCode()));
        assertTrue(appCodeConstants.getMessage().equals(((ResponseStatus) response.getEntity()).getMessage()));
    }
}
