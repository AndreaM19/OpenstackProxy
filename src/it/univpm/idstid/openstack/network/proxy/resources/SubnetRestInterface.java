package it.univpm.idstid.openstack.network.proxy.resources;

import it.univpm.idstid.openstack.network.proxy.entity.Subnet;
import it.univpm.idstid.openstack.network.proxy.var.OpenstackNetProxyConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/subnet")
public class SubnetRestInterface {
	
	@GET
	@Path("/test")
	@Produces("text/plain")
	public String resourceTest(){
		return OpenstackNetProxyConstants.TEST_MESSAGE;
	}
	
	//GET Subnets
	@GET
	@Path("/v2.0/subnets/{subnetId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Subnet showNetwork(@PathParam("subnetId") String subnetId){
		Subnet sub= new Subnet();
		sub.setSubnetID(subnetId);
		return sub;
	}
}
