package it.univpm.idstid.openstack.network.proxy.resources;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import it.univpm.idstid.openstack.network.proxy.entity.Network;
import it.univpm.idstid.openstack.network.proxy.entity.extended.ExtendedNetwork;
import it.univpm.idstid.openstack.network.proxy.entity.extended.ExtendedNetworkData;
import it.univpm.idstid.openstack.network.proxy.entity.extended.ExtendedPort;
import it.univpm.idstid.openstack.network.proxy.entity.extended.ExtendedPortData;
import it.univpm.idstid.openstack.network.proxy.utility.HTTPConnector;
import it.univpm.idstid.openstack.network.proxy.utility.JsonUtility;
import it.univpm.idstid.openstack.network.proxy.var.OpenstackNetProxyConstants;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.Gson;

@Path("/network")
public class NetworkRestInterface {

	private String path_local=OpenstackNetProxyConstants.URL_OPENSTACK+"/network/v2.0/networks";
	private String path_external=OpenstackNetProxyConstants.URL_OPENSTACK+"/v2.0/networks";
	private String URLpath=path_external;

	//This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHtml() {
		return OpenstackNetProxyConstants.HTML_RESOURCE_MESSAGE_NETWORK;
	}

	//Test if the resource is active
	@GET
	@Path("/test")
	@Produces("text/plain")
	public String resourceTest(){
		return OpenstackNetProxyConstants.MESSAGE_TEST;
	}

	//List Networks
	@GET
	@Path("/v2.0/networks")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listNetwork() throws MalformedURLException, IOException{
		//Send HTTP request and receive a list of Json content
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath), OpenstackNetProxyConstants.HTTP_METHOD_GET, null);
		String response=HTTPConnector.printStream(conn);
		Object result;
		result=(Network) JsonUtility.fromResponseStringToObject(response, Network.class);
		int responseCode=conn.getResponseCode();
		HTTPConnector.HTTPDisconnect(conn);
		return Response.ok().status(responseCode).header("Access-Control-Allow-Origin", "*").entity(result).build();
	}

	//Show Network
	@GET
	@Path("/v2.0/networks/{networkId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showNetwork(@PathParam("networkId") String networkId) throws MalformedURLException, IOException{
		//Send HTTP request and receive a single Json content identified by an ID
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath+"/"+networkId), OpenstackNetProxyConstants.HTTP_METHOD_GET, null);
		String response=HTTPConnector.printStream(conn);
		Object result;
		result=(Network) JsonUtility.fromResponseStringToObject(response, Network.class);
		int responseCode=conn.getResponseCode();
		HTTPConnector.HTTPDisconnect(conn);
		return Response.ok().status(responseCode).header("Access-Control-Allow-Origin", "*").entity(result).build();
	}

	//Create Networks
	@POST
	@Path("/v2.0/networks")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createNetwork(final Network net) throws MalformedURLException, IOException{
		//Convert input object NetworkData into a String like a Json text
		String input = JsonUtility.toJsonString(net);
		//Connect to a REST service
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath), OpenstackNetProxyConstants.HTTP_METHOD_POST, input);
		//Get the response text from the REST service
		String response=HTTPConnector.printStream(conn);
		Object result;
		if(response.equals("Multiple created")) result=response;
		else result=(Network) JsonUtility.fromResponseStringToObject(response, Network.class);
		int responseCode=conn.getResponseCode();
		HTTPConnector.HTTPDisconnect(conn);
		//Build the response
		return Response.ok().status(responseCode).header("Access-Control-Allow-Origin", "*").entity(result).build();
	}

	//Delete Network
	@DELETE
	@Path("/v2.0/networks/{networkId}")
	public Response deleteNetwork(@PathParam("networkId") String networkId) throws MalformedURLException, IOException{
		//Send the HTTP request to the REST service
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath+"/"+networkId), OpenstackNetProxyConstants.HTTP_METHOD_DELETE, null);
		int responseCode=conn.getResponseCode();
		if(responseCode==204){
			System.out.println(OpenstackNetProxyConstants.MESSAGE_DELETED_NETWORK_RESOURCE+networkId);
			return Response.ok().status(responseCode).header("Access-Control-Allow-Origin", "*").entity(OpenstackNetProxyConstants.MESSAGE_DELETED_NETWORK_RESOURCE+networkId).build();
		}
		else return Response.ok().status(responseCode).header("Access-Control-Allow-Origin", "*").entity(OpenstackNetProxyConstants.MESSAGE_FAIL).build();
	}

	//Update Network
	@PUT
	@Path("/v2.0/networks/{networkId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateNetwork(@PathParam("networkId") String networkId, final Network net) throws MalformedURLException, IOException{
		//Convert input object NetworkData into a String like a Json text
		String input = JsonUtility.toJsonString(net);
		//Connect to a REST service
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath+"/"+networkId), OpenstackNetProxyConstants.HTTP_METHOD_PUT, input);
		//Get the response text from the REST service
		String response=HTTPConnector.printStream(conn);
		Network n=(Network) JsonUtility.fromResponseStringToObject(response, Network.class);
		int responseCode=conn.getResponseCode();
		HTTPConnector.HTTPDisconnect(conn);
		//Build the response
		return Response.ok().status(responseCode).header("Access-Control-Allow-Origin", "*").entity(n).build();
	}
	
	@OPTIONS
	public Response getOptionValues(@HeaderParam("Access-Control-Request-Headers") String request){
		return Response.ok().header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS").build();
	}

	//------------------------------------------------------------------------------
	// Prove
	//------------------------------------------------------------------------------

	//Ext Show Network
	@GET
	@Path("/v2.0/ext/networks/{networkId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response extNetwork(@PathParam("networkId") String networkId) throws MalformedURLException, IOException{
//		ExtendedNetworkData ed=new ExtendedNetworkData();
//		ed.setNetworkType("TEST");
//		ed.setPhysical_network("Physical");
//		ed.setSegmentation_id("36976239dabihbvda7623gydaih");
//		ExtendedNetwork extNet = new ExtendedNetwork(ed);
		ExtendedPortData ed=new ExtendedPortData();
		ed.setVif_type("vif type");
		ed.setHost_id("I'm the host");
		ed.setProfile("profile");
		ed.setCapabilities("The best");
		ExtendedPort extPort=new ExtendedPort(ed);
		Gson gson = new Gson();
		String s=gson.toJson(extPort);
		return Response.status(200).entity(s).build();
	}

}
