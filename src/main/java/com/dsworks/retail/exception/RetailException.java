package com.dsworks.retail.exception;


import static javax.ws.rs.core.Response.Status;

public class RetailException extends Exception {
    private String respCode;
    private String respMsg;
    private Status status;

    public RetailException(final Status status, final String respCd, final String respMsg) {
        this.status = status;
        this.respCode = respCd;
        this.respMsg = respMsg;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
