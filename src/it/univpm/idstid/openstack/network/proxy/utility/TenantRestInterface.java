package it.univpm.idstid.openstack.network.proxy.utility;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import it.univpm.idstid.openstack.network.proxy.var.OpenstackNetProxyConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/tenant")
public class TenantRestInterface {

	private String path_external=OpenstackNetProxyConstants.URL_OPENSTACK_AUTH+"/v2.0/tenants";
	private String URLpath=path_external;

	//Test if the resource is active
	@GET
	@Path("/test")
	@Produces("text/plain")
	public String resourceTest(){
		return OpenstackNetProxyConstants.MESSAGE_TEST;
	}

	//List Networks
	@GET
	@Path("/v2.0/tenants")
	@Produces({MediaType.APPLICATION_JSON})
	public Response listNetwork() throws MalformedURLException, IOException{
		//Send HTTP request and receive a list of Json content
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath), OpenstackNetProxyConstants.HTTP_METHOD_GET, null);
		String response=HTTPConnector.printStream(conn);
		int responseCode=conn.getResponseCode();
		HTTPConnector.HTTPDisconnect(conn);
		return Response.ok().status(responseCode).header("Access-Control-Allow-Origin", "*").entity(response).build();
	}

}
