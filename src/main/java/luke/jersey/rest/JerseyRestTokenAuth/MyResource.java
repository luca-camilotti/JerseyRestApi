package luke.jersey.rest.JerseyRestTokenAuth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import luke.jersey.rest.classes.employee;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    @GET    
    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
	public employee employee() {
		employee e = new employee();
		e.setId("test");
		e.setFirstName("Hello");
		e.setLastName("World!");
		return e;
	}
}
