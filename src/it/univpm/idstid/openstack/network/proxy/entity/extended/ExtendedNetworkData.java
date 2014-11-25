package it.univpm.idstid.openstack.network.proxy.entity.extended;

import com.google.gson.annotations.SerializedName;

import it.univpm.idstid.openstack.network.proxy.entity.NetworkData;

public class ExtendedNetworkData extends NetworkData {

	@SerializedName("provider:network_type")
	private String network_type;
	@SerializedName("provider:physical_network")
	private String physical_network;
	@SerializedName("provider:segmentation_id")
	private String segmentation_id;

	public ExtendedNetworkData() {
		super();
	}

	public ExtendedNetworkData(String network_type, String physical_network,
			String segmentation_id) {
		super();
		this.network_type = network_type;
		this.physical_network = physical_network;
		this.segmentation_id = segmentation_id;
	}

	
	//---------------------------------------------------------------
	// Getter and Setter
	//---------------------------------------------------------------

	public String getNetwork_type() {
		return network_type;
	}

	public void setNetwork_type(String network_type) {
		this.network_type = network_type;
	}

	public String getPhysical_network() {
		return physical_network;
	}

	public void setPhysical_network(String physical_network) {
		this.physical_network = physical_network;
	}

	public String getSegmentation_id() {
		return segmentation_id;
	}

	public void setSegmentation_id(String segmentation_id) {
		this.segmentation_id = segmentation_id;
	}

	
}
