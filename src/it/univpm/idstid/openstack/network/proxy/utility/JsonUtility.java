package it.univpm.idstid.openstack.network.proxy.utility;

import it.univpm.idstid.openstack.network.proxy.entity.Network;
import it.univpm.idstid.openstack.network.proxy.entity.Port;
import it.univpm.idstid.openstack.network.proxy.entity.Subnet;
import it.univpm.idstid.openstack.network.proxy.entity.Test;

import java.io.IOException;
import java.io.Reader;

import org.json.JSONObject;

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
	// Parsers
	//-----------------------------------------------------------------------------------

	//Parser for Network entity
	public static Network NetJsonParser(JSONObject json){
		Network net=new Network();
		//Parsing the received Json file
		net.setAdminStateUp(json.getBoolean("adminStateUp"));
		net.setAllocationPools(json.getString("allocationPools"));
		net.setIpAdd(json.getString("ipAdd"));
		net.setNetworkID(json.getString("networkID"));
		net.setNetworkName(json.getString("networkName"));
		net.setShared(json.getBoolean("shared"));
		net.setStatus(json.getString("status"));
		net.setSubnets(json.getString("subnets"));
		net.setTenantUuID(json.getString("tenantUuID"));
		return net;
	}

	//Parser for Subnet entity
	public static Subnet SubnetJsonParser(JSONObject json){
		Subnet subnet=new Subnet();
		//Parsing the received Json file		
		subnet.setAllocationPools(json.getString("allocationPools"));
		subnet.setCidr(json.getString("cidr"));
		subnet.setDnsNameServers(json.getString("dnsNameServers"));
		subnet.setEnableDHCP(json.getBoolean("enableDHCP"));
		subnet.setGatewayIP(json.getString("gatewayIP"));
		subnet.setHostRoutes(json.getString("hostRoutes"));
		subnet.setIpAdd(json.getString("ipAdd"));
		subnet.setIpVersion(json.getString("ipVersion"));
		subnet.setNetworkID(json.getString("networkID"));
		subnet.setSubnetID(json.getString("subnetID"));
		subnet.setSubnetName(json.getString("subnetName"));
		subnet.setTenantUuID(json.getString("tenantUuID"));
		return subnet;
	}

	//Parser for Port entity
	public static Port PortJsonParser(JSONObject json){
		Port port=new Port();
		//Parsing the received Json file		
		port.setAdminStateUp(json.getBoolean("adminStateUp"));
		port.setDeviceID(json.getString("deviceID"));
		port.setDeviceOwner(json.getString("deviceOwner"));
		port.setFixedIPs(json.getString("fixedIPs"));
		port.setIpAdd(json.getString("ipAdd"));
		port.setMacAddress(json.getString("macAddress"));
		port.setNetworkID(json.getString("networkID"));
		port.setPortID(json.getString("portID"));
		port.setPortName(json.getString("portName"));
		port.setSecurityGroups(json.getString("securityGroups"));
		port.setStatus(json.getString("status"));
		port.setTenantUuID(json.getString("tenantUuID"));
		return port;
	}

	//Parser for Test entity
	public static Test TestJsonParser(JSONObject json){
		Test t=new Test();
		//Parsing the received Json file		
		t.setTestID(json.getString("testID"));
		t.setTestName(json.getString("testName"));
		t.setTestFlag(json.getBoolean("testFlag"));
		return t;
	}

}
