package it.univpm.idstid.openstack.network.proxy.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

import com.google.gson.Gson;

public class JsonUtility {

	//Read all content of the json received file
	public static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	
	//-----------------------------------------------------------------------------------
	// Converters
	//-----------------------------------------------------------------------------------
	
	public static String toJsonString(Object toConvert){
		Gson gson = new Gson();
		// convert java object to JSON format,
		// and returned as JSON formatted string
		String json = gson.toJson(toConvert);
		return json;
	}
	
	public static Object fromJsonToObject(BufferedReader br, Type classToconvert){
		Gson gson = new Gson();
		//convert the json string into an object
		Object objectConverted=gson.fromJson(br, classToconvert);
		return objectConverted;
	}

}
