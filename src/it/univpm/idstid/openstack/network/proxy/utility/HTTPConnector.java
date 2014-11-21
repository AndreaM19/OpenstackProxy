package it.univpm.idstid.openstack.network.proxy.utility;

import it.univpm.idstid.openstack.network.proxy.var.OpenstackNetProxyConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.core.MediaType;

public class HTTPConnector {

	//connection to the URL
	public static HttpURLConnection HTTPConnect(URL urlToConnect, String method, String jsonText) throws IOException{

		HttpURLConnection conn = (HttpURLConnection) urlToConnect.openConnection();
		
		//Set token for open connection to Openstack
		conn.addRequestProperty("X-Auth-Token", OpenstackNetProxyConstants.TOKEN);
		conn.setRequestMethod(method);

		switch (method) {
		case OpenstackNetProxyConstants.HTTP_METHOD_GET:  
			conn.setRequestProperty(OpenstackNetProxyConstants.HTTP_KEY_ACCEPT, MediaType.APPLICATION_JSON);
			break;
		case OpenstackNetProxyConstants.HTTP_METHOD_POST: 
			conn.setDoOutput(true);
			conn.setRequestProperty(OpenstackNetProxyConstants.HTTP_KEY_CONTENT_TYPE, MediaType.APPLICATION_JSON);
			sendJsonContent(jsonText, conn);
			break;
		case OpenstackNetProxyConstants.HTTP_METHOD_PUT: 
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty(OpenstackNetProxyConstants.HTTP_KEY_CONTENT_TYPE, MediaType.APPLICATION_JSON);
			conn.setRequestProperty(OpenstackNetProxyConstants.HTTP_KEY_ACCEPT, MediaType.APPLICATION_JSON);
			sendJsonContent(jsonText, conn);
			break;
		case OpenstackNetProxyConstants.HTTP_METHOD_DELETE:
			break;
		}
		
		//Response code control
		if (conn.getResponseCode()>=200 & conn.getResponseCode()<=206)System.out.println("HTTP Response code: "+conn.getResponseCode());
		else {
			System.out.println("URL: "+conn.getURL());
			throw new RuntimeException(OpenstackNetProxyConstants.MESSAGE_FAIL_HTTP_CONNECTION + conn.getResponseCode());
		}

		return conn;
	}


	//Disconnect http service
	public static void HTTPDisconnect(HttpURLConnection conn){
		conn.disconnect();
	}


	//Send json file to server
	public static void sendJsonContent(String jsonText, HttpURLConnection conn){
		System.out.println("PROXY ===> "+jsonText);
		OutputStream outputStream;
		try {
			outputStream = conn.getOutputStream();
			outputStream.write(jsonText.getBytes());
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Read into the buffer and produce a json file
	public static Object getJsonContent(URL url, String method, Type classToConvert){
		HttpURLConnection conn=null;
		Object obj=null;
		try {
			conn = HTTPConnector.HTTPConnect(url,method, null);
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			//set the json file content
			obj=JsonUtility.fromJsonToObject(br, classToConvert);
			br.close();
			HTTPConnector.HTTPDisconnect(conn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}


	//Print stream output:
	public static String printStream(HttpURLConnection conn){
		BufferedReader responseBuffer;
		String output, response = null;
		try {
			responseBuffer = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
			while ((output = responseBuffer.readLine()) != null) {
				response=output;
				System.out.println("SERVER ===> "+output);
			}
			System.out.println(OpenstackNetProxyConstants.ROW_1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}


}
