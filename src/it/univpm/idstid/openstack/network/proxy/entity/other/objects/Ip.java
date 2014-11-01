package it.univpm.idstid.openstack.network.proxy.entity.other.objects;

public class Ip {
	
	private String ipAddress;
	private String subnetId;

	public Ip(){

	}

	public Ip(String ipAddress, String subnetId) {
		this.ipAddress = ipAddress;
		this.subnetId = subnetId;
	}

	//---------------------------------------------------------------
	// Getter and Setter
	//---------------------------------------------------------------

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getSubnetId() {
		return subnetId;
	}

	public void setSubnetId(String subnetId) {
		this.subnetId = subnetId;
	}

}
