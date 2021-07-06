package luke.jersey.rest.app;


import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import luke.jersey.rest.classes.ErrorResponse;

/* Exception Handler 
 * 
 * This class intercept exceptions and return the specified
 * status code.
 * */
@Provider
public class MyExceptionHandler implements ExceptionMapper<Exception>
{
    @Override
    public Response toResponse(Exception exception)
    {
        //return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).build();
    	if(exception instanceof MyApiException) {
			//return Response.status(503).entity(exception.getMessage()).build();
    		return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(Response.Status.BAD_REQUEST.getStatusCode(), Response.Status.BAD_REQUEST.getReasonPhrase()+": "+exception.getMessage())).build();			
        }
    	else if(exception instanceof MyAuthFilterException) {
    		return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(Response.Status.BAD_REQUEST.getStatusCode(), Response.Status.BAD_REQUEST.getReasonPhrase()+": "+exception.getMessage())).build();	
    	}
    	else if(exception instanceof SQLException) {
			//return Response.status(503).entity(exception.getMessage()).build();
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase()+": "+exception.getMessage())).build();		
    	}
    	else if(exception instanceof ParseException) {
			// return Response.status(503).entity(exception.getMessage()).build();
    		return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(new ErrorResponse(Response.Status.SERVICE_UNAVAILABLE.getStatusCode(), Response.Status.SERVICE_UNAVAILABLE.getReasonPhrase()+": "+exception.getMessage())).build();		
		}
    	else if(exception instanceof NumberFormatException) {
			return Response.status(503).entity(exception.getMessage()).build();
		}
    	else if(exception instanceof IOException) {
			// return Response.status(503).entity(exception.getMessage()).build();
    		return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(new ErrorResponse(Response.Status.SERVICE_UNAVAILABLE.getStatusCode(), Response.Status.SERVICE_UNAVAILABLE.getReasonPhrase()+": "+exception.getMessage())).build();		
		}
    	else if (exception instanceof NullPointerException) {
			// return Response.status(503).entity(exception.getMessage()).build();
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase()+": null pointer exception.. "+exception.getMessage())).build();
		}
    	else if (exception instanceof ClassNotFoundException) {
			return Response.status(503).entity(exception.getMessage()).build();
		}
    	else if (exception instanceof InterruptedException) {
			return Response.status(503).entity(exception.getMessage()).build();
		}
    	else if (exception instanceof JWTCreationException) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase()+": "+exception.getMessage())).build();			
    	}
    	else if (exception instanceof JWTVerificationException) {
    		return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new ErrorResponse(Response.Status.NOT_ACCEPTABLE.getStatusCode(), Response.Status.NOT_ACCEPTABLE.getReasonPhrase()+": "+exception.getMessage())).build();
    	}
		else {
			// return Response.status(500).entity(exception.getMessage()).build();
			
			// Return an error status code with a json object in the body
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase()+": "+exception.getMessage())).build();
						
		}
    }
}
