package it.univpm.idstid.openstack.network.proxy.resources;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import it.univpm.idstid.openstack.network.proxy.entity.Quota;
import it.univpm.idstid.openstack.network.proxy.utility.HTTPConnector;
import it.univpm.idstid.openstack.network.proxy.utility.JsonUtility;
import it.univpm.idstid.openstack.network.proxy.var.OpenstackNetProxyConstants;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/quota")
public class QuotaRestInterface {

	private String URLpath=OpenstackNetProxyConstants.URL_OPENSTACK+"/quota/v2.0/quotas/";

	@GET
	@Path("/test")
	@Produces("text/plain")
	public String resourceTest(){
		return OpenstackNetProxyConstants.MESSAGE_TEST;
	}

	//This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHtml() {
		return OpenstackNetProxyConstants.HTML_RESOURCE_MESSAGE_QUOTA;
	}

	//List Quotas
	@GET
	@Path("/v2.0/quotas")
	@Produces(MediaType.APPLICATION_JSON)
	public Quota listQuota() throws MalformedURLException{
		Object ob=HTTPConnector.getJsonContent(new URL(this.URLpath), OpenstackNetProxyConstants.HTTP_METHOD_GET, Quota.class);
		Quota quota=(Quota)ob;
		return quota;
	}

	//Show Quotas
	@GET
	@Path("/v2.0/quotas/{tenantId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Quota showQuota(@PathParam("tenantId") String tenantId) throws MalformedURLException{
		Object ob=HTTPConnector.getJsonContent(new URL(this.URLpath+tenantId), OpenstackNetProxyConstants.HTTP_METHOD_GET, Quota.class);
		Quota quota=(Quota)ob;	
		return quota;
	}

	//Reset Quotas
	@DELETE
	@Path("/v2.0/quotas/{tenantId}")
	public Response resetQuota(@PathParam("tenantId") String tenantId) throws MalformedURLException, IOException{
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath+tenantId), OpenstackNetProxyConstants.HTTP_METHOD_DELETE, null);
		if(conn.getResponseCode()==204){
			System.out.println(OpenstackNetProxyConstants.MESSAGE_RESET_QUOTA_RESOURCE+tenantId);
			return Response.status(204).entity(OpenstackNetProxyConstants.MESSAGE_RESET_QUOTA_RESOURCE+tenantId).build();
		}
		else return Response.status(conn.getResponseCode()).entity(OpenstackNetProxyConstants.MESSAGE_FAIL).build();
	}

	//Update Quota
	@PUT
	@Path("/v2.0/quotas/{tenantId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateQuota(@PathParam("tenantId") String tenantId, final Quota quota) throws MalformedURLException, IOException{
		//Convert input object NetworkData into a String like a Json text
		String input = JsonUtility.toJsonString(quota);
		//Connect to a REST service
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath+tenantId), OpenstackNetProxyConstants.HTTP_METHOD_PUT, input);
		//Get the response text from the REST service
		String response=HTTPConnector.printStream(conn);
		Quota q=(Quota) JsonUtility.fromResponseStringToObject(response, Quota.class);
		HTTPConnector.HTTPDisconnect(conn);
		//Build the response
		return Response.status(200).entity(q).build();
	}

}
