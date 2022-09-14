package com.project1.project1.exception;

public class BusinessException  extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public String getErrorcode() {
        return errorcode;
    }

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }
    public BusinessException (String errorcode,String errormessage){
        super();
        this.errorcode = errorcode;
        this.errormessage = errormessage;
    }
    public BusinessException()
    {

    }
    private String errorcode;
    private String errormessage;
}
