package it.univpm.idstid.openstack.network.proxy.resources;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import it.univpm.idstid.openstack.network.proxy.entity.Network;
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

@Path("/network")
public class NetworkRestInterface {

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
	public Network listNetwork(){
		Network net= new Network();
		return net;
	}

	//Show Networks
	@GET
	@Path("/v2.0/networks/{networkId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Network showNetwork(@PathParam("networkId") String networkId) throws MalformedURLException{

		JSONObject json=HTTPConnector.getJsonResponse(new URL("http://localhost:8080/OpenstackTester/tester/v2.0/networks/"+networkId), OpenstackNetProxyConstants.HTTP_METHOD_GET, OpenstackNetProxyConstants.TYPE_JSON);

		//Active parsing of the json file and receive a Network object
		Network net=JsonUtility.NetJsonParser(json);
		//		Network net=null;
		//		Test t=JsonUtility.TestJsonParser(json);
		//		System.out.println(t.getTestID());
		//		System.out.println(t.getTestName());
		//		System.out.println(t.getTestFlag());
		return net;
	}

	//Create Networks
	@POST
	@Path("/v2.0/network")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createNetwork(final Network net){
		String result = "Product created : " + net.getNetworkName();
		System.out.println(result);//print resource name in the server console
		return Response.status(201).entity(result).build();
	}

	//Delete Network
	@DELETE
	@Path("/v2.0/networks/{networkId}")
	public void deleteNetwork(@PathParam("networkId") String networkId) throws MalformedURLException, IOException{
		HTTPConnector.HTTPConnect(new URL(""+networkId), OpenstackNetProxyConstants.HTTP_METHOD_DELETE, null);
		System.out.println(OpenstackNetProxyConstants.MESSAGE_DELETED_RESOURCE+networkId);
	}
}
