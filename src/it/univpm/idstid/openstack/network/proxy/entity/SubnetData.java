package it.univpm.idstid.openstack.network.proxy.entity;

import it.univpm.idstid.openstack.network.proxy.entity.other.objects.AllocationPools;
import it.univpm.idstid.openstack.network.proxy.entity.other.objects.DnsNameServers;
import it.univpm.idstid.openstack.network.proxy.entity.other.objects.HostRoutes;

public class SubnetData {
	
	private String subnetID;
	private String subnetName;
	private String cidr;
	private AllocationPools allocationPools;
	private String ipAdd;
	private String networkID;
	private String ipVersion;
	private String gatewayIP;
	private DnsNameServers dnsNameServers;
	private HostRoutes hostRoutes;
	private boolean enableDHCP;
	private String tenantUuID;
	
	public SubnetData(){
		
	}
	
	public SubnetData(String subnetID, String subnetName, String cidr,
			AllocationPools allocationPools, String ipAdd, String networkID,
			String ipVersion, String gatewayIP, DnsNameServers dnsNameServers,
			HostRoutes hostRoutes, boolean enableDHCP, String tenantUuID) {
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
	public AllocationPools getAllocationPools() {
		return allocationPools;
	}
	public void setAllocationPools(AllocationPools allocationPools) {
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
	public DnsNameServers getDnsNameServers() {
		return dnsNameServers;
	}
	public void setDnsNameServers(DnsNameServers dnsNameServers) {
		this.dnsNameServers = dnsNameServers;
	}
	public HostRoutes getHostRoutes() {
		return hostRoutes;
	}
	public void setHostRoutes(HostRoutes hostRoutes) {
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
