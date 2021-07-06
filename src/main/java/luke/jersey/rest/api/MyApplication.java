package luke.jersey.rest.api;

import java.sql.*;
import javax.ws.rs.core.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.*;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import luke.jersey.rest.JerseyRestTokenAuth.*;

//import com.sun.jersey.api.core.PackagesResourceConfig;
//import com.sun.jersey.api.core.ResourceConfig;

import luke.jersey.rest.api.*;
import luke.jersey.rest.app.MyExceptionHandler;
import luke.jersey.rest.auth.*;

/*
 * This Application class loads all rest resources 
 * which will be exposed to client.
 * Add resource classes to the HashSet below.
 * List of exposed resources:
 * 
 * 	dataRest.class -> data resources endpoint
 * 	AuthenticationEndpoint.class -> authentication endpoint
 * 	MyResource.class -> other example resources
 * 	DebugResource.class -> resources for debug purposes
 * 	MyExceptionHandler.class -> exception handler class for any thrown exception
 * 
 * */

@ApplicationPath("/webapi")

public class MyApplication extends Application {	
	
    public MyApplication() {
		super();		
		/*
		final ResourceConfig rc = new PackagesResourceConfig("luke.jersey.yes");
	    final Map<String, Object> config = new HashMap<String, Object>();
	    config.put("com.sun.jersey.api.json.POJOMappingFeature", true);
	    rc.setPropertiesAndFeatures(config); */ 
	    
		
	}

    /* NOT working!
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("jersey.config.server.provider.packages", "luke.jersey.yes.api");
        return properties;
    } */
	
	public Set<Class<?>> getClasses() {
		/*
		 *  Set<Class<?>> s = new HashSet<Class<?>>();
        	s.add(HelloWorldResource.class);

            //you need to add ExceptionMapper class as well         
            s.add(MyExceptionHandler.class)
        	return s;
		 */
		// Debug:
		System.out.println("JerseyRestTokenAuth -> MyApplication extends Application is loading classes..");  // this is never printed
		// Add all classes to HashSet:
        return new HashSet<Class<?>>(Arrays.asList(dataRest.class, AuthenticationEndpoint.class, MyResource.class, DebugResource.class, MyExceptionHandler.class));
    } 
}

/**
 * import javax.ws.rs.ApplicationPath;
 
import org.glassfish.jersey.server.ResourceConfig;
 
    @ApplicationPath("resources")
    public class MyApplication extends ResourceConfig {
        public MyApplication() {
            packages("com.connect2java.RestfulApplicationPathAnnotationExample");
        }
       
    }
 * */


