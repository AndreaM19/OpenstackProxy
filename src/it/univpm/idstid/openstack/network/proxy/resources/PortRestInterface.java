package it.univpm.idstid.openstack.network.proxy.resources;

import java.io.IOException;
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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("/port")
public class PortRestInterface {

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

	//Show Ports
	@GET
	@Path("/v2.0/ports/{portId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Port showPort(@PathParam("portId") String portId) throws MalformedURLException{	
		JSONObject json=HTTPConnector.getJsonResponse(new URL(""+portId), OpenstackNetProxyConstants.HTTP_METHOD_GET, OpenstackNetProxyConstants.TYPE_JSON);

		//Active parsing of the json file and receive a Port object
		Port port=JsonUtility.PortJsonParser(json);
		return port;
	}

	//Create Port
	@POST
	@Path("/v2.0/port")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPort(final Port port){
		String result = "Product created : " + port.getPortName();
		System.out.println(result);//print resource name in the server console
		return Response.status(201).entity(result).build();
	}

	//Delete Port
	@DELETE
	@Path("/v2.0/ports/{portId}")
	public void deleteNetwork(@PathParam("portId") String portId) throws MalformedURLException, IOException{
		HTTPConnector.HTTPConnect(new URL(""+portId), OpenstackNetProxyConstants.HTTP_METHOD_DELETE, null);
		System.out.println(OpenstackNetProxyConstants.MESSAGE_DELETED_RESOURCE+portId);
	}
}
