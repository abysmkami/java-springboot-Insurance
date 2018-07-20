package com.Insurance.Filter;

public enum ResultStatusCode {  
    OK(0, "OK"),  
    SYSTEM_ERR(30001, "System error"),
    PERMISSION_DENIED(30002, "Permission denied"),
    INVALID_CLIENTID(30003, "Invalid clientid"),  
    INVALID_PASSWORD(30004, "User name or password is incorrect"),  
    INVALID_CAPTCHA(30005, "Invalid captcha or captcha overdue"),  
    INVALID_TOKEN(30006, "Invalid token"); 
    ;  
      
    private int errcode;  
    private String errmsg;  
    public int getErrcode() {  
        return errcode;  
    }  
  
    public void setErrcode(int errcode) {  
        this.errcode = errcode;  
    }  
  
    public String getErrmsg() {  
        return errmsg;  
    }  
  
    public void setErrmsg(String errmsg) {  
        this.errmsg = errmsg;  
    }  
    private ResultStatusCode(int Errode, String ErrMsg)  
    {  
        this.errcode = Errode;  
        this.errmsg = ErrMsg;  
    }  
}  
