package it.univpm.idstid.openstack.network.proxy.resources;

import it.univpm.idstid.openstack.network.proxy.entity.Network;
import it.univpm.idstid.openstack.network.proxy.var.OpenstackNetProxyConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/network")
public class NetworkRestInterface {
	
	@GET
	@Path("/test")
	@Produces("text/plain")
	public String resourceTest(){
		return OpenstackNetProxyConstants.TEST_MESSAGE;
	}
	
	//GET Networks
	@GET
	@Path("/v2.0/networks/{networkId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Network showNetwork(@PathParam("networkId") String networkId){
		Network net= new Network();
		net.setNetworkID(networkId);
		return net;
	}
}
