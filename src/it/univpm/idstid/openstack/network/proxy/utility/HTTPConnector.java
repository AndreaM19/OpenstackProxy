package it.univpm.idstid.openstack.network.proxy.utility;

import it.univpm.idstid.openstack.network.proxy.var.OpenstackNetProxyConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class HTTPConnector {

	//connection to the URL
	public static HttpURLConnection HTTPConnect(URL urlToConnect, String method, String responseType, String httpKey, String jsonText) throws IOException{
		HttpURLConnection conn = (HttpURLConnection) urlToConnect.openConnection();
		if(method.equals(OpenstackNetProxyConstants.HTTP_METHOD_POST))conn.setDoOutput(true);
		conn.setRequestMethod(method);
		//HTTP Keys: "Accept" for GET, "Content-Type" for POST and PUT
		if(responseType!=null & httpKey!=null)conn.setRequestProperty(httpKey, responseType);
		
		if(method.equals(OpenstackNetProxyConstants.HTTP_METHOD_POST))sendJsonContent(jsonText, conn);
			
//		Response code control
		if (conn.getResponseCode()>=200 & conn.getResponseCode()<=206);
		else throw new RuntimeException(OpenstackNetProxyConstants.MESSAGE_FAIL_HTTP_CONNECTION + conn.getResponseCode());
		
		return conn;
	}

	//disconnect http service
	public static void HTTPDisconnect(HttpURLConnection conn){
		conn.disconnect();
	}
	
	//Send json file to server
	public static void sendJsonContent(String jsonText, HttpURLConnection conn){
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
	public static JSONObject getJsonResponse(URL url, String method, String responseType, String httpKey){
		HttpURLConnection conn=null;
		JSONObject json=null;
		try {
			conn = HTTPConnector.HTTPConnect(url,method,responseType, httpKey, null);
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
	
	//Read into the buffer and produce a json file
		public static Object getJsonContent(URL url, String method, String responseType, String httpKey, Type classToConvert){
			HttpURLConnection conn=null;
			Object obj=null;
			try {
				conn = HTTPConnector.HTTPConnect(url,method,responseType, httpKey, null);
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
			System.out.println("Output from Server:\n");
			while ((output = responseBuffer.readLine()) != null) {
				response=output;
				System.out.println("===> "+output);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}


}
