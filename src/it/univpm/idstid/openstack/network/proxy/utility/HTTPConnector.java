package it.univpm.idstid.openstack.network.proxy.utility;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPConnector {

	//connection to the URL
	public static HttpURLConnection HTTPConnect(URL urlToConnect, String method, String responseType) throws IOException{
		HttpURLConnection conn = (HttpURLConnection) urlToConnect.openConnection();
		conn.setRequestMethod(method);
		conn.setRequestProperty("Accept", responseType);	
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}
		return conn;
	}

	//disconnect http service
	public static void HTTPDisconnect(HttpURLConnection conn){
		conn.disconnect();
	}


}
