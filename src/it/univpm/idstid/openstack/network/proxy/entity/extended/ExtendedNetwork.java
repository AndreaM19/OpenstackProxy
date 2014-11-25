package it.univpm.idstid.openstack.network.proxy.entity.extended;

import java.util.ArrayList;

public class ExtendedNetwork {

	private ExtendedNetworkData network;
	private ArrayList<ExtendedNetworkData> networks;

	//Empty constructor
	public ExtendedNetwork(){

	}

	//Constructor for a single Network entity
	public ExtendedNetwork(ExtendedNetworkData network) {
		this.network = network;
	}

	//Constructor for multiple Network entities
	public ExtendedNetwork(ArrayList<ExtendedNetworkData> networks) {
		this.networks = networks;
	}

	//---------------------------------------------------------------
	// Getter and Setter
	//---------------------------------------------------------------

	public ExtendedNetworkData getExtendedNetwork() {
		return network;
	}

	public void setExtendedNetwork(ExtendedNetworkData network) {
		this.network = network;
	}

	public ArrayList<ExtendedNetworkData> getExtendedNetworks() {
		return networks;
	}

	public void setExtendedNetworks(ArrayList<ExtendedNetworkData> networks) {
		this.networks = networks;
	}




}
