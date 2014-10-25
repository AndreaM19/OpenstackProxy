package it.univpm.idstid.openstack.network.proxy.resources;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import it.univpm.idstid.openstack.network.proxy.entity.Quota;
import it.univpm.idstid.openstack.network.proxy.utility.HTTPConnector;
import it.univpm.idstid.openstack.network.proxy.utility.JsonUtility;
import it.univpm.idstid.openstack.network.proxy.var.OpenstackNetProxyConstants;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("/quota")
public class QuotaRestInterface {
	
	private String URLpath=OpenstackNetProxyConstants.URL_OPENSTACK+"/port/v2.0/ports/";

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
	public Quota listQuota(){
		Quota quota=new Quota();
		return quota;
	}

	//Show Quotas
	@GET
	@Path("/v2.0/quotas/{tenantId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Quota showQuota(@PathParam("tenantId") String tenantId) throws MalformedURLException{
		JSONObject json=HTTPConnector.getJsonResponse(new URL(this.URLpath+tenantId), OpenstackNetProxyConstants.HTTP_METHOD_GET, MediaType.APPLICATION_JSON, OpenstackNetProxyConstants.HTTP_KEY_ACCEPT);
		//Active parsing of the json file and receive a Port object
		Quota quota=JsonUtility.QuotaJsonParser(json);
		return quota;
	}
	
	//Reset Quotas
	@DELETE
	@Path("/v2.0/quotas/{tenantId}")
	public Response resetQuota(@PathParam("tenantId") String tenantId) throws MalformedURLException, IOException{
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath+tenantId), OpenstackNetProxyConstants.HTTP_METHOD_DELETE, null, null, null);
		if(conn.getResponseCode()==204){
			System.out.println(OpenstackNetProxyConstants.MESSAGE_RESET_QUOTA_RESOURCE+tenantId);
			return Response.status(204).entity(OpenstackNetProxyConstants.MESSAGE_RESET_QUOTA_RESOURCE+tenantId).build();
		}
		else return Response.status(conn.getResponseCode()).entity(OpenstackNetProxyConstants.MESSAGE_FAIL).build();
	}

}
