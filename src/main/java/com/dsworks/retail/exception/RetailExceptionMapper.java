package com.dsworks.retail.exception;

import com.dsworks.retail.beans.ResponseStatus;
import com.dsworks.retail.util.AppCodeConstants;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RetailExceptionMapper implements ExceptionMapper<Exception> {

    public Response toResponse(Exception exception) {
        Response.Status status = null;
        ResponseStatus error = new ResponseStatus();
        if (exception instanceof RetailException) {
            RetailException retailException = (RetailException) exception;
            status = retailException.getStatus();
            error.setCode(retailException.getRespCode()).setMessage(retailException.getRespMsg());

        } else if (exception instanceof NotFoundException) {
            status = Response.Status.NOT_FOUND;
            error.setCode(AppCodeConstants.RESOURCE_NOT_FOUND.getCode()).setMessage(AppCodeConstants
                                                                                            .RESOURCE_NOT_FOUND
                                                                                            .getMessage());
        } else {
            status = Response.Status.INTERNAL_SERVER_ERROR;
            error.setCode(AppCodeConstants.UNEXPECTED_ERROR.getCode()).setMessage(AppCodeConstants.UNEXPECTED_ERROR
                                                                                          .getMessage());
        }
        return Response.status(status).entity(error).build();
    }
}
