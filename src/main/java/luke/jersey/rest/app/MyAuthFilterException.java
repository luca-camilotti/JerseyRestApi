package luke.jersey.rest.app;

import java.io.IOException;
import java.io.Serializable;


public class MyAuthFilterException extends IOException implements Serializable
{
    private static final long serialVersionUID = 1L;
    public MyAuthFilterException() {
        super();
    }
    public MyAuthFilterException(String msg)   {
        super(msg);
    }
    public MyAuthFilterException(String msg, Exception e)  {
        super(msg, e);
    }
}
