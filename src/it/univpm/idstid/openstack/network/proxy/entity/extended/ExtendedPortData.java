package it.univpm.idstid.openstack.network.proxy.entity.extended;

import it.univpm.idstid.openstack.network.proxy.entity.PortData;

public class ExtendedPortData extends PortData {

	private String vifType="-";
	private String hostID="-";
	private String profile="-";
	private String capabilities="-";
	
	//---------------------------------------------------------------
	// Getter and Setter
	//---------------------------------------------------------------
	
	public String getVifType() {
		return vifType;
	}
	public void setVifType(String vifType) {
		this.vifType = vifType;
	}
	public String getHostID() {
		return hostID;
	}
	public void setHostID(String hostID) {
		this.hostID = hostID;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getCapabilities() {
		return capabilities;
	}
	public void setCapabilities(String capabilities) {
		this.capabilities = capabilities;
	}
}
