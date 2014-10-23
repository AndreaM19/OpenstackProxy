package it.univpm.idstid.openstack.network.proxy.entity;

import java.util.ArrayList;


public class Network {
	
	private NetworkData network;
	private ArrayList<NetworkData> networks;
	
	//Empty constructor
	public Network(){

	}
	
	//Constructor for a single Network entity
	public Network(NetworkData network) {
		this.network = network;
	}
	
	//Constructor for multiple Network entities
	public Network(ArrayList<NetworkData> networks) {
		this.networks = networks;
	}

	//---------------------------------------------------------------
	//Getter and Setter
	//---------------------------------------------------------------

	public NetworkData getNetwork() {
		return network;
	}

	public void setNetwork(NetworkData network) {
		this.network = network;
	}
	
	public ArrayList<NetworkData> getNetworks() {
		return networks;
	}

	public void setNetworks(ArrayList<NetworkData> networks) {
		this.networks = networks;
	}

}
