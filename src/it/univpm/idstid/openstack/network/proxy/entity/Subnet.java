package it.univpm.idstid.openstack.network.proxy.entity;

import java.util.ArrayList;

public class Subnet {

	private SubnetData subnet;
	private ArrayList<SubnetData> subnets;
	
	//Empty constructor
	public Subnet(){

	}
	
	//Constructor for a single Subnet entity
	public Subnet(SubnetData subnet) {
		this.subnet = subnet;
	}
	
	//Constructor for multiple Subnet entities
	public Subnet(ArrayList<SubnetData> subnets) {
		this.subnets = subnets;
	}
		
	//---------------------------------------------------------------
	//Getter and Setter
	//---------------------------------------------------------------
	
	public SubnetData getSubnet() {
		return subnet;
	}

	public void setSubnet(SubnetData subnet) {
		this.subnet = subnet;
	}

	public ArrayList<SubnetData> getSubnets() {
		return subnets;
	}

	public void setSubnets(ArrayList<SubnetData> subnets) {
		this.subnets = subnets;
	}


}
