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

	public ExtendedNetworkData getNetwork() {
		return network;
	}

	public void setNetwork(ExtendedNetworkData network) {
		this.network = network;
	}

	public ArrayList<ExtendedNetworkData> getNetworks() {
		return networks;
	}

	public void setNetworks(ArrayList<ExtendedNetworkData> networks) {
		this.networks = networks;
	}




}
