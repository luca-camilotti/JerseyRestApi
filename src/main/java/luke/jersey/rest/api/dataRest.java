package luke.jersey.rest.api;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import luke.jersey.rest.database.*;
import luke.jersey.rest.app.MyApiException;
import luke.jersey.rest.auth.Secured;
import luke.jersey.rest.classes.*;

// Use Postman as a RESTFul Client to test the service..

@Path("/data")
public class dataRest {
	
	// Try:
	// http://localhost:8080/RestTokenApi/webapi/data
	@GET
    @Produces("text/xml")
    public String prova( ) {
                
        return ("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result>prova</result>");
    }
	
	// Try:
	// http://localhost:8080/RestTokenApi/webapi/data/what
	@GET
	@Path("{operation}")
    //@Produces(MediaType.TEXT_HTML)
	@Produces(MediaType.APPLICATION_JSON)
	public Response error(@PathParam("operation") String operation) throws Exception {
		// return Response.status(404).entity(operation+" Not found!").build();
		
		
		//JSONObject json = new JSONObject();
        //json.put("status", "404");
        //json.put("message", operation+" Not found!");
		//return Response.ok(json.toString(), MediaType.APPLICATION_JSON).build();
		
		throw new MyApiException("Server Error Test Response: "+operation);  // testing exception handler
		// return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), operation+" Not found!")).build();  // OK
	}
	
	// Try:
	// http://localhost:8080/RestTokenApi/webapi/data/employee/h725
	@GET
	@Path("employee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public employee employee(@PathParam("id") String id) {
		employee e = new employee();
		e.setId(id);
		e.setFirstName("Pippo");
		e.setLastName("De Pippis");
		return e;
	}
	
	// Try:
	// http://localhost:8080/RestTokenApi/webapi/data/list
	@Secured  // it is secured with token
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public employee[] employeelist() {
		employee[] e = new employee[3];
		e[0] = new employee();
		e[0].setId("abc42");
		e[0].setFirstName("Pippo");
		e[0].setLastName("De Pippis");
		e[1] = new employee();
		e[1].setId("dre01");
		e[1].setFirstName("Chuck");
		e[1].setLastName("Norris");
		e[2] = new employee();
		e[2].setId("kjb38");
		e[2].setFirstName("Bruce");
		e[2].setLastName("Wayne");
		e = dbHelper.query(dbHelper.queryAllDipendente);
		return e;
	}
	
	// Try:
	// curl -d "{\"firstName\":\"Chuck\",\"lastName\":\"Norris\",\"id\":\"abc23\"}" -H "Content-Type: application/json" -X POST http://localhost:8080/Jersey/api/data/post -w "\n"	
	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/post")
    public Response postMessage(employee msg) throws Exception{
		
		if(msg==null || msg.getId()==null || msg.getFirstName()==null || msg.getLastName()==null)
			throw new NullPointerException("Json data missing!");
		
		System.out.println("Id = "+msg.getId());
        System.out.println("First Name = "+msg.getFirstName());
        System.out.println("Last Name  = "+msg.getLastName());
        
        return Response.status(200).entity(msg.getFirstName()+" "+msg.getLastName()+" OK!").build();
    }
	
	// Try:
	// http://localhost:8080/RestTokenApi/webapi/data/exception/nullpointerexception
	@GET
	@Path("exception/{type}")
    @Produces(MediaType.TEXT_HTML)
	public Response raiseException(@PathParam("type") String type) throws Exception {
		if(type.equalsIgnoreCase("IOException"))
			throw new IOException("Exception test: IOException");
		else if (type.equalsIgnoreCase("NullPointerException"))
			throw new NullPointerException("Exception test: NullPointerException");
		else if (type.equalsIgnoreCase("ClassNotFoundException"))
			throw new ClassNotFoundException("Exception test: ClassNotFoundException");
		else if (type.equalsIgnoreCase("InterruptedException"))
			throw new InterruptedException("Exception test: InterruptedException");
		else
			throw new Exception("Exception test: generic Exception");
		
	}

}
