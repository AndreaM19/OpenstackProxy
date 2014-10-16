package it.univpm.Openstack.proxy.helloworld;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

// class will be at addressable at the URI "/helloworld"
@Path("/helloworld")
public class HelloWorldResource {

	// The java method will process HTTP GET requests
	@GET
	/* The Java method will produce content identified by the
	 * MIME Media type "text/plain"
	 */
	@Produces("text/plain")
	public String getMessage() {
		return "Hello World";
	}
	
	
	@GET
	@Path("/name")
	@Produces("text/plain")
	public String getMessageWithName() {
		return "Hello World my name is Andrea";
	}
	
}