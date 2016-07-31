package com.dsworks.retail.exception;

import com.dsworks.retail.util.AppCodeConstants;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RetailExceptionMapper implements ExceptionMapper<Exception> {

    public Response toResponse(Exception exception) {
        Response.Status status = null;
        Error error = new Error();
        if(exception instanceof RetailException){
            RetailException rtlExcptn = (RetailException) exception;
            status = rtlExcptn.getStatus();
            error.setCode(rtlExcptn.getRespCode()).setMessage(rtlExcptn.getRespMsg());

        }else if (exception instanceof NotFoundException){
            status = Response.Status.NOT_FOUND;
            error.setCode(AppCodeConstants.RESOURCE_NOT_FOUND.getCode()).setMessage(AppCodeConstants.RESOURCE_NOT_FOUND.getMessage());
        } else {
            status = Response.Status.INTERNAL_SERVER_ERROR;
            error.setCode(AppCodeConstants.UNEXPECTED_ERROR.getCode()).setMessage(AppCodeConstants.UNEXPECTED_ERROR.getMessage());
        }
        return Response.status(status).entity(error).build();
    }

    class Error{

        @JsonProperty
        private String code;

        @JsonProperty
        private String message;

        public String getCode() {
            return code;
        }

        public Error setCode(String code) {
            this.code = code;
            return this;
        }

        public String getMessage() {
            return message;
        }

        public Error setMessage(String message) {
            this.message = message;
            return this;
        }
    }
}
