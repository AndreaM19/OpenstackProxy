package it.univpm.idstid.openstack.network.proxy.entity;

import it.univpm.idstid.openstack.network.proxy.entity.other.objects.AllocationPools;
import it.univpm.idstid.openstack.network.proxy.entity.other.objects.DnsNameServers;
import it.univpm.idstid.openstack.network.proxy.entity.other.objects.HostRoutes;

public class SubnetData {

	private String id;
	private String name;
	private String cidr;
	private AllocationPools allocation_pools;
	private String network_id;
	private String ip_version;
	private String gateway_ip;
	private DnsNameServers dns_name_servers;
	private HostRoutes host_routes;
	private boolean enable_dhcp;
	private String tenant_id;

	public SubnetData(){

	}

	public SubnetData(String id, String name, String cidr,
			AllocationPools allocation_pools, String network_id,
			String ip_version, String gateway_ip,
			DnsNameServers dns_name_servers, HostRoutes host_routes,
			boolean enable_dhcp, String tenant_id) {
		this.id = id;
		this.name = name;
		this.cidr = cidr;
		this.allocation_pools = allocation_pools;
		this.network_id = network_id;
		this.ip_version = ip_version;
		this.gateway_ip = gateway_ip;
		this.dns_name_servers = dns_name_servers;
		this.host_routes = host_routes;
		this.enable_dhcp = enable_dhcp;
		this.tenant_id = tenant_id;
	}

	//---------------------------------------------------------------
	//Getter and Setter
	//---------------------------------------------------------------

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCidr() {
		return cidr;
	}

	public void setCidr(String cidr) {
		this.cidr = cidr;
	}

	public AllocationPools getAllocation_pools() {
		return allocation_pools;
	}

	public void setAllocation_pools(AllocationPools allocation_pools) {
		this.allocation_pools = allocation_pools;
	}

	public String getNetwork_id() {
		return network_id;
	}

	public void setNetwork_id(String network_id) {
		this.network_id = network_id;
	}

	public String getIp_version() {
		return ip_version;
	}

	public void setIp_version(String ip_version) {
		this.ip_version = ip_version;
	}

	public String getGateway_ip() {
		return gateway_ip;
	}

	public void setGateway_ip(String gateway_ip) {
		this.gateway_ip = gateway_ip;
	}

	public DnsNameServers getDns_name_servers() {
		return dns_name_servers;
	}

	public void setDns_name_servers(DnsNameServers dns_name_servers) {
		this.dns_name_servers = dns_name_servers;
	}

	public HostRoutes getHost_routes() {
		return host_routes;
	}

	public void setHost_routes(HostRoutes host_routes) {
		this.host_routes = host_routes;
	}

	public boolean isEnable_dhcp() {
		return enable_dhcp;
	}

	public void setEnable_dhcp(boolean enable_dhcp) {
		this.enable_dhcp = enable_dhcp;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}



}
