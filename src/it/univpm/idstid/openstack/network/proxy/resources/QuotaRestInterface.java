package it.univpm.idstid.openstack.network.proxy.resources;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import it.univpm.idstid.openstack.network.proxy.entity.Quota;
import it.univpm.idstid.openstack.network.proxy.entity.XmlTester;
import it.univpm.idstid.openstack.network.proxy.utility.HTTPConnector;
import it.univpm.idstid.openstack.network.proxy.utility.JsonUtility;
import it.univpm.idstid.openstack.network.proxy.var.OpenstackNetProxyConstants;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/quota")
public class QuotaRestInterface {

	@SuppressWarnings("unused")
	private String path_local=OpenstackNetProxyConstants.URL_OPENSTACK+"/quota/v2.0/quotas/";
	private String path_external=OpenstackNetProxyConstants.URL_OPENSTACK+"/v2.0/quotas";
	private String URLpath=path_external;	

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
	@Produces({MediaType.APPLICATION_JSON, OpenstackNetProxyConstants.TYPE_RDF})
	public Response listQuota(@HeaderParam("Accept") String accept) throws MalformedURLException, IOException{
		if (accept.equals(OpenstackNetProxyConstants.TYPE_RDF)){
			System.out.println(accept);
			XmlTester t=new XmlTester();
			return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(t).build();
		}
		else{
			//Send HTTP request and receive a list of Json content
			HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath), OpenstackNetProxyConstants.HTTP_METHOD_GET, null);
			String response=HTTPConnector.printStream(conn);
			Object result;
			result=(Quota) JsonUtility.fromResponseStringToObject(response, Quota.class);
			int responseCode=conn.getResponseCode();
			HTTPConnector.HTTPDisconnect(conn);
			return Response.status(responseCode).header("Access-Control-Allow-Origin", "*").entity(result).build();
		}
	}

	//Show Quotas
	@GET
	@Path("/v2.0/quotas/{tenantId}")
	@Produces({MediaType.APPLICATION_JSON, OpenstackNetProxyConstants.TYPE_RDF})
	public Response showQuota(@PathParam("tenantId") String tenantId, @HeaderParam("Accept") String accept) throws MalformedURLException, IOException{
		//Send HTTP request and receive a single Json content identified by an ID
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath+"/"+tenantId), OpenstackNetProxyConstants.HTTP_METHOD_GET, null);
		String response=HTTPConnector.printStream(conn);
		Object result;
		result=(Quota) JsonUtility.fromResponseStringToObject(response, Quota.class);
		int responseCode=conn.getResponseCode();
		HTTPConnector.HTTPDisconnect(conn);
		return Response.status(responseCode).header("Access-Control-Allow-Origin", "*").entity(result).build();
	}

	//Reset Quotas
	@DELETE
	@Path("/v2.0/quotas/{tenantId}")
	public Response resetQuota(@PathParam("tenantId") String tenantId) throws MalformedURLException, IOException{
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath+"/"+tenantId), OpenstackNetProxyConstants.HTTP_METHOD_DELETE, null);
		int responseCode=conn.getResponseCode();
		if(responseCode==204){
			System.out.println(OpenstackNetProxyConstants.MESSAGE_RESET_QUOTA_RESOURCE+tenantId);
			return Response.status(responseCode).header("Access-Control-Allow-Origin", "*").entity(OpenstackNetProxyConstants.MESSAGE_RESET_QUOTA_RESOURCE+tenantId).build();
		}
		else return Response.status(responseCode).header("Access-Control-Allow-Origin", "*").entity(OpenstackNetProxyConstants.MESSAGE_FAIL).build();
	}

	//Update Quota
	@PUT
	@Path("/v2.0/quotas/{tenantId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateQuota(@PathParam("tenantId") String tenantId, final String request) throws MalformedURLException, IOException{
		//Convert input object NetworkData into a String like a Json text
		Object quota;
		quota = JsonUtility.fromResponseStringToObject(request,Quota.class);
		String input = JsonUtility.toJsonString(quota);
		//Connect to a REST service
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath+"/"+tenantId), OpenstackNetProxyConstants.HTTP_METHOD_PUT, input);
		//Get the response text from the REST service
		String response=HTTPConnector.printStream(conn);
		Quota q=(Quota) JsonUtility.fromResponseStringToObject(response, Quota.class);
		int responseCode=conn.getResponseCode();
		HTTPConnector.HTTPDisconnect(conn);
		//Build the response
		return Response.status(responseCode).header("Access-Control-Allow-Origin", "*").entity(q).build();
	}

	@OPTIONS
	@Path("/v2.0/quotas")
	public Response getOptionValues(@HeaderParam("Access-Control-Request-Headers") String request){
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
				.build();
	}

	@OPTIONS
	@Path("/v2.0/quotas/{tenantId}")
	public Response getOptionValuesPar(@HeaderParam("Access-Control-Request-Headers") String request){
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
				.build();
	}

}
