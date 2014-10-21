package it.univpm.idstid.openstack.network.proxy.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import it.univpm.idstid.openstack.network.proxy.entity.Port;
import it.univpm.idstid.openstack.network.proxy.entity.Subnet;
import it.univpm.idstid.openstack.network.proxy.utility.HTTPConnector;
import it.univpm.idstid.openstack.network.proxy.utility.JsonUtility;
import it.univpm.idstid.openstack.network.proxy.var.OpenstackNetProxyConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

@Path("/subnet")
public class SubnetRestInterface {
	
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
	public Subnet showNetwork(@PathParam("subnetId") String subnetId){
		HttpURLConnection conn=null;
		JSONObject json=null;
		try {
			conn = HTTPConnector.HTTPConnect(new URL(""), OpenstackNetProxyConstants.HTTP_METHOD_GET, OpenstackNetProxyConstants.TYPE_JSON);
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String jsonText=JsonUtility.readAll(br);
			//set the json file content
			json = new JSONObject(jsonText);
			br.close();
			HTTPConnector.HTTPDisconnect(conn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Active parsing of the json file and receive a Subnet object
		Subnet sub=JsonUtility.SubnetJsonParser(json);
		return sub;
	}
}
