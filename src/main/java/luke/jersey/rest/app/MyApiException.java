package luke.jersey.rest.app;

import java.io.Serializable;

/*
 * This class implements a custom exception
 * that can be thrown by api.
 * */

public class MyApiException extends Exception implements Serializable
{
    private static final long serialVersionUID = 1L;
    public MyApiException() {
        super();
    }
    public MyApiException(String msg)   {
        super(msg);
    }
    public MyApiException(String msg, Exception e)  {
        super(msg, e);
    }
}