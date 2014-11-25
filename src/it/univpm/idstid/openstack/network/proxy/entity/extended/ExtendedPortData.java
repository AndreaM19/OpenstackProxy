package it.univpm.idstid.openstack.network.proxy.entity.extended;

import com.google.gson.annotations.SerializedName;

import it.univpm.idstid.openstack.network.proxy.entity.PortData;
import it.univpm.idstid.openstack.network.proxy.entity.other.objects.Profile;

public class ExtendedPortData extends PortData {
	
	@SerializedName("binding:vif_type")
	private String vif_type;
	@SerializedName("binding:host_id")
	private String host_id;
	@SerializedName("binding:profile")
	private Profile profile;
	@SerializedName("binding:capabilities")
	private String capabilities;
	
	public ExtendedPortData(){
		
	}
	
	public ExtendedPortData(String vif_type, String host_id, Profile profile,
			String capabilities) {
		this.vif_type = vif_type;
		this.host_id = host_id;
		this.profile = profile;
		this.capabilities = capabilities;
	}
	
	//---------------------------------------------------------------
	// Getter and Setter
	//---------------------------------------------------------------
	
	public String getVif_type() {
		return vif_type;
	}
	public void setVif_type(String vif_type) {
		this.vif_type = vif_type;
	}
	public String getHost_id() {
		return host_id;
	}
	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public String getCapabilities() {
		return capabilities;
	}
	public void setCapabilities(String capabilities) {
		this.capabilities = capabilities;
	}
	
	
	}
