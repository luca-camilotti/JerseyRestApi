package luke.jersey.rest.JerseyRestTokenAuth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import luke.jersey.rest.classes.employee;

@Path("debug")
public class DebugResource {
	
	@GET    
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
	public Response test() {
		JSONObject json = new JSONObject();
        json.put("status", "404");
        json.put("message", " Not found!");
		return Response.ok(json.toString(), MediaType.APPLICATION_JSON).build();
		
		// throw new Exception("Server Error Test Response");  // testing exception handler
		// return Response.status(Response.Status.).entity(new ErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), operation+" Not found!")).build();  // OK
	}

}
