package it.univpm.idstid.openstack.network.proxy.resources;

import it.univpm.idstid.openstack.network.proxy.entity.Test;
import it.univpm.idstid.openstack.network.proxy.entity.TestData;
import it.univpm.idstid.openstack.network.proxy.entity.extended.ExtendedNetwork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class TestRestInterface {
	
	@GET
	@Path("/resource/{networkId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Test showTest(){	
		Test t=new Test(new TestData());
		return t;
	}
	
	@GET
	@Path("/extended/")
	@Produces(MediaType.APPLICATION_JSON)
	public ExtendedNetwork getTest(){	
		ExtendedNetwork exNet=new ExtendedNetwork();
		return exNet;
	}

}
