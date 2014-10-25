package it.univpm.idstid.openstack.network.proxy.entity;

import java.util.ArrayList;

public class Port {

	private PortData port;
	private ArrayList<PortData> ports;
	
	//Empty constructor
	public Port(){

	}
	
	//Constructor for a single Port entity
	public Port(PortData port) {
		this.port = port;
	}
	
	//Constructor for multiple Port entities
	public Port(ArrayList<PortData> ports) {
		this.ports = ports;
	}

	//---------------------------------------------------------------
	//Getter and Setter
	//---------------------------------------------------------------

	public PortData getPort() {
		return port;
	}

	public void setPort(PortData port) {
		this.port = port;
	}

	public ArrayList<PortData> getPorts() {
		return ports;
	}

	public void setPorts(ArrayList<PortData> ports) {
		this.ports = ports;
	}

}
