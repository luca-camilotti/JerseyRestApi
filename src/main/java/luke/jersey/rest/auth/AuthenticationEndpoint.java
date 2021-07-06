package luke.jersey.rest.auth;

import java.util.Date;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import luke.jersey.rest.test.*;
import luke.jersey.rest.classes.*;
import luke.jersey.rest.config.*;

/*
 * Authentication Endpoint
 * */
@Path("/authentication")
public class AuthenticationEndpoint {

	/*
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("username") String username, 
                                     @FormParam("password") String password) {

        try {

            // Authenticate the user using the credentials provided
            authenticate(username, password);

            // Issue a token for the user
            String token = issueToken(username);

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }      
    }*/
    
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/test")
    public Credentials test() {

        return new Credentials("pluto", "12345");
    }
	
	
    // http://localhost:8080/RestTokenApi/webapi/authentication/authuser
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/authuser")
    public Response authenticateUser(Credentials credentials) {

        
        try {
        	
        	String username = credentials.getUsername();
            String password = credentials.getPassword();

            // Authenticate the user, issue a token and return a response
            

            // Authenticate the user using the credentials provided
            authenticate(username, password);

            // Issue a token for the user
            String token = issueToken(username);

            JSONObject jsontoken = new JSONObject();
            jsontoken.put("token", token);
            // Return the token on the response
            return Response.ok(jsontoken.toString()).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } 
    }

    private void authenticate(String username, String password) throws Exception {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
    }

    private String issueToken(String username) throws JWTCreationException {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
    	
    	
    	Algorithm algorithm = Algorithm.HMAC256(ServiceConfiguration.secret);
    	String token = JWT.create()
    			.withIssuer(ServiceConfiguration.issuer)
    			.withExpiresAt(new Date(System.currentTimeMillis()+ServiceConfiguration.tokenExpiredMs))
    			.withClaim(ServiceConfiguration.keyName, username)
    			.withArrayClaim("array", new Integer[]{1, 2, 3})
    			.sign(algorithm);

    	// debug
	    if(ServiceConfiguration.Debug) {
	    	System.out.println("Now: "+new Date(System.currentTimeMillis()).toString());
	    	System.out.println("Token duration: "+ServiceConfiguration.tokenExpiredMs/1000.0+" sec");
	    	System.out.println("Token will be valid through: "+new Date(System.currentTimeMillis()+ServiceConfiguration.tokenExpiredMs).toString());
	 	   
	    }
    	return token;
    	
    	// return testStuff.testToken;  // testing
    	
    }
}