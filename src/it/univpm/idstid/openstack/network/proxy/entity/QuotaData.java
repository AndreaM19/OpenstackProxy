package it.univpm.idstid.openstack.network.proxy.entity;

public class QuotaData {

	private String tenant_id;
	private int subnet;
	private int router;
	private int network;
	private int port;
	private int floatingip;

	public QuotaData(){

	}

	public QuotaData(String tenant_id, int subnet, int router, int network,
			int port, int floatingip) {
		this.tenant_id = tenant_id;
		this.subnet = subnet;
		this.router = router;
		this.network = network;
		this.port = port;
		this.floatingip = floatingip;
	}

	//---------------------------------------------------------------
	//Getter and Setter
	//---------------------------------------------------------------

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
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

	public int getFloatingip() {
		return floatingip;
	}

	public void setFloatingip(int floatingip) {
		this.floatingip = floatingip;
	}



}
