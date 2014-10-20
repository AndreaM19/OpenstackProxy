package it.univpm.idstid.openstack.network.proxy.utility;

import java.io.IOException;
import java.io.Reader;

public class JsonUtility {

	//Read all content of json received file
	public static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
}
