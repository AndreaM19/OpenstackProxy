package it.univpm.idstid.openstack.network.proxy.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import it.univpm.idstid.openstack.network.proxy.entity.Port;
import it.univpm.idstid.openstack.network.proxy.utility.HTTPConnector;
import it.univpm.idstid.openstack.network.proxy.utility.JsonUtility;
import it.univpm.idstid.openstack.network.proxy.var.OpenstackNetProxyConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

@Path("/port")
public class PortRestInterface {
	
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
		JSONObject json=HTTPConnector.getJsonResponse(new URL(""), OpenstackNetProxyConstants.HTTP_METHOD_GET, OpenstackNetProxyConstants.TYPE_JSON);
		
		//Active parsing of the json file and receive a Port object
		Port port=JsonUtility.PortJsonParser(json);
		return port;
	}
}
