package it.univpm.idstid.openstack.network.proxy.entity.other.objects;

public class Ip {

	private String ip_address;
	private String subnet_id;

	public Ip(){

	}

	public Ip(String ip_address, String subnet_id) {
		this.ip_address = ip_address;
		this.subnet_id = subnet_id;
	}

	//---------------------------------------------------------------
	// Getter and Setter
	//---------------------------------------------------------------

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public String getSubnet_id() {
		return subnet_id;
	}

	public void setSubnet_id(String subnet_id) {
		this.subnet_id = subnet_id;
	}	

}
