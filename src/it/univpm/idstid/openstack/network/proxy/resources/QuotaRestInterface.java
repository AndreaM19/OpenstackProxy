package it.univpm.idstid.openstack.network.proxy.resources;

import it.univpm.idstid.openstack.network.proxy.entity.Quota;
import it.univpm.idstid.openstack.network.proxy.var.OpenstackNetProxyConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/quota")
public class QuotaRestInterface {

	@GET
	@Path("/test")
	@Produces("text/plain")
	public String resourceTest(){
		return OpenstackNetProxyConstants.MESSAGE_TEST;
	}

	//List Quotas
	@GET
	@Path("/v2.0/quotas")
	@Produces(MediaType.APPLICATION_JSON)
	public Quota listQuota(){
		Quota quota=new Quota();
		return quota;
	}

	//Show Quotas
	@GET
	@Path("/v2.0/quotas/{tenantId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Quota showNetwork(@PathParam("tenantId") String tenantId){
		Quota quota=new Quota();
		quota.setTenantId(tenantId);
		return quota;
	}

}
