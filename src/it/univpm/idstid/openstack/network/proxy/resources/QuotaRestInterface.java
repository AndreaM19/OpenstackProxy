package it.univpm.idstid.openstack.network.proxy.resources;

import it.univpm.idstid.openstack.network.proxy.var.OpenstackNetProxyConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/quota")
public class QuotaRestInterface {
	
	@GET
	@Path("/test")
	@Produces("text/plain")
	public String resourceTest(){
		return OpenstackNetProxyConstants.TEST_MESSAGE;
	}
}
