package it.univpm.idstid.openstack.network.proxy.entity.extended;

import java.util.ArrayList;

public class ExtendedPort {
	
	private ExtendedPortData port;
	private ArrayList<ExtendedPortData> ports;
	
	//Empty constructor
	public ExtendedPort(){

	}
	
	//Constructor for a single Port entity
	public ExtendedPort(ExtendedPortData port) {
		this.port = port;
	}
	
	//Constructor for multiple Port entities
	public ExtendedPort(ArrayList<ExtendedPortData> ports) {
		this.ports = ports;
	}

	//---------------------------------------------------------------
	//Getter and Setter
	//---------------------------------------------------------------

	public ExtendedPortData getPort() {
		return port;
	}

	public void setPort(ExtendedPortData port) {
		this.port = port;
	}

	public ArrayList<ExtendedPortData> getPorts() {
		return ports;
	}

	public void setPorts(ArrayList<ExtendedPortData> ports) {
		this.ports = ports;
	}


}
