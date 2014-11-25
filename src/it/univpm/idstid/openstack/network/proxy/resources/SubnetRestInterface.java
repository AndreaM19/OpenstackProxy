package it.univpm.idstid.openstack.network.proxy.resources;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import it.univpm.idstid.openstack.network.parliament.SubnetOntology;
import it.univpm.idstid.openstack.network.proxy.entity.Subnet;
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

@Path("/subnet")
public class SubnetRestInterface {

	@SuppressWarnings("unused")
	private String path_local=OpenstackNetProxyConstants.URL_OPENSTACK+"/subnet/v2.0/subnets";
	private String path_external=OpenstackNetProxyConstants.URL_OPENSTACK+"/v2.0/subnets";
	private String URLpath=path_external;	

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
	@Produces({MediaType.APPLICATION_JSON, OpenstackNetProxyConstants.TYPE_RDF})
	public Response listSubnet(@HeaderParam("Accept") String accept) throws MalformedURLException, IOException{
		if (accept.equals(OpenstackNetProxyConstants.TYPE_RDF)){
			System.out.println(accept);
			String dataFromKB=SubnetOntology.listSubnet();
			System.out.println(dataFromKB);
//			XmlTester t=new XmlTester();
			return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(dataFromKB).build();
		}
		else{
			//Send HTTP request and receive a list of Json content
			HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath), OpenstackNetProxyConstants.HTTP_METHOD_GET, null);
			String response=HTTPConnector.printStream(conn);
			Object result;
			result=(Subnet) JsonUtility.fromResponseStringToObject(response, Subnet.class);
			int responseCode=conn.getResponseCode();
			HTTPConnector.HTTPDisconnect(conn);
			return Response.status(responseCode).entity(result).build();
		}
	}

	//Show Subnet
	@GET
	@Path("/v2.0/subnets/{subnetId}")
	@Produces({MediaType.APPLICATION_JSON, OpenstackNetProxyConstants.TYPE_RDF})
	public Response showSubnet(@PathParam("subnetId") String subnetId, @HeaderParam("Accept") String accept) throws MalformedURLException, IOException{
		if (accept.equals(OpenstackNetProxyConstants.TYPE_RDF)){
			System.out.println(accept);
			String dataFromKB=SubnetOntology.showSubnet(subnetId);
			System.out.println(dataFromKB);
//			XmlTester t=new XmlTester();
			return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(dataFromKB).build();
		}
		else{
			//Send HTTP request and receive a single Json content identified by an ID
			HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath+"/"+subnetId), OpenstackNetProxyConstants.HTTP_METHOD_GET, null);
			String response=HTTPConnector.printStream(conn);
			Object result;
			result=(Subnet) JsonUtility.fromResponseStringToObject(response, Subnet.class);
			int responseCode=conn.getResponseCode();
			HTTPConnector.HTTPDisconnect(conn);
			return Response.status(responseCode).entity(result).build();
		}
	}

	//Create Subnet
	@POST
	@Path("/v2.0/subnets")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createSubnet(final String request) throws MalformedURLException, IOException{
		//Convert input object NetworkData into a String like a Json text
		Object sub;
		sub = JsonUtility.fromResponseStringToObject(request,Subnet.class);
		String input = JsonUtility.toJsonString(sub);
		//Connect to a REST service
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath), OpenstackNetProxyConstants.HTTP_METHOD_POST, input);
		//Get the response text from the REST service
		String response=HTTPConnector.printStream(conn);
		Object result;
		if(response.equals("Multiple created")) result=response;
		else result=(Subnet) JsonUtility.fromResponseStringToObject(response, Subnet.class);
		int responseCode=conn.getResponseCode();
		HTTPConnector.HTTPDisconnect(conn);
		//Insert data into the Knowledge Base
		if (responseCode==201){
			Subnet s=(Subnet)result;
			if(s.getSubnets()==null)SubnetOntology.insertSubnet(s, null);
			else SubnetOntology.insertMultipleSubnets(s);
		}
		//Build the response
		return Response.status(responseCode).header("Access-Control-Allow-Origin", "*").entity(result).build();
	}

	//Delete Subnet
	@DELETE
	@Path("/v2.0/subnets/{subnetId}")
	public Response deleteSubnet(@PathParam("subnetId") String subnetId) throws MalformedURLException, IOException{
		//Send the HTTP request to the REST service
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath+"/"+subnetId), OpenstackNetProxyConstants.HTTP_METHOD_DELETE, null);
		int responseCode=conn.getResponseCode();
		if(responseCode==204){
			System.out.println(OpenstackNetProxyConstants.MESSAGE_DELETED_SUBNET_RESOURCE+subnetId);
			//Delete resource in the Knowledge Base
			SubnetOntology.deleteSubnet(subnetId);
			//Build the response
			return Response.status(responseCode).header("Access-Control-Allow-Origin", "*").entity(OpenstackNetProxyConstants.MESSAGE_DELETED_SUBNET_RESOURCE+subnetId).build();
		}
		else return Response.status(responseCode).header("Access-Control-Allow-Origin", "*").entity(OpenstackNetProxyConstants.MESSAGE_FAIL).build();
	}

	//Update Subnet
	@PUT
	@Path("/v2.0/subnets/{subnetId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateSubnet(@PathParam("subnetId") String subnetId, final String request) throws MalformedURLException, IOException{
		//Convert input object NetworkData into a String like a Json text
		Object sub;
		sub = JsonUtility.fromResponseStringToObject(request,Subnet.class);
		String input = JsonUtility.toJsonString(sub);
		//Connect to a REST service
		HttpURLConnection conn=HTTPConnector.HTTPConnect(new URL(this.URLpath+"/"+subnetId), OpenstackNetProxyConstants.HTTP_METHOD_PUT, input);
		//Get the response text from the REST service
		String response=HTTPConnector.printStream(conn);
		Subnet s=(Subnet) JsonUtility.fromResponseStringToObject(response, Subnet.class);
		int responseCode=conn.getResponseCode();
		HTTPConnector.HTTPDisconnect(conn);
		if (responseCode==200){
			SubnetOntology.updateSubnet(s);
		}
		//Build the response
		return Response.status(responseCode).header("Access-Control-Allow-Origin", "*").entity(s).build();
	}

	@OPTIONS
	@Path("/v2.0/subnets")
	public Response getOptionValues(@HeaderParam("Access-Control-Request-Headers") String request){
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
				.build();
	}

	@OPTIONS
	@Path("/v2.0/subnets/{idSubnet}")
	public Response getOptionValuesPar(@HeaderParam("Access-Control-Request-Headers") String request){
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
				.build();
	}

}
