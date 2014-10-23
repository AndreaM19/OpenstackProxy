package it.univpm.idstid.openstack.network.proxy.resources;

import java.io.IOException;

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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("/subnet")
public class SubnetRestInterface {

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

	//Show Subnets
	@GET
	@Path("/v2.0/subnets/{subnetId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Subnet showNetwork(@PathParam("subnetId") String subnetId) throws MalformedURLException{
		JSONObject json=HTTPConnector.getJsonResponse(new URL(""+subnetId), OpenstackNetProxyConstants.HTTP_METHOD_GET, OpenstackNetProxyConstants.TYPE_JSON);

		//Active parsing of the json file and receive a Subnet object
		Subnet sub=JsonUtility.SubnetJsonParser(json);
		return sub;
	}

	//Create Subnet
	@POST
	@Path("/v2.0/subnet")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createSubnet(final Subnet sub){
		String result = "Product created : " + sub.getSubnet().getSubnetName();
		System.out.println(result);//print resource name in the server console
		return Response.status(201).entity(result).build();
	}

	//Delete Subnet
	@DELETE
	@Path("/v2.0/subnets/{subnetId}")
	public void deleteNetwork(@PathParam("subnetId") String subnetId) throws MalformedURLException, IOException{
		HTTPConnector.HTTPConnect(new URL(""+subnetId), OpenstackNetProxyConstants.HTTP_METHOD_DELETE, null);
		System.out.println(OpenstackNetProxyConstants.MESSAGE_DELETED_RESOURCE+subnetId);
	}
}
