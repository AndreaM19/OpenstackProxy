package it.univpm.idstid.openstack.network.proxy.resources;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import it.univpm.idstid.openstack.network.proxy.entity.Port;
import it.univpm.idstid.openstack.network.proxy.utility.HTTPConnector;
import it.univpm.idstid.openstack.network.proxy.utility.JsonUtility;
import it.univpm.idstid.openstack.network.proxy.var.OpenstackNetProxyConstants;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/port")
public class PortRestInterface {

	private String URLpath=OpenstackNetProxyConstants.URL_OPENSTACK+"/port/v2.0/ports/";

	//This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHtml() {
		return OpenstackNetProxyConstants.HTML_RESOURCE_MESSAGE_PORT;
	}

	//Test if the resource is active
	@GET
	@Path("/test")
	@Produces("text/plain")
	public String resourceTest(){
		return OpenstackNetProxyConstants.MESSAGE_TEST;
	}

	//List Ports
	@GET
	@Path("/v2.0/ports")
	@Produces(MediaType.APPLICATION_JSON)
	public Port listPort() throws MalformedURLException{
		//Send HTTP request and receive a list of Json content
		Object ob=HTTPConnector.getJsonContent(new URL(this.URLpath), OpenstackNetProxyConstants.HTTP_METHOD_GET, Port.class);
		Port port=(Port)ob;
		return port;
	}

	//Show Port
	@GET
	@Path("/v2.0/ports/{portId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Port showPort(@PathParam("portId") String portId) throws MalformedURLException{	
		//Send HTTP request and receive a single Json content identified by an ID
		Object ob=HTTPConnector.getJsonContent(new URL(this.URLpath+portId), OpenstackNetProxyConstants.HTTP_METHOD_GET, Port.class);
		Port port=(Port)ob;
		return port;
	}

	//Create Port
	@POST
	@Path("/v2.0/ports")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPort(final Port port) throws MalformedURLException, IOException{
		//Convert input object NetworkData into a String like a Json text
		String input = JsonUtility.toJsonString(port);
		//Connect to a REST service
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath), OpenstackNetProxyConstants.HTTP_METHOD_POST, input);
		//Get the response text from the REST service
		String response=HTTPConnector.printStream(conn);
		Object result;
		if(response.equals("Multiple created")) result=response;
		else result=(Port) JsonUtility.fromResponseStringToObject(response, Port.class);
		HTTPConnector.HTTPDisconnect(conn);
		//Build the response
		return Response.status(201).entity(result).build();
	}

	//Delete Port
	@DELETE
	@Path("/v2.0/ports/{portId}")
	public Response deleteNetwork(@PathParam("portId") String portId) throws MalformedURLException, IOException{
		//Send the HTTP request to the REST service
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath+portId), OpenstackNetProxyConstants.HTTP_METHOD_DELETE, null);
		if(conn.getResponseCode()==204){
			System.out.println(OpenstackNetProxyConstants.MESSAGE_DELETED_PORT_RESOURCE+portId);
			return Response.status(204).entity(OpenstackNetProxyConstants.MESSAGE_DELETED_PORT_RESOURCE+portId).build();
		}
		else return Response.status(conn.getResponseCode()).entity(OpenstackNetProxyConstants.MESSAGE_FAIL).build();
	}

	//Update Port
	@PUT
	@Path("/v2.0/ports/{portId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePort(@PathParam("networkId") String portId, final Port port) throws MalformedURLException, IOException{
		//Convert input object NetworkData into a String like a Json text
		String input = JsonUtility.toJsonString(port);
		//Connect to a REST service
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath+portId), OpenstackNetProxyConstants.HTTP_METHOD_PUT, input);
		//Get the response text from the REST service
		String response=HTTPConnector.printStream(conn);
		Port p=(Port) JsonUtility.fromResponseStringToObject(response, Port.class);
		HTTPConnector.HTTPDisconnect(conn);
		//Build the response
		return Response.status(200).entity(p).build();
	}
}
