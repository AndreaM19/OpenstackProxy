package it.univpm.idstid.openstack.network.proxy.resources;

import it.univpm.idstid.openstack.network.proxy.entity.Test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class TestRestInterface {
	
	@GET
	@Path("/resource")
	@Produces(MediaType.APPLICATION_JSON)
	public Test showTest(){	
		Test t=new Test();
		return t;
	}

}
