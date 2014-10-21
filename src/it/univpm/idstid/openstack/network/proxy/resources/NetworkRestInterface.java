package it.univpm.idstid.openstack.network.proxy.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import it.univpm.idstid.openstack.network.proxy.entity.Network;
import it.univpm.idstid.openstack.network.proxy.entity.Test;
import it.univpm.idstid.openstack.network.proxy.utility.HTTPConnector;
import it.univpm.idstid.openstack.network.proxy.utility.JsonUtility;
import it.univpm.idstid.openstack.network.proxy.var.OpenstackNetProxyConstants;

import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

@Path("/network")
public class NetworkRestInterface {
	
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
		HttpURLConnection conn=null;
		JSONObject json=null;
		try {
			conn = HTTPConnector.HTTPConnect(new URL("http://localhost:8080/OpenstackProxy/test/resourcetester"), OpenstackNetProxyConstants.HTTP_METHOD_GET, OpenstackNetProxyConstants.TYPE_JSON);
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			System.out.println("Output from Server .... \n");
			String jsonText=JsonUtility.readAll(br);
			json = new JSONObject(jsonText);
			System.out.println(json.toString());
			br.close();
			HTTPConnector.HTTPDisconnect(conn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Network net= new Network();

		net.setIpAdd(json.getString("portID"));
		net.setAdminStateUp(json.getBoolean("adminStateUp"));
		net.setSubnets("subnets");
		System.out.println("prova console");
		return net;
	}

	//Show Networks
	@GET
	@Path("/v2.0/networks/{networkId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Network showNetwork(@PathParam("networkId") String networkId){
		
		HttpURLConnection conn=null;
		JSONObject json=null;
		try {
			conn = HTTPConnector.HTTPConnect(new URL("http://localhost:8080/OpenstackProxy/proxy/test/resource"), OpenstackNetProxyConstants.HTTP_METHOD_GET, OpenstackNetProxyConstants.TYPE_JSON);
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
		
		//Active parsing of the json file and receive a Network object
		//Network net=JsonUtility.NetJsonParser(json);
		Network net=null;
		Test t=JsonUtility.TestJsonParser(json);
//		net.setNetworkID(networkId);
		System.out.println(t.getTestID());
		System.out.println(t.getTestName());
		System.out.println(t.getTestFlag());
		return net;
	}

	//Create Networks
	@POST
	@Path("/v2.0/{network}")
	@Produces(MediaType.APPLICATION_JSON)
	public String createNetwork(@PathParam("network") String networkId){
		Json networkData;//This json file is for network creation
		//tempFlag is used for process emulation
		boolean tempFlag=true;
		if(tempFlag)return OpenstackNetProxyConstants.MESSAGE_DONE_WITH_SUCCESS;
		else return OpenstackNetProxyConstants.MESSAGE_FAIL;
	}

	

}
