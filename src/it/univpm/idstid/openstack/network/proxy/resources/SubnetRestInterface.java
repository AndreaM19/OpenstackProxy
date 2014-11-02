package it.univpm.idstid.openstack.network.proxy.resources;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import it.univpm.idstid.openstack.network.proxy.entity.Subnet;
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

@Path("/subnet")
public class SubnetRestInterface {

	private String URLpath=OpenstackNetProxyConstants.URL_OPENSTACK+"/subnet/v2.0/subnets/";

	//This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHtml() {
		return OpenstackNetProxyConstants.HTML_RESOURCE_MESSAGE_SUBNET;
	}

	//Test if the resource is active
	@GET
	@Path("/test")
	@Produces("text/plain")
	public String resourceTest(){
		return OpenstackNetProxyConstants.MESSAGE_TEST;
	}

	//List Subnets
	@GET
	@Path("/v2.0/subnets")
	@Produces(MediaType.APPLICATION_JSON)
	public Subnet listSubnet() throws MalformedURLException{
		//Send HTTP request and receive a list of Json content
		Object ob=HTTPConnector.getJsonContent(new URL(this.URLpath), OpenstackNetProxyConstants.HTTP_METHOD_GET, Subnet.class);
		Subnet sub=(Subnet)ob;
		return sub;
	}

	//Show Subnet
	@GET
	@Path("/v2.0/subnets/{subnetId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Subnet showNetwork(@PathParam("subnetId") String subnetId) throws MalformedURLException{
		//Send HTTP request and receive a single Json content identified by an ID
		Object ob=HTTPConnector.getJsonContent(new URL(this.URLpath+subnetId), OpenstackNetProxyConstants.HTTP_METHOD_GET, Subnet.class);
		Subnet sub=(Subnet)ob;
		return sub;
	}

	//Create Subnet
	@POST
	@Path("/v2.0/subnets")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createSubnet(final Subnet sub) throws MalformedURLException, IOException{
		//Convert input object NetworkData into a String like a Json text
		String input = JsonUtility.toJsonString(sub);
		//Connect to a REST service
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath), OpenstackNetProxyConstants.HTTP_METHOD_POST, input);
		//Get the response text from the REST service
		String response=HTTPConnector.printStream(conn);
		Object result;
		if(response.equals("Multiple created")) result=response;
		else result=(Subnet) JsonUtility.fromResponseStringToObject(response, Subnet.class);
		HTTPConnector.HTTPDisconnect(conn);
		//Build the response
		return Response.status(201).entity(result).build();
	}

	//Delete Subnet
	@DELETE
	@Path("/v2.0/subnets/{subnetId}")
	public Response deleteNetwork(@PathParam("subnetId") String subnetId) throws MalformedURLException, IOException{
		//Send the HTTP request to the REST service
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath+subnetId), OpenstackNetProxyConstants.HTTP_METHOD_DELETE, null);
		if(conn.getResponseCode()==204){
			System.out.println(OpenstackNetProxyConstants.MESSAGE_DELETED_SUBNET_RESOURCE+subnetId);
			return Response.status(204).entity(OpenstackNetProxyConstants.MESSAGE_DELETED_SUBNET_RESOURCE+subnetId).build();
		}
		else return Response.status(conn.getResponseCode()).entity(OpenstackNetProxyConstants.MESSAGE_FAIL).build();
	}

	//Update Subnet
	@PUT
	@Path("/v2.0/subnets/{subnetId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSubnet(@PathParam("subnetId") String subnetId, final Subnet sub) throws MalformedURLException, IOException{
		//Convert input object NetworkData into a String like a Json text
		String input = JsonUtility.toJsonString(sub);
		//Connect to a REST service
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath+subnetId), OpenstackNetProxyConstants.HTTP_METHOD_PUT, input);
		//Get the response text from the REST service
		String response=HTTPConnector.printStream(conn);
		Subnet s=(Subnet) JsonUtility.fromResponseStringToObject(response, Subnet.class);
		HTTPConnector.HTTPDisconnect(conn);
		//Build the response
		return Response.status(200).entity(s).build();
	}
}
