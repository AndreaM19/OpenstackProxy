package it.univpm.idstid.openstack.network.proxy.resources;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import it.univpm.idstid.openstack.network.parliament.NetworkOntology;
import it.univpm.idstid.openstack.network.proxy.entity.Network;
import it.univpm.idstid.openstack.network.proxy.entity.extended.ExtendedPort;
import it.univpm.idstid.openstack.network.proxy.entity.extended.ExtendedPortData;
import it.univpm.idstid.openstack.network.proxy.utility.HTTPConnector;
import it.univpm.idstid.openstack.network.proxy.utility.JsonUtility;
import it.univpm.idstid.openstack.network.proxy.var.OpenstackNetProxyConstants;

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

import com.google.gson.Gson;

@Path("/network")
public class NetworkRestInterface {

	@SuppressWarnings("unused")
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
	@Produces({MediaType.APPLICATION_JSON, OpenstackNetProxyConstants.TYPE_RDF})
	public Response listNetwork(@HeaderParam("Accept") String accept) throws MalformedURLException, IOException{
		if (accept.equals(OpenstackNetProxyConstants.TYPE_RDF)){
			String dataFromKB=NetworkOntology.listNetwork();
			System.out.println(dataFromKB);
			System.out.println(accept);
			//			XmlTester t=new XmlTester();
			return Response.ok().status(200).header("Access-Control-Allow-Origin", "*").entity(dataFromKB).build();
		}
		else{
			//Send HTTP request and receive a list of Json content
			HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath), OpenstackNetProxyConstants.HTTP_METHOD_GET, null);
			String response=HTTPConnector.printStream(conn);
			Object result;
			result=(Network) JsonUtility.fromResponseStringToObject(response, Network.class);
			int responseCode=conn.getResponseCode();
			HTTPConnector.HTTPDisconnect(conn);
			return Response.ok().status(responseCode).header("Access-Control-Allow-Origin", "*").entity(result).build();
		}
	}

	//Show Network
	@GET
	@Path("/v2.0/networks/{networkId}")
	@Produces({MediaType.APPLICATION_JSON, OpenstackNetProxyConstants.TYPE_RDF})
	public Response showNetwork(@PathParam("networkId") String networkId, @HeaderParam("Accept") String accept) throws MalformedURLException, IOException{
		if (accept.equals(OpenstackNetProxyConstants.TYPE_RDF)){
			String dataFromKB=NetworkOntology.showNetwork(networkId);
			System.out.println(dataFromKB);
			System.out.println(accept);
			//			XmlTester t=new XmlTester();
			return Response.ok().status(200).header("Access-Control-Allow-Origin", "*").entity(dataFromKB).build();
		}
		else{
			System.out.println(this.URLpath+"/"+networkId);
			//Send HTTP request and receive a single Json content identified by an ID
			HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath+"/"+networkId), OpenstackNetProxyConstants.HTTP_METHOD_GET, null);
			String response=HTTPConnector.printStream(conn);
			Object result;
			result=(Network) JsonUtility.fromResponseStringToObject(response, Network.class);
			int responseCode=conn.getResponseCode();
			HTTPConnector.HTTPDisconnect(conn);
			return Response.ok().status(responseCode).header("Access-Control-Allow-Origin", "*").entity(result).build();
		}
	}

	//Create Networks
	@POST
	@Path("/v2.0/networks")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNetwork(final String request) throws MalformedURLException, IOException{
		//Convert input object NetworkData into a String like a Json text
		Object net;
		net = JsonUtility.fromResponseStringToObject(request,Network.class);
		String input = JsonUtility.toJsonString(net);
		System.out.println(input);
		//Connect to a REST service
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath), OpenstackNetProxyConstants.HTTP_METHOD_POST, input);
		//Get the response text from the REST service
		String response=HTTPConnector.printStream(conn);
		Object result;
		if(response.equals("Multiple created")) result=response;
		else result=(Network) JsonUtility.fromResponseStringToObject(response, Network.class);
		int responseCode=conn.getResponseCode();
		HTTPConnector.HTTPDisconnect(conn);
		//Insert data into the Knowledge Base
				if (responseCode==201){
					Network n=(Network)result;
					if(n.getNetworks()==null)NetworkOntology.insertNetwork(n, null);
					else NetworkOntology.insertMultipleNetworks(n);
				}
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
		//Success
		if(responseCode==204){
			System.out.println(OpenstackNetProxyConstants.MESSAGE_DELETED_NETWORK_RESOURCE+networkId);
			//Delete resource in the Knowledge Base
						NetworkOntology.deleteNetwork(networkId);
			//Build the response
			return Response.status(responseCode).header("Access-Control-Allow-Origin", "*").entity(OpenstackNetProxyConstants.MESSAGE_DELETED_NETWORK_RESOURCE+networkId).build();
		}
		//Fail
		else return Response.status(responseCode).header("Access-Control-Allow-Origin", "*").entity(OpenstackNetProxyConstants.MESSAGE_FAIL).build();
	}

	//Update Network
	@PUT
	@Path("/v2.0/networks/{networkId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateNetwork(@PathParam("networkId") String networkId, final String request) throws MalformedURLException, IOException{
		Object net;
		net = JsonUtility.fromResponseStringToObject(request,Network.class);
		//Convert input object NetworkData into a String like a Json text
		String input = JsonUtility.toJsonString(net);
		//Connect to a REST service
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath+"/"+networkId), OpenstackNetProxyConstants.HTTP_METHOD_PUT, input);
		//Get the response text from the REST service
		String response=HTTPConnector.printStream(conn);
		Network n=(Network) JsonUtility.fromResponseStringToObject(response, Network.class);
		int responseCode=conn.getResponseCode();
		HTTPConnector.HTTPDisconnect(conn);
		//Update data into the Knowledge Base
		if (responseCode==200){
			NetworkOntology.updateNetwork(n);
		}
		//Build the response
		return Response.status(responseCode).header("Access-Control-Allow-Origin", "*").entity(n).build();
	}

	@OPTIONS
	@Path("/v2.0/networks")
	public Response getOptionValues(@HeaderParam("Access-Control-Request-Headers") String request){
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
				.build();
	}

	@OPTIONS
	@Path("/v2.0/networks/{idNetwork}")
	public Response getOptionValuesPar(@HeaderParam("Access-Control-Request-Headers") String request){
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
				.build();
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
