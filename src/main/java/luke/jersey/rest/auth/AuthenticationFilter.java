package luke.jersey.rest.auth;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Provider;

import org.json.JSONException;
import org.json.JSONObject;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import luke.jersey.rest.app.MyApiException;
import luke.jersey.rest.app.MyAuthFilterException;
import luke.jersey.rest.config.ServiceConfiguration;
import luke.jersey.rest.test.*;




@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	private static final String REALM = "example";
	private static final String AUTHENTICATION_SCHEME = "Bearer";

	//@Override
	public void filter(ContainerRequestContext requestContext) throws MyAuthFilterException, IOException, JWTVerificationException {

		// Get the Authorization header from the request
		String authorizationHeader =
				requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		// Validate the Authorization header
		if (!isTokenBasedAuthentication(authorizationHeader)) {
			// abortWithUnauthorized(requestContext);
			// return;
			throw new MyAuthFilterException("missing authentication header");
		}

		// Extract the token from the Authorization header
		String token = authorizationHeader
				.substring(AUTHENTICATION_SCHEME.length()).trim();

		//try {

			// Validate the token
			validateToken(token);

		//} catch (Exception e) {
			//abortWithUnauthorized(requestContext);
		//}
	}

	private boolean isTokenBasedAuthentication(String authorizationHeader) {

		// Check if the Authorization header is valid
		// It must not be null and must be prefixed with "Bearer" plus a whitespace
		// The authentication scheme comparison must be case-insensitive
		// debug
	    if(ServiceConfiguration.Debug)
	    	if(authorizationHeader != null)
	    		System.out.println("authorizationHeader: "+authorizationHeader.toLowerCase());
		
	    return authorizationHeader != null && authorizationHeader.toLowerCase()
				.startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
	}

	private void abortWithUnauthorized(ContainerRequestContext requestContext) {

		// Abort the filter chain with a 401 status code response
		// The WWW-Authenticate header is sent along with the response
		requestContext.abortWith(
				Response.status(Response.Status.UNAUTHORIZED)
				.header(HttpHeaders.WWW_AUTHENTICATE, 
						AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
				.build());
	}

	private void validateToken(String token) throws JWTVerificationException {
		// Check if the token was issued by the server and if it's not expired
		// Throw an Exception if the token is invalid
		
		
		//if(!token.equals(testStuff.testToken))
		//	throw new Exception("invalid token");
		
		Algorithm algorithm = Algorithm.HMAC256(ServiceConfiguration.secret);
	    JWTVerifier verifier = JWT.require(algorithm)
	        .withIssuer(ServiceConfiguration.issuer)
	        //.acceptLeeway(1)   //1 sec for nbf and iat
	        //.acceptExpiresAt(5)   //5 secs for exp
	        .build(); //Reusable verifier instance
	    DecodedJWT jwt = verifier.verify(token);
	    Claim claim = jwt.getClaim(ServiceConfiguration.keyName);
	    
	    // debug
	    if(ServiceConfiguration.Debug)
	    	System.out.println("JWT token claim. "+ServiceConfiguration.keyName+": "+claim.asString());
		
	}

	
}


