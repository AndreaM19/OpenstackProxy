package it.univpm.idstid.openstack.network.proxy.utility;

import it.univpm.idstid.openstack.network.proxy.entity.Network;
import it.univpm.idstid.openstack.network.proxy.entity.NetworkData;
import it.univpm.idstid.openstack.network.proxy.entity.Port;
import it.univpm.idstid.openstack.network.proxy.entity.PortData;
import it.univpm.idstid.openstack.network.proxy.entity.Quota;
import it.univpm.idstid.openstack.network.proxy.entity.QuotaData;
import it.univpm.idstid.openstack.network.proxy.entity.Subnet;
import it.univpm.idstid.openstack.network.proxy.entity.SubnetData;
import it.univpm.idstid.openstack.network.proxy.entity.Test;
import it.univpm.idstid.openstack.network.proxy.entity.TestData;

import java.io.IOException;
import java.io.Reader;

import org.json.JSONObject;

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
	// Parsers
	//-----------------------------------------------------------------------------------

	//Parser for Network entity
	public static Network NetJsonParser(JSONObject json){
		Network net=new Network(new NetworkData());
		JSONObject j=new JSONObject(json.get("network").toString());
		//Parsing the received Json file
		net.getNetwork().setAdminStateUp(j.getBoolean("adminStateUp"));
		net.getNetwork().setAllocationPools(j.getString("allocationPools"));
		net.getNetwork().setIpAdd(j.getString("ipAdd"));
		net.getNetwork().setNetworkID(j.getString("networkID"));
		net.getNetwork().setNetworkName(j.getString("networkName"));
		net.getNetwork().setShared(j.getBoolean("shared"));
		net.getNetwork().setStatus(j.getString("status"));
		net.getNetwork().setSubnets(j.getString("subnets"));
		net.getNetwork().setTenantUuID(j.getString("tenantUuID"));
		return net;
	}

	//Parser for Subnet entity
	public static Subnet SubnetJsonParser(JSONObject json){
		Subnet subnet=new Subnet(new SubnetData());
		JSONObject j=new JSONObject(json.get("subnet").toString());
		//Parsing the received Json file		
		subnet.getSubnet().setAllocationPools(j.getString("allocationPools"));
		subnet.getSubnet().setCidr(j.getString("cidr"));
		subnet.getSubnet().setDnsNameServers(j.getString("dnsNameServers"));
		subnet.getSubnet().setEnableDHCP(j.getBoolean("enableDHCP"));
		subnet.getSubnet().setGatewayIP(j.getString("gatewayIP"));
		subnet.getSubnet().setHostRoutes(j.getString("hostRoutes"));
		subnet.getSubnet().setIpAdd(j.getString("ipAdd"));
		subnet.getSubnet().setIpVersion(j.getString("ipVersion"));
		subnet.getSubnet().setNetworkID(j.getString("networkID"));
		subnet.getSubnet().setSubnetID(j.getString("subnetID"));
		subnet.getSubnet().setSubnetName(j.getString("subnetName"));
		subnet.getSubnet().setTenantUuID(j.getString("tenantUuID"));
		return subnet;
	}

	//Parser for Port entity
	public static Port PortJsonParser(JSONObject json){
		Port port=new Port(new PortData());
		JSONObject j=new JSONObject(json.get("port").toString());
		//Parsing the received Json file		
		port.getPort().setAdminStateUp(j.getBoolean("adminStateUp"));
		port.getPort().setDeviceID(j.getString("deviceID"));
		port.getPort().setDeviceOwner(j.getString("deviceOwner"));
		port.getPort().setFixedIPs(j.getString("fixedIPs"));
		port.getPort().setIpAdd(j.getString("ipAdd"));
		port.getPort().setMacAddress(j.getString("macAddress"));
		port.getPort().setNetworkID(j.getString("networkID"));
		port.getPort().setPortID(j.getString("portID"));
		port.getPort().setPortName(j.getString("portName"));
		port.getPort().setSecurityGroups(j.getString("securityGroups"));
		port.getPort().setStatus(j.getString("status"));
		port.getPort().setTenantUuID(j.getString("tenantUuID"));
		return port;
	}

	//Parser for Quota entity
	public static Quota QuotaJsonParser(JSONObject json){
		Quota quota=new Quota(new QuotaData());
		JSONObject j=new JSONObject(json.get("port").toString());
		//Parsing the received Json file		
		quota.getQuota().setFloatingIp(j.getInt("floatingIp"));
		quota.getQuota().setNetwork(j.getInt("network"));
		quota.getQuota().setPort(j.getInt("port"));
		quota.getQuota().setRouter(j.getInt("router"));
		quota.getQuota().setSubnet(j.getInt("subnet"));
		quota.getQuota().setTenantId(j.getString("tenantId"));
		return quota;
	}

	//Parser for Test entity
	public static Test TestJsonParser(JSONObject json){
		Test t=new Test(new TestData());
		JSONObject j=new JSONObject(json.get("test").toString());
		t.getTest().setTestID(j.getString("testID"));
		t.getTest().setTestName(j.getString("testName"));
		t.getTest().setTestFlag(j.getBoolean("testFlag"));
		return t;
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

}
