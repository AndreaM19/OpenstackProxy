package it.univpm.idstid.openstack.network.proxy.entity;

public class QuotaData {
	
	private String tenantId="-";
	private int subnet=0;
	private int router=0;
	private int network=0;
	private int port=0;
	private int floatingIp=0;
	
	public QuotaData(){
		
	}

	public QuotaData(String tenantId, int subnet, int router, int network,
			int port, int floatingIp) {
		this.tenantId = tenantId;
		this.subnet = subnet;
		this.router = router;
		this.network = network;
		this.port = port;
		this.floatingIp = floatingIp;
	}
	
	//---------------------------------------------------------------
	//Getter and Setter
	//---------------------------------------------------------------

	
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	public int getSubnet() {
		return subnet;
	}
	public void setSubnet(int subnet) {
		this.subnet = subnet;
	}
	public int getRouter() {
		return router;
	}
	public void setRouter(int router) {
		this.router = router;
	}
	public int getNetwork() {
		return network;
	}
	public void setNetwork(int network) {
		this.network = network;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getFloatingIp() {
		return floatingIp;
	}
	public void setFloatingIp(int floatingIp) {
		this.floatingIp = floatingIp;
	}

}
