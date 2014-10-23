package it.univpm.idstid.openstack.network.proxy.entity;

public class Subnet {

	private String subnetID="-";
	private String subnetName="-";
	private String cidr="-";
	private String allocationPools="-";
	private String ipAdd="-";
	private String networkID="-";
	private String ipVersion="-";
	private String gatewayIP="-";
	private String dnsNameServers="-";
	private String hostRoutes="-";
	private boolean enableDHCP=false;
	private String tenantUuID="-";
	
	public Subnet(){
		
	}
	
	public Subnet(String subnetID, String subnetName, String cidr,
			String allocationPools, String ipAdd, String networkID,
			String ipVersion, String gatewayIP, String dnsNameServers,
			String hostRoutes, boolean enableDHCP, String tenantUuID) {
		this.subnetID = subnetID;
		this.subnetName = subnetName;
		this.cidr = cidr;
		this.allocationPools = allocationPools;
		this.ipAdd = ipAdd;
		this.networkID = networkID;
		this.ipVersion = ipVersion;
		this.gatewayIP = gatewayIP;
		this.dnsNameServers = dnsNameServers;
		this.hostRoutes = hostRoutes;
		this.enableDHCP = enableDHCP;
		this.tenantUuID = tenantUuID;
	}


	//---------------------------------------------------------------
	//Getter and Setter
	//---------------------------------------------------------------
	public String getSubnetID() {
		return subnetID;
	}
	public void setSubnetID(String subnetID) {
		this.subnetID = subnetID;
	}
	public String getSubnetName() {
		return subnetName;
	}
	public void setSubnetName(String subnetName) {
		this.subnetName = subnetName;
	}
	public String getCidr() {
		return cidr;
	}
	public void setCidr(String cidr) {
		this.cidr = cidr;
	}
	public String getAllocationPools() {
		return allocationPools;
	}
	public void setAllocationPools(String allocationPools) {
		this.allocationPools = allocationPools;
	}
	public String getIpAdd() {
		return ipAdd;
	}
	public void setIpAdd(String ipAdd) {
		this.ipAdd = ipAdd;
	}
	public String getNetworkID() {
		return networkID;
	}
	public void setNetworkID(String networkID) {
		this.networkID = networkID;
	}
	public String getIpVersion() {
		return ipVersion;
	}
	public void setIpVersion(String ipVersion) {
		this.ipVersion = ipVersion;
	}
	public String getGatewayIP() {
		return gatewayIP;
	}
	public void setGatewayIP(String gatewayIP) {
		this.gatewayIP = gatewayIP;
	}
	public String getDnsNameServers() {
		return dnsNameServers;
	}
	public void setDnsNameServers(String dnsNameServers) {
		this.dnsNameServers = dnsNameServers;
	}
	public String getHostRoutes() {
		return hostRoutes;
	}
	public void setHostRoutes(String hostRoutes) {
		this.hostRoutes = hostRoutes;
	}
	public boolean isEnableDHCP() {
		return enableDHCP;
	}
	public void setEnableDHCP(boolean enableDHCP) {
		this.enableDHCP = enableDHCP;
	}
	public String getTenantUuID() {
		return tenantUuID;
	}
	public void setTenantUuID(String tenantUuID) {
		this.tenantUuID = tenantUuID;
	}

}
