package it.univpm.idstid.openstack.network.proxy.var;

public class OpenstackNetProxyConstants {
		
	//Application messages
	public static final String MESSAGE_TEST="OK";
	public static final String MESSAGE_DONE_WITH_SUCCESS="Done";
	public static final String MESSAGE_FAIL="Fail";
	public static final String MESSAGE_FAIL_HTTP_CONNECTION="Failed : HTTP error code : ";
	public static final String MESSAGE_DELETED_RESOURCE="Deleted: ";
	
	//HTTP methods
	public static final String HTTP_METHOD_GET="GET";
	public static final String HTTP_METHOD_POST="POST";
	public static final String HTTP_METHOD_PUT="PUT";
	public static final String HTTP_METHOD_DELETE="DELETE";
	
	//Response/request types
	public static final String TYPE_JSON="application/json";
	
	//HTML elements
	public static final String HTML_RESOURCE_MESSAGE_NETWORK="<html> " + "<title>" + "Network" + "</title>"+ "<body><h1>" + "Network section" + "</h1></body>" + "</html> ";
	public static final String HTML_RESOURCE_MESSAGE_SUBNET="<html> " + "<title>" + "Subnet" + "</title>"+ "<body><h1>" + "Subnet section" + "</h1></body>" + "</html> ";
	public static final String HTML_RESOURCE_MESSAGE_PORT="<html> " + "<title>" + "Port" + "</title>"+ "<body><h1>" + "Port section" + "</h1></body>" + "</html> ";
	public static final String HTML_RESOURCE_MESSAGE_QUOTA="<html> " + "<title>" + "Quota" + "</title>"+ "<body><h1>" + "Quota section" + "</h1></body>" + "</html> ";

}
