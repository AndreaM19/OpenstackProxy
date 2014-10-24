package it.univpm.idstid.openstack.network.proxy.utility;

import it.univpm.idstid.openstack.network.proxy.var.OpenstackNetProxyConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class HTTPConnector {

	//connection to the URL
	public static HttpURLConnection HTTPConnect(URL urlToConnect, String method, String responseType) throws IOException{
		HttpURLConnection conn = (HttpURLConnection) urlToConnect.openConnection();
		conn.setRequestMethod(method);
		if(responseType!=null)conn.setRequestProperty("Accept", responseType);
		
		//Response code control
		if (conn.getResponseCode()>=200 & conn.getResponseCode()<=206);
		else throw new RuntimeException(OpenstackNetProxyConstants.MESSAGE_FAIL_HTTP_CONNECTION + conn.getResponseCode());
		
		return conn;
	}

	//disconnect http service
	public static void HTTPDisconnect(HttpURLConnection conn){
		conn.disconnect();
	}
	
	//Read into the buffer and produce a json file
	public static JSONObject getJsonResponse(URL url, String method, String responseType){
		HttpURLConnection conn=null;
		JSONObject json=null;
		try {
			conn = HTTPConnector.HTTPConnect(url,method,responseType);
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
		return json;
	}


}
