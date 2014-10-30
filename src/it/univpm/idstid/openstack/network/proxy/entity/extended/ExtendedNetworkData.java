package it.univpm.idstid.openstack.network.proxy.entity.extended;

import it.univpm.idstid.openstack.network.proxy.entity.NetworkData;

public class ExtendedNetworkData extends NetworkData {

	private String networkType="-";
	private String physicalNetwork="-";
	private String segmentationID="-";

	//---------------------------------------------------------------
	// Getter and Setter
	//---------------------------------------------------------------
	
	public String getNetworkType() {
		return networkType;
	}
	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}
	public String getPhysicalNetwork() {
		return physicalNetwork;
	}
	public void setPhysicalNetwork(String physicalNetwork) {
		this.physicalNetwork = physicalNetwork;
	}
	public String getSegmentationID() {
		return segmentationID;
	}
	public void setSegmentationID(String segmentationID) {
		this.segmentationID = segmentationID;
	}

}
